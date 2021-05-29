package com.example.queue.modificardato.apimandacuenta;

import android.content.Context;

import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.GuardarDatoCuenta;
import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.Usuario;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken;
import com.example.queue.valorFijo.ConexionUrl;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LlamaApiModifica extends  Thread{


    private Call<Usuario> dataCall;
    private Response<Usuario> data = null;

    private Context context;

    public LlamaApiModifica( Context context) {
        this.context = context;
    }


    public void crear(Usuario user) {

        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(ConexionUrl.Companion.getBASE_URL())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        ApiModifica apiGet = retrofit.create(ApiModifica.class);

        String token=  LeerToken.tokenUsuario(context);

        //对 发送请求 进行封装
        dataCall = apiGet.modficcaCuenta(user,token);

    }

    @Override
    public void run() {


        try {
            data = dataCall.execute();


            GuardarDatoCuenta guardarDatoCuenta=new GuardarDatoCuenta(context);

            guardarDatoCuenta.guardarDatosAcceso(data.body());

           guardarDatoCuenta.guardaEmail(data.body().getEmail());

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}
