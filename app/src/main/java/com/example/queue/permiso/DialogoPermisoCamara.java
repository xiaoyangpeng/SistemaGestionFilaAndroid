package com.example.queue.permiso;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.queue.MainActivity;

public class DialogoPermisoCamara {


    MainActivity mainActivity;

    public DialogoPermisoCamara(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void dialogoPermiso(){

        new AlertDialog.Builder(mainActivity)
                .setMessage("Para escannear el código QR \n" +
                        "[Toca Ajuestes>Permisos>activa Cámara]")
                .setPositiveButton("Ajuste", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //引导用户至设置页手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",mainActivity.getApplicationContext().getPackageName(), null);
                        intent.setData(uri);
                        mainActivity.startActivity(intent);

                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //引导用户手动授权，权限请求失败
                Toast.makeText(mainActivity, "Error de permiso", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(mainActivity, "Error de permiso", Toast.LENGTH_SHORT).show();
            }
        } )    .show();


    }




}
