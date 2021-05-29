package com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.queue.valorFijo.DatoAcceso;

import java.math.BigDecimal;

public class LeerDatoCuenta {

    private Context activity;


    private     SharedPreferences preferencias;


    public LeerDatoCuenta(Context activity){

        this.activity=activity;
        preferencias = activity.getApplicationContext().getSharedPreferences(DatoAcceso.DATOCUENTASHARE, Context.MODE_PRIVATE );
    }



    public boolean hayDatos(){

        return preferencias.getBoolean(DatoAcceso.SIHYACUENTA,false);
    }

    public Usuario datoEnShare(){

            Usuario user=new Usuario();
              user.setNombre(preferencias.getString(DatoAcceso.NOMBRE,""));
            user.setSexo(preferencias.getString(DatoAcceso.SEXO,""));
             user.setTelefono(new BigDecimal(preferencias.getString(DatoAcceso.TELEFONO,"0")));

        return user;
        }

    public String getEmail(){

        SharedPreferences preferencias = activity.getApplicationContext().getSharedPreferences(DatoAcceso.NOMBRESHARE, Context.MODE_PRIVATE );//m

        return  preferencias.getString(DatoAcceso.EMALI,"");
    }


}
