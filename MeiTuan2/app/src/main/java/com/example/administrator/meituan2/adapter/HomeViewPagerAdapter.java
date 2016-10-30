package com.example.administrator.meituan2.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.meituan2.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public class HomeViewPagerAdapter extends PagerAdapter {

    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV",
            "机票/火车票", "周边游", "美甲美睫"};
    private int[] imags = {R.mipmap.ic_category_0,R.mipmap.ic_category_1,R.mipmap.ic_category_2,R.mipmap.ic_category_3,
            R.mipmap.ic_category_4,R.mipmap.ic_category_5,R.mipmap.ic_category_6,R.mipmap.ic_category_7,
            R.mipmap.ic_category_8,R.mipmap.ic_category_9};

    private List<View> mViewList;

    public HomeViewPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return (mViewList.get(position));
    }

    @Override
    public int getCount() {
        if (mViewList == null)
            return 0;
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}