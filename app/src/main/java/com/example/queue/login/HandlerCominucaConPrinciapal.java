package com.example.queue.login;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import androidx.appcompat.app.AlertDialog;

import com.example.queue.MainActivity;
import com.example.queue.R;
import com.example.queue.activarCuenta.ActivarCuentaActivity;
import com.example.queue.activarCuenta.VuelveMandarCorreo;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.GuardarDatoAcceso;
import com.example.queue.probarConexionInternet.Fallaconexion;
import com.example.queue.valorFijo.DatoAcceso;


public class HandlerCominucaConPrinciapal  {

    private LoginActivity loginActivity;

   public HandlerCominucaConPrinciapal(   LoginActivity loginActivity){

       this.loginActivity = loginActivity;

    }

    /*      // con este metodo coge datos que ha pasado desde subHilo
                                Bundle bundle = msg.getData();
                                // en suHilo utilizar  para poner datos

                                    Bundle bundle = new Bundle();
                                    bundle.putInt("ok", 5);

                                      msg.setData(bundle);
                                 Message msg = new Message();
                         */

   public void CambiarIU(Message msg){

       // -1 no puede accerder
       // 1 exito
       // 2 no esta activado
       // 3 ya esta en liena

       // 4 falla conexion


       loginActivity.activarODesactivaView(true);

       switch (msg.what) {

           case -1:
               // cuanto en clase recibeLogin detecta usuario o contrasena incorrecto salta un dialogo

               dialogoError("Usuario o Contraseña Incorrecto");

               break;
           case 1:

               // una vez que el usuario entreado con exito guadar
               // su email y contrasen en sharedPreference
               Bundle bundle = msg.getData();

               GuardarDatoAcceso guardarDatoAcceso=new GuardarDatoAcceso(loginActivity);

               guardarDatoAcceso.guardarDatosAcceso(bundle.getString(DatoAcceso.EMALI),bundle.getString(DatoAcceso.CONTRSENA));

               // termiar el activity de login
               // porque ya no necesita mas
               loginActivity.startActivity(new Intent(loginActivity.getBaseContext(), MainActivity.class));

               loginActivity.finish();

               break;
           case 2:

                dialogoActivarOcancelar();

               break;

           case 3:
               dialogoError("El usuario ya esta en linea");
               break;

           case 4:

               Fallaconexion.fallaconexionServidor(loginActivity);

               break;
       }

   }

    public void dialogoError(String texto){

        new AlertDialog.Builder(loginActivity)
                .setTitle("Login Fail")
                .setMessage(texto)
                .setPositiveButton("Aceptar", null)
                .show();

    }

    public void dialogoActivarOcancelar(){



       AlertDialog.Builder dialogo=new AlertDialog.Builder(loginActivity);


        DialogInterface.OnClickListener acvitarCuenta=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                VuelveMandarCorreo vuelveMandarCorreo=new VuelveMandarCorreo(loginActivity.getUsuario().getText().toString());
                vuelveMandarCorreo.start();// pedir al servidor que le manda un correo con codigo de activacion

                Intent i=new Intent(loginActivity, ActivarCuentaActivity.class);

                i.putExtra(loginActivity.getResources().getString(R.string.email), loginActivity.getUsuario().getText().toString());

                loginActivity.startActivity(i);



            }
        };


        dialogo.setTitle("Login Fail");

        dialogo.setMessage("El usuario no esta activado");

        dialogo.setPositiveButton("Activar Cuenta", acvitarCuenta);

        dialogo.setNegativeButton("Cancelar",null);

        dialogo.show();

    }

}
