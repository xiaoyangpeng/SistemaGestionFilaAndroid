package com.example.queue.fragments.incorporar.incorporarRemota.vistas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.queue.R
import com.example.queue.fragments.incorporar.incorporarRemota.apitienda.Tienda
import com.example.queue.informaciontienda.InformacionTiendaActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins.onSubscribe
import java.util.concurrent.TimeUnit

class MiAdaptadorTienda(var tiendaArray: ArrayList<Tienda>) : RecyclerView.Adapter<MiAdaptadorTienda.MiViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {

        val inlador=LayoutInflater.from(parent.context)

        val v: View=inlador.inflate(R.layout.lista_tienda, parent, false)

        val mv=MiViewHolder(v)

        return mv

    }


    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {

             holder.nombreTienda.setText(tiendaArray.get(position).nombre)
             holder.calleTienda.setText(tiendaArray.get(position).direccion)
             holder.categoriaTienda.setText(tiendaArray.get(position).nombre_categoria)
             holder.anadirPulsacion(tiendaArray.get(position))

    }

    override fun getItemCount(): Int {
        return tiendaArray.size
    }




    class MiViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombreTienda: TextView
        var calleTienda: TextView

        var categoriaTienda: TextView

        var myitemView: View?=null

        init {

            nombreTienda = itemView.findViewById(R.id.nomreTienda) as TextView
            calleTienda = itemView.findViewById(R.id.calleTienda) as TextView
            categoriaTienda = itemView.findViewById(R.id.categoriaTienda) as TextView

            myitemView=itemView
        }

        fun anadirPulsacion(tienda :Tienda){

            RxView.clicks(myitemView!!)
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .subscribe(

                         {

                             var info:Bundle= Bundle();
                             info.putSerializable("tienda",tienda)
                             var i:Intent=Intent(myitemView?.context,InformacionTiendaActivity::class.java)
                             i.putExtras(info)

                             myitemView?.context?.startActivity(i)


                         }



                    )





        }



    }




}




