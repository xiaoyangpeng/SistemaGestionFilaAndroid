package com.example.queue.fragments.incorporar.incorporarRemota.apitienda

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Tienda (


       @SerializedName("id_tienda")

        var id_tienda :Int,

        @SerializedName("nombre")

        val nombre: String,

        @SerializedName("direccion")
         val direccion: String,

       @SerializedName("nombre_categoria")

       val nombre_categoria:String




) : Serializable
