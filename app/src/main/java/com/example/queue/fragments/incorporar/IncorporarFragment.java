package com.example.queue.fragments.incorporar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.queue.MainActivity;
import com.example.queue.R;
import com.example.queue.comunicacionQR.QRActivity;
import com.example.queue.fragments.incorporar.incorporarQR.ListenerEscannear;
import com.example.queue.fragments.incorporar.incorporarRemota.RemotaActivity;

public class IncorporarFragment extends Fragment {

    private IncorporarViewModel mViewModel;
    private ImageView escanear;
    private View view;
    public Context context;

    private Button button;

    public static IncorporarFragment newInstance() {
        return new IncorporarFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.incorporar_fragment, container, false);

        escanear = (ImageView) view.findViewById(R.id.escannear);


        button=view.findViewById(R.id.remota);
        context=this.getContext();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(IncorporarViewModel.class);

         escanear.setOnClickListener(new ListenerEscannear(view,this));


         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                 Intent i=new Intent(view.getContext(),RemotaActivity.class);

                 startActivity(i);


             }
         });

    }




}