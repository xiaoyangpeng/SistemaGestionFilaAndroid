package com.example.queue.listaproducto.interfaz.servicio;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ServicioViewModelFactory implements ViewModelProvider.Factory {


    private Double precio;
    public ServicioViewModelFactory(Double precio){
        this.precio=precio;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModelServicio.class)) {
            return (T) new ViewModelServicio(precio);
        }
        throw new RuntimeException("unknown class :" + modelClass.getName());
    }
}
