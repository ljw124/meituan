package com.example.administrator.meituan2.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.example.administrator.meituan2.Model.LikeData;
import com.example.administrator.meituan2.Model.WanLeData;
import com.example.administrator.meituan2.R;

import java.util.List;


/**
 * Created by Administrator on 2016/10/15.
 */
public class BaseListviewAdapter extends BaseAdapter {

    private List<WanLeData.DataBean> wanLeDatas;

    public void setWanLeDatas(List<WanLeData.DataBean> wanLeDatas) {
        this.wanLeDatas = wanLeDatas;
    }

    private List<LikeData.DataBean> likeDatas;

    public void setLikeDatas(List<LikeData.DataBean> likeDatas) {
        this.likeDatas = likeDatas;
    }

    @Override
    public int getCount() {
        return likeDatas == null ? 0 : likeDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return likeDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = View.inflate(viewGroup.getContext(), R.layout.fragment_home, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HomeGrideVeiwAdapter grideVeiwAdapter = new HomeGrideVeiwAdapter();
        grideVeiwAdapter.setWanLeDatas(wanLeDatas);
        viewHolder.mGridView.setAdapter(grideVeiwAdapter);

//        HomeViewPagerAdapter viewPagerAdapter = new HomeViewPagerAdapter();
//        // TODO: 2016/10/15
//        viewHolder.mViewPager.setAdapter(viewPagerAdapter);

        HomeListviewAdapter innerListviewAdapter = new HomeListviewAdapter();
        innerListviewAdapter.setLikeDatas(likeDatas);
        viewHolder.mListView.setAdapter(innerListviewAdapter);
        return view;
    }

    private class ViewHolder{
        ViewPager mViewPager;
        GridView mGridView;
        ListView mListView;

        ViewHolder(View rootView){
            initView(rootView);
        }

        private void initView(View rootView) {
            mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_home);
            mListView = (ListView) rootView.findViewById(R.id.listview_home_item);
            mGridView = (GridView) rootView.findViewById(R.id.gtidview);
        }

    }
}
