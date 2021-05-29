package com.example.queue.login;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.queue.R;
import com.example.queue.guardarDatoSharedPre.guradarDatoAcceso.LeerDatodeAcceso;
import com.example.queue.probarConexionInternet.ProbarConexionInternet;
import com.example.queue.sign.SignActivity;
import com.example.queue.valorFijo.ConexionUrl;


public class LoginActivity extends Activity {

    private  EditText usuario;

   private Button botonLogin;

   private EditText contrasena;

   private LoginActivity loginActivity;

   public  Handler mainHandler;

   private TextView  botonSign;

   private ProgressBar procesoLogin;

    private ScrollView viewLogin;

    private  ProbarConexionInternet probarConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginActivity =this; // para que luego en clase recibe login y envia login puede utilizar los variable de maiactivity

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        probarConexion = new ProbarConexionInternet(this);
        registerReceiver(probarConexion, intentFilter);

        // permite cambiar UI de login Activity en subHilos
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                HandlerCominucaConPrinciapal handlerCominucaConPrinciapal=new HandlerCominucaConPrinciapal(loginActivity);
                handlerCominucaConPrinciapal.CambiarIU(msg);

            }
        };



        LeerDatodeAcceso leerDatodeAcceso=new LeerDatodeAcceso(this);

        botonLogin =(Button) findViewById(R.id.botonLogin);

        usuario=(EditText) findViewById(R.id.usuario);

        contrasena=(EditText) findViewById(R.id.contrasena);

        botonSign=(TextView) findViewById(R.id.registararPrinciapl);

        procesoLogin=(ProgressBar)findViewById(R.id.progressBarLgin);

        viewLogin=(ScrollView)findViewById(R.id.viewLogin);

        accionBoton();


        leerDatodeAcceso.autoAccede();



    }

    private void accionBoton(){


        botonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(loginActivity.getBaseContext(), SignActivity.class));
            }
        });


        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(comprobar()) {

                   EnviaLogin enviaLogin = new EnviaLogin(usuario.getText().toString(), contrasena.getText().toString(), loginActivity);
                    enviaLogin.start();

                    activarODesactivaView(false); //descativa el view entro para que el usuario no puede tocar

                }

            }
        });




    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(probarConexion);

    }

    public boolean comprobar(){

        if(usuario.getText().toString().equals("")){

            usuario.setError("Usuario vacío");

            return false;
        }


        if(contrasena.getText().toString().equals("")){

            contrasena.setError("Contraseña vacío");

            return  false;
        }



        return  true;
    }


    public void activarODesactivaView(boolean que){

        usuario.setEnabled(que);
        contrasena.setEnabled(que);
        botonLogin.setEnabled(que);
        botonSign.setEnabled(que);

        if(que){
            procesoLogin.setVisibility(View.INVISIBLE);
            viewLogin.setAlpha(1);
        }else{

            viewLogin.setAlpha((float)0.3); //grado de visiblidad a view Login

            procesoLogin.setVisibility(View.VISIBLE);// ser visible el ciruclo de proceso
        }

    }

    public void OlvidarMiContrasena(View view){

        Uri uri = Uri.parse(ConexionUrl.Companion.getOLVIDARCONTRASENA());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }


    public EditText getUsuario() {
        return usuario;
    }

    public EditText getContrasena() {
        return contrasena;
    }
}