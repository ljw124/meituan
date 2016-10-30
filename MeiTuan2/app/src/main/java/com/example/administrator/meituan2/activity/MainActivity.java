package com.example.administrator.meituan2.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.meituan2.R;
import com.example.administrator.meituan2.adapter.HomeFragmentAdapter;
import com.example.administrator.meituan2.fragment.HomeFragment;
import com.example.administrator.meituan2.fragment.MyFragment;
import com.example.administrator.meituan2.fragment.NearbyFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    //底部导航栏的文字和图片
    private String[] mTitles = {"首页", "附近", "我的"};
    private int[] mImags={
            R.drawable.home_icon_select,
            R.drawable.nearby_icon_select,
            R.drawable.my_icon_select};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mViewPager =  (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

    }

    private void initData() {
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getSupportFragmentManager());
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            titles.add(mTitles[i]);
        }
        adapter.setTitles(titles);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(NearbyFragment.newInstance());
        fragments.add(MyFragment.newInstance());
        adapter.setFragments(fragments);

        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
        //自定义tab样式
        for (int i = 0; i < mTitles.length; i++) {
            //获取某个tab(这个API对应自定义TabLayout很重要)
            TabLayout.Tab tabAt = mTabLayout.getTabAt(i);
            //加载自定义的tab布局
            View inflate = View.inflate(this, R.layout.tab_custom_item, null);
            ImageView mTabCustomImage = (ImageView) inflate.findViewById(R.id.tab_custom_image);
            TextView mTabCustomText = (TextView) inflate.findViewById(R.id.tab_custom_text);
            mTabCustomText.setText(mTitles[i]);
            mTabCustomImage.setImageResource(mImags[i]);
            //把自定义的tab布局设置给对应的tab
            tabAt.setCustomView(inflate);
        }
    }
}
