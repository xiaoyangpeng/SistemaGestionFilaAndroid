package com.example.queue.incorporarremota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.TextView
import com.example.queue.R
import com.example.queue.fragments.incorporar.incorporarRemota.apitienda.Tienda
import com.example.queue.incorporarremota.apiTienda.ApiTiendaRemota
import com.example.queue.incorporarremota.apiTienda.Tiendaremota

class InformacionTiendaActivity : AppCompatActivity() {



     var tienda:Tienda?=null


    var tiempoUltimo:Int=0
    lateinit var optionTiempo :CalculatTiempo

    lateinit var nombre:TextView;

    lateinit var turno_Actual:TextView;

    lateinit var ultimo_turno:TextView;

    lateinit var tiempo_estimada:TextView;

    lateinit var  filaRemota:Tiendaremota


    lateinit var mainHandler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_tienda)

        var info =intent.extras

        tienda = info?.getSerializable("tienda")  as? Tienda


        tienda?.id_tienda

        llamarApi()

        val informacionTiendaActivity=this

        mainHandler= object :Handler(Looper.getMainLooper()){

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                    var handler=HandlerComunicacionRemota(informacionTiendaActivity)
                    handler.cambiarUI(msg)

            }


        }










        nombre=findViewById<TextView>(R.id.nombreTiendaInfo)
        nombre.text = tienda?.nombre

        turno_Actual=findViewById<TextView>(R.id.turnoActualTiendaInfo)
        turno_Actual.text = filaRemota.turno_actual.toString()

        ultimo_turno=findViewById<TextView>(R.id.ultimoTurnoTiendaInfo)

        ultimo_turno.text = filaRemota.ultimo_turno.toString()

        tiempo_estimada=findViewById<TextView>(R.id.tiempoEstimaTiendaInfo)

        formatoTiempo()

        optionTiempo =CalculatTiempo(tiempoUltimo)
    }






    fun llamarApi(){

        var remota=ApiTiendaRemota(this);

        remota.crear(tienda?.id_tienda.toString())

        remota.start()

        remota.join()


        filaRemota=remota.tiendaremota

        tiempoUltimo=filaRemota.tiempomedia

    }



    fun formatoTiempo(){

        val hora=tiempoUltimo/60

        val minutos=tiempoUltimo%60

        tiempo_estimada.text =hora.toString() +" H "+ "  "+minutos.toString()+"Minutos"
    }


      // elegir rango de tiempo
    fun EligeTiempo(view : View){

      optionTiempo.tiempo(this,tiempo_estimada)

    }

    fun incorporar(view :View){

        filaRemota.tiempomedia=optionTiempo.tiempoUltimo

        var remotaEnviar=RemotaEnviar(this,filaRemota)

        remotaEnviar.start()




    }






}

