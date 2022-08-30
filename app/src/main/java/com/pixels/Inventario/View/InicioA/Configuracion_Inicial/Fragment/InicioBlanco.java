package com.pixels.Inventario.View.InicioA.Configuracion_Inicial.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pixels.Inventario.R;

public class InicioBlanco extends Fragment {


    public InicioBlanco() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio_blanco, container, false);
    }
}