package com.bit3000.servicetest.services.base;

public enum HttpStatusCode {

    OK (200),
    Created(201),
    NoContent(204),
    Conflict(409),
    BadGateway(502);

    private final int  mValue;

    HttpStatusCode(int valor)
    {
        mValue = valor;
    }

    public int value()
    {
        return mValue;
    }
}
