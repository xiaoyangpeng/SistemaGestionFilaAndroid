package com.example.queue.listaproducto.interfaz.comida;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelComida extends ViewModel {

    private MutableLiveData<String> nombre;

    private MutableLiveData<Double> precio;


    private MutableLiveData<Integer> cantidad;

    private MutableLiveData<String> ingrediente;

    private  Double precioFijo;


    private boolean guardarCambio;

    public ViewModelComida(Double precioFijo){


        guardarCambio=true;

        this.precioFijo=precioFijo;

        if(nombre==null){

            nombre=new MutableLiveData<String>();

            nombre.setValue("");
        }
        if(precio==null){

            precio=new MutableLiveData<Double>();

            precio.setValue(precioFijo);

        }   if(cantidad==null){

            cantidad=new MutableLiveData<Integer>();

            cantidad.setValue(0);
        }

        if(ingrediente==null){

            ingrediente=new MutableLiveData<String>();

            ingrediente.setValue("");
        }

    }

    public MutableLiveData<String> getNombre() {


        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.setValue(nombre);
    }

    public MutableLiveData<Double> getPrecio() {

        return precio;
    }

    
    public void setPrecio(Double precio) {
        // solo guarda el precio fijo en primer vez

        this.precioFijo=precio;
        this.precio.setValue((Double) precio);


    }

    public MutableLiveData<Integer> getCantidad() {

        return cantidad;
    }

    public void setCantidad(int cantidad) {

        this.guardarCambio=false;

        this.cantidad.setValue(getCantidad().getValue()+cantidad) ;

        if(getCantidad().getValue()<=0){

            this.cantidad.setValue(0);

            this.precio.setValue(precioFijo);

        }
        else{

                this.precio.setValue((Double)(getCantidad().getValue()*precioFijo));

            }



    }

    public MutableLiveData<String> getIngrediente() {

        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {

        this.ingrediente.setValue(ingrediente);
    }

    public boolean isGuardarCambio() {
        return guardarCambio;
    }

    public void setGuardarCambio(boolean guardarCambio) {
        this.guardarCambio = guardarCambio;
    }
}
