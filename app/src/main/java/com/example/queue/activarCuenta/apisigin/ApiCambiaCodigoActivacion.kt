package com.example.queue.activarCuenta.apisigin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiCambiaCodigoActivacion {


    @GET("proyectoFinalEntrada/cambiacodigoactivacion")
    fun activaCuenta(
            @Header("Authorization") authorization:String
    ) :
            Call<Void>?
}