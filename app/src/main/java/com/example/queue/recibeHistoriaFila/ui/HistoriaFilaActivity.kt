package com.example.queue.recibeHistoriaFila.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.queue.R
import com.example.queue.recibeHistoriaFila.api.ApiHistoriaFila
import kotlinx.android.synthetic.main.activity_historia_fila.*

class HistoriaFilaActivity : AppCompatActivity() {


    private lateinit var historiaView:ConstraintLayout
    private lateinit var      progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historia_fila)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

         historiaView=findViewById<ConstraintLayout>(R.id.viewHistoria)

        progressBar=findViewById(R.id.historiaprogressBar)

        serVisibleProgessBar(true)

        val initApi=ApiHistoriaFila(this)

        initApi.crear()

        initApi.start()

        initApi.join()

        serVisibleProgessBar(false)

        var arrayHistoriaFila=initApi.historiaEnFilas

        if(arrayHistoriaFila.size!=0) {

            var adapter = AdaptadorHistoriaFila(this, arrayHistoriaFila)

            adaptadorHistoria.adapter=adapter

        }else{

            historiaAunNohayFila.visibility=View.VISIBLE
        }
    }


   private fun serVisibleProgessBar(visible: Boolean) {
        if (visible) {
            historiaView.setAlpha(0.3.toFloat())
            historiaprogressBar.setVisibility(View.VISIBLE)
        } else {

            historiaView.setAlpha(1f)
            historiaprogressBar.setVisibility(View.INVISIBLE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}