package com.example.queue.activarCuenta;

import android.os.Message;

import com.example.queue.activarCuenta.apisigin.LlamaActivaCuenta;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RecibeActivaCuenta extends Thread{

    private ActivarCuentaActivity activarCuentaActivity;

    private String token;
    public RecibeActivaCuenta(ActivarCuentaActivity activarCuentaActivity,String token) {
        this.activarCuentaActivity = activarCuentaActivity;
        this.token=token;

    }

    @Override
    public void run() {

        LlamaActivaCuenta activaCuenta=new LlamaActivaCuenta();

        // codigo de activacion que ha puesto el usuario
        String codigo=activarCuentaActivity.getTextCodigoActivacion().getText().toString();

        activaCuenta.crear(codigo,token);

        activaCuenta.start();

        try {
            activaCuenta.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean respuesta=activaCuenta.getRespuetaActiva();

        Message msg = new Message();

            // si codigo de activacion esta bien pone 1
            //sino pone 2
            if(respuesta){

                msg.what = 1;

            }else{

                msg.what = 2;

            }
            activarCuentaActivity.mainHandler.sendMessage(msg);

    }
}
