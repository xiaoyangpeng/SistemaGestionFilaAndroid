package com.example.queue.comunicacionQR;

import android.os.Message;
import android.util.Log;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerEmailUsuario;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class QrEnviar extends Thread{

    private  Socket socketQR;
    private QRActivity qrActivity;
    private String codigoQR;
    private  static DataOutputStream outNumero=null;

    public QrEnviar( QRActivity qrActivity, String codigoQR) {
        this.qrActivity=qrActivity;
        this.codigoQR = codigoQR;
    }


    @Override
    public void run() {


        try {

            String email= LeerEmailUsuario.emailUsuario(qrActivity);



            socketQR =new Socket();
//
            socketQR.connect(new InetSocketAddress(ConexionUrl.Companion.getIP(),ConexionUrl.Companion.getPORT()),10000);

            outNumero=new DataOutputStream(socketQR.getOutputStream());

            outNumero.writeInt("incorporarQR".hashCode()); // primero envia numero para que el servidor sepa que es operaicon de sign up


            // enviar email

        /*   if(email!=null) {

                outNumero.writeInt(email.length());

                outNumero.write(email.getBytes());
            }else {

                outNumero.writeInt("nada".length());
                outNumero.write("nada".getBytes());
            }*/


            outNumero.writeInt("8888".length());
            outNumero.write("8888".getBytes());



            //enviar codigoQR
            outNumero.writeInt(codigoQR.length());

            outNumero.write(codigoQR.getBytes());


            outNumero.flush();


            RecibeRespuestaQR recibeRespuestaQR =new RecibeRespuestaQR(socketQR,qrActivity);

            recibeRespuestaQR.start();

        } catch (SocketTimeoutException e) {


            // en caso no puede conecetar con el servidor
            //es decir el tiempo de conexion es out
            Message msg = new Message();
            msg.what=4;
            qrActivity.mainHandler.sendMessage(msg);
            e.printStackTrace();

        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static DataOutputStream getOutNumero() {
        return outNumero;
    }

    public  Socket getSocketQR() {
        return socketQR;
    }
}
