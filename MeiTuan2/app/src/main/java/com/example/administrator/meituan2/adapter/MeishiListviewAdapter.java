package com.example.administrator.meituan2.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.meituan2.Model.MeiShiData;
import com.example.administrator.meituan2.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MeishiListviewAdapter extends BaseAdapter {

    private static final int ITEM_LAYOUT_TYPE_COUNT = 2; //两种布局
    private static final int TYPE_ONE = 0; //listview布局
    private static final int TYPE_TWO = 1; //gridview布局

    private List<MeiShiData.DataBean> mDataBeens;

    public void setmDataBeens(List<MeiShiData.DataBean> mDataBeens) {
        this.mDataBeens = mDataBeens;
    }

    @Override
    public int getCount() {
        return mDataBeens == null ? 0 : mDataBeens.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataBeens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_LAYOUT_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 3){
            return TYPE_TWO;
        }else{
            return TYPE_ONE;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        int itemViewType = getItemViewType(i); //布局类型
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            switch (itemViewType){
                case TYPE_ONE:
                    view = View.inflate(viewGroup.getContext(), R.layout.meishi_list_item, null);
                    viewHolder = new ViewHolder(view);
                    break;
                case TYPE_TWO:
                    view = View.inflate(viewGroup.getContext(),R.layout.meishi_list_center,null);
                    break;
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        switch (itemViewType){
            case TYPE_ONE:
                BitmapUtils utils = new BitmapUtils(viewGroup.getContext());
                MeiShiData.DataBean.PoiBean poi = mDataBeens.get(i).getPoi();

                String frontImg = poi.getFrontImg();
                String[] split = frontImg.split("/w.h");
                String url = split[0] + split[1];
                utils.display(viewHolder.iv_frontImg, url);
//        String icon_url = poi.getPayAbstracts().get(0).getIcon_url();
//        utils.display(viewHolder.iv_icon_url, icon_url);

//        String uri = poi.getExtra().getIcons().get(0);
//        if (!uri.isEmpty()){
//            utils.display(viewHolder.iv_jian, uri);
//        }

                viewHolder.tv_name.setText(poi.getName());
//        String abstractX = poi.getPayAbstracts().get(0).getAbstractX();
//        viewHolder.tv_abstract.setText(abstractX);
                viewHolder.tv_areaName.setText(poi.getAreaName());
                viewHolder.tv_avgPrice.setText(poi.getAvgPrice()+"");
                viewHolder.tv_avgScore.setText(poi.getAvgScore()+"");
                viewHolder.tv_campaignTag.setText(poi.getCampaignTag());
                viewHolder.tv_cateName.setText(poi.getCateName());
        }
        return view;
    }

    private class ViewHolder {
        TextView tv_campaignTag;
        TextView tv_name;
        TextView tv_avgScore;
        TextView tv_avgPrice;
        TextView tv_cateName;
        TextView tv_areaName;
        TextView tv_abstract;
        ImageView iv_frontImg;
        ImageView iv_jian;
        ImageView iv_icon_url;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            tv_campaignTag = (TextView) rootView.findViewById(R.id.tv_campaignTag);
            tv_avgScore = (TextView) rootView.findViewById(R.id.tv_avgScore);
            tv_avgPrice = (TextView) rootView.findViewById(R.id.tv_avgPrice);
            tv_cateName = (TextView) rootView.findViewById(R.id.tv_cateName);
            tv_areaName = (TextView) rootView.findViewById(R.id.tv_areaName);
            tv_abstract = (TextView) rootView.findViewById(R.id.tv_abstract);
            iv_frontImg = (ImageView) rootView.findViewById(R.id.iv_frontImg);
            iv_jian = (ImageView) rootView.findViewById(R.id.iv_jian);
            iv_icon_url = (ImageView) rootView.findViewById(R.id.iv_icon_url);
        }
    }
}
