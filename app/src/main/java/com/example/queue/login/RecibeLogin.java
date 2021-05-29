package com.example.queue.login;

import android.os.Bundle;
import android.os.Message;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerDatodeAcceso;
import com.example.queue.valorFijo.DatoAcceso;

public class RecibeLogin {

   private LoginActivity loginActivity;
    private String email;
    private  String contrasena;
    public RecibeLogin( String email, String contrasena,LoginActivity loginActivity){

        this.loginActivity = loginActivity;
        this.email=email;
        this.contrasena=contrasena;
    }

    public void actuar(String respuesta) {

            // -1 no puede accerder
            // 1 exito
            // 2 no esta activado
            // 3 ya esta en liena
            // 4 falla conexion
           String estadoUsuario=respuesta;

             Message msg = new Message();

             if(estadoUsuario==null){
                 estadoUsuario="4";
             }

           switch (estadoUsuario){

               case "-1" :

                   msg.what = -1;

               break;

               case "4" :
                   msg.what = 4;

                   break;
               case "2":
                   msg.what = 2;

                break;

               case "3":

                   msg.what = 3;

                   break;

               case "5":
                   msg.what = 5;
                   break;

                   default:


                       // leer token
                       Bundle bundle = new Bundle();
                       bundle.putString(DatoAcceso.EMALI, email);
                       bundle.putString(DatoAcceso.CONTRSENA, contrasena);
                       bundle.putString(DatoAcceso.TOKEN, respuesta);
                       msg.setData(bundle);
                       msg.what = 1;

                       // en calse leerdatos de acceso esta esperando que su contrasena y usuario del sharedpreferend
                       // son correcto
                       LeerDatodeAcceso.puedeacceder = true;

                       break;

           }
            loginActivity.mainHandler.sendMessage(msg);


    }


}
