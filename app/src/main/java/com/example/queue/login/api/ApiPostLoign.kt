package com.example.queue.login.api

import com.example.queue.comunicacionQR.ProductoMandaUsuario
import com.example.queue.listamisproductos.apimisProducto.ListaProductoAux
import retrofit2.Call
import retrofit2.http.*

interface ApiPostLoign {

    @FormUrlEncoded
    @POST("proyectoFinalEntrada/androidlogin")

    fun getJSonData(
        @FieldMap data: Map<String, String>
    ):
            Call<RespuestaLogin>?

    @FormUrlEncoded
    @POST("proyectoFinalEntrada/androidlogin/token")
    fun postLoginToken(
            @FieldMap data: Map<String, String>,
            @Header("Authorization") authorization:String?
    ) :
        Call<RespuestaLogin>?

}