package com.example.administrator.meituan2.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.meituan2.Model.Consts;
import com.example.administrator.meituan2.Model.DianyingMoreData;
import com.example.administrator.meituan2.R;
import com.example.administrator.meituan2.adapter.DianyingMoreAdapter;
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
public class DianYingMoreActivity extends AppCompatActivity {

    private ImageButton back;
    private PullToRefreshListView mPullToRefreshListView;
    private DianyingMoreAdapter adapter;
    private List<DianyingMoreData.DataBean.HotBean> hotBeen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianying_more);
        initData(false);
        initView();
    }

    private void initView() {
        back = (ImageButton) findViewById(R.id.ib_more_back);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_dianying_more);

        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //如果mode为both时（即有两种状态），使用这个监听
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            //开始下拉  我们需要在用户下拉的时候重新做http请求
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData(true);
            }
        });
        //数据刷新之后给listview设置适配器
        adapter = new DianyingMoreAdapter();
        mPullToRefreshListView.setAdapter(adapter);

        //获取一个头部和尾部的布局
        ILoadingLayout loadingLayoutProxy = mPullToRefreshListView.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel("刚刚"); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");

        //初始化实体类对象，当上拉加载更多时，在此数据基础上进行添加
        hotBeen = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData(final boolean isUP) {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Consts.DYMORE_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                List<DianyingMoreData.DataBean.HotBean> hot = gson.fromJson(result, DianyingMoreData.class).getData().getHot();
                if (isUP){ //上拉加载更多
                    hotBeen.addAll(hot); //在原来数据的基础上增加数据
                    adapter.setmHotBeen(hotBeen);
                    adapter.notifyDataSetChanged();
                }else {
                    adapter.setmHotBeen(hot); //直接重新设置数据（覆盖掉原来的）
                    adapter.notifyDataSetChanged();
                }
                //刷新完成后要调用此方法
                mPullToRefreshListView.onRefreshComplete();
                Toast.makeText(DianYingMoreActivity.this, "数据请求成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("onFailure", error.getMessage());
            }
        });
    }
}
