package com.example.queue.listaproducto.interfaz;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.queue.R;
import com.example.queue.listaproducto.acciones.Pulsacion;
import com.example.queue.listaproducto.productos.Productos;

import java.util.ArrayList;

public class MiAdaptadorListaProducto extends RecyclerView.Adapter<MiAdaptadorListaProducto.MiViewHolder> {

    ArrayList<Productos> producto;

    MiAdaptadorListaProducto miAdaptadorListaProducto;
    View v;
    public MiAdaptadorListaProducto(ArrayList<Productos> producto){

        this.producto = producto;

        miAdaptadorListaProducto =this;

    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inlador=LayoutInflater.from(parent.getContext());

         v=inlador.inflate(R.layout.lista_productos,parent,false);

        MiViewHolder mv=new MiViewHolder(v);

        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull final MiViewHolder holder, final int position) {

        holder.nombre.setText(producto.get(position).getNombre());
        holder.precio.setText(String.valueOf(producto.get(position).getPrecio()));

        holder.categoria.setText(producto.get(position).getCategoria());

        holder.anadirPulsacion(producto.get(position));

        holder.masInformacion.setOnClickListener(new Pulsacion(producto.get(position),v.getContext()));


    }

    @Override
    public int getItemCount() {
        return producto.size();
    }



    public static class MiViewHolder extends RecyclerView.ViewHolder{

        public TextView nombre;

        public TextView precio;

        public TextView producoInformacion;

        public TextView categoria;

        public TextView masInformacion;

        View itemView;

        public MiViewHolder(@NonNull View itemView) {

            super(itemView);

            this.itemView=itemView;

            nombre=(TextView)itemView.findViewById(R.id.nomreProducto);

            precio=(TextView)itemView.findViewById(R.id.precioProducto);

            producoInformacion=(TextView)itemView.findViewById(R.id.producoInformacion);


            categoria=(TextView)itemView.findViewById(R.id.categoriaProducto);

            masInformacion=(TextView)itemView.findViewById(R.id.producoInformacion);

        }

        public void anadirPulsacion(Productos productos){


           /* Pulsacion pulsa=new Pulsacion(url, itemView.getContext());

            foto.setOnClickListener(pulsa);  // solo añadir onclick en textView*/

        }

    }




}
