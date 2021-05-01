package com.example.queue.listaproducto.interfaz.mercancias;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.queue.R;
import com.example.queue.databinding.ActivityMercanciasBinding;
import com.example.queue.listaproducto.acciones.GuardarCambioProducto;
import com.example.queue.listaproducto.acciones.cargarfoto.CargarFoto;
import com.example.queue.listaproducto.interfaz.comida.ComidaViewModelFactory;
import com.example.queue.listaproducto.interfaz.comida.ViewModelComida;
import com.example.queue.listaproducto.productos.Mercancia;
import com.example.queue.listaproducto.productos.Productos;

public class MercanciasActivity extends AppCompatActivity {



    private ActivityMercanciasBinding binding;

    private ViewModelMercancias viewModelMercancias;
    private Productos productos;

    private Mercancia mercancia;


    private CargarFoto cargarFoto;

    private ImageView imagenMercancia;


    public static final int SHOWIMAGE=1;


    private Handler handler=new Handler(){

        public void handleMessage(Message msg){

            switch(msg.what){
                case SHOWIMAGE:

                    Bitmap bitmap=(Bitmap) msg.obj;

                    imagenMercancia.setImageBitmap(bitmap);

                    break;
                default:
                    break;
            }
        };
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_mercancias);

        Bundle extras=getIntent().getExtras();

        // añadir en el toolbar la flecha hacia atras
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Mercancia");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Double precio = (Double) 0.0;

        if(extras!=null){

            mercancia= (Mercancia) extras.getSerializable("mercancia");

            imagenMercancia=findViewById(R.id.mercanciaFoto);

            cargarFoto=new CargarFoto(mercancia.getId_producto(),handler);

            cargarFoto.cargarFoto();

            productos=(Productos)extras.getSerializable("producto");

            precio=productos.getPrecio();

        }

        viewModelMercancias = new ViewModelProvider((ViewModelStoreOwner)this,new MercanciasViewModelFactory(precio)).get(ViewModelMercancias.class);

        if(extras!=null){

            viewModelMercancias.setStock(mercancia.getStock());

            viewModelMercancias.setDescripcion(mercancia.getDescripcion());

            viewModelMercancias.setCantidad(mercancia.getCantidad());

            viewModelMercancias.setNombre(productos.getNombre());

            viewModelMercancias.setGuardarCambio(true);

        }

        binding.setData(viewModelMercancias);
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

            viewModelMercancias.setGuardarCambio(true);

            // mandar al servidor los cambios de productos
            GuardarCambioProducto guardarCambioProducto=new GuardarCambioProducto(productos.getId_producto(),viewModelMercancias.getCantidad().getValue());
            guardarCambioProducto.start();

            try {
                guardarCambioProducto.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dialogoExito();

        }else if(id== android.R.id.home){// en caso id es flecha es decir vuelve atras

            // para vertificar que el usuario ha guardao su cambio o no
            // cuando pulsa guaradarcambio el boolena de guaradarcambio
            // en el viewModelComida va dejar como true
            // y si cambio cantidad de producto dejara como false
            if(viewModelMercancias.isGuardarCambio()){

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