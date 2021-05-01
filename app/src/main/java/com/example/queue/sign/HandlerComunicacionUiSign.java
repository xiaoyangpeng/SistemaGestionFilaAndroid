package com.example.queue.sign;

import android.os.Message;

import androidx.appcompat.app.AlertDialog;

import com.example.queue.probarConexionInternet.Fallaconexion;

public class HandlerComunicacionUiSign {


    private  SignActivity signActivity;

   public  HandlerComunicacionUiSign(SignActivity signActivity){

        this.signActivity=signActivity;

    }
    public void CambiarIU(Message msg){

        // 1falla email
        // 2 falla repite contrasena
        // 3 falla telefono
        //4 vacio email
        // 5 vacio contrasena
        // 6 contrasena no nay 6 caracter
        // 7 congelar pantalla
        // 8 nombre vacio
        switch (msg.what){

            case 1:

                showDialogo("Ya existe este email");

                // en caso todos va bien va cerra el sign activiy
                // pero si ya existe el usuario hay que activar lo otra vez
                signActivity.activarODesactivaView(true);

            break;

            case 2:

               signActivity.getContrasenaRepite().setError("Contraseña no coincide");

                break;

            case 3:

                showDialogo("Telefono debe ser numerico y 9 cifras pero no es obligatorio poner lo");

                break;

            case 4:

                signActivity.getEmail().setError("Email vacio o formato incorrecto");
                break;
            case 5:

                signActivity.getContrasena().setError("Contrasena vacío");

                break;

            case 6:

                signActivity.getContrasena().setError("Contrasena debe tener 6 caracter");

                break;
        case 7:
                // despues de verifica que los datos con corretos
                // manda dato al servidor
                // en este momento desactivar el view entreo
                signActivity.activarODesactivaView(false);

                // en caso todos va bien va cerra el sign activiy
                // pero si ya existe el usuario hay que activar lo otra vez
                break;
            case 8 :

                signActivity.getNombre().setError("Nombre esta vacío");

                break;

            case 9:
                Fallaconexion.fallaconexionServidor(signActivity);
                break;
        }



    }

    private void showDialogo(String texto){

    new AlertDialog.Builder(signActivity)
            .setTitle("Sign Fail")
            .setMessage(texto)
            .setPositiveButton("Aceptar", null)
            .show();


    }


}
