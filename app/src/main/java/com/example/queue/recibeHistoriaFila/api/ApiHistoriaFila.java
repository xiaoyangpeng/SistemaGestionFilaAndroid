package com.example.queue.recibeHistoriaFila.api;

import android.app.Activity;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.listamisproductos.apimisProducto.ApiGetMisProductos;
import com.example.queue.listamisproductos.apimisProducto.ListaProducto;
import com.example.queue.listamisproductos.apimisProducto.ListaProductoAux;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHistoriaFila extends  Thread {


    private Activity activity;


    private Call<ArrayList<HistoriaEnFila>> dataCall;


    private ArrayList<HistoriaEnFila> historiaEnFilas;

    public ApiHistoriaFila(Activity activity) {
        this.activity = activity;
    }

    public void crear(){

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGetHistoria apiGet =retrofit.create(ApiGetHistoria.class);


        String token=  LeerToken.tokenUsuario(activity);

        //对 发送请求 进行封装
        dataCall= apiGet.getJSonData(token);

    }

    @Override
    public void run() {
        try {
           historiaEnFilas = dataCall.execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<HistoriaEnFila> getHistoriaEnFilas() {
        return historiaEnFilas;
    }
}
