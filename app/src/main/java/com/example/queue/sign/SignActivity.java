package com.example.queue.sign;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.queue.R;

public class SignActivity extends AppCompatActivity {


    private TextView sexo;

    SignActivity signActivity;

    private Button botonSignUp;

    private  EditText contrasenaRepite;

    public Handler mainHandler;

    private  EditText email;

    private EditText contrasena;

    private ImageView imageContrasena;

    private ImageView imageRepiteContrasena;

    private boolean abreOjoContrasena=false;

    private boolean abreOjoRepiteContrasena=false;

    private ScrollView viewSign;

    private ProgressBar procesoSign;


    private EditText textTelefono ;

    private EditText  nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.signActivity=this;
        textTelefono =(EditText) signActivity.findViewById(R.id.telefono);

        nombre=(EditText)signActivity.findViewById(R.id.nombreSign);
        sexo=(TextView)findViewById(R.id.sexo);
        botonSignUp=(Button)findViewById(R.id.botonSginUp);
        contrasenaRepite=(EditText) findViewById(R.id.contrasenaRepiteSign);
        email=(EditText) findViewById(R.id.emailSign);
        contrasena=(EditText)findViewById(R.id.contrasenaSign);

        imageContrasena=(ImageView)findViewById(R.id.imageContrasenaSign);

        imageRepiteContrasena =(ImageView)findViewById(R.id.imageContrasenaRepiteSign);

        viewSign =(ScrollView)findViewById(R.id.viewSign);

        procesoSign =(ProgressBar)findViewById(R.id.progressBarSign);

        sexo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ShowChoise();
            }
        });


       imageContrasena.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               abreOjoContrasena=!abreOjoContrasena;

               if (abreOjoContrasena){

                   imageContrasena.setImageDrawable(signActivity.getResources().getDrawable(R.drawable.abre_ojo));

                  contrasena.setTransformationMethod(HideReturnsTransformationMethod.getInstance());



               }else{

                     contrasena.setTransformationMethod(PasswordTransformationMethod.getInstance());

                   imageContrasena.setImageDrawable(signActivity.getResources().getDrawable(R.drawable.cerra_ojo));
               }


           }
       });


        imageRepiteContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abreOjoRepiteContrasena=! abreOjoRepiteContrasena;

                if (  abreOjoRepiteContrasena){

                    imageRepiteContrasena.setImageDrawable(signActivity.getResources().getDrawable(R.drawable.abre_ojo));

                    contrasenaRepite.setTransformationMethod(HideReturnsTransformationMethod.getInstance());



                }else{

                    contrasenaRepite.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    imageRepiteContrasena.setImageDrawable(signActivity.getResources().getDrawable(R.drawable.cerra_ojo));
                }


            }
        });





        botonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    ComprobarPreviaDatosSign comprobarPreviaDatosSign = new ComprobarPreviaDatosSign(signActivity);


            }
        });


        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);
                HandlerComunicacionUiSign handlerComunicacionUiSign=new HandlerComunicacionUiSign(signActivity);

                handlerComunicacionUiSign.CambiarIU(msg);

            }
        };


    }

    private void ShowChoise()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(signActivity,android.R.style.Theme_Holo_Light_Dialog);
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("Elige su Sexo");
        //    指定下拉列表的显示数据
        final String[] cities = {"Mujer", "Hombre", "Prefiero no decir"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                sexo.setText(cities[which]);
            }
        });
        builder.show();
    }



    public void activarODesactivaView(boolean que){

        email.setEnabled(que);
        contrasena.setEnabled(que);
        contrasenaRepite.setEnabled(que);
        botonSignUp.setEnabled(que);
        sexo.setEnabled(que);
        nombre.setEnabled(que);
        textTelefono.setEnabled(que);

        imageContrasena.setEnabled(que);

        imageRepiteContrasena.setEnabled(que);

        if(que){
            procesoSign.setVisibility(View.INVISIBLE);
            viewSign.setAlpha(1);
        }else{

            viewSign.setAlpha((float)0.3); //grado de visiblidad a view Login

            procesoSign.setVisibility(View.VISIBLE);// ser visible el ciruclo de proceso
        }

    }


    public EditText getNombre() {
        return nombre;
    }

    public EditText getTextTelefono() {
        return textTelefono;
    }

    public TextView getSexo() {
        return sexo;
    }

    public EditText getContrasenaRepite() {
        return contrasenaRepite;
    }

    public EditText getEmail() {
        return email;
    }

    public EditText getContrasena() {
        return contrasena;
    }






}