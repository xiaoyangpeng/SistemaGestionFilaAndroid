package com.example.queue.login;

import com.example.queue.login.api.ApiLogin;
import com.example.queue.login.api.RespuestaLogin;

import java.net.Socket;

public class EnviaLogin   {

    private Socket socketLogin;
    private  String editUsuario;
    private String editContrasena;
    private LoginActivity loginActivity;

    public EnviaLogin(String editusuario, String editcontrasena, LoginActivity loginActivity){

         this.editUsuario=editusuario;
         this.editContrasena=editcontrasena;
         this.loginActivity = loginActivity;
    }

    public void start () {

        ApiLogin login=new ApiLogin(loginActivity);

        login.crear(editUsuario,editContrasena);

        login.start();

        try {
            login.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RespuestaLogin respuestaLogin=login.respuesta();

        RecibeLogin recibeLogin=new RecibeLogin(editUsuario,editContrasena,loginActivity);
        recibeLogin.actuar(respuestaLogin);


    }


}
