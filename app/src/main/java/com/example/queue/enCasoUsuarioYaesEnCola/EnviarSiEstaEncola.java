package com.example.queue.enCasoUsuarioYaesEnCola;

import android.os.Message;
import android.util.Log;

import com.example.queue.MainActivity;
import com.example.queue.comunicacionQR.InformacionColaJson;
import com.example.queue.fragments.miCola.CuentaAtrasTurno;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerEmailUsuario;
import com.example.queue.valorFijo.ConexionUrl;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class EnviarSiEstaEncola extends Thread{

    private  Socket miSocket;
    private MainActivity mainActivity;
    private InformacionColaJson colaJson;
    private  static DataOutputStream outNumero=null;

    private   MicolaFragment micolaFragment;
    public EnviarSiEstaEncola(MainActivity mainActivity, MicolaFragment micolaFragment) {

        this.mainActivity=mainActivity;

        this.micolaFragment=micolaFragment;
    }


    @Override
    public void run() {


        try {

            String email= LeerEmailUsuario.emailUsuario(mainActivity);

            miSocket =new Socket();
            miSocket.connect(new InetSocketAddress(ConexionUrl.Companion.getIP(),ConexionUrl.Companion.getPORT()),10000);

            outNumero=new DataOutputStream(miSocket.getOutputStream());

            outNumero.writeInt("siestaencola".hashCode()); // primero envia numero para que el servidor

            // enviar email

            if(email!=null) {

                outNumero.writeInt(email.length());
                outNumero.write(email.getBytes());
            }else {
                outNumero.writeInt("nada".length());
                outNumero.write("nada".getBytes());
            }


            outNumero.flush();


            escuchaRespuesta();



        } catch (SocketTimeoutException e) {

            // en caso no puede conecetar con el servidor
            //es decir el tiempo de conexion es out

            if(!micolaFragment.getActivity().isFinishing()) {
                Message msg = new Message();
                msg.what = 4;
                mainActivity.mainHandler.sendMessage(msg);
            }
            
            e.printStackTrace();

        }catch (IOException e) {
            e.printStackTrace();
        }


    }



    private void escuchaRespuesta(){


        try {

            DataInputStream resquestaCola=new DataInputStream(miSocket.getInputStream());

            Ids.yaestaEncola=true;

            // dejar lo interfaz puede visualiza y tocar lo
            Message msg=new Message();
            msg.what=-1;
            micolaFragment.handlerMicola.sendMessage(msg);

            String respuesta=resquestaCola.readUTF();

                Log.d("123","555"+respuesta);
            if(respuesta.equals("true")){
                Log.d("123","111111111111");
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
                        break;
                    }

                }
            }else{
                Log.d("123","23222222222222222222222");
                MicolaFragment.mViewModel.setPuedeAnadirPorducto(false);

                miSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static DataOutputStream getOutNumero() {
        return outNumero;
    }

    public  Socket getMiSocket() {
        return miSocket;
    }
}
