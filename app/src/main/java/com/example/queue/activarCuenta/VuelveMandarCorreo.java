package com.example.queue.activarCuenta;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class VuelveMandarCorreo extends  Thread{

    private Socket socketActiva;
    private String email;

    public VuelveMandarCorreo(String email) {

        this.email = email;
    }

    @Override
    public void run() {
        DataOutputStream out=null;

        try {
            socketActiva=new Socket("192.168.31.146",8888);

            out=new DataOutputStream(socketActiva.getOutputStream());


            // primero manda operacion de cambiarcodido al servidor
            out.writeInt("cambiarcodido".hashCode());

            // escribe su email
            out.writeInt(email.length());
            out.write(email.getBytes());

            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
