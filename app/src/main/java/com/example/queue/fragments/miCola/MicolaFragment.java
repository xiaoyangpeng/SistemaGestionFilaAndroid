package com.example.queue.fragments.miCola;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.queue.MainActivity;
import com.example.queue.R;
import com.example.queue.databinding.MicolaFragmentBinding;
import com.example.queue.enCasoUsuarioYaesEnCola.EnviarSiEstaEncola;
import com.example.queue.login.HandlerCominucaConPrinciapal;
import com.example.queue.valorFijo.Ids;

public class MicolaFragment extends Fragment {

    public static MicolaViewModel mViewModel;


    private MicolaFragmentBinding binding;

    private View view;

    private  MainActivity mainActivity;

    private LinearLayout colaView;

    private MicolaFragment micolaFragment;

    private ProgressBar progressBar;

    public Handler handlerMicola;

    public static MicolaFragment newInstance() {
        return new MicolaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,

                            @Nullable Bundle savedInstanceState) {
      //  View view =inflater.inflate(, container, false);

        binding=MicolaFragmentBinding.inflate(inflater);
     //   binding=inflater.inflate(R.layout.micola_fragment,container,false);

        view=binding.getRoot();



        micolaFragment=this;

        colaView=(LinearLayout)view.findViewById(R.id.viewMicola);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBarMicola);


        // permite cambiar UI de login Activity en subHilos
        handlerMicola= new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);

               HnadlerUiMicola hnadlerUiMicola=new HnadlerUiMicola(micolaFragment);

                hnadlerUiMicola.CambiarIU(msg);
            }
        };

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

       if(mViewModel==null){

           mViewModel = new ViewModelProvider(this).get(MicolaViewModel.class);

           MicolaFragment.mViewModel.modificarTurno("");

           MicolaFragment.mViewModel.modificarTiempo(0);

           MicolaFragment.mViewModel.setTurnosQueda("");

       }

        binding.setData(mViewModel);

        binding.setLifecycleOwner(this);


        // enviar si ya esta dentro de una fila si esta manda informacion
        // de la cola al usuario


        // cuando corta internet yaestaEncola va ser false
        // primera vez que entra va ser false
        // una vez en la cola va ser true
        // cuando es true ya no mando socket al servidor

        if(!Ids.yaestaEncola) {

            EnviarSiEstaEncola enviarSiEstaEncola = new EnviarSiEstaEncola(mainActivity, micolaFragment);

            enviarSiEstaEncola.start();

            serVisibleoNO(false);
        }


    }

    // asignar el activity padre
    // como este fragemente hacer referencia a manactivity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity) {

            mainActivity= (MainActivity) activity;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public void serVisibleoNO(Boolean visible){

        if(visible){

            colaView.setAlpha(1);
            progressBar.setVisibility(View.INVISIBLE);

        }else{
            colaView.setAlpha((float) 0.3);
            progressBar.setVisibility(View.VISIBLE);
        }

    }

}