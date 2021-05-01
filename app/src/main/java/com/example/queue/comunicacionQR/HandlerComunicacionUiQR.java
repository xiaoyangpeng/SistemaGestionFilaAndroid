package com.example.queue.comunicacionQR;

import android.content.DialogInterface;
import android.os.Message;

import androidx.appcompat.app.AlertDialog;

import com.example.queue.probarConexionInternet.Fallaconexion;

public class HandlerComunicacionUiQR {


    private QRActivity qrActivity;

    public HandlerComunicacionUiQR(QRActivity qrActivity) {
        this.qrActivity = qrActivity;
    }

    /**
     *
     * @param -1: error QR
     * 1 QR correcto
     * 4 fallo conexion servidor
     * 2 ya esta dentro de la cola
     */
    public void cambiarUI(Message msg){


        switch (msg.what){
            case -1:

               dialogoQR("QR Fail","No existe este QR");

                break;

            case 1:
                // desactivar barra de proceso
                qrActivity.activarDesactivarView(false);

                dialogoQR("Existo","Ya has incorporado en la fila");

                break;
            case 2 :

                dialogoQR("Fail","Ya estas dentro de una fila no puedes incorporar en otra");

                break;
            case 4:

                Fallaconexion.fallaconexionServidor(qrActivity);
                break;

        }
    }


    private void dialogoQR(String text,String mensaje){

        DialogInterface.OnClickListener acepatr=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                qrActivity.finish();
            }
        };

        new AlertDialog.Builder(qrActivity)
                .setTitle(text)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", acepatr)
                .show();

    }


}
