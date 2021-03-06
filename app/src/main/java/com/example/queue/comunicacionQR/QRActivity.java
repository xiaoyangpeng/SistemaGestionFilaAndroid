package com.example.queue.comunicacionQR;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.queue.R;

import java.io.IOException;

public class QRActivity extends AppCompatActivity {



	public Handler mainHandler;

	private QRActivity qrActivity;

	private RecibeRespuestaQR qrrespeusta;

	private ProgressBar progressBarQR;

	private ConstraintLayout viewQR;

	private  String codigoQR;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr);

		Bundle extras = getIntent().getExtras();

		progressBarQR=(ProgressBar) findViewById(R.id.progressBarQR);

		viewQR=(ConstraintLayout)findViewById(R.id.viewQR);


		qrActivity=this;

		if (null != extras) {
			 codigoQR = extras.getString("result");
		}

		activarDesactivarView(true);

		qrrespeusta=new RecibeRespuestaQR(codigoQR,this);

		qrrespeusta.start();

		// permite cambiar UI de qr Activity en subHilos
		mainHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {

				super.handleMessage(msg);

				HandlerComunicacionUiQR handlerComunicacionUiQR=new HandlerComunicacionUiQR(qrActivity);

				handlerComunicacionUiQR.cambiarUI(msg);

			}
		};



	}


	public void activarDesactivarView(boolean que){

		if(que){

			progressBarQR.setVisibility(View.VISIBLE);
			viewQR.setAlpha((float) 0.3);

		}else{
			progressBarQR.setVisibility(View.INVISIBLE);
			viewQR.setAlpha(1);

		}


	}


	public String getCodigoQR() {
		return codigoQR;
	}
}
