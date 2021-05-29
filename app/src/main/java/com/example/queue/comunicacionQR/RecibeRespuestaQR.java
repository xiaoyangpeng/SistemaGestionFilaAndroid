package com.example.queue.comunicacionQR;

import android.os.Message;
import com.example.queue.notificacion.TocaSuTurno;

import com.example.queue.fragments.miCola.CuentaAtrasTurno;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RecibeRespuestaQR extends  Thread{

    private Socket qrSocket;
    private  QRActivity qrActivity;
    private DataInputStream resquestaQR;

    private InformacionColaJson colaJson;

    private String miturno;

    public RecibeRespuestaQR(Socket qrSocket, QRActivity qrActivity) {
        this.qrSocket = qrSocket;
        this.qrActivity = qrActivity;
    }

    /**
     *
     * @preturn  -1: error QR
     * 1 QR correcto
     * 4 fallo conexion servidor
     * 2 ya esta dentro de la cola
     */
    @Override
    public void run() {


        try {
            resquestaQR=new DataInputStream(qrSocket.getInputStream());


            Message msg=new Message();
            Gson gosn=new Gson();

            int respuesta=resquestaQR.readInt();

                if(respuesta==1){

                    Message msg2=new Message();

                    msg2.what=1; // ya ha incorporado en fila

                    qrActivity.mainHandler.sendMessage(msg2);

                    MicolaFragment.mViewModel.setPuedeAnadirPorducto(true);

                    Ids.yaestaEncola=true;

                    Ids.id_cola=resquestaQR.readUTF();

                    Ids.id_usuario=resquestaQR.readUTF();

                    String miturno=resquestaQR.readUTF();

                    MicolaFragment.mViewModel.setMiTurno(miturno);

                    while(qrSocket.isConnected()){// si esta conectado va estar escuchando al servidor

                        CuentaAtrasTurno  cuentaAtras =new CuentaAtrasTurno(MicolaFragment.mViewModel);

                        cuentaAtras.start();

                        String datos=resquestaQR.readUTF();

                        colaJson=gosn.fromJson(datos, InformacionColaJson.class);

                        // para actualizar el turno y tiempo en la pantalla
                        MicolaFragment.mViewModel.modificarTurno(colaJson.turnoActual);

                        MicolaFragment.mViewModel.modificarTiempo(colaJson.timepo);

                        MicolaFragment.mViewModel.setTurnosQueda(colaJson.turnoQueda);
                        // cuando recibe respueta del servidor
                        // vulve iniciar el temporizador
                        // porque el tiempo va calcular por servidor
                       cuentaAtras.sigueCuentando=false;

                       int mitrunoInt=Integer.parseInt(miturno);

                       int turnoAcutal=Integer.parseInt(colaJson.turnoActual);

                       if(mitrunoInt<=turnoAcutal){

                           MicolaFragment.mViewModel.modificarTiempo(0);
                           MicolaFragment.mViewModel.setTurnosQueda("Toca su turno");
                           MicolaFragment.mViewModel.setPuedeAnadirPorducto(false);

                            TocaSuTurno.tocaturno(qrActivity);
                            
                           break;
                       }

                    }

                }else if(respuesta==-1){
                    MicolaFragment.mViewModel.setPuedeAnadirPorducto(true);
                    msg.what=-1;
                    qrSocket.close();

                }else if(respuesta==2){
                    msg.what=2;
                    qrSocket.close();
                }

            qrActivity.mainHandler.sendMessage(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
