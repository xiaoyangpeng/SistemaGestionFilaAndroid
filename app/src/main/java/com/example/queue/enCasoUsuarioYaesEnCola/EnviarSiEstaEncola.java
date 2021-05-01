package com.example.queue.enCasoUsuarioYaesEnCola;

import android.os.Message;

import com.example.queue.MainActivity;
import com.example.queue.comunicacionQR.QRActivity;
import com.example.queue.comunicacionQR.RecibeRespuestaQR;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerEmailUsuario;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class EnviarSiEstaEncola extends Thread{

    private  Socket misocket;
    private MainActivity mainActivity;

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

            misocket =new Socket();
            misocket.connect(new InetSocketAddress(ConexionUrl.Companion.getIP(),ConexionUrl.Companion.getPORT()),10000);

            outNumero=new DataOutputStream(misocket.getOutputStream());

            outNumero.writeInt("siestaencola".hashCode()); // primero envia numero para que el servidor

            // enviar email

            if(email!=null) {

                outNumero.writeInt(email.length());

                outNumero.write(email.getBytes());
            }else {

                outNumero.writeInt("nada".length());
                outNumero.write("nada".getBytes());
            }

           /* outNumero.writeInt("8888".length());
            outNumero.write("8888".getBytes());*/

            outNumero.flush();


          EscucharRespuestaSienCola escucharRespuestaSienCola=new EscucharRespuestaSienCola(misocket,micolaFragment);

          escucharRespuestaSienCola.start();



        } catch (SocketTimeoutException e) {

            // en caso no puede conecetar con el servidor
            //es decir el tiempo de conexion es out

            if(!micolaFragment.getActivity().isDestroyed()) {
                Message msg = new Message();
                msg.what = 4;
                mainActivity.mainHandler.sendMessage(msg);
            }
            e.printStackTrace();

        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static DataOutputStream getOutNumero() {
        return outNumero;
    }

    public  Socket getMisocket() {
        return misocket;
    }
}
