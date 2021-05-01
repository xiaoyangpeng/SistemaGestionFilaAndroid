package com.example.queue.activarCuenta;

import android.os.Bundle;
import android.os.Message;

import com.example.queue.valorFijo.Tiempo;

public class CuentaAtras extends Thread{

    private  int timepo=Tiempo.TIEMPOPARAMANDAREMAIL;

    private  ActivarCuentaActivity activarCuentaActivity;

    CuentaAtras( ActivarCuentaActivity activarCuentaActivity){

        this.activarCuentaActivity=activarCuentaActivity;

    }

    @Override
    public void run() {


        while(!activarCuentaActivity.isFinishing()){
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putInt("tiempo", timepo);
            msg.setData(bundle);
            msg.what=3;

            activarCuentaActivity.mainHandler.sendMessage(msg);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timepo--;

            if(timepo<0){

                timepo= Tiempo.TIEMPOPARAMANDAREMAIL;
            }
        }

    }

    public void setTimepo(int timepo) {
        this.timepo = timepo;
    }
}
