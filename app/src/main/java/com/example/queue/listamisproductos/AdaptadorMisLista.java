package com.example.queue.listamisproductos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.queue.R;
import com.example.queue.listamisproductos.apimisProducto.ListaProducto;

import java.util.ArrayList;

public class AdaptadorMisLista extends RecyclerView.Adapter<AdaptadorMisLista.MiListaViewHolder> {

        ArrayList<ListaProducto> producto;


        View v;
public AdaptadorMisLista (ArrayList<ListaProducto> producto){

        this.producto = producto;


        }


    @NonNull
    @Override
    public MiListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inlador=LayoutInflater.from(parent.getContext());

        v=inlador.inflate(R.layout.lista_mis_productos,parent,false);

        MiListaViewHolder mv= new MiListaViewHolder(v);
        return mv;
    }


    @Override
    public void onBindViewHolder(@NonNull final MiListaViewHolder holder, final int position) {

        holder.nombre.setText(producto.get(position).getNombre());
        holder.precio.setText(String.valueOf(producto.get(position).getPrecio()));
        holder.cantidad.setText(String.valueOf(producto.get(position).getCantidad_producto()));

        holder.precioTotal.setText(String.valueOf(producto.get(position).getPrecioTotal()));


        }



    @Override
public int getItemCount() {
        return producto.size();
        }



public static class MiListaViewHolder extends RecyclerView.ViewHolder{

    public TextView nombre;

    public TextView precio;


    public TextView cantidad;

    public TextView precioTotal;

    View itemView;

    public MiListaViewHolder(@NonNull View itemView) {

        super(itemView);

        this.itemView=itemView;

        nombre=(TextView)itemView.findViewById(R.id.nomreMisProducto);

        precio=(TextView)itemView.findViewById(R.id.precioMisProducto);

        cantidad=(TextView)itemView.findViewById(R.id.cantidadMisProducto);

         precioTotal=(TextView)itemView.findViewById(R.id.ProductosPrecioTotal);



    }



}




}
