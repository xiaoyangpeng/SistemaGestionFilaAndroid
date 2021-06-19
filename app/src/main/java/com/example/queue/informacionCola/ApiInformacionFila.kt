package com.example.queue.informacionCola

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Call
interface ApiInformacionFila {

    @GET("proyectoFinalEntrada/informacionfila")

    fun getInformacionFila( @Header("Authorization") authorization:String)
    :Call<InformacionColaJson>

}