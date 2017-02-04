package com.bit3000.servicetest.services.base;

public class ErrorResponse {

    private String mensaje;
    private String id;

    public ErrorResponse(String id, String mensaje)
    {
        this.mensaje = mensaje;
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
