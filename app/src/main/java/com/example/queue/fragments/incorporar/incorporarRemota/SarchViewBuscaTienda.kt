package com.example.queue.fragments.incorporar.incorporarRemota

import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queue.R
import com.example.queue.fragments.incorporar.incorporarRemota.apitienda.ApiCrearTienda
import com.example.queue.fragments.incorporar.incorporarRemota.apitienda.Tienda
import com.example.queue.fragments.incorporar.incorporarRemota.vistas.MiAdaptadorTienda

class SarchViewBuscaTienda(var remotaActivity: RemotaActivity, var buscatienda: SearchView) :SearchView.OnQueryTextListener {




    override fun onQueryTextSubmit(query: String?): Boolean {

        //此处添加查询开始后的具体时间和方法

        buscatienda.clearFocus() //可以收起键盘 // recoge teclado

        var buscaTienda : ApiCrearTienda = ApiCrearTienda(remotaActivity)

        buscaTienda.crear(query)

        buscaTienda.start()

        buscaTienda.join() // esperar respuesta


        val resultadoTienda:ArrayList<Tienda>?=buscaTienda.tiendaArray.tiendaArray


        // solo ejecuta cuando no es nulo
        resultadoTienda?.let {

            var lista: RecyclerView? = remotaActivity.findViewById(R.id.lista_tienda) as RecyclerView
            lista?.isNestedScrollingEnabled = false

            lista?.setHasFixedSize(true)

            lista?.isFocusable = false

            lista?.setHasFixedSize(true)

            val rl: RecyclerView.LayoutManager = LinearLayoutManager(remotaActivity)

            lista?.layoutManager = rl


            // para valorar si existe tienda hay que mirarar por su tamnño
            // porque no va devolver nu arraylist null
            // si no un arraylist ya iniciado pero vacio

            if(it.size==0){
                // cuando no existe la tienda

                remotaActivity.nohayTiendaText.text="No existe este establecimiento "

                lista?.adapter=null

                 }else{

                  remotaActivity.nohayTiendaText.text=""

                    val adaptador= MiAdaptadorTienda(it)

                    lista?.adapter=adaptador

                     }
        }


        return  true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}