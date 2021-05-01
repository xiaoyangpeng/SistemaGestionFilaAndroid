package com.example.queue.listaproducto.interfaz.mercancias;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelMercancias extends ViewModel {


    private MutableLiveData<String> nombre;

    private MutableLiveData<Double> precio;

    private MutableLiveData<Integer> cantidad;

    private MutableLiveData<String> descripcion;

    private MutableLiveData<Integer> stock;

    private Double precioFijo;

    private boolean guardarCambio;

    public ViewModelMercancias(Double precioFijo){

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

        if(descripcion ==null){

            descripcion =new MutableLiveData<String>();

            descripcion.setValue("");
        }if(stock==null){

            stock=new MutableLiveData<Integer>();

            stock.setValue(0);
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

        this.precio.setValue(precio);
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

    public MutableLiveData<String> getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion.setValue(descripcion);
    }

    public MutableLiveData<Integer> getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.setValue(stock);
    }


    public boolean isGuardarCambio() {
        return guardarCambio;
    }

    public void setGuardarCambio(boolean guardarCambio) {
        this.guardarCambio = guardarCambio;
    }
}
