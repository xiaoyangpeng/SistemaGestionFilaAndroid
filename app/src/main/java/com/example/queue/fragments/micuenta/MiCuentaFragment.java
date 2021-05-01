package com.example.queue.fragments.micuenta;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.queue.databinding.MiCuentaFragmentBinding;
import com.example.queue.listaproducto.acciones.cargarfoto.CargarFoto;

public class MiCuentaFragment extends Fragment {

    private  static  MicuentaViewModel  mViewModel;

    private MiCuentaFragmentBinding binding;

    private View view;

    private ImageView imageView;
    public static MiCuentaFragment newInstance() {
        return new MiCuentaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding=MiCuentaFragmentBinding.inflate(inflater);

        view=binding.getRoot();

       //view=inflater.inflate(R.layout.mi_cuenta_fragment, container, false);

        imageView=binding.imageView4;

        CargarFoto foto=new CargarFoto(0,handler);

        foto.cargarFoto();


        return view;
    }
    public static final int SHOWIMAGE=1;


    private Handler handler=new Handler(){

            public void handleMessage(Message msg){

                switch(msg.what){
                    case SHOWIMAGE:

                        Bitmap bitmap=(Bitmap) msg.obj;

                        imageView.setImageBitmap(bitmap);

                        break;
                    default:
                        break;
                }
            };
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /*PideInformacionCuenta pideInformacionCuenta=new PideInformacionCuenta();

        pideInformacionCuenta.pide();*/


        if(mViewModel==null) {

            mViewModel = new ViewModelProvider(this).get(MicuentaViewModel.class);

            mViewModel.setNombre("nada");
        }
          binding.setData(mViewModel);

        binding.setLifecycleOwner(this);


    }

}