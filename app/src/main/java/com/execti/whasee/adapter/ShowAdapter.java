package com.execti.whasee.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.execti.whasee.R;
import com.execti.whasee.bean.ShowBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShowBean.DataBean> mList;
    private Context mContext;

    public ShowAdapter(Context mContext) {
        this.mContext = mContext;
        mList=new ArrayList<>();
    }

    public void setmList(List<ShowBean.DataBean> lists) {
        mList.clear();
        if (lists!=null){
            mList.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.show_item,viewGroup,false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ShowViewHolder holder= (ShowViewHolder) viewHolder;
        //String[] split = mList.get(i).getDetailUrl().split("\\|");
        Uri uri=Uri.parse(mList.get(i).getUrl());
        holder.icon.setImageURI(uri);
        holder.tv1.setText(mList.get(i).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.callback(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    static class ShowViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView icon;
        TextView tv1;
        public ShowViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            tv1=itemView.findViewById(R.id.tv1);

        }
    }
    private NineAdapter.OnClickListener onClickListener;

    public void setOnClickListener(NineAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void callback(int index);
    }
}
