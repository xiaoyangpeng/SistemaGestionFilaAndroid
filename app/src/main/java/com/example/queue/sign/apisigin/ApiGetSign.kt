package com.example.queue.sign.apisigin;

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiGetSign {

    @POST("proyectoFinalEntrada/signandroid")
    fun siginCuenta(
            @Body user: Usuario?,
    ) :
            Call<respuestaSign>?
}