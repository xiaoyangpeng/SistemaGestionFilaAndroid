package com.example.queue.listaproducto.acciones.apilistaproducto;

import android.app.Activity;
import android.os.Message;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;



import com.example.queue.listaproducto.productos.Productos;
import com.example.queue.listaproducto.productos.ProductosAux;
import com.example.queue.valorFijo.ConexionUrl;


import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApillaListaProducto extends  Thread {


    private Activity activity;

    private ProductosAux productosAux;

    private Call<ProductosAux> dataCall;

    public ApillaListaProducto(Activity activity) {
        this.activity = activity;
    }

    public void crear(String idcola,String idusuario,String nombreProducto){

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApigetListaProducto apiGet=retrofit.create(ApigetListaProducto.class);


        String token=  LeerToken.tokenUsuario(activity);

        //对 发送请求 进行封装
        dataCall= apiGet.getJSonData(idcola,idusuario,nombreProducto,token);

    }

    @Override
    public void run() {

        Response<ProductosAux> data=null;

        try {
            data = dataCall.execute();

         productosAux= data.body();


        } catch (IOException e) {

            com.example.queue.listaproducto.interfaz.Listaproducto listaActivity=(com.example.queue.listaproducto.interfaz.Listaproducto)activity;
            Message msg=new Message();
            listaActivity.handler.sendMessage(msg);

        }


    }

    public ArrayList<Productos> getProductos() {

        if(productosAux==null){
            productosAux=new ProductosAux();
        }

        return productosAux.getProductos();
    }
}
