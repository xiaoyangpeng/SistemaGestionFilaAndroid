package com.example.queue.activarCuenta;

import android.os.Message;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RecibeActivaCuenta extends Thread{

    private ActivarCuentaActivity activarCuentaActivity;

    private Socket misocket;

    private DataInputStream entrada;


    public RecibeActivaCuenta(ActivarCuentaActivity activarCuentaActivity, Socket misocket) {
        this.activarCuentaActivity = activarCuentaActivity;
        this.misocket = misocket;
    }

    @Override
    public void run() {

        try {
            entrada = new DataInputStream(misocket.getInputStream());

            Message msg = new Message();

            // si codigo de activacion esta bien pone 1
            //sino pone 2

            int valor=entrada.readInt();

            if(valor==1){

                msg.what = 1;

            }else{

                msg.what = 2;

            }
            activarCuentaActivity.mainHandler.sendMessage(msg);

            misocket.close();

        } catch (IOException e) {
            e.printStackTrace();


        }

    }
}
