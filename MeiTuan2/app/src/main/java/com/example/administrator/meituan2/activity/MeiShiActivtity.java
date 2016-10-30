package com.example.administrator.meituan2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.meituan2.Model.Consts;
import com.example.administrator.meituan2.Model.MeiShiData;
import com.example.administrator.meituan2.R;
import com.example.administrator.meituan2.adapter.MeishiGalleryAdapter;
import com.example.administrator.meituan2.adapter.MeishiGridviewAdapter;
import com.example.administrator.meituan2.adapter.MeishiListviewAdapter;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MeiShiActivtity extends AppCompatActivity {

    private ImageButton mImageButton;
    private RecyclerView mRecyclerView;
    private String[] types = {"美味西餐","火锅","创意菜","生日蛋糕","甜点饮品",
                            "海鲜","东北菜","名店抢购"};
    private int[] images = {R.mipmap.ic_category_0,R.mipmap.ic_category_1,R.mipmap.ic_category_2,R.mipmap.ic_category_0,
                        R.mipmap.ic_category_0,R.mipmap.ic_category_0,R.mipmap.ic_category_0,R.mipmap.ic_category_0};
    private MeishiGridviewAdapter meishiGridviewAdapter;
    private Gallery mGallery;
    private MeishiGalleryAdapter meishiGalleryAdapter;
    private Spinner spinnerAll,spinnerCity,spinnerSort,spinnerArea;
    private List<String> list1,list2,list3,list4;
    private ArrayAdapter<String> adapter1,adapter2,adapter3,adapter4;
    private ListView mListView;
    private LinearLayout mLinearLayout;
    private LinearLayout mLinearLayout2;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meishi);
        initView();
        initSinner();
        initData();
    }

    private void initView() {
        //listview对应的布局文件
        mListView = (ListView) findViewById(R.id.lv_meishi);
        mImageButton = (ImageButton) findViewById(R.id.iv_meishi_back);
        mImageView = (ImageView) findViewById(R.id.iv_food_map);
        //listview头部对应的布局文件
        View inflate = View.inflate(this, R.layout.meishi_list_header, null);
        mLinearLayout = (LinearLayout) inflate.findViewById(R.id.ll_meishi_root);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.rv_meishi_gridview);
        mGallery = (Gallery) inflate.findViewById(R.id.gallery);
        spinnerAll = (Spinner) inflate.findViewById(R.id.spinner_all);
        spinnerCity = (Spinner) inflate.findViewById(R.id.spinner_city);
        spinnerSort = (Spinner) inflate.findViewById(R.id.spinner_sort);
        spinnerArea = (Spinner) inflate.findViewById(R.id.spinner_area);
        //listview中间对应的布局
        View inflate1 = View.inflate(this, R.layout.meishi_list_center, null);
        mLinearLayout2 = (LinearLayout) inflate1.findViewById(R.id.ll_root_center);
    }
    private void initData() {

        /**
         * 给Gridview设置适配器
         */
        ArrayList<String> mTypes = new ArrayList<>();
        ArrayList<Integer> mIcons = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            mTypes.add(types[i]);
            mIcons.add(images[i]);
        }
        meishiGridviewAdapter = new MeishiGridviewAdapter(this,mTypes,mIcons);
        //给RecyclerView设置布局容器
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //给RecyclerView设置适配器
        mRecyclerView.setAdapter(meishiGridviewAdapter);

        /**
         * 给Gallery设置适配器
         */
        meishiGalleryAdapter = new MeishiGalleryAdapter(this);
        mGallery.setAdapter(meishiGalleryAdapter);

        /**
         * 给spinner填充数据
         */
        adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAll.setAdapter(adapter1);
        spinnerCity.setAdapter(adapter2);
        spinnerSort.setAdapter(adapter3);
        spinnerArea.setAdapter(adapter4);

        /**
         * 给listview添加头部，并设置适配器
         */
        mListView.addHeaderView(mLinearLayout);
        getMeishiData();

        /**
         * 点击返回按钮，结束当前页面
         */
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * 点击定位按钮，跳转到定位页面
         */
        /*mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeiShiActivtity.this,MeishiLocationActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void initSinner(){
        list1 = new ArrayList<>();
        list1.add("甜点饮品");
        list1.add("生日蛋糕");
        list1.add("火锅");
        list1.add("自助餐");
        list1.add("小吃快餐");
        list1.add("西餐");
        list1.add("生鲜蔬果");
        list1.add("聚餐宴请");
        list1.add("川菜馆");
        list1.add("粤菜");
        list2 = new ArrayList<>();
        list2.add("信阳");
        list2.add("郑州");
        list2.add("焦作");
        list2.add("驻马店");
        list2.add("新乡");
        list2.add("平顶山");
        list2.add("漯河");
        list3 = new ArrayList<>();
        list3.add("离我最近");
        list3.add("好评优先");
        list3.add("人气最高");
        list4 = new ArrayList<>();
        list4.add("火锅");
        list4.add("炒菜");
        list4.add("小吃");
    }

    public void getMeishiData(){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Consts.MEISHI_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                MeiShiData meiShiData = gson.fromJson(result, MeiShiData.class);
                List<MeiShiData.DataBean> data = meiShiData.getData();
                MeishiListviewAdapter adapter = new MeishiListviewAdapter();

                adapter.setmDataBeens(data);
                mListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("error",error.getMessage());
            }
        });
    }

}
