package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GColumnas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.DatosColumn;
import com.pixels.Inventario.R;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.GraficasFragment.GColumnas.GraficaColumnDRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasDiariasRecyclerViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

public class GraficaColumnaD extends Fragment {
    public static String Fecha;
    private ColumnChartView GColumna;
    private ColumnChartData columnData;
    public GraficaColumnaD(){

    }
    public GraficaColumnaD(String fecha){
        this.Fecha=fecha;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentgraficacolumna, container, false);
        GColumna = (ColumnChartView) rootView.findViewById(R.id.chart_bottom);
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
                List<AxisValue> axisValues = new ArrayList<AxisValue>();
                List<AxisValue> axisValuesY = new ArrayList<AxisValue>();
                List<Column> columns = new ArrayList<Column>();
                List<SubcolumnValue> values;
                for(int i=0;i<datosColumns.size();i++){
                   if(datosColumns.get(i).getTotalV()!=0){
                       values = new ArrayList<SubcolumnValue>();
                       values.add(new SubcolumnValue((float) (datosColumns.get(i).getTotalV()), Color.parseColor("#0090FD")).setLabel(""+datosColumns.get(i).getNombre()));
                       axisValues.add(new AxisValue(i).setLabel(""));
                       axisValuesY.add(new AxisValue(i).setLabel("$ "+datosColumns.get(i).getTotalV()).setValue((float) (datosColumns.get(i).getTotalV())));
                       columns.add(new Column(values).setHasLabelsOnlyForSelected(true).setHasLabels(true));
                   }
                }
                columnData = new ColumnChartData(columns);
                columnData.setAxisXBottom(new Axis(axisValues).setHasLines(false).setName("Productos").setTextColor(Color.parseColor("#000000")));
                columnData.setAxisYLeft(new Axis(axisValuesY).setHasLines(true).setName("Total Vendido").setTextColor(Color.parseColor("#000000")));

                GColumna.setColumnChartData(columnData);

                GColumna.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
                    @Override
                    public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
                        NumberFormat formato= NumberFormat.getNumberInstance();
                        Toast.makeText(getActivity(), "Total Vendido por el Produto es de:\n $ "+formato.format(value.getValue()), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onValueDeselected() {

                    }
                });
                GColumna.setValueSelectionEnabled(true);
                GColumna.setZoomEnabled(false);
                if(datosColumns.size()>=7){
                    int Widtch=(int)((1100/7)*datosColumns.size());
                    GColumna.setLayoutParams(new LinearLayout.LayoutParams(Widtch,LinearLayout.LayoutParams.MATCH_PARENT));
                    GColumna.setZoomEnabled(true);
                    GColumna.setContainerScrollEnabled(true,ContainerScrollType.HORIZONTAL);
                }else{
                    GColumna.setLayoutParams(new LinearLayout.LayoutParams(580,LinearLayout.LayoutParams.MATCH_PARENT));
                }
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
