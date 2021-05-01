package com.example.queue.listaproducto.acciones;

import android.os.Message;

import com.example.queue.listaproducto.interfaz.Listaproducto;
import com.example.queue.listaproducto.productos.Productos;
import com.example.queue.listaproducto.productos.ProductosAux;
import com.example.queue.valorFijo.ConexionUrl;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PetecionProductos extends  Thread  {


    private ProductosAux productosAux;

    private String nombreProducto;
    private Listaproducto listaproducto;
    public PetecionProductos(String nombreProducto, Listaproducto listaproducto) {

        this.nombreProducto=nombreProducto;
        this.listaproducto = listaproducto;

    }

    @Override
    public void run() {

        HttpURLConnection miconexion= null;

        try {


            URL url =new URL(ConexionUrl.Companion.getHTTPJSON()+Ids.codigoQR+"&idusuario="+Ids.id_usuario+"&idcola="+Ids.id_cola+"&nombreproducto="+nombreProducto);

            miconexion = (HttpURLConnection) url.openConnection();

            miconexion.setRequestProperty("Accept","application/json");

            miconexion.setConnectTimeout(5000);// poner tiempo de espera
            // si esta fuera de tiempo de espera de conexion salta el error

            if(miconexion.getResponseCode()==200)// conexion 200 con exito con 404 no encontrar la pagina
            {

                InputStreamReader reader = new InputStreamReader( miconexion.getInputStream(), "UTF-8");

                Gson gson=new Gson();

                productosAux=gson.fromJson(reader, ProductosAux.class);

            }

        } catch (IOException e) {
            Message msg=new Message();
            listaproducto.handler.sendMessage(msg);
            e.printStackTrace();
        }

    }


    public ArrayList<Productos> getProductos() {

        if(productosAux==null){
            productosAux=new ProductosAux();
        }

        return productosAux.getProductos();
    }
}
