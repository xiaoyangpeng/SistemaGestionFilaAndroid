package com.example.queue.guardarDatoSharedPre.guradarDatoAcceso;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.queue.login.EnviaLogin;
import com.example.queue.login.LoginActivity;

import com.example.queue.login.RecibeLogin;
import com.example.queue.login.api.ApiLogin;
import com.example.queue.login.api.ApiLoginToken;
import com.example.queue.login.api.RespuestaLogin;
import com.example.queue.valorFijo.DatoAcceso;

public class LeerDatodeAcceso   {

    private LoginActivity activity;


    public static boolean norecibidomensaje=true;
    public static boolean puedeacceder=false;

    private     SharedPreferences preferencias;

    private String email;
    private String contrasena;

    public LeerDatodeAcceso(LoginActivity activity){

        this.activity=activity;
        preferencias = activity.getApplicationContext().getSharedPreferences(DatoAcceso.NOMBRESHARE, Context.MODE_PRIVATE );
    }


    public void autoAccede(){

        boolean hayDatos=preferencias.getBoolean(DatoAcceso.TIENEDATOS,false);
        email=preferencias.getString(DatoAcceso.EMALI,null);

        contrasena=preferencias.getString(DatoAcceso.CONTRSENA,null);

         if(hayDatos){

             // en caso si hay datos poner email y contasena en su campo
             activity.getUsuario().setText(email);
             activity.getContrasena().setText(contrasena);

             ApiLoginToken login=new ApiLoginToken(activity);
             login.crear();
             login.start();
             try {
                 login.join();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             RespuestaLogin repuesta=login.respuesta();

             RecibeLogin recibeLogin=new RecibeLogin(email,contrasena,activity);
             recibeLogin.actuar(repuesta);

         }


    }



}
