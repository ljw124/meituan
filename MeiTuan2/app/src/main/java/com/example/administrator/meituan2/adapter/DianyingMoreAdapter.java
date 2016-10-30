package com.example.administrator.meituan2.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.meituan2.Model.DianyingMoreData;
import com.example.administrator.meituan2.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class DianyingMoreAdapter extends BaseAdapter {

    private List<DianyingMoreData.DataBean.HotBean> mHotBeen;

    public void setmHotBeen(List<DianyingMoreData.DataBean.HotBean> mHotBeen) {
        this.mHotBeen = mHotBeen;
    }

    @Override
    public int getCount() {
        return mHotBeen == null ? 0 : mHotBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return mHotBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = View.inflate(viewGroup.getContext(), R.layout.dianying_more_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        BitmapUtils utils = new BitmapUtils(viewGroup.getContext());
        String img = mHotBeen.get(i).getImg();
        String[] split = img.split("/w.h");
        String url = split[0] + split[1];
        utils.display(viewHolder.img, url);

        viewHolder.nm.setText(mHotBeen.get(i).getNm());
        viewHolder.boxInfo.setText(mHotBeen.get(i).getBoxInfo());
        viewHolder.desc.setText(mHotBeen.get(i).getDesc());
        return view;
    }
    private class ViewHolder {
        TextView nm;
        TextView boxInfo;
        TextView desc;
        ImageView img;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            nm = (TextView) rootView.findViewById(R.id.tv_more_nm);
            boxInfo = (TextView) rootView.findViewById(R.id.tv_more_boxInfo);
            desc = (TextView) rootView.findViewById(R.id.tv_more_desc);
            img = (ImageView) rootView.findViewById(R.id.iv_more_img);
        }
    }
}
