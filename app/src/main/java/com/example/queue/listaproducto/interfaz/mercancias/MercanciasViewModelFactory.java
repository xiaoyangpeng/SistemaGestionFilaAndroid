package com.example.queue.listaproducto.interfaz.mercancias;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.queue.listaproducto.interfaz.comida.ViewModelComida;

public class MercanciasViewModelFactory implements ViewModelProvider.Factory  {

    private Double precio;
    public MercanciasViewModelFactory(Double precio){
      this.precio=precio;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModelMercancias.class)) {
            return (T) new ViewModelMercancias(precio);
        }
        throw new RuntimeException("unknown class :" + modelClass.getName());
    }

}
