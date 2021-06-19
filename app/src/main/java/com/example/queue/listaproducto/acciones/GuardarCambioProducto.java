package com.example.queue.listaproducto.acciones;

import android.app.Activity;

import com.example.queue.comunicacionQR.ProductoMandaUsuario;
import com.example.queue.listaproducto.acciones.apilistaproducto.ApiMandaListaProducto;
import com.example.queue.valorFijo.Ids;

public class GuardarCambioProducto  {


    private  int id_producto;

    private int cantidad;
    private Activity activity;
    public GuardarCambioProducto(int id_producto, int cantidad,Activity activity) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.activity=activity;
    }

    public void start() {

        ApiMandaListaProducto manda=new ApiMandaListaProducto(activity);

        ProductoMandaUsuario productoMandaUsuario = new ProductoMandaUsuario();

        productoMandaUsuario.setId_cola(Integer.parseInt(Ids.id_cola));

        productoMandaUsuario.setId_producto(id_producto);

        productoMandaUsuario.setCantidad(cantidad);

        manda.crear(productoMandaUsuario);

        manda.start();

        try {
            manda.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
