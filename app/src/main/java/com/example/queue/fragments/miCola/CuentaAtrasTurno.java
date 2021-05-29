package com.example.queue.fragments.miCola;

import android.util.Log;

public class CuentaAtrasTurno extends Thread {


    public  boolean sigueCuentando=true;

    private MicolaViewModel micolaViewModel;

    public CuentaAtrasTurno(MicolaViewModel micolaViewModel){

        this.micolaViewModel=micolaViewModel;
    }

    @Override
    public void run() {



        while(sigueCuentando){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int tiempo= micolaViewModel.getTimepoEstimado().getValue();

            if(tiempo<=0){
                micolaViewModel.modificarTiempo(0);
            }else{
                micolaViewModel.modificarTiempo(tiempo-1);
            }


        }

    }
}
