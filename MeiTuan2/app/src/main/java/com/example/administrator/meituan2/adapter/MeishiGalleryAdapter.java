package com.example.administrator.meituan2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.administrator.meituan2.R;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MeishiGalleryAdapter extends BaseAdapter {

    private int[] mImageResourceIds = {R.mipmap.ic_category_0,R.mipmap.ic_category_0,R.mipmap.ic_category_0,R.mipmap.ic_category_0};

    Context mContext;
    public MeishiGalleryAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mImageResourceIds == null ? 0 : mImageResourceIds.length;
    }

    @Override
    public Object getItem(int i) {
        return mImageResourceIds[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mImageResourceIds[i]);
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 120));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }
}
