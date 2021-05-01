package com.example.queue.informaciontienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.bigkoo.pickerview.OptionsPickerView
import com.bigkoo.pickerview.TimePickerView
import com.example.queue.R
import com.example.queue.fragments.incorporar.incorporarRemota.apitienda.Tienda
import kotlinx.android.synthetic.main.activity_informacion_tienda.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InformacionTiendaActivity : AppCompatActivity() {



     var tienda:Tienda?=null

    lateinit var utlmimoTurnotienda:TextView;

    var tiempoUltimo:Int=7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_tienda)

        var info =intent.extras

        tienda = info?.getSerializable("tienda")  as? Tienda

        utlmimoTurnotienda=findViewById<TextView>(R.id.ultimoTurnoTiendaInfo)


    }


      // elegir rango de tiempo
    fun EligeTiempo(view : View){

        val optionTiempo =CalculatTiempo()

      optionTiempo.tiempo(tiempoUltimo,this,utlmimoTurnotienda)


    }







}

