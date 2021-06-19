package com.example.queue.comunicacionQR;

import android.os.Message;

import com.example.queue.comunicacionQR.apiIncorporar.ApiIncorporar;
import com.example.queue.modificarUiFila.ModifciarUIMicola;

import com.example.queue.fragments.miCola.CuentaAtrasTurno;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.valorFijo.Ids;

public class RecibeRespuestaQR extends Thread {


    private QRActivity qrActivity;

    private int respuesta;

    private String qr;

    public RecibeRespuestaQR(String qr, QRActivity qrActivity) {
        this.qrActivity = qrActivity;
        this.qr = qr;
    }

    /**
     * @preturn -1: error QR
     * 1 QR correcto
     * 4 fallo conexion servidor
     * 2 ya esta dentro de la cola
     */

    @Override
    public void run() {

        ApiIncorporar incorporar = new ApiIncorporar(qrActivity);

        incorporar.crear(qr);

        incorporar.start();

        try {
            incorporar.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        respuesta = incorporar.getRespuesta();

        Message msg = new Message();

        if (respuesta == 1) {

            Message msg2 = new Message();

            msg2.what = 1; // ya ha incorporado en fila

            qrActivity.mainHandler.sendMessage(msg2);

            MicolaFragment.mViewModel.setPuedeAnadirPorducto(true);

            Ids.yaestaEncola = true;

            CuentaAtrasTurno cuentaAtrasTurno = new CuentaAtrasTurno(MicolaFragment.mViewModel);

            ModifciarUIMicola modifciarUIMicola = new ModifciarUIMicola(cuentaAtrasTurno, qrActivity);

            modifciarUIMicola.modificar();

        }
            else if (respuesta == -1) {
                MicolaFragment.mViewModel.setPuedeAnadirPorducto(true);
                msg.what = -1;

            } else if (respuesta == 2) {
                msg.what = 2;

            }

            qrActivity.mainHandler.sendMessage(msg);


        }
    }
