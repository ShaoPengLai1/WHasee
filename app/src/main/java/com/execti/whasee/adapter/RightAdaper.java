package com.execti.whasee.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.execti.whasee.R;
import com.execti.whasee.bean.RightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class RightAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RightBean.DataBean> mList;
    private Context mContext;

    public RightAdaper(Context mContext) {
        this.mContext = mContext;
        mList=new ArrayList<>();
    }

    public void setmList(List<RightBean.DataBean> lists) {
        mList.clear();
        if (lists!=null){
            mList.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.reght_recycle_item,viewGroup,false);
        return new ViewHolderRight(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderRight holderRight = (ViewHolderRight) viewHolder;
        holderRight.rightTitle.setText(mList.get(i).getTitle());
        holderRight.rightSimpl.setImageURI(Uri.parse(mList.get(i).getDetailUrl()));
        holderRight.con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rightCallBack!=null){
                    rightCallBack.CallBack(mList.get(i).getPscid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class ViewHolderRight extends RecyclerView.ViewHolder {

        SimpleDraweeView rightSimpl;
        TextView rightTitle;
        ConstraintLayout con;
        public ViewHolderRight(@NonNull View itemView) {
            super(itemView);
            rightSimpl=itemView.findViewById(R.id.right_simpl);
            rightTitle=itemView.findViewById(R.id.right_title);
            con=itemView.findViewById(R.id.con);
        }
    }

    //定义接口
    private RightCallBack rightCallBack;

    public void setRightCallBack(RightCallBack rightCallBack) {
        this.rightCallBack = rightCallBack;
    }

    public interface RightCallBack {
        void CallBack(int pscid);
    }
}
