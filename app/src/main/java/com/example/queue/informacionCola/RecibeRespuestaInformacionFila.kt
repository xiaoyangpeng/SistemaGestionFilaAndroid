package com.example.queue.informacionCola

import android.app.Activity
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerToken
import com.example.queue.probarConexionInternet.Fallaconexion
import com.example.queue.valorFijo.ConexionUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RecibeRespuestaInformacionFila(private var activity: Activity) :Thread(){

    var informacioCola: InformacionColaJson?=null


    private    lateinit var dataCall: Call<InformacionColaJson>

    fun crear(){

        val retrofit = Retrofit.Builder()
                .baseUrl(ConexionUrl.Companion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var apiGet=retrofit.create(ApiInformacionFila::class.java)

        val token=LeerToken.tokenUsuario(activity)

        if(token!=null){
            dataCall= apiGet.getInformacionFila(token)
        }

    }

    override fun run() {
        try {

            if(dataCall!=null){
                informacioCola = dataCall.execute().body()
            }

        }
            catch (e: IOException) {
              activity.runOnUiThread{
                  Fallaconexion.fallaconexionServidor(activity)

              }
             }
    }


}