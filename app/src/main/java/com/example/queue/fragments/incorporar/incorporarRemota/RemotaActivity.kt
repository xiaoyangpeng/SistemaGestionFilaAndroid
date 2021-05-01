package com.example.queue.fragments.incorporar.incorporarRemota

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.queue.R

class RemotaActivity : AppCompatActivity() {



    lateinit  var searchViewBuscaTienda: SearchView


    lateinit var nohayTiendaText :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remota)


        nohayTiendaText= findViewById<TextView>(R.id.nohaytienda)

        var actionar: ActionBar? =this.supportActionBar

        actionar?.title = "Busca Tienda"

        actionar?.setDisplayHomeAsUpEnabled(true)

    }

    // añadir menu al toolbar
    // y añadir el SearchView en el toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_lista_producto, menu)

        var item:MenuItem?=menu?.findItem(R.id.backUp)

        // añadir SarchView en toolbar
        searchViewBuscaTienda = item?.actionView as SearchView

        searchViewBuscaTienda.queryHint="Nombre de tienda o calle"


        var accionuscaTienda :SarchViewBuscaTienda=SarchViewBuscaTienda(this,searchViewBuscaTienda)

        //añadir accion al SearchView
        searchViewBuscaTienda.setOnQueryTextListener(accionuscaTienda)


        return true
    }


    // accion de flecha vuelve atras
    override fun onSupportNavigateUp():Boolean{

        finish();//关闭activity

        return super.onSupportNavigateUp()

    }


}