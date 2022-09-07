package com.pixels.Inventario.View.Gestion_Ventas.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pixels.Inventario.R;

public class VerVentasFragment extends Fragment {
    private Context Context;
    public VerVentasFragment(){

    }
    public VerVentasFragment(Context context){
        this.Context=context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ver_ventas, container, false);
        return rootView;
    }
}
