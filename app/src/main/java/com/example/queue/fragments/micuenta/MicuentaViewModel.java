package com.example.queue.fragments.micuenta;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MicuentaViewModel extends ViewModel {

    private MutableLiveData<String> sexo;

    private MutableLiveData<String> nombre;

    private MutableLiveData<String> telefono;


    public MicuentaViewModel(){


        if(sexo==null){

            sexo=new MutableLiveData<>();

            sexo.setValue("");
        }

        if(telefono ==null){

           telefono =new MutableLiveData<>();

            telefono.setValue("");
        }

        if(nombre==null){

         nombre=new MutableLiveData<>();

          nombre.setValue("");

        }

    }



    public MutableLiveData<String> getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.postValue(sexo);
    }

    public MutableLiveData<String> getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
       this.nombre.setValue(nombre);
    }

    public MutableLiveData<String> getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {

        this.telefono.postValue(telefono);
    }
}
