package com.example.queue.sign.apisigin;


import com.example.queue.sign.SignActivity;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LlamaApiSigin extends  Thread{

    private Call<respuestaSign> dataCall;
    private respuestaSign respeusta;

    private  SignActivity    signActivity;

    public LlamaApiSigin(SignActivity    signActivity){

        this.signActivity=signActivity;
    }

    public void crear(Usuario user) {

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiGetSign apiGet = retrofit.create(ApiGetSign.class);

        //对 发送请求 进行封装
        dataCall = apiGet.siginCuenta(user);

    }

    @Override
    public void run() {


        try {
           respeusta = dataCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }




         /*   // en caso no puede conecetar con el servidor
            //es decir el tiempo de conexion es out
            Message msg = new Message();
            msg.what=9;
             signActivity.mainHandler.sendMessage(msg);*/




    }

    public respuestaSign getRespeusta() {
        return respeusta;
    }


}
