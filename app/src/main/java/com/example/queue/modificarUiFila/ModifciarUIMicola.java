package com.example.queue.modificarUiFila;

import android.app.Activity;

import com.example.queue.fragments.miCola.CuentaAtrasTurno;
import com.example.queue.fragments.miCola.MicolaFragment;
import com.example.queue.informacionCola.InformacionColaJson;
import com.example.queue.informacionCola.RecibeRespuestaInformacionFila;
import com.example.queue.modificardato.ModificaActivity;
import com.example.queue.notificacion.TocaSuTurno;
import com.example.queue.probarConexionInternet.Fallaconexion;
import com.example.queue.valorFijo.HiloModificaUiMiCola;
import com.example.queue.valorFijo.Ids;

public class ModifciarUIMicola {

         private  static String turnoAcutal="";

         private Activity activity;

         private CuentaAtrasTurno cuentaAtras;

     private   RecibeRespuestaInformacionFila informacionFila;

        public ModifciarUIMicola(   CuentaAtrasTurno cuentaAtras,Activity activity){

            this.activity=activity;
            this.cuentaAtras=cuentaAtras;



        }

        public void modificar(){

            while(HiloModificaUiMiCola.arrancar) {

                informacionFila = new RecibeRespuestaInformacionFila(activity);

                informacionFila.crear();

                informacionFila.start();

                try {
                    informacionFila.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                InformacionColaJson inf = informacionFila.getInformacioCola();

                if (inf != null) {

                    // para actualizar el turno y tiempo en la pantalla
                    MicolaFragment.mViewModel.modificarTurno(inf.turnoActual);

                    MicolaFragment.mViewModel.setTurnosQueda(inf.turnoQueda);

                    MicolaFragment.mViewModel.setMiTurno(inf.miturno);
                    Ids.id_cola = inf.id_cola;


                    if (!turnoAcutal.equals(inf.turnoActual)) {
                        turnoAcutal = inf.turnoActual;

                        // cuando recibe respueta del servidor
                        // vulve iniciar el temporizador
                        // porque el tiempo va calcular por servidor

                        MicolaFragment.mViewModel.modificarTiempo(inf.timepo);

                        cuentaAtras.sigueCuentando = false;

                        cuentaAtras.sigueCuentando = true;

                        cuentaAtras.start();

                    }

                    int numTurnoAcutal = Integer.parseInt(inf.turnoActual);

                    int numMiturno = Integer.parseInt(inf.miturno);

                    if (numMiturno <= numTurnoAcutal) {

                        MicolaFragment.mViewModel.modificarTiempo(0);
                        MicolaFragment.mViewModel.setTurnosQueda("Toca su turno");

                        TocaSuTurno.tocaturno(activity);

                        break;

                    }


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                Fallaconexion.fallaconexionServidor(activity);

                        }
                    });

                    break;
                }
            }
        }


}
