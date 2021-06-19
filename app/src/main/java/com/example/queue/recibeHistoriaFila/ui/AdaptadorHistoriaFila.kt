package com.example.queue.recibeHistoriaFila.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.queue.R
import com.example.queue.recibeHistoriaFila.api.HistoriaEnFila
import kotlinx.android.synthetic.main.adaptador_historia_fila.view.*

class AdaptadorHistoriaFila(
        private val historiaFilaActivity: HistoriaFilaActivity,
        private val historiaFila: ArrayList<HistoriaEnFila>

)
 :
BaseAdapter(){


    lateinit var cell: View


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        val historiaFilaItem=historiaFila[position]

        val inf=historiaFilaActivity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        cell=inf.inflate(R.layout.adaptador_historia_fila,null)

        cell.historiaFecha.text=historiaFilaItem.fecha

        cell.historiaNombre.text=historiaFilaItem.nombre

        cell.historiaEntrada.text=historiaFilaItem.hora_entrada

        cell.historiaTerminada.text=historiaFilaItem.hora_terminada


        return cell

    }




    override fun getCount(): Int {
            return  historiaFila.size
    }

    override fun getItem(position: Int): Any {
        return historiaFila[position]
    }

    override fun getItemId(position: Int): Long {

        return  position.toLong()
    }


}