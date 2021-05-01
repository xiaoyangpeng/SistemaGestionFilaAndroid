package com.example.queue;

import android.os.Message;

import com.example.queue.probarConexionInternet.Fallaconexion;

public class HanlderComunicacionUIPrincipal {

    private MainActivity mainActivity;

    public HanlderComunicacionUIPrincipal(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void CambiarIU(Message msg){


        switch (msg.what){


            case 4:

                Fallaconexion.fallaconexionServidor(mainActivity);
                break;
        }


    }

}
