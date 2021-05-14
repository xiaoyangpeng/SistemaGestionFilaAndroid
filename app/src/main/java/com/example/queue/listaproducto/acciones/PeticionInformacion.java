package com.example.queue.listaproducto.acciones;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.listaproducto.interfaz.comida.InformacionComidaActivity;
import com.example.queue.listaproducto.interfaz.mercancias.MercanciasActivity;
import com.example.queue.listaproducto.productos.Comida;
import com.example.queue.listaproducto.productos.Mercancia;

import com.example.queue.listaproducto.productos.Productos;
import com.example.queue.listaproducto.productos.Servicio;
import com.example.queue.valorFijo.ConexionUrl;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PeticionInformacion extends  Thread{



   private  Productos productos;
   private Comida comida;

   private Mercancia mercancia;

   private Servicio servicio;

   private Context  context;

    public PeticionInformacion(Productos productos,Context context) {
        this.productos=productos;
        this.context=context;

    }

    @Override
    public void run() {

        HttpURLConnection miconexion= null;

        try {
            URL url =new URL(ConexionUrl.Companion.getHTTPJSONINFORMACION()+ Ids.id_usuario+"&idcola="+Ids.id_cola+"&idproducto="+productos.getId_producto()+"&categoria="+productos.getCategoria());


            miconexion = (HttpURLConnection) url.openConnection();

            miconexion.setRequestProperty("Accept","application/json");

            miconexion.setConnectTimeout(5000);// poner tiempo de espera
            // si esta fuera de tiempo de espera de conexion salta el error

            String token= LeerToken.tokenUsuario(context);

            miconexion.setRequestProperty("Authorization",token);

            if(miconexion.getResponseCode()==200)// conexion 200 con exito con 404 no encontrar la pagina

            {
                InputStreamReader reader = new InputStreamReader( miconexion.getInputStream(), "UTF-8");

                Gson gson=new Gson();

                if(productos.getCategoria().equals("comida")) {

                   comida=gson.fromJson(reader, Comida.class);

                    Bundle b=new Bundle();
                    b.putSerializable("producto",productos);
                    b.putSerializable("comida",comida);

                    context.startActivity(new Intent(context, InformacionComidaActivity.class).putExtras(b));

                }else if(productos.getCategoria().equals("servicio")) {


                    servicio=gson.fromJson(reader, Servicio.class);

                }else if(productos.getCategoria().equals("mercancia")) {

                    mercancia=gson.fromJson(reader, Mercancia.class);

                    Bundle b2=new Bundle();
                    b2.putSerializable("producto",productos);
                    b2.putSerializable("mercancia",mercancia);

                    context.startActivity(new Intent(context, MercanciasActivity.class).putExtras(b2));
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public Mercancia getMercancia() {
        return mercancia;
    }

    public void setMercancia(Mercancia mercancia) {
        this.mercancia = mercancia;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}







