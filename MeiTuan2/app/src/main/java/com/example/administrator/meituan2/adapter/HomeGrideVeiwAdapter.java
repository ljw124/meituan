package com.example.administrator.meituan2.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.meituan2.Model.WanLeData;
import com.example.administrator.meituan2.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public class HomeGrideVeiwAdapter extends BaseAdapter {

    private List<WanLeData.DataBean> wanLeDatas;

    public void setWanLeDatas(List<WanLeData.DataBean> wanLeDatas) {
        this.wanLeDatas = wanLeDatas;
    }

    @Override
    public int getCount() {
        return wanLeDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return wanLeDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = View.inflate(viewGroup.getContext(), R.layout.gradview_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        BitmapUtils bitmapUtils = new BitmapUtils(viewGroup.getContext());
        String[] split = wanLeDatas.get(i).getImageurl().split("/w.h");
        String url = split[0] + split[1];
        bitmapUtils.display(viewHolder.iv_grid, url);

        viewHolder.title.setText(wanLeDatas.get(i).getMaintitle());
        viewHolder.deputytitle.setText(wanLeDatas.get(i).getDeputytitle());
        viewHolder.deputytitle.setTextColor(Color.parseColor(wanLeDatas.get(i).getDeputy_typeface_color()));
        return view;
    }

    private class ViewHolder{
        TextView title;
        TextView deputytitle;
        ImageView iv_grid;

        ViewHolder(View rootView){
            initView(rootView);
        }

        private void initView(View rootView) {
            title = (TextView) rootView.findViewById(R.id.tv_grid_title);
            deputytitle = (TextView) rootView.findViewById(R.id.tv_deputytitle);
            iv_grid = (ImageView) rootView.findViewById(R.id.iv_grid);
        }

    }
}
