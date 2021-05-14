package com.example.queue.listaproducto.interfaz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.queue.R;
import com.example.queue.listaproducto.acciones.BuscarProducto;
import com.example.queue.listaproducto.acciones.apilistaproducto.ApillaListaProducto;
import com.example.queue.listaproducto.productos.Productos;
import com.example.queue.probarConexionInternet.Fallaconexion;

import java.util.ArrayList;

public class Listaproducto extends AppCompatActivity {


   private SearchView buscarProducto;

   private BuscarProducto accionbuscarProducto;



   public Handler handler;


   private Listaproducto listaproducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listaproducot);

        // añadir en el toolbar la flecha hacia atras
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Busca su producto");
        actionBar.setDisplayHomeAsUpEnabled(true);

        listaproducto =this;

        handler= new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                Fallaconexion.fallaconexionServidor(listaproducto);
            }
        };


    }


    // devuelve el objeto PetecionProductos pero dentro de clase BuscarProducto ya que
    // PetecionProductos no crea dentro de esta clase
    public ArrayList<Productos> getPetecionProductos() {
        return accionbuscarProducto.getPetecionProductos();
    }


    // añadir menu al toolbar
    // y añadir el SearchView en el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_producto, menu);


        MenuItem item=menu.findItem(R.id.backUp);

        // añadir SarchView en toolbar
        buscarProducto=(SearchView) MenuItemCompat.getActionView(item);

        // poner nombre de SearchView
        buscarProducto.setQueryHint("Pone su producto");

        accionbuscarProducto=new BuscarProducto(this,buscarProducto);

        //añdir accion al SearchView
        buscarProducto.setOnQueryTextListener(accionbuscarProducto);

        return true;
    }


    // añadir evento a los opciones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();// cuando pulsa los opciones del menu coge su id



       if(id== android.R.id.home){// en caso id es flecha es decir vuelve atras

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}