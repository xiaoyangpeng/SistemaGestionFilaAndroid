package com.example.queue.incorporarremota.apiTienda;

import android.app.Activity;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiTiendaRemota extends Thread {

    private Activity activity;

    private Tiendaremota tiendaremota;

    private Call<Tiendaremota> dataCall;

    public ApiTiendaRemota(Activity activity) {
        this.activity = activity;
    }

    public void crear(String idtienda){

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGetInfTienda apiGet =retrofit.create(ApiGetInfTienda.class);


         String token=  LeerToken.tokenUsuario(activity);

        //对 发送请求 进行封装
        dataCall= apiGet.getJSonData(idtienda,token);

    }

    @Override
    public void run() {

        Response<Tiendaremota> data=null;

        try {
            data = dataCall.execute();
            tiendaremota=data.body();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Tiendaremota getTiendaremota() {
        return tiendaremota;
    }
}
