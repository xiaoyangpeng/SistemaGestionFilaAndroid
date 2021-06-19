package com.example.queue.activarCuenta.apisigin;


import com.example.queue.sign.SignActivity;
import com.example.queue.sign.apisigin.ApiGetActiva;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LlamaActivaCuenta extends  Thread{

    private Call<Boolean> dataCall;

    private Boolean respuetaActiva;

    public void crear(String codigoActivacion,String token) {

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGetActiva apiGet = retrofit.create(ApiGetActiva.class);

        //对 发送请求 进行封装
        dataCall = apiGet.activaCuenta(codigoActivacion,token);

    }

    @Override
    public void run() {

        try {
            respuetaActiva = dataCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public Boolean getRespuetaActiva() {
        return respuetaActiva;
    }


}


