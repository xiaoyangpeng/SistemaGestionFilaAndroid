package com.example.queue.incorporarremota;

import android.os.Message;

import com.example.queue.comunicacionQR.InformacionColaJson;
import com.example.queue.fragments.miCola.CuentaAtrasTurno;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.incorporarremota.apiTienda.Tiendaremota;
import com.example.queue.valorFijo.ConexionUrl;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class RemotaEnviar extends Thread{

    private  Socket socketQR;
    private InformacionTiendaActivity informacionTiendaActivity;

    private  static DataOutputStream out=null;
    private String token;
    private Tiendaremota tiendaremota;

    public RemotaEnviar(InformacionTiendaActivity informacionTiendaActivity, Tiendaremota tiendaremota) {

      this.informacionTiendaActivity=informacionTiendaActivity;

      this.tiendaremota=tiendaremota;

    }


    @Override
    public void run() {


        try {

            token= LeerToken.tokenUsuario(informacionTiendaActivity);

            socketQR =new Socket();
//
            socketQR.connect(new InetSocketAddress(ConexionUrl.Companion.getIP(),ConexionUrl.Companion.getPORT()),10000);

            out=new DataOutputStream(socketQR.getOutputStream());

            out.writeInt("incorporarToken".hashCode()); // primero envia numero para que el servidor sepa que es operaicon de sign up

            Gson gson=new GsonBuilder().setPrettyPrinting().create();

            String json=gson.toJson(tiendaremota);

            out.writeUTF(json);

            out.writeUTF(token);

            out.flush();

           /* RecibeRespuestaRemota recibeRespuestaRemota=new RecibeRespuestaRemota(socketQR,informacionTiendaActivity);
            recibeRespuestaRemota.start();*/

            RecibeRespuesta();

        } catch (SocketTimeoutException e) {


            // en caso no puede conecetar con el servidor
            //es decir el tiempo de conexion es out
            Message msg = new Message();
            msg.what=4;
          //  qrActivity.mainHandler.sendMessage(msg);
            e.printStackTrace();

        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    private DataInputStream resquestaQR;

    private InformacionColaJson colaJson;
    private void RecibeRespuesta(){



        try {
            resquestaQR=new DataInputStream(socketQR.getInputStream());

            Message msg=new Message();
            Gson gosn=new Gson();

            int respuesta=resquestaQR.readInt();

            if(respuesta==1){

                Message msg2=new Message();

                msg2.what=1; // ya ha incorporado en fila

                informacionTiendaActivity.mainHandler.sendMessage(msg2);

                MicolaFragment.mViewModel.setPuedeAnadirPorducto(true);

                Ids.yaestaEncola=true;

                Ids.id_cola=resquestaQR.readUTF();

                Ids.id_usuario=resquestaQR.readUTF();

                String miturno=resquestaQR.readUTF();

                MicolaFragment.mViewModel.setMiTurno(miturno);

                while(socketQR.isConnected()){// si esta conectado va estar escuchando al servidor

                    CuentaAtrasTurno cuentaAtras =new CuentaAtrasTurno(MicolaFragment.mViewModel);

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

                    if(miturno.equals(colaJson.turnoActual)){

                        MicolaFragment.mViewModel.modificarTiempo(0);
                        MicolaFragment.mViewModel.setTurnosQueda("Toca su turno");
                        MicolaFragment.mViewModel.setPuedeAnadirPorducto(false);
                        break;
                    }

                }

            }else if(respuesta==2){
                msg.what=2;
                socketQR.close();
            }

            informacionTiendaActivity.mainHandler.sendMessage(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataOutputStream getOut() {
        return out;
    }

    public  Socket getSocketQR() {
        return socketQR;
    }
}
