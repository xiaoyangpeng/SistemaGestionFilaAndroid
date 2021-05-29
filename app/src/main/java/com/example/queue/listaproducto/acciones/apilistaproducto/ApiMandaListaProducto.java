package com.example.queue.listaproducto.acciones.apilistaproducto;

import android.app.Activity;
import android.os.Message;

import com.example.queue.comunicacionQR.ProductoMandaUsuario;
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

public class ApiMandaListaProducto extends  Thread {


    private Activity activity;

    private ProductoMandaUsuario productoMandaUsuario;

    private Call<Void> dataCall;

    public ApiMandaListaProducto(Activity activity) {
        this.activity = activity;
    }


    public void crear(ProductoMandaUsuario productoMandaUsuario){

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiPostProducto apiGet=retrofit.create(ApiPostProducto.class);


        String token=  LeerToken.tokenUsuario(activity);

        //对 发送请求 进行封装
        dataCall= apiGet.getJSonData(productoMandaUsuario,token);

    }

    @Override
    public void run() {

        Response<Void> data=null;

        try {
            data = dataCall.execute();

        } catch (IOException e) {


        }

    }


}
