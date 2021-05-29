package com.example.queue.enCasoUsuarioYaesEnCola;

import android.os.Message;

import com.example.queue.MainActivity;
import com.example.queue.comunicacionQR.InformacionColaJson;
import com.example.queue.comunicacionQR.QRActivity;
import com.example.queue.fragments.miCola.CuentaAtrasTurno;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.notificacion.TocaSuTurno;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class EscucharRespuestaSienCola extends  Thread {


    private Socket miSocket;
    private InformacionColaJson colaJson;
    private String miturno;

    private DataInputStream resquestaCola;

    private MicolaFragment micolaFragment;
    public EscucharRespuestaSienCola(Socket miSocket,MicolaFragment micolaFragment) {
       this.miSocket=miSocket;
       this.micolaFragment=micolaFragment;

    }


    @Override
    public void run() {

        try {
            resquestaCola=new DataInputStream(miSocket.getInputStream());



            // dejar lo interfaz puede visualiza y tocar lo
            Message msg=new Message();
            msg.what=-1;
            micolaFragment.handlerMicola.sendMessage(msg);


            if(resquestaCola.readBoolean()){

                Ids.yaestaEncola=true;

                MicolaFragment.mViewModel.setPuedeAnadirPorducto(true);

                Gson gosn=new Gson();

                Ids.codigoQR=resquestaCola.readUTF();

                Ids.id_cola=resquestaCola.readUTF();

                Ids.id_usuario=resquestaCola.readUTF();

                String miturno=resquestaCola.readUTF();

                MicolaFragment.mViewModel.setMiTurno(miturno);

                while(miSocket.isConnected()){// si esta conectado va estar escuchando al servidor

                    CuentaAtrasTurno cuentaAtras =new CuentaAtrasTurno(MicolaFragment.mViewModel);

                    cuentaAtras.start();

                    String datos=resquestaCola.readUTF();

                    colaJson=gosn.fromJson(datos, InformacionColaJson.class);

                    // para actualizar el turno y tiempo en la pantalla
                    MicolaFragment.mViewModel.modificarTurno(colaJson.turnoActual);

                    MicolaFragment.mViewModel.modificarTiempo(colaJson.timepo);

                    MicolaFragment.mViewModel.setTurnosQueda(colaJson.turnoQueda);
                    // cuando recibe respueta del servidor
                    // vulve iniciar el temporizador
                    // porque el tiempo va calcular por servidor
                    cuentaAtras.sigueCuentando=false;

                    if(miturno.equals(colaJson.turnoActual)){

                        MicolaFragment.mViewModel.modificarTiempo(0);
                        MicolaFragment.mViewModel.setTurnosQueda("Toca su turno");
                        MicolaFragment.mViewModel.setPuedeAnadirPorducto(false);
                        TocaSuTurno.tocaturno(micolaFragment.getContext());
                        break;
                    }

                }
            }else{

                MicolaFragment.mViewModel.setPuedeAnadirPorducto(false);

                miSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
