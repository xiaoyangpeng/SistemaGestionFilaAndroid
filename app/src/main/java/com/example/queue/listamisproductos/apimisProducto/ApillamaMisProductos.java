package com.example.queue.listamisproductos.apimisProducto;

import android.app.Activity;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApillamaMisProductos extends  Thread {


    private Activity activity;

    private ListaProductoAux listaProductoAux;

    private Call<ListaProductoAux> dataCall;

    public ApillamaMisProductos(Activity activity) {
        this.activity = activity;
    }

    public void crear(String idcola,String idusuario){

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGetMisProductos apiGet =retrofit.create(ApiGetMisProductos.class);


        String token=  LeerToken.tokenUsuario(activity);

        //对 发送请求 进行封装
        dataCall= apiGet.getJSonData(idcola,idusuario,token);

    }

    @Override
    public void run() {

        Response<ListaProductoAux> data=null;

        try {
            data = dataCall.execute();

           listaProductoAux=data.body();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public ArrayList<ListaProducto> getListaProducto(){

        return  listaProductoAux.getListaproducto();

    }

}
