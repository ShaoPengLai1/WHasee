package com.execti.whasee.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.execti.whasee.R;

public class CarCuctomView extends RelativeLayout implements View.OnClickListener {

    private Context context;
    private ImageButton add;
    private ImageButton jian;
    private EditText ed_num;
    private int mCount=10;


    public CarCuctomView(Context context) {
        super(context);
        initView(context);
    }

    public CarCuctomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CarCuctomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.custom_item,null);
        jian = view.findViewById(R.id.jian);
        add = view.findViewById(R.id.add);
        ed_num = view.findViewById(R.id.ed_num);
        jian.setOnClickListener(this);
        add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                String countent = ed_num.getText().toString().trim();
                int count = Integer.valueOf(countent) + 1;
                mCount=count;
                ed_num.setText(mCount+"");
                if (customCallBack != null){
                    customCallBack.callBack(mCount);
                }
                break;
            case R.id.jian:
                String countent2 = ed_num.getText().toString().trim();
                int count2 = Integer.valueOf(countent2) + 1;
                if (count2>1){
                    mCount=count2-1;
                    ed_num.setText(mCount+"");
                    if (customCallBack!=null){
                        customCallBack.callBack(mCount);
                    }
                }
                break;
                default:
                    break;
        }
    }

    private CustomCallBack customCallBack;
    public void setCustomCallBack(CustomCallBack customCallBack){
        this.customCallBack = customCallBack;
    }
    public interface CustomCallBack{
        void callBack(int count);
        void inputEdit(int count);
    }

    public void setEditText(int num){
        if(ed_num!=null){
            ed_num.setText(num+"");
        }
    }
}
