package com.example.queue.listamisproductos;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMiListaApi {


    @GET
    Call<ListaProducto> getMisListas();

}
