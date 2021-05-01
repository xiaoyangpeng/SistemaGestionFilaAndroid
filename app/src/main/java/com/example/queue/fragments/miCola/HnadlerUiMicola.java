package com.example.queue.fragments.miCola;

import android.os.Message;

public class HnadlerUiMicola {

    private  MicolaFragment micolaFragment;


    public HnadlerUiMicola(MicolaFragment micolaFragment) {
        this.micolaFragment = micolaFragment;
    }


    public void CambiarIU(Message msg){


        switch (msg.what){

            case -1:

                micolaFragment.serVisibleoNO(true);

                break;


        }



    }
}
