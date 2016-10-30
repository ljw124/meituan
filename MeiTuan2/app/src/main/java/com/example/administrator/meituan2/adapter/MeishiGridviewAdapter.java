package com.example.administrator.meituan2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.meituan2.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MeishiGridviewAdapter extends RecyclerView.Adapter<MeishiGridviewAdapter.MyViewHolder> {

    //通过构造方法把数据传递过来
    protected ArrayList<String> mTypes;
    protected ArrayList<Integer> mIcons;
    protected LayoutInflater inflater;

    public MeishiGridviewAdapter(Context context, ArrayList<String> mTypes,ArrayList<Integer> mIcons) {
        this.mTypes = mTypes;
        this.mIcons = mIcons;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 渲染布局
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.meishi_gridview_item, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * 将数据绑定到ViewHolder
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //将传递过来的数据设置给holder中的控件
        holder.mTextView.setText(mTypes.get(position));
        holder.mImageView.setImageResource(mIcons.get(position));
    }

    /**
     * 返回条目数
     */
    @Override
    public int getItemCount() {
        return mTypes.size();
    }

    /**
     * 用于缓存条目数据的Holder类
     * RcycleView要求必须实现此类
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{

        //参数itemView即为要缓存的条目布局
        TextView mTextView;
        ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_meishi_type);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_meishi_ic);
        }
    }
}
