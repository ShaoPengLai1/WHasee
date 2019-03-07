package com.execti.whasee.utils.view;


public interface IView<T> {
    void getDataSuccess(T data);
    void getDataFail(String error);
}
