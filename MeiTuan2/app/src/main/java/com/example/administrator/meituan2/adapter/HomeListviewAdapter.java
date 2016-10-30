package com.example.administrator.meituan2.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.meituan2.Model.LikeData;
import com.example.administrator.meituan2.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public class HomeListviewAdapter extends BaseAdapter {

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
        ViewHolder viewHolder;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = View.inflate(viewGroup.getContext(), R.layout.listview_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        BitmapUtils bitmapUtils = new BitmapUtils(viewGroup.getContext());
        String imageUrl = likeDatas.get(i).getImageUrl();
        bitmapUtils.display(viewHolder.iv, imageUrl);

        viewHolder.title.setText(likeDatas.get(i).getTitle());
        viewHolder.subtitle.setText(likeDatas.get(i).getSubTitle());
        viewHolder.submessage.setText(likeDatas.get(i).getSubMessage());
        return view;
    }

    private class ViewHolder {
        TextView title;
        TextView subtitle;
        TextView submessage;
        ImageView iv;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            title = (TextView) rootView.findViewById(R.id.tv_title);
            subtitle = (TextView) rootView.findViewById(R.id.tv_subtitle);
            submessage = (TextView) rootView.findViewById(R.id.tv_submessage);
            iv = (ImageView) rootView.findViewById(R.id.iv_listview);
        }
    }
}