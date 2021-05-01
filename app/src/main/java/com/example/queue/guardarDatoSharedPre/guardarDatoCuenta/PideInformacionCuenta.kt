package com.example.queue.guardarDatoSharedPre.guardarDatoCuenta

import android.util.Log
import com.example.queue.valorFijo.ConexionUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class PideInformacionCuenta {





    fun pide()=GlobalScope.launch{

        val nombre=async(Dispatchers.IO) {

            conexion()
        }


    }

    private fun conexion(){

        var miconexion:HttpURLConnection

        var url: URL =URL(ConexionUrl.HTTPJSONMANDACUENTA)


        miconexion=url.openConnection() as HttpURLConnection

        miconexion.setRequestProperty("Accept","application/json")


        if(miconexion.responseCode==200){

            Log.v("123","22222222")

        }



    }


}