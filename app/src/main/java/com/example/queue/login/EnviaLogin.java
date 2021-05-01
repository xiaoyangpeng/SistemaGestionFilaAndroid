package com.example.queue.login;

import android.os.Message;
import android.util.Log;

import com.example.queue.valorFijo.ConexionUrl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class EnviaLogin extends Thread  {

    private Socket socketLogin;
    private  String editUsuario;
    private String editContrasena;
    private LoginActivity loginActivity;

    public EnviaLogin(String editusuario, String editcontrasena, LoginActivity loginActivity){

         this.editUsuario=editusuario;
         this.editContrasena=editcontrasena;
         this.loginActivity = loginActivity;
    }

    @Override
    public void run() {

        DataOutputStream out= null;

        try {

            socketLogin=new Socket();
            socketLogin.connect(new InetSocketAddress(ConexionUrl.Companion.getIP(),ConexionUrl.Companion.getPORT()),5000);

            out = new DataOutputStream( socketLogin.getOutputStream());

            //  para que el servido sabe es operacion login
            out.writeInt("login".hashCode());

            String usuarioAux=editUsuario;
            // escribir usuario en byte
            out.writeInt(usuarioAux.length());
            out.write(usuarioAux.getBytes());


            // esctibir contraseña en hash
            int contrasenaAux=editContrasena.hashCode();
            out.writeInt(contrasenaAux);

            RecibeLogin recibeLogin=new RecibeLogin(socketLogin, loginActivity,usuarioAux,editContrasena);
            recibeLogin.start();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketTimeoutException e) {

            // en caso no puede conecetar con el servidor
            //es decir el tiempo de conexion es out
            /*if(!loginActivity.isFinishing()){
            Message msg = new Message();
            msg.what=4;
            loginActivity.mainHandler.sendMessage(msg);}*/


            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


}
