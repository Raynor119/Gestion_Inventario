package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GLineas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;

import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.R;

import java.util.List;


public class GraficaLinearD extends Fragment {
    public static List<TotalVentas> VentasD;

    private LineChart GColumna;

    public GraficaLinearD(){

    }
    public GraficaLinearD(List<TotalVentas> ventasD){
        this.VentasD=ventasD;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentgraficalinear, container, false);
        GColumna = (LineChart) rootView.findViewById(R.id.chart_bottom);
        GenerarGrafica();
        return rootView;
    }
    public void GenerarGrafica(){

    }

}
