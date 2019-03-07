package com.execti.whasee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.execti.whasee.adapter.LeftAdaper;
import com.execti.whasee.adapter.RightAdaper;
import com.execti.whasee.bean.LeftBean;
import com.execti.whasee.bean.RightBean;
import com.execti.whasee.utils.Apis;
import com.execti.whasee.utils.presenter.IPresenterImpl;
import com.execti.whasee.utils.view.IView;

import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopCarActivity extends AppCompatActivity implements IView {

    @BindView(R.id.left_recycle)
    RecyclerView leftRecycle;
    @BindView(R.id.right_recycle)
    RecyclerView rightRecycle;
    private IPresenterImpl iPresenter;
    private LeftAdaper leftAdaper;
    private RightAdaper rightAdaper;
    private LeftBean leftBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        iPresenter=new IPresenterImpl(this);
        leftRecycle.setLayoutManager(new LinearLayoutManager(ShopCarActivity.this,
                LinearLayoutManager.VERTICAL,false));
        leftAdaper=new LeftAdaper(this);
        leftRecycle.setAdapter(leftAdaper);
        rightRecycle.setLayoutManager(new LinearLayoutManager(ShopCarActivity.this,
                LinearLayoutManager.VERTICAL,false));
        rightAdaper=new RightAdaper(this);
        rightRecycle.setAdapter(rightAdaper);
        initData();
        leftAdaper.setLeftCallBack(new LeftAdaper.LeftCallBack() {
            @Override
            public void callBack(int index) {
                String pcid = leftBean.getData().get(index).getPcid();
                Map<String,String> map = new HashMap<>();
                map.put("pcid",String.valueOf(pcid));
                iPresenter.startRequestPost(Apis.RIGHT_SHOW,map, RightBean.class);
            }
        });
    }

    private void initData() {

        iPresenter.startRequestGet(Apis.LEFT_SHOW,null, LeftBean.class);
    }

    @Override
    public void getDataSuccess(Object data) {
        if (data instanceof LeftBean){
            leftBean = (LeftBean) data;
            leftAdaper.setmList(leftBean.getData());
        }else if (data instanceof RightBean){
            RightBean rightBean= (RightBean) data;
            rightAdaper.setmList(rightBean.getData());
        }
    }

    @Override
    public void getDataFail(String error) {

    }
}
