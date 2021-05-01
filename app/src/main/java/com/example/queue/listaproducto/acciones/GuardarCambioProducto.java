package com.example.queue.listaproducto.acciones;

import com.example.queue.comunicacionQR.ProductoMandaUsuario;
import com.example.queue.comunicacionQR.QrEnviar;
import com.example.queue.enCasoUsuarioYaesEnCola.EnviarSiEstaEncola;
import com.example.queue.valorFijo.Ids;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class GuardarCambioProducto extends Thread {



    private  int id_producto;

    private int cantidad;


    public GuardarCambioProducto(int id_producto, int cantidad) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {


        Gson gson=new GsonBuilder().setPrettyPrinting().create();

        try {


                ProductoMandaUsuario productoMandaUsuario = new ProductoMandaUsuario();

                productoMandaUsuario.setId_cola(Integer.parseInt(Ids.id_cola));

                productoMandaUsuario.setId_producto(id_producto);

                productoMandaUsuario.setCantidad(cantidad);

                productoMandaUsuario.setId_usuario(Integer.parseInt(Ids.id_usuario));

                String json = gson.toJson(productoMandaUsuario);


                if(QrEnviar.getOutNumero()!=null) {

                    QrEnviar.getOutNumero().writeUTF(json);

                }else{

                    EnviarSiEstaEncola.getOutNumero().writeUTF(json);
                }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
