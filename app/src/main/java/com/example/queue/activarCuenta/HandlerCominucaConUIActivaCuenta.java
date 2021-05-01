package com.example.queue.activarCuenta;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;

import androidx.appcompat.app.AlertDialog;

public class HandlerCominucaConUIActivaCuenta {

private ActivarCuentaActivity activarCuentaActivity;

    public HandlerCominucaConUIActivaCuenta(ActivarCuentaActivity activarCuentaActivity) {
        this.activarCuentaActivity = activarCuentaActivity;
    }

    // si codigo de activacion esta bien pone 1
    //sino pone 2

    // 3 es para cambiar el cuenta atras en el activity

    public void CambiarIU(Message msg){

        if(msg.what==2){


            dialogo("Código inválido","Activate Fail" );

            activarCuentaActivity.activarODesactivarView(true);

        }else if(msg.what==1){

            dialogo("Ya has activado su cuenta","Activate succeed" );

        }else if(msg.what==3){

            Bundle bundle = msg.getData();
            int tiempo=bundle.getInt("tiempo");

            activarCuentaActivity.getTextTiempoActivaCuenta().setText(String.valueOf(tiempo));

            if(tiempo==0){

                activarCuentaActivity.getVuelveMandarEamil().setEnabled(true);
                activarCuentaActivity.getVuelveMandarEamil().setAlpha(1);
            }


        }

    }


    public void dialogo(String texto, final String titulo){

        AlertDialog.Builder builder= new AlertDialog.Builder(activarCuentaActivity);
        builder.setTitle(titulo);
        builder.setMessage(texto);
        builder .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(titulo.equals("Activate succeed")){

                    activarCuentaActivity.finish();
                }

            }
        });
        builder .show();


    }

}
