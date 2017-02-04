package com.bit3000.servicetest.services.callbacks;

public interface IServiceAsyncManager<E> {

    void onResponse(E responseObj);

    void onFailure(String result);

}