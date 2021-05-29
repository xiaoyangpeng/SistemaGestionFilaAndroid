package com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api;

import android.view.View;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.login.api.ApiGetCuenta;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApillamaGetCuenta extends  Thread {


    private Call<Usuario> dataCall;
    private Response<Usuario> data = null;

    private View activity;

    public ApillamaGetCuenta( View activity) {
        this.activity=activity;
    }


    public void crear() {

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGetCuenta apiGet = retrofit.create(ApiGetCuenta.class);

        String token=  LeerToken.tokenUsuario(activity.getContext());

        //对 发送请求 进行封装
        dataCall = apiGet.getCuenta(token);

    }

    @Override
    public void run() {


        try {
            data = dataCall.execute();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

        public Usuario getUsuario() {

            return data.body();
        }

}