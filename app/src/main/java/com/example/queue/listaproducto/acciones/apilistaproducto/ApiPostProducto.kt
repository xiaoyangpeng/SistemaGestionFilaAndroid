package com.example.queue.listaproducto.acciones.apilistaproducto

import com.example.queue.comunicacionQR.ProductoMandaUsuario
import com.example.queue.listamisproductos.apimisProducto.ListaProductoAux
import retrofit2.Call
import retrofit2.http.*

interface ApiPostProducto {


    @POST("proyectoFinalEntrada/recibeproducto")

    fun getJSonData(
            @Body request: ProductoMandaUsuario,
            @Header("Authorization") authorization:String):
            Call<Void>?



}