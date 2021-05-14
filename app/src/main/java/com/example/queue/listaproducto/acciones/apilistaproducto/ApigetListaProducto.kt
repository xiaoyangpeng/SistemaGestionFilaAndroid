package com.example.queue.listaproducto.acciones.apilistaproducto

import com.example.queue.listamisproductos.apimisProducto.ListaProductoAux
import com.example.queue.listaproducto.productos.ProductosAux
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApigetListaProducto {


    @GET("proyectoFinalEntrada/mandaListaporqr")

    fun getJSonData( @Query("idcola") idcola: String?,
                     @Query("idusuario") idusuario: String?,
                    @Query("nombreproducto") nombreProducto:String?,
                    @Header("Authorization") authorization:String?): Call<ProductosAux>?

}