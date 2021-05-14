package com.example.queue.guardarDatoSharedPre.guradarDatoAcceso;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.queue.valorFijo.DatoAcceso;

public class LeerToken {


    public static String tokenUsuario(Context activity){

        SharedPreferences preferencias = activity.getApplicationContext().getSharedPreferences(com.example.queue.valorFijo.DatoAcceso.NOMBRESHARE, Context.MODE_PRIVATE );

        return preferencias.getString(DatoAcceso.TOKEN,null);
    }




}
