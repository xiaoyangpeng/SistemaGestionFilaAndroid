package com.example.queue.listaproducto.interfaz.servicio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.queue.R;
import com.example.queue.databinding.ActivityServicioBinding;
import com.example.queue.listaproducto.acciones.GuardarCambioProducto;
import com.example.queue.listaproducto.productos.Productos;
import com.example.queue.listaproducto.productos.Servicio;

public class ServicioActivity extends AppCompatActivity {


    private ActivityServicioBinding binding;

    private ViewModelServicio viewModelServicio;

    private Productos productos;

    private Servicio servicio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_servicio);

        Bundle extras=getIntent().getExtras();


        // añadir en el toolbar la flecha hacia atras
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Servicio");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Double precio = (Double) 0.0;

        if(extras!=null){

            servicio= (Servicio) extras.getSerializable("servicio");

            productos=(Productos)extras.getSerializable("producto");

            precio=productos.getPrecio();

        }

        viewModelServicio = new ViewModelProvider((ViewModelStoreOwner)this,new ServicioViewModelFactory(precio)).get(ViewModelServicio.class);

        if(extras!=null){

           viewModelServicio.setDescripcion(servicio.getDescripcion());

            viewModelServicio.setCantidad(servicio.getCantidad());

            viewModelServicio.setNombre(productos.getNombre());

            viewModelServicio.setGuardarCambio(true);

        }

        binding.setData(viewModelServicio);
        binding.setLifecycleOwner(this);

    }

    // añadir menu al toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mas_informacion, menu);
        return true;
    }

    // añadir evento a los opciones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();// cuando pulsa los opciones del menu coge su id

        if (id == R.id.terminado) {

            viewModelServicio.setGuardarCambio(true);

            // mandar al servidor los cambios de productos
            GuardarCambioProducto guardarCambioProducto=new GuardarCambioProducto(productos.getId_producto(),viewModelServicio.getCantidad().getValue(),this);
            guardarCambioProducto.start();

            dialogoExito();

        }else if(id== android.R.id.home){// en caso id es flecha es decir vuelve atras

            // para vertificar que el usuario ha guardao su cambio o no
            // cuando pulsa guaradarcambio el boolena de guaradarcambio
            // en el viewModelComida va dejar como true
            // y si cambio cantidad de producto dejara como false
            if(viewModelServicio.isGuardarCambio()){

                finish();

            }else{

                dialogoSalirSeguro();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public void dialogoSalirSeguro(){


        AlertDialog.Builder dialogo=new AlertDialog.Builder(this);


        DialogInterface.OnClickListener acvitarCuenta=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        };

        dialogo.setMessage("No has guardado los cambio seguro quieres salir ?");

        dialogo.setPositiveButton("Aceptar", acvitarCuenta);

        dialogo.setNegativeButton("Cancelar",null);
        dialogo.show();

    }


    public void dialogoExito(){


        AlertDialog.Builder dialogo=new AlertDialog.Builder(this);


        DialogInterface.OnClickListener acvitarCuenta=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        };

        dialogo.setMessage("Ya has guardado los cambios");

        dialogo.setPositiveButton("Aceptar", acvitarCuenta);

        dialogo.show();

    }
}