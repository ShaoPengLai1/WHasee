package com.execti.whasee.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.execti.whasee.R;
import com.execti.whasee.bean.LeftBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LeftAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LeftBean.DataBean> mList;
    private Context mContext;

    public LeftAdaper(Context mContext) {
        this.mContext = mContext;
        mList=new ArrayList<>();
    }

    public void setmList(List<LeftBean.DataBean> lists) {
        mList.clear();
        if (lists!=null){
            mList.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.left_recycle_item,viewGroup,false);
        return new ViewHolderLeft(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderLeft holderLeft = (ViewHolderLeft) viewHolder;
        holderLeft.leftTextName.setText(mList.get(i).getName());
        holderLeft.leftTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leftCallBack!=null){
                    leftCallBack.callBack(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class ViewHolderLeft extends RecyclerView.ViewHolder {

        TextView leftTextName;
        public ViewHolderLeft(@NonNull View itemView) {
            super(itemView);
            leftTextName=itemView.findViewById(R.id.left_text_name);
        }
    }
    //定义接口
    private LeftCallBack leftCallBack;
    public void setLeftCallBack(LeftCallBack leftCallBack){
        this.leftCallBack = leftCallBack;
    }
    public interface LeftCallBack{
        void callBack(int index);
    }
}
