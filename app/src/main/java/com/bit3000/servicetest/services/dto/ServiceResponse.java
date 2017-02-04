package com.bit3000.servicetest.services.dto;

import com.bit3000.servicetest.services.base.ServiceResponseBase;

import java.io.IOException;

import retrofit2.Response;

public class ServiceResponse<E> extends ServiceResponseBase {

    private E mObj;

    public void setObj(E obj)
    {
        mObj = obj;
    }

    public E getObj()
    {
        return mObj;
    }

    public void setResponse(Response<E> response) throws IOException {
        setCodigo(response.code());
        setObj(response.body());
    }

}
