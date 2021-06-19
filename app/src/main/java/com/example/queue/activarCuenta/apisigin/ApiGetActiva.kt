package com.example.queue.sign.apisigin;

import retrofit2.Call
import retrofit2.http.*

interface ApiGetActiva {

    @GET("proyectoFinalEntrada/activaCuentaandroid")
    fun activaCuenta(
            @Query("codigoactivacion") codigoActivacion: String,
            @Header("Authorization") authorization:String
    ) :
            Call<Boolean>?
}