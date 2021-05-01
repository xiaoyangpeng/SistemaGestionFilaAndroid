package com.example.queue.listaproducto.interfaz.comida;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ComidaViewModelFactory implements ViewModelProvider.Factory  {

    private Double precio;
    public ComidaViewModelFactory(Double precio){
      this.precio=precio;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModelComida.class)) {
            return (T) new ViewModelComida(precio);
        }
        throw new RuntimeException("unknown class :" + modelClass.getName());
    }

}
