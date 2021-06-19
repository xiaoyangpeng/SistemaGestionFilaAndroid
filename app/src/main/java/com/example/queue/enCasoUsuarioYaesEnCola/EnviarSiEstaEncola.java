package com.example.queue.enCasoUsuarioYaesEnCola;

import android.os.Message;
import android.util.Log;

import com.example.queue.MainActivity;
import com.example.queue.enCasoUsuarioYaesEnCola.api.ApiGetSiestaEnfila;
import com.example.queue.enCasoUsuarioYaesEnCola.api.ApiSiEstaEnFila;
import com.example.queue.fragments.miCola.CuentaAtrasTurno;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerEmailUsuario;
import com.example.queue.modificarUiFila.ModifciarUIMicola;
import com.example.queue.valorFijo.ConexionUrl;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class EnviarSiEstaEncola extends Thread{

    private MainActivity mainActivity;

    private   MicolaFragment micolaFragment;

    public EnviarSiEstaEncola(MainActivity mainActivity, MicolaFragment micolaFragment) {

        this.mainActivity=mainActivity;

        this.micolaFragment=micolaFragment;
    }

    @Override
    public void run() {

            escuchaRespuesta();

    }

    private void escuchaRespuesta(){

            ApiSiEstaEnFila estaEnFila=new ApiSiEstaEnFila(mainActivity);
            estaEnFila.crear();
            estaEnFila.start();

            try {
                estaEnFila.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // dejar lo interfaz puede visualiza y tocar lo
            Message msg=new Message();
            msg.what=-1;
            micolaFragment.handlerMicola.sendMessage(msg);

            boolean respuesta=estaEnFila.getRespuesta();

            if(respuesta){

                Ids.yaestaEncola=true;

                MicolaFragment.mViewModel.setPuedeAnadirPorducto(true);

                CuentaAtrasTurno cuentaAtrasTurno = new CuentaAtrasTurno(MicolaFragment.mViewModel);

                ModifciarUIMicola modifciarUIMicola = new ModifciarUIMicola(cuentaAtrasTurno, mainActivity);

                modifciarUIMicola.modificar();

            }else{

                MicolaFragment.mViewModel.setPuedeAnadirPorducto(false);


            }


    }


}
