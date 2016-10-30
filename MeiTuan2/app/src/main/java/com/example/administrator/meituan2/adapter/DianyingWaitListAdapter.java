package com.example.administrator.meituan2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.meituan2.Model.DianyingWaitListData;
import com.example.administrator.meituan2.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class DianyingWaitListAdapter extends BaseAdapter{

    private List<DianyingWaitListData.DataBean.ComingBean> mComingBean;

    public void setmComingBean(List<DianyingWaitListData.DataBean.ComingBean> mComingBean) {
        this.mComingBean = mComingBean;
    }

    @Override
    public int getCount() {
        return mComingBean == null ? 0 : mComingBean.size();
    }

    @Override
    public Object getItem(int i) {
        return mComingBean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = View.inflate(viewGroup.getContext(), R.layout.dianying_wait_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        BitmapUtils utils = new BitmapUtils(viewGroup.getContext());
        String img = mComingBean.get(i).getImg();
        String[] split = img.split("/w.h");
        String url = split[0] + split[1];
        utils.display(viewHolder.image, url);

        viewHolder.name.setText(mComingBean.get(i).getVideoName());
        viewHolder.scm.setText(mComingBean.get(i).getScm());
        viewHolder.pubDesc.setText(mComingBean.get(i).getPubDesc());
        return view;
    }

    public class ViewHolder{
        TextView name;
        TextView scm;
        TextView pubDesc;
        ImageView image;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            name = (TextView) rootView.findViewById(R.id.tv_list_nm);
            scm = (TextView) rootView.findViewById(R.id.tv_list_scm);
            pubDesc = (TextView) rootView.findViewById(R.id.tv_list_pubDesc);
            image = (ImageView) rootView.findViewById(R.id.iv_list_imag);
        }
    }
}
