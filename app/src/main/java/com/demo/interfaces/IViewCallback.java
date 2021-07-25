package com.demo.interfaces;


public interface IViewCallback<T> {

    void onSuccess(T dataObject);

    void onError(String errorMessage, int errorCode, T dataObject);
}
