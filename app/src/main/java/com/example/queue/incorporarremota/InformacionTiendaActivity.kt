package com.example.queue.incorporarremota

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.queue.R
import com.example.queue.fragments.incorporar.incorporarRemota.apitienda.Tienda
import com.example.queue.incorporarremota.apiTienda.ApiTiendaRemota
import com.example.queue.incorporarremota.apiTienda.Tiendaremota
import java.io.File

class InformacionTiendaActivity : AppCompatActivity() {



     var tienda:Tienda?=null


    var tiempoUltimo:Int=0
    lateinit var optionTiempo :CalculatTiempo

    lateinit var nombre:TextView;

    lateinit var turno_Actual:TextView;

    lateinit var ultimo_turno:TextView;

    lateinit var tiempo_estimada:TextView;

        var  filaRemota:Tiendaremota?=null


    lateinit var mainHandler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_tienda)




        // añadir en el toolbar la flecha hacia atras

        // añadir en el toolbar la flecha hacia atras
        val actionBar = this.supportActionBar
        actionBar!!.title = "Incorporación remota"
        actionBar.setDisplayHomeAsUpEnabled(true)


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

        if(filaRemota!=null) {

            nombre = findViewById<TextView>(R.id.nombreTiendaInfo)

            nombre.text = tienda?.nombre

            turno_Actual = findViewById<TextView>(R.id.turnoActualTiendaInfo)

            turno_Actual.text = filaRemota?.turno_actual.toString()

            ultimo_turno = findViewById<TextView>(R.id.ultimoTurnoTiendaInfo)

            ultimo_turno.text = filaRemota?.ultimo_turno.toString()

            tiempo_estimada = findViewById<TextView>(R.id.tiempoEstimaTiendaInfo)

            formatoTiempo()

            optionTiempo = CalculatTiempo(tiempoUltimo)

        }
    }






    fun llamarApi(){

        var remota=ApiTiendaRemota(this);

        remota.crear(tienda?.id_tienda.toString())

        remota.start()

        remota.join()

        val filaRemotaAux=remota.tiendaremota

        if(filaRemotaAux!=null){

            filaRemota=filaRemotaAux

            tiempoUltimo=filaRemota!!.tiempomedia

        }else{

            dialogNoexisteFila()

        }


    }
    fun dialogNoexisteFila() {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("La fila de dicha Tienda no esta activado")

        builder.setPositiveButton(
                "Aceptar",
                DialogInterface.OnClickListener {

                    _, _ ->
                        finish()
                }
        ).setCancelable(false)

        builder.show()
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

        filaRemota?.tiempomedia=optionTiempo.tiempoUltimo

        var remotaEnviar=RecibeRespuestaRemota(filaRemota,this
        )

        remotaEnviar.start()




    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val id = item.itemId // cuando pulsa los opciones del menu coge su id

        if (id == android.R.id.home) { // en caso id es flecha es decir vuelve atras
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}

