package com.example.queue.modificardato.apimandacuenta

import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiModifica {



    @POST("proyectoFinalEntrada/auth/modificar")
    fun modficcaCuenta(

            @Body user:Usuario?,
            @Header("Authorization") authorization:String?
    ) :
            Call<Usuario>?
}