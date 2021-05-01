package com.example.queue.listamisproductos;

import android.util.Log;

import com.example.queue.valorFijo.ConexionUrl;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PeticionListaMisProductos extends Thread {


        private ListaProductoAux listaProductoAux;


        public PeticionListaMisProductos(){



        }

    @Override
    public void run() {



        HttpURLConnection miconexion= null;

        try {
            URL url =new URL(ConexionUrl.Companion.getHTTPJSONMIPRODUCTO()+ Ids.id_cola+"&idusuario="+Ids.id_usuario);

            miconexion = (HttpURLConnection) url.openConnection();

            miconexion.setRequestProperty("Accept","application/json");

            miconexion.setConnectTimeout(5000);// poner tiempo de espera
            // si esta fuera de tiempo de espera de conexion salta el error

            if(miconexion.getResponseCode()==200)// conexion 200 con exito con 404 no encontrar la pagina

            {
                InputStreamReader reader = new InputStreamReader( miconexion.getInputStream(), "UTF-8");

                Gson gson=new Gson();

                listaProductoAux=gson.fromJson(reader,ListaProductoAux.class);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ListaProducto> getListaProducto(){

            return  listaProductoAux.getListaproducto();

        }

}
