package com.example.queue.fragments.incorporar.incorporarRemota.apitienda;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGetTienda {


    @GET("proyectoFinalEntrada/mandainformaciontienda")
    Call<TiendaArray> getJSonData(@Query("nombre") String nombre) ;

}
