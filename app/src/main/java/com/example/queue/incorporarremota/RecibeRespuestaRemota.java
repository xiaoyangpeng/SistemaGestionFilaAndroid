package com.example.queue.incorporarremota;

import android.os.Message;

import com.example.queue.fragments.miCola.CuentaAtrasTurno;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.incorporarremota.apiTienda.ApiIncorporarRemota;
import com.example.queue.incorporarremota.apiTienda.Tiendaremota;
import com.example.queue.modificarUiFila.ModifciarUIMicola;
import com.example.queue.valorFijo.Ids;

public class RecibeRespuestaRemota extends  Thread{

    private InformacionTiendaActivity informacionTiendaActivity;

    private Tiendaremota tiendaremota;

    private boolean respuesta;


    public RecibeRespuestaRemota(Tiendaremota tiendaremota,InformacionTiendaActivity informacionTiendaActivity) {

        this.informacionTiendaActivity=informacionTiendaActivity;

        this.tiendaremota=tiendaremota;
    }

    /**
     *
     * @preturn
     * 1 incorporado
     * 2 ya esta dentro de la cola
     */
    @Override
    public void run() {

        ApiIncorporarRemota remota=new ApiIncorporarRemota(informacionTiendaActivity);

        remota.crear(tiendaremota.getTiempomedia(),tiendaremota.getId_cola());

        remota.start();

        try {
            remota.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * repuesta true: ya esta dentro de una fila
         * false aun no esta
         */
        respuesta=remota.getRespuesta();

        Message msg=new Message();

                if(!respuesta){

                    Message msg2=new Message();

                    msg2.what=1; // ya ha incorporado en fila

                    informacionTiendaActivity.mainHandler.sendMessage(msg2);

                    MicolaFragment.mViewModel.setPuedeAnadirPorducto(true);

                    Ids.yaestaEncola=true;

                    CuentaAtrasTurno cuentaAtrasTurno = new CuentaAtrasTurno(MicolaFragment.mViewModel);

                    ModifciarUIMicola modifciarUIMicola = new ModifciarUIMicola(cuentaAtrasTurno, informacionTiendaActivity);

                    modifciarUIMicola.modificar();

                }else{
                    msg.what=2;

                }

            informacionTiendaActivity.mainHandler.sendMessage(msg);


    }
}
