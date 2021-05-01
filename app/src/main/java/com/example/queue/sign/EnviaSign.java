package com.example.queue.sign;

import android.os.Message;

import com.example.queue.valorFijo.ConexionUrl;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class EnviaSign extends  Thread {


    private Socket socketSign;
    private SignActivity signActivity;
    private Usuario usuario;

    public  EnviaSign (SignActivity signActivity,Usuario usuario){

        this.signActivity=signActivity;
        this.usuario=usuario;
    }

    @Override
    public void run() {

        BufferedWriter outTexto;
        DataOutputStream outNumero=null;

        try {
            socketSign=new Socket();
            socketSign.connect(new InetSocketAddress(ConexionUrl.Companion.getIP(),ConexionUrl.Companion.getPORT()),10000);
            RecibeSign recibeSign=new RecibeSign(socketSign,signActivity,usuario.email);

            recibeSign.start();

            outNumero=new DataOutputStream(socketSign.getOutputStream());

            outNumero.writeInt("sign".hashCode()); // primero envia numero para que el servidor sepa que es operaicon de sign up

            outNumero.flush();

            // enviar json que lleva informaciond del usuario

            outTexto=new BufferedWriter(new OutputStreamWriter(socketSign.getOutputStream(),"UTF-8"));

            outTexto.write(crearJsondeUsuario());

            outTexto.flush();

            socketSign.shutdownOutput();


        } catch (SocketTimeoutException e) {


            // en caso no puede conecetar con el servidor
            //es decir el tiempo de conexion es out
            Message msg = new Message();
            msg.what=9;
             signActivity.mainHandler.sendMessage(msg);
            e.printStackTrace();

        }catch (IOException e) {
            e.printStackTrace();
        }


    }


    private String crearJsondeUsuario(){

        Gson gosn=new Gson();

        return  gosn.toJson(usuario);

    }
}
