package com.example.queue.fragments.miCola;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MicolaViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<Integer> timepoEstimado;

    private  MutableLiveData<String> turnoActual;

    private MutableLiveData<String> pintaTiempo;

    private MutableLiveData<String> miTurno;


    private MutableLiveData<String> turnosQueda;

    private MutableLiveData<Boolean> puedeAnadirPorducto;


    public MicolaViewModel(){


        if(turnoActual==null){

            turnoActual=new MutableLiveData<>();

            turnoActual.setValue("");
        }

        if(miTurno==null){

            miTurno=new MutableLiveData<String >();

            miTurno.setValue("");

        }

        if(timepoEstimado==null){

            timepoEstimado=new MutableLiveData<>();

            timepoEstimado.setValue(0);
        }


        if(pintaTiempo==null){

            pintaTiempo=new MutableLiveData<>();

            pintaTiempo.setValue("");
        }


        if(turnosQueda==null){

            turnosQueda=new MutableLiveData<>();

           turnosQueda.setValue("");
        }


        if(puedeAnadirPorducto==null){

            puedeAnadirPorducto=new MutableLiveData<>();

            puedeAnadirPorducto.setValue(false);
        }

    }

    public MutableLiveData<Integer> getTimepoEstimado() {

        return timepoEstimado;
    }

    public MutableLiveData<String> getTurnoActual() {

        return turnoActual;
    }

    public  void  modificarTurno(String turno){

        turnoActual.postValue(turno);

    }


    public  void modificarTiempo(int tiempo){

        timepoEstimado.postValue(tiempo);

    }

    public MutableLiveData<String> getMiTurno() {
        return miTurno;
    }

    public void setMiTurno(String miTurno) {

        this.miTurno.postValue(miTurno);
    }


    public MutableLiveData<String> getTurnosQueda() {
        return turnosQueda;
    }

    public void setTurnosQueda(String turnosQueda) {
        this.turnosQueda.postValue(turnosQueda);
    }

    public MutableLiveData<String> getPintaTiempo() {

        String hora=String.valueOf(getTimepoEstimado().getValue()/3600);
        String minutos=String.valueOf((getTimepoEstimado().getValue()%3600)/60);
        String segundo=String.valueOf((getTimepoEstimado().getValue()%3600)%60);

        if(hora.length()==1){
            hora="0"+hora;
        }

        if(minutos.length()==1){

            minutos="0"+minutos;
        }
        if(segundo.length()==1){

           segundo="0"+segundo;
        }
        pintaTiempo.postValue(hora+":"+minutos+":"+segundo);

        return pintaTiempo;
    }

    public MutableLiveData<Boolean> getPuedeAnadirPorducto() {
        return puedeAnadirPorducto;
    }

    public void setPuedeAnadirPorducto(Boolean puedeAnadirPorducto) {
        this.puedeAnadirPorducto.postValue(puedeAnadirPorducto);
    }
}