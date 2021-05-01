package com.example.queue.noHayConexion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.queue.MainActivity;
import com.example.queue.R;
import com.example.queue.login.LoginActivity;
import com.example.queue.valorFijo.ConexionUrl;

public class NohayConexionLoginActivity extends AppCompatActivity {


    private NohayConexionLoginActivity nohayConexionLoginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nohay_conexion_login);


        Button bo=findViewById(R.id.botonoNohayConexionLogin);

        nohayConexionLoginActivity  =this;


        bo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ConexionUrl.Companion.getCONEXION()){

                    startActivity(new Intent(nohayConexionLoginActivity, LoginActivity.class));

                    nohayConexionLoginActivity.finish();

                }


            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode== KeyEvent.KEYCODE_BACK)
            return true;
        return super.onKeyDown(keyCode, event);
    }//屏蔽返回键
}