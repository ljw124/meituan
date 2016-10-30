package com.example.administrator.meituan2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.administrator.meituan2.Model.Consts;
import com.example.administrator.meituan2.Model.DianyingWaitListData;
import com.example.administrator.meituan2.Model.DianyingWaitViewpagerData;
import com.example.administrator.meituan2.R;
import com.example.administrator.meituan2.adapter.DianyingWaitAdapter;
import com.example.administrator.meituan2.adapter.DianyingWaitListAdapter;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class DianyingWaitActivity extends AppCompatActivity {

    private PullToRefreshListView mPulltPullToRefreshListView;
    private RecyclerView mRecyclerView;
    private List<DianyingWaitListData.DataBean.ComingBean> comingBeen;
    private DianyingWaitListAdapter adapter;
    private ImageButton back;
    private Button hot;
    private Button store;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianying_wait);
        initView();
        initRecyclerView();
        initData(false);
        initRefreshListView();
    }

    private void initView() {
        mPulltPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_wait);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_wait);
        back = (ImageButton) findViewById(R.id.ib_dianying_back);
        hot = (Button) findViewById(R.id.btn_hot);
        store = (Button) findViewById(R.id.btn_store);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DianyingWaitActivity.this,DianYingActivity.class);
                startActivity(intent);
            }
        });
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DianyingWaitActivity.this,DianyingStoreActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initRecyclerView(){
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Consts.DYWAIT_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                List<DianyingWaitViewpagerData.DataBean> data = gson.fromJson(result, DianyingWaitViewpagerData.class).getData();
                DianyingWaitAdapter adapter = new DianyingWaitAdapter(getApplicationContext(),data);
                mRecyclerView.setLayoutManager(new GridLayoutManager(DianyingWaitActivity.this,1, LinearLayoutManager.HORIZONTAL,false));
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("error" ,error.getMessage());
            }
        });
    }

    public void initRefreshListView(){
        mPulltPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPulltPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData(true);
            }
        });

        comingBeen = new ArrayList<>();

        adapter = new DianyingWaitListAdapter();
        mPulltPullToRefreshListView.setAdapter(adapter);
        //获取一个头部和尾部的布局
        ILoadingLayout loadingLayoutProxy = mPulltPullToRefreshListView.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel("刚刚"); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");
    }

    private void initData(final boolean isUP) {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Consts.DYWAIT_LIST_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                List<DianyingWaitListData.DataBean.ComingBean> coming = gson.fromJson(result, DianyingWaitListData.class).getData().getComing();
                if (isUP){
                    comingBeen.addAll(coming);
                    adapter.setmComingBean(comingBeen);
                    adapter.notifyDataSetChanged();
                }else {
                    adapter.setmComingBean(coming);
                    adapter.notifyDataSetChanged();
                }
                mPulltPullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("error", error.getMessage());
            }
        });
    }
}
