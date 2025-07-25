package com.pixels.Inventario.View.Gestion_Ventas.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Ajustes.AlertDialog.AlertContrasenaB;
import com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.alertVentaCodigo;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.PagerController;
import com.pixels.Inventario.View.Gestion_Ventas.VerVentas;
import com.pixels.Inventario.View.Menu_Inicio.MenuInicio;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra.obtenerContraViewModel;

public class VerVentasFragment extends Fragment {
    public boolean verventas=true;
    public alertVentaCodigo alertdialog;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerController pagerController;

    public VerVentasFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            verventas=false;
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ver_ventas, container, false);
        verificarBloqueo();
        tabLayout= rootView.findViewById(R.id.Tablayout);
        viewPager= rootView.findViewById(R.id.viewpager);
        pagerController= new PagerController(getChildFragmentManager(), 3);
        viewPager.setAdapter(pagerController);
        tabLayout.setupWithViewPager(viewPager);
        ImageView imageView= rootView.findViewById(R.id.buscarRecivo);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialog=new alertVentaCodigo(VerVentasFragment.this);
                alertdialog.pediridventa();
                VerVentas.alertdialog=alertdialog;
                MenuInicio.alertdialog=alertdialog;
            }
        });
        return rootView;
    }
    public void verificarBloqueo(){
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
    }
}
