package com.example.queue.incorporarremota;

import android.os.Message;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.incorporarremota.apiTienda.Tiendaremota;
import com.example.queue.valorFijo.ConexionUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

            RecibeRespuestaRemota recibeRespuestaRemota=new RecibeRespuestaRemota(socketQR,informacionTiendaActivity);
            recibeRespuestaRemota.start();

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

    public static DataOutputStream getOut() {
        return out;
    }

    public  Socket getSocketQR() {
        return socketQR;
    }
}
