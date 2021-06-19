package com.example.queue.enCasoUsuarioYaesEnCola.api;

import android.app.Activity;

import com.example.queue.comunicacionQR.apiIncorporar.ApiGetIncorporar;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.incorporarremota.apiTienda.ApiGetInfTienda;
import com.example.queue.incorporarremota.apiTienda.Tiendaremota;
import com.example.queue.probarConexionInternet.Fallaconexion;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSiEstaEnFila extends Thread{

    private Activity activity;

    private Call<Boolean> dataCall;

    private boolean respuesta;

    public ApiSiEstaEnFila(Activity activity){

        this.activity=activity;
    }

    public void crear(){

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGetSiestaEnfila apiGet =retrofit.create(ApiGetSiestaEnfila.class);

        String token=  LeerToken.tokenUsuario(activity.getApplicationContext());

        //对 发送请求 进行封装
        dataCall= apiGet.getRepuestaEnfila(token);

    }


    public void run(){

        try {
          respuesta= dataCall.execute().body();

        } catch (IOException e) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Fallaconexion.fallaconexionServidor(activity);
                }
            });


        }

    }

    public boolean getRespuesta() {
        return respuesta;
    }
}
