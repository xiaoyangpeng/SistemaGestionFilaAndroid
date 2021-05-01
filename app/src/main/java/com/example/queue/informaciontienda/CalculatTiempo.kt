package com.example.queue.informaciontienda

import android.content.Context
import android.widget.TextView
import com.bigkoo.pickerview.OptionsPickerView

class CalculatTiempo {


    fun tiempo( tiempoUltimo:Int,context:Context,timepoEligido:TextView){

        var hora:ArrayList<Int> = ArrayList()

        var tiempoEnhora=tiempoUltimo/60

        hora.add(tiempoEnhora)
        hora.add(tiempoEnhora+1)
        hora.add(tiempoEnhora+2)


        var tiempoMinuto=(((((tiempoUltimo%60)/5.0))).toInt()+1)*5


        var minutos:ArrayList<ArrayList<Int>> = ArrayList()

        var array1:ArrayList<Int> = ArrayList()

        for(index in tiempoMinuto..60 step 5){

            array1.add(index)

        }


        var array2:ArrayList<Int> = ArrayList()

        for(index in 0..60 step 5){

            array2.add(index)


        }
        minutos.add(array1)
        minutos.add(array2)
        minutos.add(array2)


        var a:ArrayList<Int> = ArrayList()

        a.add(tiempoUltimo)
        a.add(tiempoUltimo+1)
        a.add(tiempoUltimo+2)

        var optcionView= OptionsPickerView.Builder(context, OptionsPickerView.OnOptionsSelectListener({

            options1, options2, _,_
            ->

            timepoEligido.setText(hora.get(options1).toString() + minutos[options1].get(options2).toString())

        }))

                .build()
        optcionView.setPicker(hora,minutos)

        optcionView.show()

    }


}