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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.meituan2.Model.Consts;
import com.example.administrator.meituan2.Model.DianyingGridviewData;
import com.example.administrator.meituan2.Model.DianyingViewpagerData;
import com.example.administrator.meituan2.R;
import com.example.administrator.meituan2.adapter.DianYingGridviewAdapter;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.recker.flybanner.FlyBanner;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class DianYingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton back;
    private Button hot;
    private Button wait;
    private Button store;
    private ImageButton search;
    private ListView mListView;
    private TextView more;
    private GridView mGridView;
    private GridView mGridView1;
    private FlyBanner mFlyBanner;
    private LinearLayout mLinearLayout;
    private XBanner mXBanner;
    private XBanner mXBanner2;
    private RecyclerView mRecyclerView;
    private PullToRefreshListView mPullToRefreshListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianying);
        initView();
        initViewPager();
        initGridView();
    }

    private void initView() {
        back = (ImageButton) findViewById(R.id.ib_dianying_back);
        hot = (Button) findViewById(R.id.btn_hot);
        wait = (Button) findViewById(R.id.btn_wait);
        store = (Button) findViewById(R.id.btn_store);
        search = (ImageButton) findViewById(R.id.ib_search);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_dianying);
        mXBanner2 = (XBanner) findViewById(R.id.xbanner_dianying2);
        more = (TextView) findViewById(R.id.tv_more);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_hot);
//        mFlyBanner2 = (FlyBanner) findViewById(R.id.banner_dianying2);

        View inflate = View.inflate(this, R.layout.dianyig_reying, null);
        mXBanner = (XBanner) inflate.findViewById(R.id.xbanner_dianying);
//        mFlyBanner = (FlyBanner) inflate.findViewById(R.id.banner_dianying);
        mLinearLayout = (LinearLayout) inflate.findViewById(R.id.root_dianying);

//        mListView.addHeaderView(mLinearLayout,null,true);

        more.setOnClickListener(this);
        wait.setOnClickListener(this);
        back.setOnClickListener(this);
        store.setOnClickListener(this);
    }

    /**
     * viewpager
     */
    HttpUtils utils = new HttpUtils();
    public void initViewPager(){
        utils.send(HttpRequest.HttpMethod.GET, Consts.DYVP_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                List<DianyingViewpagerData.DataBean> data = gson.fromJson(result, DianyingViewpagerData.class).getData();
                final List<String> imagesUrl = new ArrayList<>();
                //final List<String> names = new ArrayList<String>();
                //先把图片存放到一个集合中，可以方便传入XBanner
                for (int i = 0; i < data.size(); i++) {
                    imagesUrl.add(data.get(i).getImgUrl());
                }
                //使用XBanner实现图片轮播
                mXBanner2.setData(imagesUrl);
                mXBanner2.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(getApplicationContext()).load(imagesUrl.get(position)).into((ImageView) view);
                    }
                });

               /* mFlyBanner.setImagesUrl(imagesUrl);
                mFlyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getApplicationContext(),"点击了第" + position + "张图片",Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("error" ,error.getMessage());
            }
        });
    }

    /**
     * gridview
     */
    public void initGridView(){
        utils.send(HttpRequest.HttpMethod.GET, Consts.DYGV_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                List<DianyingGridviewData.DataBean.HotBean> hot = gson.fromJson(result, DianyingGridviewData.class).getData().getHot();
                DianYingGridviewAdapter adapter = new DianYingGridviewAdapter(DianYingActivity.this,hot);
//                adapter.setmHotBeens(hot);
                mRecyclerView.setLayoutManager(new GridLayoutManager(DianYingActivity.this,1, LinearLayoutManager.HORIZONTAL,false));
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("error" ,error.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_more:
                Intent intent = new Intent(DianYingActivity.this,DianYingMoreActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wait:
                Intent intent2 = new Intent(DianYingActivity.this,DianyingWaitActivity.class);
                startActivity(intent2);
                break;
            case R.id.ib_dianying_back:
                finish();
                break;
            case R.id.btn_store:
                Intent intent3 = new Intent(DianYingActivity.this,DianyingStoreActivity.class);
                startActivity(intent3);
        }
    }

    public void initPullToRefreshListView(){

    }
}
