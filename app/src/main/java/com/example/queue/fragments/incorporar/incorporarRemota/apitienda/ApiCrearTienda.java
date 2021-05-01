package com.example.queue.fragments.incorporar.incorporarRemota.apitienda;

import android.util.Log;

import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCrearTienda  extends  Thread{


    private TiendaArray tiendaArray;
    private  Call<TiendaArray> dataCall;


    public void crear(String nombre){


        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
       ApiGetTienda apiGet =retrofit.create(ApiGetTienda.class);


         //对 发送请求 进行封装
        dataCall= apiGet.getJSonData(nombre);

    }

    @Override
    public void run() {


//同步请求

        Response<TiendaArray> data= null;
        try {

            data = dataCall.execute();
            tiendaArray=data.body();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public TiendaArray getTiendaArray() {
        return tiendaArray;
    }

}
