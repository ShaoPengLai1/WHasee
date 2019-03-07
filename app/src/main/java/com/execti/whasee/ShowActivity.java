package com.execti.whasee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.execti.whasee.adapter.NineAdapter;
import com.execti.whasee.adapter.ShowAdapter;
import com.execti.whasee.bean.NineBean;
import com.execti.whasee.bean.ShowBean;
import com.execti.whasee.utils.Apis;
import com.execti.whasee.utils.presenter.IPresenterImpl;
import com.execti.whasee.utils.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity implements IView {

    @BindView(R.id.maPlace)
    ImageButton maPlace;
    @BindView(R.id.saomiao)
    ImageButton saomiao;
    @BindView(R.id.nineView)
    RecyclerView nineView;
    @BindView(R.id.showRecycle)
    RecyclerView showRecycle;
    private NineAdapter adapter;
    private IPresenterImpl iPresenter;
    private ShowAdapter showAdapter;
    private NineBean bean;
    private ShowBean showBean;
    //private int pscid=39;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        iPresenter=new IPresenterImpl(this);

        initView();


    }

    private void initView() {
        maPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
        saomiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowActivity.this,SmActivity.class);
                startActivity(intent);
            }
        });
        adapter=new NineAdapter(this);
        showAdapter=new ShowAdapter(this);
        showRecycle.setLayoutManager(new LinearLayoutManager(ShowActivity.this,LinearLayoutManager.VERTICAL,false));
        showRecycle.setAdapter(showAdapter);
        nineView.setLayoutManager(new GridLayoutManager(this,4,GridLayoutManager.VERTICAL,false));
        nineView.setAdapter(adapter);
        showAdapter.setOnClickListener(new NineAdapter.OnClickListener() {
            @Override
            public void callback(int index) {
                Intent intent=new Intent(ShowActivity.this,ShopCarActivity.class);
                startActivity(intent);
            }
        });
        adapter.setOnClickListener(new NineAdapter.OnClickListener() {
            @Override
            public void callback(int index) {
                String name = bean.getData().get(index).getName();
                Toast.makeText(ShowActivity.this,name,Toast.LENGTH_LONG).show();
            }
        });
        initData();

    }

    private void initData() {

        iPresenter.startRequestGet(Apis.NINE_SHOW,null, NineBean.class);
    }

    @Override
    public void getDataSuccess(Object data) {
        if (data instanceof  NineBean){
            bean = (NineBean) data;
            adapter.setmList(bean.getData());
            iPresenter.startRequestGet(Apis.SHOW_SHOW,null, ShowBean.class);
        }else if (data instanceof ShowBean){
            showBean = (ShowBean) data;
            showAdapter.setmList(showBean.getData());
        }
    }

    @Override
    public void getDataFail(String error) {
        Toast.makeText(ShowActivity.this,error,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
    }
}
