package com.example.queue.probarConexionInternet;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Fallaconexion {


    public static void fallaconexionServidor(final Activity activity){
        
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        builder.setTitle("Falla ");
        builder.setMessage("Falla conexión con el servidor intentas mas tarde");
        builder .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                android.os.Process.killProcess(android.os.Process.myPid());

               // System.exit(0);

               // activity.finish();

            }
        });

        builder .show();

    }



}
