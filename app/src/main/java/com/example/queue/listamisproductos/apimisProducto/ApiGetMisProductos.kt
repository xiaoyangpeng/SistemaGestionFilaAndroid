package com.example.queue.listamisproductos.apimisProducto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiGetMisProductos {



    @GET("proyectoFinalEntrada/mandarlistaproductousuario")

    fun getJSonData(@Query("idcola") idcola: String?,@Query("idusuario") idusuario: String?,  @Header("Authorization") authorization:String): Call<ListaProductoAux>?


}