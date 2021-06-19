package com.example.queue.sign;

import android.content.Intent;
import android.os.Message;
import com.example.queue.R;
import com.example.queue.activarCuenta.ActivarCuentaActivity;
import com.example.queue.activarCuenta.VuelveMandarCorreo;
import com.example.queue.sign.apisigin.LlamaApiSigin;
import com.example.queue.sign.apisigin.Usuario;
import com.example.queue.sign.apisigin.respuestaSign;

public class EnviaSign extends  Thread {

    private SignActivity signActivity;
    private Usuario usuario;

    public  EnviaSign (SignActivity signActivity,Usuario usuario){

        this.signActivity=signActivity;
        this.usuario=usuario;
    }

    @Override
    public void run() {



        LlamaApiSigin sigin=new LlamaApiSigin(signActivity);

        sigin.crear(usuario);

        sigin.start();

        try {
            sigin.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        recibemensaje(sigin.getRespeusta());


    }

    private void recibemensaje(respuestaSign respuesta)  {

        if( respuesta.existeEamil){

            Message msg = new Message();

            msg.what=1;

            signActivity.mainHandler.sendMessage(msg);

        }else{

            VuelveMandarCorreo mandarCorreo=new VuelveMandarCorreo(respuesta.token);

            mandarCorreo.run();

            Intent i=new Intent(signActivity, ActivarCuentaActivity.class);

            i.putExtra(signActivity.getResources().getString(R.string.email),usuario.email);

            i.putExtra("Token",respuesta.token);

            signActivity.startActivity(i);

            signActivity.finish();
        }

    }
}
