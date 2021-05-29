package com.example.queue.login.api

import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.Usuario
import retrofit2.Call
import retrofit2.http.*

interface ApiGetCuenta {



    @GET("proyectoFinalEntrada/getuserbyid")
    fun getCuenta(
            @Header("Authorization") authorization:String?
    ) :
        Call<Usuario>?

}