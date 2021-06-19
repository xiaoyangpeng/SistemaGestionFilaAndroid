package com.example.queue.incorporarremota.apiTienda

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiGerIncorporarRemota {

    @GET("proyectoFinalEntrada/incorporarRemota")

    fun getRepuestaIncorporar(@Query("tiempo") tiempo: Int?,
                              @Query("id_cola") id_cola: Int?,
                              @Header("Authorization") authorization:String): Call<Boolean>

}