package com.example.queue.login.api;

public class RespuestaLogin {

    public RespuestaLogin(String respuesta,String token) {
        this.respuesta = respuesta;
        this.token=token;
    }

    public String respuesta;

    public String token;
}
