package com.example.administrator.meituan2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.meituan2.Model.DianyingGridviewData;
import com.example.administrator.meituan2.R;
import com.lidroid.xutils.BitmapUtils;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class DianYingGridviewAdapter extends RecyclerView.Adapter<DianYingGridviewAdapter.MyViewHolder>{

    List<DianyingGridviewData.DataBean.HotBean> mHotBeens;
//    public void setmHotBeens(List<DianyingGridviewData.DataBean.HotBean> mHotBeens) {
//        this.mHotBeens = mHotBeens;
//    }

    protected LayoutInflater inflater;
    Context context;
    public DianYingGridviewAdapter(Context context,List<DianyingGridviewData.DataBean.HotBean> mHotBeens) {
        this.inflater = LayoutInflater.from(context);
        this.mHotBeens = mHotBeens;
    }

    /**
     * 渲染布局
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.dianying_gridview, parent, false);
        context = parent.getContext();
        return new MyViewHolder(inflate);
    }

    /**
     * 将数据绑定到ViewHolder
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BitmapUtils utils = new BitmapUtils(context);
        String img = mHotBeens.get(position).getImg();
        String[] split = img.split("/w.h");
        String url = split[0] + split[1];
        utils.display(holder.image, url);

        holder.name.setText(mHotBeens.get(position).getNm());
        holder.marker.setText(mHotBeens.get(position).getMk()+"");
        holder.marker.setTextColor(Color.WHITE);

        holder.fen.setTextColor(Color.WHITE);
    }

    /**
     * 返回条目数
     */
    @Override
    public int getItemCount() {
        return mHotBeens == null ? 0 : mHotBeens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        //参数itemView即为要缓存的条目布局
        TextView name;
        TextView marker;
        ImageView image;
        TextView fen;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nm);
            marker = (TextView) itemView.findViewById(R.id.tv_mk);
            image = (ImageView) itemView.findViewById(R.id.iv_img);
            fen = (TextView) itemView.findViewById(R.id.tv_fen);
        }
    }


    /*List<DianyingGridviewData.DataBean.HotBean> mHotBeens;

    public void setmHotBeens(List<DianyingGridviewData.DataBean.HotBean> mHotBeens) {
        this.mHotBeens = mHotBeens;
    }

    @Override
    public int getCount() {
        return mHotBeens == null ? 0 : mHotBeens.size();
    }

    @Override
    public Object getItem(int i) {
        return mHotBeens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = View.inflate(viewGroup.getContext(), R.layout.dianying_gridview, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        BitmapUtils utils = new BitmapUtils(viewGroup.getContext());
        String img = mHotBeens.get(i).getImg();
        String[] split = img.split("/w.h");
        String url = split[0] + split[1];
        utils.display(viewHolder.image, url);

        viewHolder.name.setText(mHotBeens.get(i).getNm());
        viewHolder.marker.setText(mHotBeens.get(i).getMk()+"");
        return view;
    }

    private class ViewHolder {
        TextView name;
        TextView marker;
        ImageView image;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            name = (TextView) rootView.findViewById(R.id.tv_nm);
            marker = (TextView) rootView.findViewById(R.id.tv_mk);
            image = (ImageView) rootView.findViewById(R.id.iv_img);
        }
    }*/

}
