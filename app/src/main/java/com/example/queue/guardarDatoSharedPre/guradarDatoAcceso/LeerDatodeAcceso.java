package com.example.queue.guardarDatoSharedPre.guradarDatoAcceso;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.queue.login.EnviaLogin;
import com.example.queue.login.LoginActivity;

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

             activity.activarODesactivaView(false);
             EnviaLogin enviaLogin = new EnviaLogin(email, contrasena, activity);
             enviaLogin.start();
         }


    }



}
