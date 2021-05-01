package com.example.queue.fragments.micuenta;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MicuentaViewModel extends ViewModel {

    private MutableLiveData<String> sexo;

    private MutableLiveData<String> nombre;

    private MutableLiveData<String> email;


    public MicuentaViewModel(){


        if(sexo==null){

            sexo=new MutableLiveData<>();

            sexo.setValue("");
        }

        if(email==null){

           email=new MutableLiveData<>();

            email.setValue("");
        }

        if(nombre==null){

         nombre=new MutableLiveData<>();

          nombre.setValue("");

        }

    }



    public MutableLiveData<String> getSexo() {
        return sexo;
    }

    public void setSexo(MutableLiveData<String> sexo) {
        this.sexo = sexo;
    }

    public MutableLiveData<String> getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
       this.nombre.setValue(nombre);
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(MutableLiveData<String> email) {
        this.email = email;
    }
}
