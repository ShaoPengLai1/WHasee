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
import com.execti.whasee.bean.NineBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class NineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NineBean.DataBean> mList;
    private Context mContext;

    public NineAdapter(Context mContext) {
        this.mContext = mContext;
        mList=new ArrayList<>();
    }

    public void setmList(List<NineBean.DataBean> lists) {
        mList.clear();
        if (lists!=null){
            mList.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.nine_item,viewGroup,false);
        return new NineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        NineViewHolder holder= (NineViewHolder) viewHolder;
        Uri uri=Uri.parse(mList.get(i).getIcon());
        holder.icon.setImageURI(uri);
        holder.title.setText(mList.get(i).getName());
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

    static class NineViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView icon;
        TextView title;
        public NineViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            title=itemView.findViewById(R.id.title);

        }
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void callback(int index);
    }
}
