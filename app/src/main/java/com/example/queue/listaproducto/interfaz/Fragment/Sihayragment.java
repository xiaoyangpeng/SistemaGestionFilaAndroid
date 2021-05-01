package com.example.queue.listaproducto.interfaz.Fragment;

import android.os.Bundle;

import android.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.queue.R;
import com.example.queue.listaproducto.acciones.PetecionProductos;
import com.example.queue.listaproducto.interfaz.Listaproducto;
import com.example.queue.listaproducto.interfaz.MiAdaptadorListaProducto;

public class Sihayragment extends Fragment {


    PetecionProductos petecionProductos;


    public Sihayragment(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.sihayragment_fragment, container, false);

        // crear view de lista
        RecyclerView lista=( RecyclerView)view.findViewById(R.id.lista_producto);

        lista.setNestedScrollingEnabled(false);
        lista.setHasFixedSize(true);

        lista.setFocusable(false);

        lista.setHasFixedSize(true);

        RecyclerView.LayoutManager rl=new LinearLayoutManager(view.getContext());

        lista.setLayoutManager(rl);

        // coger el objeto PetecionPorductos de Listaproducto Activity
        RecyclerView.Adapter adaptador=new MiAdaptadorListaProducto(((Listaproducto)getActivity()).getPetecionProductos().getProductos());

        lista.setAdapter(adaptador);


        return view;
    }





}