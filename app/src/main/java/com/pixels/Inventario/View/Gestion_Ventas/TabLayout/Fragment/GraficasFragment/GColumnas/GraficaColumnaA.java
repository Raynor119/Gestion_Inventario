package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GColumnas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.DatosColumn;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.Widgets.XYMarkerView;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.GraficasFragment.GColumnas.GraficaColumnARecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.GraficasFragment.GColumnas.GraficaColumnMRecyclerViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GraficaColumnaA extends Fragment {
    public static String Fecha;
    private BarChart GColumna;
    private ArrayList<BarEntry> barEntryArrayList;

    public GraficaColumnaA(){

    }
    public GraficaColumnaA(String fecha){
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
        GraficaColumnARecyclerViewModel productos= ViewModelProviders.of(getActivity()).get(GraficaColumnARecyclerViewModel.class);
        productos.reset();
        productos.buscarVProductos(getActivity(),getConsulta(Fecha));
        Observer<List<DatosColumn>> observer= new Observer<List<DatosColumn>>() {
            @Override
            public void onChanged(List<DatosColumn> datosColumns) {
                barEntryArrayList=new ArrayList<>();
                for(int i=0;i<datosColumns.size();i++){
                    barEntryArrayList.add(new BarEntry(i,datosColumns.get(i).getTotalV()));

                }
                BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Productos Vendidos");
                barDataSet.setColors(ColorTemplate.rgb("0090FD"));
                barDataSet.setValueTextSize(11);
                barDataSet.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        NumberFormat mFormat = NumberFormat.getNumberInstance();
                        return "$ "+mFormat.format(value);
                    }
                });
                Description description=new Description();
                description.setText("");
                GColumna.setDescription(description);
                BarData barData=new BarData(barDataSet);
                GColumna.setData(barData);

                XAxis xAxis= GColumna.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return "#"+((int)value+1);
                    }
                });
                xAxis.setDrawGridLines(false);
                xAxis.setDrawAxisLine(false);
                xAxis.setGranularity(1f);
                xAxis.setLabelCount(datosColumns.size());
                xAxis.setLabelRotationAngle(0);


                YAxis leftAxis = GColumna.getAxisLeft();
                leftAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        NumberFormat mFormat = NumberFormat.getNumberInstance();
                        return "$ "+mFormat.format(value);
                    }
                });
                GColumna.animateX(900);

                YAxis rigthAxis = GColumna.getAxisRight();
                rigthAxis.setEnabled(false);

                GColumna.animateY(900);
                GColumna.invalidate();
                GColumna.setDoubleTapToZoomEnabled(false);
                XYMarkerView mv = new XYMarkerView(getActivity(),datosColumns);
                mv.setChartView(GColumna);
                GColumna.setMarker(mv);

            }
        };
        productos.getResultado().observe(getActivity(),observer);
    }

    public String getConsulta(String fechaE){
        int anno;
        anno=Integer.parseInt(fechaE);
        String c="";
        consultasDatos dinici=new consultasDatos(getActivity());
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            c="WHERE strftime('%Y', venta.Fecha) = '"+anno+"' GROUP BY VentasProductos.codigoP ORDER BY TotalV DESC LIMIT 10";
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            c="WHERE YEAR(Fecha) = '"+anno+"' GROUP BY VentasProductos.codigoP ORDER BY TotalV DESC LIMIT 10";
        }

        return c;
    }
}
