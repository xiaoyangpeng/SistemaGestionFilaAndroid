package com.example.queue.notificacion;

import android.app.NotificationManager;
import android.content.Context;
import android.app.Notification;
import android.app.NotificationChannel;

import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.queue.R;

public class TocaSuTurno {

    public static void tocaturno(Context context){

    NotificationManager   notificationManager = (NotificationManager)context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) ;
            String id="01";

          String name="nombre";

        Notification   notification = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);

                notificationManager.createNotificationChannel(mChannel);
                notification = new Notification.Builder(context)
                        .setChannelId(id)
                        .setContentTitle("Toca su turno")
                        .setContentText("Ya puedes entrar que toca su turno")
                        .setSmallIcon(R.mipmap.ic_launcher).build();
            } else {
                NotificationCompat.Builder notificationBuilder  = new NotificationCompat.Builder(context)
                        .setContentTitle("Toca su turno")
                        .setContentText("Ya puedes entrar que toca su turno")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setOngoing(true);
                notification=notificationBuilder.build();
            }

            notificationManager.notify(111123,notification);



    }
}
