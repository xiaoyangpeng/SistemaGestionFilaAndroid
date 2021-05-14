package com.example.queue.incorporarremota.apiTienda

import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Header

import retrofit2.http.Query

public interface  ApiGetInfTienda {


    @GET("proyectoFinalEntrada/mandatiendaremota")

    fun getJSonData(@Query("idtienda") idtienda: String?,@Header("Authorization") authorization:String): Call<Tiendaremota>?


}