package com.example.administrator.meituan2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/15.
 * 因为加载的每个页面的数据量都很多，不适合缓存到本地，所以使用FragmentStatePagerAdapter
 */
public class HomeFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;
    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    private ArrayList<String> titles;
    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    //获取tab上的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
