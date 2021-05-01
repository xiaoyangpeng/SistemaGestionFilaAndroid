package com.example.queue.login;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerDatodeAcceso;
import com.example.queue.valorFijo.ConexionUrl;
import com.example.queue.valorFijo.DatoAcceso;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecibeLogin extends Thread {

    private DataInputStream entrada;

   private  Socket misocket;
   private LoginActivity loginActivity;
    private String email;
    private  String contrasena;
    public RecibeLogin(Socket misocket, LoginActivity loginActivity, String email, String contrasena){

        this.misocket=misocket;
        this.loginActivity = loginActivity;

        this.email=email;
        this.contrasena=contrasena;
    }
    @Override
    public void run() {


        try {

            entrada = new DataInputStream(misocket.getInputStream());
            // -1 no puede accerder
            // 1 exito
            // 2 no esta activado
            // 3 ya esta en liena

            // 4 falla conexion
           int estadoUsuario=entrada.readInt();

             Message msg = new Message();

           switch (estadoUsuario){

               case -1 :

                   msg.what = -1;

               break;

               case 1 :

                   Bundle bundle = new Bundle();
                   bundle.putString(DatoAcceso.EMALI,email);
                   bundle.putString(DatoAcceso.CONTRSENA,contrasena);
                   msg.setData(bundle);
                   msg.what = 1;

                   // en calse leerdatos de acceso esta esperando que su contrasena y usuario del sharedpreferend
                   // son correcto
                   LeerDatodeAcceso.puedeacceder=true;
                break;

               case 2:
                   msg.what = 2;

                break;

               case 3:

                   msg.what = 3;

                   break;


           }
            loginActivity.mainHandler.sendMessage(msg);

           // decir el clase LeerDatodeAcceso en el autoaccede ya puede salir de su bucle

            LeerDatodeAcceso.norecibidomensaje=false;


           if(msg.what==1){

              //  misocket.setOOBInline(true);

               DataOutputStream out=new DataOutputStream(misocket.getOutputStream());
               while(ConexionUrl.Companion.getCONEXION()) {

                   out.writeInt(10);

                   Thread.sleep(5000);
              }

               misocket.close();

           }
        } catch (IOException | InterruptedException e) {

            if(!loginActivity.isFinishing()) {
                Message msg = new Message();
                msg.what = 4;
                loginActivity.mainHandler.sendMessage(msg);

            }

            e.printStackTrace();
        }

    }


}
