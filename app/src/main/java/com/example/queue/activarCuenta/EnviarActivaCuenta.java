package com.example.queue.activarCuenta;

import com.example.queue.valorFijo.ConexionUrl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EnviarActivaCuenta extends  Thread{


    private ActivarCuentaActivity activarCuentaActivity;
    private  Socket socketActiva;

    public EnviarActivaCuenta(ActivarCuentaActivity activarCuentaActivity){

        this.activarCuentaActivity=activarCuentaActivity;

    }

    @Override
    public void run() {

        DataOutputStream out=null;

        try {
            socketActiva=new Socket(ConexionUrl.Companion.getIP(),ConexionUrl.Companion.getPORT());

            out=new DataOutputStream(socketActiva.getOutputStream());


            // arranca hilo de escuchar socket
            RecibeActivaCuenta recibeActivaCuenta=new RecibeActivaCuenta(activarCuentaActivity,socketActiva);

            recibeActivaCuenta.start();


            // primero dice al servidor que operacion va realizar
            out.writeInt("activarcuenta".hashCode());

            // enviar email
            String email=activarCuentaActivity.getEmail();
            out.writeInt(email.length());
            out.write(email.getBytes());


            // enviar codigo de activacion que ha puesto el usuario
            String codigo=activarCuentaActivity.getTextCodigoActivacion().getText().toString();
            out.writeInt(codigo.length());
            out.write(codigo.getBytes());

            out.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
