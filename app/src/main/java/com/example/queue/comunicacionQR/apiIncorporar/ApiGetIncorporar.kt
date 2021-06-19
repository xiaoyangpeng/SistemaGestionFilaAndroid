package com.example.queue.comunicacionQR.apiIncorporar

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiGetIncorporar {


    @GET("proyectoFinalEntrada/incorporarqr")

    fun getRepuestaIncorporar(@Query("qr") idtienda: String?, @Header("Authorization") authorization:String): Call<Int>

}