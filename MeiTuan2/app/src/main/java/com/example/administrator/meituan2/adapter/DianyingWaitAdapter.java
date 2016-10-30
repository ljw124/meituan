package com.example.administrator.meituan2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.meituan2.Model.DianyingWaitViewpagerData;
import com.example.administrator.meituan2.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 *
 */
public class DianyingWaitAdapter extends RecyclerView.Adapter<DianyingWaitAdapter.MyViewHolder> {

    public List<DianyingWaitViewpagerData.DataBean> mDataBeen;

    protected LayoutInflater inflater;
    Context context;
    public DianyingWaitAdapter(Context context,List<DianyingWaitViewpagerData.DataBean> mDataBeen) {
        this.inflater = LayoutInflater.from(context);
        this.mDataBeen = mDataBeen;
    }

    /**
     * 渲染布局
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // View inflate = inflater.inflate(R.layout.dianying_wait_gridview, parent, false);
        View inflate = View.inflate(context, R.layout.dianying_gridview, null);
        context = parent.getContext();
        return new MyViewHolder(inflate);
    }

    /**
     * 将数据绑定到ViewHolder
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        BitmapUtils utils = new BitmapUtils(context);
        utils.display(holder.image,mDataBeen.get(position).getImg());

        holder.movieName.setText(mDataBeen.get(position).getMovieName());

        String url = mDataBeen.get(position).getUrl();

    }

    /**
     * 返回条目数
     */
    @Override
    public int getItemCount() {
        return mDataBeen == null ? 0 : mDataBeen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        //参数itemView即为要缓存的条目布局
        TextView movieName;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.tv_wait_movieName);
            image = (ImageView) itemView.findViewById(R.id.iv_wait_img);
        }
    }
}
