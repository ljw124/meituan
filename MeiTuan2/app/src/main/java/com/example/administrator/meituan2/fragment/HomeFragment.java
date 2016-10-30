package com.example.administrator.meituan2.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.meituan2.Model.Consts;
import com.example.administrator.meituan2.Model.LikeData;
import com.example.administrator.meituan2.Model.PagerData;
import com.example.administrator.meituan2.Model.WanLeData;
import com.example.administrator.meituan2.R;
import com.example.administrator.meituan2.activity.DianYingActivity;
import com.example.administrator.meituan2.activity.HomeFoundActivity;
import com.example.administrator.meituan2.activity.LocationActivity;
import com.example.administrator.meituan2.activity.MainActivity;
import com.example.administrator.meituan2.activity.MeiShiActivtity;
import com.example.administrator.meituan2.adapter.HomeGrideVeiwAdapter;
import com.example.administrator.meituan2.adapter.HomeListviewAdapter;
import com.example.administrator.meituan2.adapter.HomeViewPagerAdapter;
import com.example.administrator.meituan2.adapter.PagerGrideViewAdapter;
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
 * Created by Administrator on 2016/10/15.
 */
public class HomeFragment extends Fragment {

    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "景点", "机票/火车票", "生日蛋糕", "美甲美睫",
            "火锅", "丽人", "足疗按摩", "水上乐园", "汽车服务", "美发", "运动健身", "KTV", "甜品饮品", "周边游"};

    private ViewPager mViewPager;
    private LinearLayout mLlDot;
    private LayoutInflater inflater;
    private List<View> mPagerList;
    private List<PagerData> mDatas = new ArrayList<>();
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 10;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;
    private TextView position;
    private GridView mGridView;
    private ImageButton homeFound;
    private ImageButton homeMore;
    private PullToRefreshListView mPullToRefreshListView;
    private HttpUtils httpUtils;
    private List<LikeData.DataBean> dataBeen;
    private HomeListviewAdapter listAdapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        mPullToRefreshListView = (PullToRefreshListView) inflate.findViewById(R.id.listview_home_item);
        position = (TextView) inflate.findViewById(R.id.tv_position);
        homeFound = (ImageButton) inflate.findViewById(R.id.ib_home_found);
        homeMore = (ImageButton) inflate.findViewById(R.id.ib_home_more);

        //给ListView添加头布局
        View inflate1 = View.inflate(getContext(), R.layout.home_list_header, null);
        mGridView = (GridView) inflate1.findViewById(R.id.gtidview);
        mViewPager = (ViewPager) inflate1.findViewById(R.id.viewpager_home);
        mLlDot = (LinearLayout) inflate1.findViewById(R.id.ll_dot);
        LinearLayout root = (LinearLayout) inflate1.findViewById(R.id.ll_root);
        ListView refreshableView = mPullToRefreshListView.getRefreshableView();
        refreshableView.addHeaderView(root,null,true);

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
        //刷新之后设置适配器
        listAdapter = new HomeListviewAdapter();
        mPullToRefreshListView.setAdapter(listAdapter);

        //获取一个头部和尾部的布局
        ILoadingLayout loadingLayoutProxy = mPullToRefreshListView.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel("刚刚"); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");

        //实体类对象
        dataBeen = new ArrayList<>();

        /**
         * 数据解析
         */
        httpUtils = new HttpUtils();
        //吃喝玩乐对应的GridView中数据
        httpUtils.send(HttpRequest.HttpMethod.GET, Consts.WANLE_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                List<WanLeData.DataBean> wanLeData = gson.fromJson(result, WanLeData.class).getData();

                HomeGrideVeiwAdapter adapter1 = new HomeGrideVeiwAdapter();
                adapter1.setWanLeDatas(wanLeData);
                mGridView.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("error", error.getMessage());
            }
        });

        //ViewPager对应的数据
        initViewPager(container.getContext());
        return inflate;

   }

    /**
     * 加载listview的数据，并动态判断是下拉刷新还是上拉加载跟多
     */
    private void initData(final boolean isUP) {
        httpUtils.send(HttpRequest.HttpMethod.GET, Consts.LIKE_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                List<LikeData.DataBean> likeDate = gson.fromJson(result, LikeData.class).getData();

                if (isUP) { //上拉加载更多
                    dataBeen.addAll(likeDate); //在原来数据的基础上增加数据
                    listAdapter.setLikeDatas(dataBeen); //重新设置适配器的数据
                    listAdapter.notifyDataSetChanged();
                } else { //下拉刷新
                    listAdapter.setLikeDatas(likeDate); //直接重新设置数据（覆盖掉原来的）
                    listAdapter.notifyDataSetChanged();
                }

                //刷新完成提供给我们手动调用
                mPullToRefreshListView.onRefreshComplete();
                Toast.makeText(getContext(), "数据请求成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("onFailure", error.getMessage());
            }
        });
    }

    public void initViewPager(final Context context){
        //初始化数据源
        initDatas();

        inflater = LayoutInflater.from(context);
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.pager_gridview, mViewPager, false);
            gridView.setAdapter(new PagerGrideViewAdapter(context, mDatas, i, pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + curIndex * pageSize;
                    if (pos == 0){
                        Intent intent = new Intent(getActivity(),MeiShiActivtity.class);
                        startActivity(intent);
                    }
                    if(pos == 1){
                        Intent intent = new Intent(getActivity(), DianYingActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        //设置适配器
        mViewPager.setAdapter(new HomeViewPagerAdapter(mPagerList));
        //设置圆点
        setOvalLayout();
    }

    /**
     * 初始化数据源
     */
    public void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", "com.example.administrator.meituan2");
            mDatas.add(new PagerData(titles[i], imageId));
        }
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.pager_dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });

        /**
         * 位置选择
         */
        position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LocationActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 搜索
         */
        homeFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeFoundActivity.class);
                startActivity(intent);
            }
        });
    }
}
