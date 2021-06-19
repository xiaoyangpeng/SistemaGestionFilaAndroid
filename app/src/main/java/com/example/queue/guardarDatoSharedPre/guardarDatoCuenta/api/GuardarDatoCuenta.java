package com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.queue.valorFijo.DatoAcceso;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class GuardarDatoCuenta {

    private Context activity;


    public GuardarDatoCuenta(Context activity){

        this.activity=activity;
    }

    public void guardaEmail(String email){

        SharedPreferences preferencias = activity.getApplicationContext().getSharedPreferences(DatoAcceso.NOMBRESHARE, Context.MODE_PRIVATE );//modo privado solo permite en esta aplicacion
        SharedPreferences.Editor editor=preferencias.edit();// editor
        editor.putString(DatoAcceso.EMALI,email);

        editor.commit();
    }

    public void guardarDatosAcceso(@NotNull Usuario usuario){

        SharedPreferences preferencias = activity.getApplicationContext().getSharedPreferences(DatoAcceso.DATOCUENTASHARE, Context.MODE_PRIVATE );//modo privado solo permite en esta aplicacion
        SharedPreferences.Editor editor=preferencias.edit();// editor

        editor.putBoolean(DatoAcceso.SIHYACUENTA,true);

        editor.putString(DatoAcceso.NOMBRE,usuario.getNombre());

        String sexo=usuario.getSexo();

        if(sexo==null){

            sexo="";
        }
        editor.putString(DatoAcceso.SEXO,sexo);

        BigDecimal telefonoaux=usuario.getTelefono();

        String telefono;
        if (telefonoaux==null){

            telefono="0";
        }else{
            telefono=telefonoaux.toString();
        }
        editor.putString(DatoAcceso.TELEFONO,telefono);

        editor.commit();
    }


}
