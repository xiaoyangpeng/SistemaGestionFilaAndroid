package com.example.queue.activarCuenta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.queue.R;
import com.example.queue.valorFijo.Tiempo;

public class ActivarCuentaActivity extends AppCompatActivity {


    private EditText textCodigoActivacion;

    private Button botonActivar;
    private String email;

    private ActivarCuentaActivity activarCuentaActivity;

    public  Handler mainHandler;


    private Button vuelveMandarEamil;

    private  TextView textTiempoActivaCuenta;

    private ProgressBar progressBarActivarCuenta;

    private ConstraintLayout viewAcitvarCuenta;

    private  TextView textEmailActivacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar_cuenta);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.activarCuentaActivity=this;


        Intent i=getIntent();

        email=i.getStringExtra(getResources().getString(R.string.email));

        textCodigoActivacion=(EditText) findViewById(R.id.editextCodigoActivacion);

        botonActivar=(Button) findViewById(R.id.botonActivarCuenta);

        vuelveMandarEamil=(Button) findViewById(R.id.vuelveMandarEmail);

        textTiempoActivaCuenta=(TextView) findViewById(R.id.textTiempoActivaCuenta);

        progressBarActivarCuenta=(ProgressBar) findViewById(R.id.progressBarActivarCuenta);
        viewAcitvarCuenta=(ConstraintLayout)findViewById(R.id.viewAcitvarCuenta);

        textEmailActivacion=(TextView) findViewById(R.id.textEmailActivacion);

        textEmailActivacion.setText(email);

        botonActivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (comprobarVacio()) {

                        EnviarActivaCuenta enviarActivaCuenta = new EnviarActivaCuenta(activarCuentaActivity);
                        enviarActivaCuenta.start();

                        //desactiva el view entro para que el usuario no puede tocar
                        activarODesactivarView(false);
                    }


            }
        });


// permite en hilo de socket cambair ui de esta activity
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);


                 HandlerCominucaConUIActivaCuenta handlerCominucaConUIActivaCuenta=new HandlerCominucaConUIActivaCuenta(activarCuentaActivity);

                handlerCominucaConUIActivaCuenta.CambiarIU(msg);

            }
        };



        // cuenta atras de 90s para que el usuario puede volver a pedir que el sistema le manda un correo
        final CuentaAtras cuentaAtras=new CuentaAtras(activarCuentaActivity);
        cuentaAtras.start();



        vuelveMandarEamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    vuelveMandarEamil.setEnabled(false);
                    vuelveMandarEamil.setAlpha((float) 0.3);
                    cuentaAtras.setTimepo(Tiempo.TIEMPOPARAMANDAREMAIL);

                    VuelveMandarCorreo vuelveMandarCorreo = new VuelveMandarCorreo(email);

                    vuelveMandarCorreo.start();

                    dialogoMandarOtraVez();
            }
        });



    }

    private void dialogoMandarOtraVez(){

        new AlertDialog.Builder(activarCuentaActivity)
            .setMessage("Ya te hemos mando el código de activación a su correo")
            .setPositiveButton("Aceptar", null)
            .show();

    }

    public void activarODesactivarView(boolean que){


        textCodigoActivacion.setEnabled(que);

        vuelveMandarEamil.setEnabled(que);

        botonActivar.setEnabled(que);

        if(que) {
            progressBarActivarCuenta.setVisibility(View.INVISIBLE);
            viewAcitvarCuenta.setAlpha(1);
        }else{

            progressBarActivarCuenta.setVisibility(View.VISIBLE);
            viewAcitvarCuenta.setAlpha((float)0.3);
        }

    }

    private boolean comprobarVacio(){


        if(textCodigoActivacion.getText().toString().equals("")){

            textCodigoActivacion.setError("Esta vacío");
            return false;
        }

        return true;
    }


    public EditText getTextCodigoActivacion() {
        return textCodigoActivacion;
    }

    public String getEmail() {
        return email;
    }

    public Button getVuelveMandarEamil() {
        return vuelveMandarEamil;
    }

    public TextView getTextTiempoActivaCuenta() {
        return textTiempoActivaCuenta;
    }
}