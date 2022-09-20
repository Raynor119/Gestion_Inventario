package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GColumnas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.BarChart;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.DatosColumn;
import com.pixels.Inventario.R;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.GraficasFragment.GColumnas.GraficaColumnDRecyclerViewModel;

import java.util.List;


public class GraficaColumnaD extends Fragment {
    public static String Fecha;
    private BarChart GColumna;

    public GraficaColumnaD(){

    }
    public GraficaColumnaD(String fecha){
        this.Fecha=fecha;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentgraficacolumna, container, false);
        GColumna = (BarChart) rootView.findViewById(R.id.chart_bottom);
        GenerarGrafica();
        return rootView;
    }
    public void GenerarGrafica(){
        GraficaColumnDRecyclerViewModel productos= ViewModelProviders.of(getActivity()).get(GraficaColumnDRecyclerViewModel.class);
        productos.reset();
        productos.buscarVProductos(getActivity(),getConsulta(Fecha));
        Observer<List<DatosColumn>> observer= new Observer<List<DatosColumn>>() {
            @Override
            public void onChanged(List<DatosColumn> datosColumns) {

            }
        };
        productos.getResultado().observe(getActivity(),observer);
    }

    public String getConsulta(String fechaE){
        int dia=1,mes=1,anno;
        int cont=0;
        String date="";
        for(int i=0;i<fechaE.length();i++){
            if((fechaE.charAt(i)+"").equals("/")){
                if(cont==0){
                    dia=Integer.parseInt(date);
                    date="";
                }
                if (cont==1){
                    mes=Integer.parseInt(date);
                    date="";
                }
                cont++;
            }else {
                date = date + (fechaE.charAt(i));
            }
        }
        anno=Integer.parseInt(date);

        String c="";
        consultasDatos dinici=new consultasDatos(getActivity());
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            if(mes<10){
                c="WHERE DATE(Venta.Fecha) = '"+anno+"-"+("0"+mes)+"-"+dia+"' GROUP BY VentasProductos.codigoP ORDER BY TotalV DESC";
            }else{
                c="WHERE DATE(Venta.Fecha) = '"+anno+"-"+mes+"-"+dia+"' GROUP BY VentasProductos.codigoP ORDER BY TotalV DESC";
            }

        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            c="WHERE CAST(Fecha AS DATE) = '"+anno+"-"+mes+"-"+dia+"' GROUP BY VentasProductos.codigoP ORDER BY TotalV DESC";
        }

        return c;
    }
}
