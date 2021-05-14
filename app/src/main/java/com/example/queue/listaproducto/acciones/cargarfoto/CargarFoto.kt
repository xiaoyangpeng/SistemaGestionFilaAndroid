package com.example.queue.listaproducto.acciones.cargarfoto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Message
import android.widget.ImageView
import com.example.queue.valorFijo.ConexionUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class CargarFoto( val idProducto: Int ,val handler :Handler) {


    fun cargarFoto()=GlobalScope.launch{

        async(Dispatchers.IO) {

            conexion()

        }


    }

    private fun conexion(){

        var miconexion: HttpURLConnection

        var url: URL =URL(ConexionUrl.HTTPJSONMANDACUENTA+idProducto)

        miconexion=url.openConnection() as HttpURLConnection

       miconexion.setRequestMethod("GET")


        if(miconexion.responseCode==200){


            var input:InputStream=miconexion.inputStream

            var bitmap:Bitmap=BitmapFactory.decodeStream(input)

            var messaje:Message=Message()

            messaje.what=1

            messaje.obj=bitmap

            handler.sendMessage(messaje)


        }



    }




}