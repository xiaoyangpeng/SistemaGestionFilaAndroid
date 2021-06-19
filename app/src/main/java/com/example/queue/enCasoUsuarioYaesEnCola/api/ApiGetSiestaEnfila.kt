package com.example.queue.enCasoUsuarioYaesEnCola.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiGetSiestaEnfila {


    @GET("proyectoFinalEntrada/estaenfila")

    fun getRepuestaEnfila( @Header("Authorization") authorization:String): Call<Boolean>

}