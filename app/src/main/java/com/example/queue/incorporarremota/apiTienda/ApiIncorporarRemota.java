package com.example.queue.incorporarremota.apiTienda;

import android.app.Activity;

import com.example.queue.comunicacionQR.apiIncorporar.ApiGetIncorporar;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiIncorporarRemota extends Thread{




    private Activity activity;

    private Call<Boolean> dataCall;

    private Boolean respuesta=null;


    public     ApiIncorporarRemota(Activity activity){
        this.activity=activity;

    }

    public void crear(int timepo,int id_cola){

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGerIncorporarRemota apiGet =retrofit.create(ApiGerIncorporarRemota.class);

        String token=  LeerToken.tokenUsuario(activity.getApplicationContext());

        //对 发送请求 进行封装
        dataCall= apiGet.getRepuestaIncorporar(timepo,id_cola,token);

    }


    public void run(){

        try {
            respuesta= dataCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean getRespuesta() {
        return respuesta;
    }
}
