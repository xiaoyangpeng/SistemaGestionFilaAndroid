package com.example.queue.sign;

import android.os.Message;
import android.widget.EditText;
import android.widget.TextView;

import com.example.queue.R;
import com.example.queue.sign.apisigin.Usuario;


public class ComprobarPreviaDatosSign {


    private EditText nombre;

    private EditText email;

    private EditText contrasena;

    private  EditText contrasenaRepite;

    private EditText textTelefono;

    private SignActivity signActivity;

    private TextView textsexo;

    private Usuario usuario;

    private String sexo;

    private  Message msg;

   public ComprobarPreviaDatosSign(SignActivity signActivity){

        this.signActivity=signActivity;

        textsexo=signActivity.getSexo();

        msg = new Message();

        email=signActivity.getEmail();

        contrasena=signActivity.getContrasena();

        contrasenaRepite=signActivity.getContrasenaRepite();

        textTelefono =(EditText) signActivity.findViewById(R.id.telefono);

        nombre=(EditText)signActivity.findViewById(R.id.nombreSign);

        comprobar();
   }


    public void comprobar(){

       if (probarVacio()) {

             if (probarContrasena() && probarTelefono()) {

                creaObjetoUsuario();

              msg.what=7;

             signActivity.mainHandler.sendMessage(msg);

           EnviaSign enviaSign=new EnviaSign(signActivity,usuario);

           enviaSign.start();

           }
       }
    }
// 1falla email
    // 2 falla repite contrasena
    // 3 falla telefono
    //4 vacio email
    // 5 vacio contrasena
    // 6 contrasena no nay 6 caracter
    // 7 congelar pantalla
    // 8 nombre vacio

   private boolean probarVacio(){

       if(email.getText().toString().equals("")){

           msg.what = 4;

           signActivity.mainHandler.sendMessage(msg);
           return false;

       }else if(contrasena.getText().toString().equals("")){

           msg.what = 5;

           signActivity.mainHandler.sendMessage(msg);
           return false;
       }else if(!probarFormatoEmail()){
           msg.what = 4;
           signActivity.mainHandler.sendMessage(msg);
           return  false;
       }else if(nombre.getText().toString().equals("")){

           msg.what = 8;

           signActivity.mainHandler.sendMessage(msg);
           return false;
       }


        return true;
    }



    private boolean probarFormatoEmail(){

        if(email.getText().toString().matches("\\w+@\\w+\\.\\w+")) {
            return true;

        }
        else {
            return false;

        }

    }

    // 1falla email
    // 2 falla repite contrasena
    // 3 falla telefono
    //4 vacio email
    // 5 vacio contrasena
    // 6 contrasena no nay 6 caracter
    // 7 congelar pantalla
    // 8 nombre vacio
    private boolean probarContrasena(){


       if(!contrasenaRepite.getText().toString().equals(contrasena.getText().toString()))
       {
           msg.what = 2;

           signActivity.mainHandler.sendMessage(msg);

           return false;
       }else if(contrasena.getText().toString().length()<6){

           msg.what = 6;

           signActivity.mainHandler.sendMessage(msg);

           return false;

       }

       return  true;
    }



    // 1falla email
    // 2 falla repite contrasena
    // 3 falla telefono
    //4 vacio email
    // 5 vacio contrasena
    // 6 contrasena no nay 6 caracter
    private boolean probarTelefono(){

       try {


          String telefono=textTelefono.getText().toString();

          if(!telefono.equals("")) {

              if (telefono.length() != 9) {

                  msg.what = 3;

                  signActivity.mainHandler.sendMessage(msg);

                  return false;
              }

              Integer.parseInt(telefono);
          }
       }catch (Exception e){

           msg.what = 3;

           signActivity.mainHandler.sendMessage(msg);

           return false;
       }
       return  true;
    }

    private void transformarSexo(){

        if(textsexo.getText().toString().equals("Mujer")){

            this.sexo="M";

        }else if (textsexo.getText().toString().equals("Hombre")){

            this.sexo="H";

        }else{
            this.sexo="N";
        }



    }



    public void creaObjetoUsuario(){


       transformarSexo();
       this.usuario=new Usuario();

       usuario.contrasena=contrasena.getText().toString().hashCode();

       usuario.email=email.getText().toString();

       usuario.nombre=nombre.getText().toString();

       usuario.sexo=this.sexo;

       String tel=textTelefono.getText().toString();

       if(!tel.equals("")) {

           usuario.telefono = Integer.parseInt(tel);
       }

    }




}
