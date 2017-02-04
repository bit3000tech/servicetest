package com.bit3000.servicetest.services.base;

public abstract class ServiceResponseBase {

    private int codigo;
    private ErrorResponse error;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
}
