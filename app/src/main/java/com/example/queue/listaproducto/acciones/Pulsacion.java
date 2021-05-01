package com.example.queue.listaproducto.acciones;

import android.content.Context;
import android.view.View;

import com.example.queue.listaproducto.productos.Productos;

public class Pulsacion implements View.OnClickListener {


   private Productos productos;
    private  Context context;
    public Pulsacion(Productos productos, Context context) {
        this.productos = productos;

        this.context=context;
    }


    @Override
    public void onClick(View v) {


        PeticionInformacion informacion=new PeticionInformacion(productos,context);

        informacion.start();

        try {

            informacion.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
