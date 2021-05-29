package com.example.queue.guardarDatoSharedPre.limpiarShare;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.queue.valorFijo.DatoAcceso;

public class Limpiarshare {

    public Limpiarshare(Context context){

        SharedPreferences preferencias = context.getApplicationContext().getSharedPreferences(DatoAcceso.NOMBRESHARE, Context.MODE_PRIVATE );//modo privad

        limpia(preferencias);

        SharedPreferences preferencias2 = context.getApplicationContext().getSharedPreferences(DatoAcceso.DATOCUENTASHARE, Context.MODE_PRIVATE );//modo privad

        limpia(preferencias2);
    }


    private void limpia(    SharedPreferences preferencias){

        SharedPreferences.Editor editor=preferencias.edit();// editor

        editor.clear();

        editor.apply();

    }

}
