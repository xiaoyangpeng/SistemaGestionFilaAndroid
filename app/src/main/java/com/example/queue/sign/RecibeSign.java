package com.example.queue.sign;

import android.content.Intent;
import android.os.Message;

import com.example.queue.R;
import com.example.queue.activarCuenta.ActivarCuentaActivity;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RecibeSign extends Thread {

    private Socket socketSign;
    private SignActivity signActivity;
    private DataInputStream entrada;
    private  String email;

    public RecibeSign(Socket socketSign,SignActivity signActivity,String email){

       this.socketSign=socketSign;
       this.signActivity=signActivity;
        this.email=email;

    }


    @Override
    public void run() {

        try {

            entrada = new DataInputStream(socketSign.getInputStream());

            Boolean existeUsuario = entrada.readInt()==1;


            if( existeUsuario){

                Message msg = new Message();

                msg.what=1;

                signActivity.mainHandler.sendMessage(msg);

            }else{

                Intent i=new Intent(signActivity, ActivarCuentaActivity.class);

                i.putExtra(signActivity.getResources().getString(R.string.email),email);

                signActivity.startActivity(i);

                signActivity.finish();
            }

            socketSign.close();

         } catch ( IOException e) {
            e.printStackTrace();

    }



        }
}
