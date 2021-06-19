package com.example.queue.listaproducto.acciones;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.SearchView;

import com.example.queue.R;
import com.example.queue.listaproducto.acciones.apilistaproducto.ApillaListaProducto;
import com.example.queue.listaproducto.interfaz.Fragment.Sihayragment;
import com.example.queue.listaproducto.interfaz.Fragment.VacioFragment;
import com.example.queue.listaproducto.interfaz.Listaproducto;
import com.example.queue.listaproducto.productos.Productos;
import com.example.queue.valorFijo.Ids;

import java.util.ArrayList;

public class BuscarProducto implements SearchView.OnQueryTextListener {

    private Listaproducto listaproducto =null;
    private ApillaListaProducto    petecionProducto;
    private Fragment sihayragment;

    private   Fragment nohayFragment;
    private  SearchView buscarProducto;


    public BuscarProducto(Listaproducto listaproducto, SearchView buscarProducto) {

        this.listaproducto = listaproducto;
        this.buscarProducto=buscarProducto;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        //此处添加查询开始后的具体时间和方法

        buscarProducto.clearFocus();  //可以收起键盘 // recoge teclado

        //buscarProducto.onActionViewCollapsed();//可以收起SearchView视图

        // hacer peticion de buscar productos

        petecionProducto=new ApillaListaProducto(listaproducto);

        petecionProducto.crear(String.valueOf(Ids.id_cola),query);

        petecionProducto.start();

        // esperar respueta del servidor
        try {
            petecionProducto.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }


        // depende del resultado cambiar fragmente
        FragmentManager mimanejador= listaproducto.getFragmentManager();

        FragmentTransaction mitransaccion=mimanejador.beginTransaction();

        // cuanto no existe este producto es devuelve json vacio
        // pero el objeto Productos no es null
        // ya que ya ha iniciado(new ) pero esta vacio
        if(petecionProducto.getProductos()==null||petecionProducto.getProductos().size()!=0) {
            sihayragment= new Sihayragment();

            mitransaccion.replace(R.id.herramientas, sihayragment);

        }else{
            nohayFragment=new VacioFragment();
            mitransaccion.replace(R.id.herramientas, nohayFragment);
        }
        mitransaccion.commit();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    public ArrayList<Productos> getPetecionProductos() {
        return petecionProducto.getProductos();
    }
}
