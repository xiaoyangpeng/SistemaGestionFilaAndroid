package com.example.queue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.queue.guardarDatoSharedPre.limpiarShare.Limpiarshare;
import com.example.queue.listamisproductos.MisProductosActivity;
import com.example.queue.listamisproductos.apimisProducto.ListaProducto;
import com.example.queue.listaproducto.interfaz.Listaproducto;
import com.example.queue.login.LoginActivity;
import com.example.queue.modificardato.ModificaActivity;
import com.example.queue.permiso.DialogoPermisoCamara;
import com.example.queue.probarConexionInternet.ProbarConexionInternet;
import com.example.queue.recibeHistoriaFila.ui.HistoriaFilaActivity;
import com.example.queue.valorFijo.HiloModificaUiMiCola;
import com.example.queue.zxing.activity.CaptureActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    public static MainActivity mainActivity;

    private  ProbarConexionInternet probarConexion;


    public Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // un bug de salir cuenta siguie arranca el hilo
        HiloModificaUiMiCola.arrancar=true;

        mainActivity=this;

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationPrincipal);

        NavController navController= Navigation.findNavController(this,R.id.fragmentPrincipal);

        AppBarConfiguration configuration=new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();

        NavigationUI.setupActionBarWithNavController(this,navController,configuration);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);


        // probar conexion Internet
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        probarConexion = new ProbarConexionInternet(this);
        registerReceiver(probarConexion, intentFilter);


        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                HanlderComunicacionUIPrincipal hanlderComunicacionUIPrincipal=new HanlderComunicacionUIPrincipal(mainActivity);

                hanlderComunicacionUIPrincipal.CambiarIU(msg);
            }
        };




    }

    @Override   //cuando vuelve de la ventana de perdir permiso
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case 1:
                // public static final int PERMISSION_GRANTED = 0;
                // si el usuario nos deja permiso
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){ //

                    Toast.makeText(this, "Permiso obtenido", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(this, CaptureActivity.class);

                    startActivity(i);

                } else {
                    // cuando no nos deja permiso , vertificar si ha puslado no volver pregungar mas

                    // solo comproba el perimiso cuando el sistema es mayor que 23
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                        // comprobas si el usuario ha pulsado no volver preguntar nunca mas
                        boolean    nunca = shouldShowRequestPermissionRationale(permissions[0]);

                        if (nunca==false) {

                         DialogoPermisoCamara dialogoPermisoCamara=new DialogoPermisoCamara(this);
                         dialogoPermisoCamara. dialogoPermiso();

                        } else {

                            Toast.makeText(this, "Error de permiso", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                break;
            default:
        }
    }

        public void buscarListaProducto(View view){

            startActivity(new Intent(mainActivity, Listaproducto.class));
        }


        public void buscarMisPoducto(View view){


            startActivity(new Intent(mainActivity, MisProductosActivity.class));

        }


        public void salircuenta(View view){

            Limpiarshare limpiarshare=new Limpiarshare(this);

            startActivity(new Intent(mainActivity, LoginActivity.class));

            HiloModificaUiMiCola.arrancar=false;

            this.finish();

        }


        public void modificarCuenta(View view){

            startActivity(new Intent(mainActivity, ModificaActivity.class));

        }

        public void historiaFila(View view){

                startActivity(new Intent(mainActivity, HistoriaFilaActivity.class));

        }

}