package com.example.queue.activarCuenta;


import com.example.queue.activarCuenta.apisigin.LlamaCambiaCodigo;

public class VuelveMandarCorreo {

    private String token;

    public VuelveMandarCorreo(String token) {

        this.token=token;
    }


    public void run() {


        LlamaCambiaCodigo cambiaCodigo=new LlamaCambiaCodigo();

        cambiaCodigo.crear(token);

        cambiaCodigo.start();


    }
}
