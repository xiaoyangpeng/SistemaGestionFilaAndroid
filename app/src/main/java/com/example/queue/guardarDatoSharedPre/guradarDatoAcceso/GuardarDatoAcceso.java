package com.example.queue.guardarDatoSharedPre.guradarDatoAcceso;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.queue.valorFijo.DatoAcceso;

public class GuardarDatoAcceso {

    private Activity activity;


    public GuardarDatoAcceso(Activity activity){

        this.activity=activity;
    }

    public void guardarDatosAcceso(String email,String contrasena){

        // una vez que el usuario ha entrado con exito
        // su email y contrasena
        // guardae ne sharedPrefenrence
        // para que no tiene que acceder cada vez arranca la aplicacion
        SharedPreferences preferencias = activity.getApplicationContext().getSharedPreferences(DatoAcceso.NOMBRESHARE, Context.MODE_PRIVATE );//modo privado solo permite en esta aplicacion
        SharedPreferences.Editor editor=preferencias.edit();// editor

        editor.putBoolean(DatoAcceso.TIENEDATOS,true);
        editor.putString(DatoAcceso.EMALI,email);
        editor.putString(DatoAcceso.CONTRSENA,contrasena);
        editor.commit();
    }


}
