package com.example.queue.fragments.micuenta;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.queue.databinding.MiCuentaFragmentBinding;
import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.ApillamaGetCuenta;
import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.GuardarDatoCuenta;
import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.LeerDatoCuenta;
import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.Usuario;

import com.example.queue.R;


public class MiCuentaFragment extends Fragment {

    private   MicuentaViewModel  mViewModel;

    private MiCuentaFragmentBinding binding;

    private View view;

    private Button botonSalir;
    private Button botonModificar;
    public static MiCuentaFragment newInstance() {
        return new MiCuentaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding=MiCuentaFragmentBinding.inflate(inflater);

        view=binding.getRoot();

       //view=inflater.inflate(R.layout.mi_cuenta_fragment, container, false);

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


            mViewModel = new ViewModelProvider(this).get(MicuentaViewModel.class);

            Usuario user=setValue();

            mViewModel.setNombre(user.getNombre());

            mViewModel.setSexo(user.getSexo());

            mViewModel.setTelefono(user.getTelefono().toString());

          binding.setData(mViewModel);

        binding.setLifecycleOwner(this);


    }


    private Usuario setValue(){

        LeerDatoCuenta leerDatoCuenta=new LeerDatoCuenta(view.getContext());

        if(leerDatoCuenta.hayDatos()){

            return leerDatoCuenta.datoEnShare();
        }else{
            ApillamaGetCuenta cuenta=new ApillamaGetCuenta(view);
            cuenta.crear();
            cuenta.start();

            try {
                cuenta.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Usuario user=cuenta.getUsuario();

            GuardarDatoCuenta guardarDatoCuenta=new GuardarDatoCuenta(view.getContext());

            guardarDatoCuenta.guardarDatosAcceso(user);

            return user;
        }

    }
}