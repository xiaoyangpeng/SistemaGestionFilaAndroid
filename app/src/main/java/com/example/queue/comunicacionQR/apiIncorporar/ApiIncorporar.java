package com.example.queue.comunicacionQR.apiIncorporar;

import android.app.Activity;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.incorporarremota.apiTienda.ApiGetInfTienda;
import com.example.queue.incorporarremota.apiTienda.Tiendaremota;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiIncorporar extends Thread{


    private Activity activity;

    private Call<Integer> dataCall;

    private Integer respuesta=null;

    public ApiIncorporar(Activity activity){

        this.activity=activity;
    }

    public void crear(String qr){

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGetIncorporar apiGet =retrofit.create(ApiGetIncorporar.class);

        String token=  LeerToken.tokenUsuario(activity.getApplicationContext());

        //对 发送请求 进行封装
        dataCall= apiGet.getRepuestaIncorporar(qr,token);

    }


    public void run(){


        try {
          respuesta= dataCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Integer getRespuesta() {
        return respuesta;
    }
}
