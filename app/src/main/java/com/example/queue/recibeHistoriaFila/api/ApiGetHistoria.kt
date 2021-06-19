package com.example.queue.recibeHistoriaFila.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiGetHistoria {


    @GET("proyectoFinalEntrada/historiafila")

    fun getJSonData( @Header("Authorization") authorization:String): Call<ArrayList<HistoriaEnFila>>?


}