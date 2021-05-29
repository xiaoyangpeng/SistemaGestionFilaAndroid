package com.example.queue.login.api;

import android.os.Message;

import com.example.queue.login.LoginActivity;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiLogin extends  Thread {


    private Call<RespuestaLogin> dataCall;
    private Response<RespuestaLogin> data = null;

    private LoginActivity loginActivity;

    public ApiLogin(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }


    public void crear(String email, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiPostLoign apiGet = retrofit.create(ApiPostLoign.class);

        Map<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("email", email);

        hashMap.put("password", password);

        //对 发送请求 进行封装
        dataCall = apiGet.getJSonData(hashMap);

    }

    @Override
    public void run() {

        try {
            data = dataCall.execute();

        } catch (IOException e) {
            // en caso no puede conecetar con el servidor
            //es decir el tiempo de conexion es out
            if (!loginActivity.isFinishing()) {
                  Message msg = new Message();
                   msg.what=4;
                   loginActivity.mainHandler.sendMessage(msg);}
            }


    }

        public String respuesta () {

            return data.body().respuesta;
        }

}