package com.example.queue.listaproducto.interfaz.comida;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.queue.R;
import com.example.queue.databinding.ActivityInformacionComidaBinding;
import com.example.queue.listaproducto.acciones.GuardarCambioProducto;
import com.example.queue.listaproducto.acciones.cargarfoto.CargarFoto;
import com.example.queue.listaproducto.productos.Comida;
import com.example.queue.listaproducto.productos.Productos;

public class InformacionComidaActivity extends AppCompatActivity {


    private ActivityInformacionComidaBinding binding;


    private  ViewModelComida viewModelComida;
    private Productos productos;

    private Comida comida;

    private CargarFoto cargarFoto;

    private ImageView imagenComida;

    public static final int SHOWIMAGE=1;


    private Handler handler=new Handler(){

        public void handleMessage(Message msg){

            switch(msg.what){
                case SHOWIMAGE:

                    Bitmap bitmap=(Bitmap) msg.obj;

                   imagenComida.setImageBitmap(bitmap);

                    break;
                default:
                    break;
            }
        };
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_informacion_comida);

        Bundle extras=getIntent().getExtras();

        // añadir en el toolbar la flecha hacia atras
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Comida");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Double precio = (Double) 0.0;

        if(extras!=null){

            comida= (Comida) extras.getSerializable("comida");

            imagenComida=(ImageView) findViewById(R.id.imageComida);

            cargarFoto=new CargarFoto(comida.getId_producto(),handler);

            cargarFoto.cargarFoto();

            productos=(Productos)extras.getSerializable("producto");

            precio=productos.getPrecio();

        }

        viewModelComida = new ViewModelProvider((ViewModelStoreOwner)this,new ComidaViewModelFactory(precio)).get(ViewModelComida.class);

        if(extras!=null){

            viewModelComida.setCantidad(comida.getCantidad());

            viewModelComida.setIngrediente(comida.getIngrediente());

            viewModelComida.setNombre(productos.getNombre());

            viewModelComida.setGuardarCambio(true);

        }

        binding.setData(viewModelComida);

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

            viewModelComida.setGuardarCambio(true);

            // mandar al servidor los cambios de productos
            GuardarCambioProducto guardarCambioProducto=new GuardarCambioProducto(productos.getId_producto(),viewModelComida.getCantidad().getValue());
            guardarCambioProducto.start();

            dialogoExito();

        }else if(id== android.R.id.home){// en caso id es flecha es decir vuelve atras

            // para vertificar que el usuario ha guardao su cambio o no
            // cuando pulsa guaradarcambio el boolena de guaradarcambio
            // en el viewModelComida va dejar como true
            // y si cambio cantidad de producto dejara como false
            if(viewModelComida.isGuardarCambio()){

                finish();

            }else{

                dialogoSalirSeguro();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



        // en caso no ha guarda los cambios
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