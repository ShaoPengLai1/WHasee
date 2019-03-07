package com.execti.whasee.utils.model;


import com.execti.whasee.utils.callback.MyCallBack;
import com.execti.whasee.utils.network.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void requestDataGet(String url, String params, final Class clazz, final MyCallBack myCallBack) {
        RetrofitManager.getInstance().get(url).result(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try{
                    Object o = new Gson().fromJson(data, clazz);
                    if(myCallBack != null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(myCallBack != null){
                        myCallBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                if(myCallBack != null){
                    myCallBack.onFail(error);
                }
            }
        });
    }

    @Override
    public void requestDataPost(String url, Map<String, String> params, final Class clazz, final MyCallBack myCallBack) {

        RetrofitManager.getInstance().post(url,params).result(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try{
                    Object o = new Gson().fromJson(data, clazz);
                    if(myCallBack != null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(myCallBack != null){
                        myCallBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFail(String error) {
                if(myCallBack != null){
                    myCallBack.onFail(error);
                }
            }
        });
    }
}
