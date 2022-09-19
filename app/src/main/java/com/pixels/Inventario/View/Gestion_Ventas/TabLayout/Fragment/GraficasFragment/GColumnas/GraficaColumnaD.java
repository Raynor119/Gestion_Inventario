package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GColumnas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.R;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.GraficasFragment.GColumnas.GraficaColumnDRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasDiariasRecyclerViewModel;

import lecho.lib.hellocharts.view.ColumnChartView;

public class GraficaColumnaD extends Fragment {
    public static String Fecha;
    private ColumnChartView GColumna;
    public GraficaColumnaD(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentgraficacolumna, container, false);
        GColumna = (ColumnChartView) rootView.findViewById(R.id.chart_bottom);
        return rootView;
    }
    public void GenerarGrafica(){
        GraficaColumnDRecyclerViewModel productos= ViewModelProviders.of(getActivity()).get(GraficaColumnDRecyclerViewModel.class);
        productos.reset();
        productos.buscarVProductos(getActivity(),getConsulta(Fecha));
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
                c="WHERE DATE(Venta.Fecha) = '"+anno+"-"+("0"+mes)+"-"+dia+"' GROUP BY Venta.codigo";
            }else{
                c="WHERE DATE(Venta.Fecha) = '"+anno+"-"+mes+"-"+dia+"' GROUP BY Venta.codigo";
            }

        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            c="WHERE CAST(Fecha AS DATE) = '"+anno+"-"+mes+"-"+dia+"' GROUP BY Venta.codigo";
        }

        return c;
    }
}
