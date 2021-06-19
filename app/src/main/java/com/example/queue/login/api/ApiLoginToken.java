package com.example.queue.login.api;

import android.os.Message;
import android.util.Log;

import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.login.LoginActivity;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiLoginToken extends  Thread {


    private Call<RespuestaLogin> dataCall;
    private Response<RespuestaLogin> data = null;

    private LoginActivity loginActivity;

    public ApiLoginToken(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }


    public void crear() {

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例


        ApiPostLoign apiGet = retrofit.create(ApiPostLoign.class);

        String token=  LeerToken.tokenUsuario(loginActivity);
        Map<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("email", "jjj");
        //对 发送请求 进行封装
        dataCall = apiGet.postLoginToken(hashMap,token);

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

    public RespuestaLogin respuesta () {

        RespuestaLogin resul=null;
        try {
          resul = data.body();

        }catch (Exception e){

            if (!loginActivity.isFinishing()) {
                Message msg = new Message();
                msg.what=4;
                loginActivity.mainHandler.sendMessage(msg);}
        }
        return resul;
    }

}