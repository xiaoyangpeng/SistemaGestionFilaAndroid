package com.example.queue.incorporarremota

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Message
import com.example.queue.MainActivity

class HandlerComunicacionRemota(var informacionTiendaActivity: InformacionTiendaActivity) {

    fun cambiarUI(msg :Message){

        when(msg.what){

            1 ->  dialogoRemota("Existo","Ya has incorporado en la fila")

            2->  dialogoRemota("Fail","Ya estas dentro de una fila no puedes incorporar en otra")

        }

    }

    private fun dialogoRemota(text:String, mensaje :String){

        var aceptar=DialogInterface.OnClickListener({
            _,_  ->

            var i= Intent(informacionTiendaActivity,MainActivity::class.java)

            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            informacionTiendaActivity.startActivity(i)

        })

        AlertDialog.Builder(informacionTiendaActivity)
                .setTitle(text)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",aceptar)
                .show()


    }

}