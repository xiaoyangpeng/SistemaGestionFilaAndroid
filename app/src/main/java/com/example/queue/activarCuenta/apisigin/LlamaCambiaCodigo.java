package com.example.queue.activarCuenta.apisigin;


import com.example.queue.sign.apisigin.ApiGetActiva;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LlamaCambiaCodigo extends  Thread{

    private Call<Void> dataCall;

    public void crear(String token) {

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiCambiaCodigoActivacion apiGet = retrofit.create(ApiCambiaCodigoActivacion.class);

        //对 发送请求 进行封装
        dataCall = apiGet.activaCuenta(token);

    }

    @Override
    public void run() {

        try {
             dataCall.execute();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}


