package com.pixels.Inventario.View.Gestion_Ventas.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Ajustes.AlertDialog.AlertContrasenaB;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra.obtenerContraViewModel;

public class VerVentasFragment extends Fragment {
    public AppCompatActivity Context;
    public boolean verventas=true;
    public VerVentasFragment(){

    }
    public VerVentasFragment(AppCompatActivity context){
        this.Context=context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle("Inventario de Productos");
            verventas=false;
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ver_ventas, container, false);
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean bloqueo = myPreferences.getBoolean("bloqueoA",false);
        if(bloqueo){
            AlertContrasenaB contra=new AlertContrasenaB(getActivity());
            obtenerContraViewModel obtenercontra= ViewModelProviders.of(getActivity()).get(obtenerContraViewModel.class);
            obtenercontra.reset();
            obtenercontra.ObtenerContra(getActivity());
            final Observer<String> observer=new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    try {
                        if (getActivity().findViewById(R.id.opcion_detail_container) != null) {
                            contra.pedircontra(s,true,verventas);
                        }
                    }catch (Exception e){

                    }
                }
            };
            obtenercontra.getResultado().observe(getActivity(),observer);
        }
        return rootView;
    }
}
