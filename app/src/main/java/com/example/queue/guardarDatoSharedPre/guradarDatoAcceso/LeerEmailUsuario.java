package com.example.queue.guardarDatoSharedPre.guradarDatoAcceso;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class LeerEmailUsuario {



    public static String emailUsuario(Activity activity){

    SharedPreferences   preferencias = activity.getApplicationContext().getSharedPreferences(com.example.queue.valorFijo.DatoAcceso.NOMBRESHARE, Context.MODE_PRIVATE );

        return preferencias.getString(com.example.queue.valorFijo.DatoAcceso.EMALI,null);
    }

}
