package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GLineas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.Widgets.XYMarkerViewL;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.Widgets.XYMarkerViewLM;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class GraficaLinearM extends Fragment {
    public static List<TotalVentas> VentasD;
    public static String FechaM;

    private LineChart GLinear;
    private ArrayList<Entry> data;

    public GraficaLinearM(){

    }
    public GraficaLinearM(List<TotalVentas> ventasD,String fechaM){
        this.VentasD=ventasD;
        this.FechaM=fechaM;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentgraficalinear, container, false);
        GLinear = (LineChart) rootView.findViewById(R.id.chart_bottom);
        GenerarGrafica();
        return rootView;
    }
    public void GenerarGrafica(){
        int mes=1,anno;
        int cont=0;
        String dateT="";
        for(int i=0;i<FechaM.length();i++){
            if((FechaM.charAt(i)+"").equals("/")){
                if (cont==0){
                    mes=Integer.parseInt(dateT);
                    dateT="";
                }
                cont++;
            }else {
                dateT = dateT + (FechaM.charAt(i));
            }
        }
        anno=Integer.parseInt(dateT);

        Calendar calendar=new GregorianCalendar();
        calendar.set(anno,mes-1,1);

        data = new ArrayList<Entry>();
        data.add(new Entry(1,0));
        boolean verificar=true;
        boolean verificarI=false;
        for(int i=0;i<VentasD.size();i++){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = simpleDateFormat.parse(VentasD.get(i).getFecha());
                float hora = (float) (date.getHours()+(date.getMinutes()*0.0166667)+(date.getSeconds()*0.000277778));
                simpleDateFormat = new SimpleDateFormat("dd");
                float dia=(float)(Integer.parseInt(simpleDateFormat.format(date))+(hora*0.0416667));
                data.add(new Entry(dia,((float)VentasD.get(i).getTotalV())));
                if(dia>28){
                    verificar=false;
                }
                if(dia<1){
                    verificarI=true;
                }
            }catch (Exception e){

            }
        }

        if(verificarI){
            data.remove(0);
        }
        if(verificar){
            data.add(new Entry(calendar.getActualMaximum(Calendar.DAY_OF_MONTH),0));
        }
        LineDataSet lineDataSet=new LineDataSet(data,"Total Vendido");
        lineDataSet.setColors(ColorTemplate.rgb("0090FD"));
        lineDataSet.setCircleColors(ColorTemplate.rgb("0090FD"));
        lineDataSet.setCircleRadius(6f);
        lineDataSet.setValueTextSize(11);
        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "";
            }
        });
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData Ldata=new LineData(dataSets);
        Description description=new Description();
        description.setText("");
        GLinear.setData(Ldata);
        GLinear.setDescription(description);
        //Para que las lineas sean Curbas
        List<ILineDataSet> sets = GLinear.getData().getDataSets();
        for (ILineDataSet iSet : sets) {

            LineDataSet set = (LineDataSet) iSet;
            set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                    ? LineDataSet.Mode.LINEAR
                    :  LineDataSet.Mode.HORIZONTAL_BEZIER);
        }
        GLinear.invalidate();
        GLinear.setDoubleTapToZoomEnabled(false);

        XAxis xAxis =GLinear.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMaximum(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        xAxis.setAxisMinimum(1);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int dias=((int) value);
                int horas=((int) (24 * (value - dias)));
                float auxH=(24 * (value - dias));
                int minutos=((int) (60 * ( auxH - horas)));
                float auxm=60 * ( auxH - horas);
                int segundos=((int) (60 * ( auxm - minutos)));
                String formato="";
                if(dias>9){
                    if(minutos>0){
                        if(minutos>9){
                            if(segundos>0){
                                if (segundos>9){
                                    formato=dias+" ["+horas+":"+minutos+":"+segundos+"]";
                                }else{
                                    formato=dias+" ["+horas+":"+minutos+":0"+segundos+"]";
                                }
                                xAxis.setTextSize(9);
                            }else{
                                formato=dias+" ["+horas+":"+minutos+"]";
                                xAxis.setTextSize(9);
                            }
                        }else{
                            if(segundos>0){
                                if(segundos>9){
                                    formato=dias+" ["+horas+":0"+minutos+":"+segundos+"]";
                                }else{
                                    formato=dias+" ["+horas+":0"+minutos+":0"+segundos+"]";
                                }
                                xAxis.setTextSize(8);
                            }else{
                                formato=dias+" ["+horas+":0"+minutos+"]";
                                xAxis.setTextSize(9);
                            }
                        }
                    }else{
                        formato=dias+"";
                        xAxis.setTextSize(11);
                    }
                }else{
                    if(minutos>0){
                        if(minutos>9){
                            if(segundos>0){
                                if (segundos>9){
                                    formato="0"+dias+" ["+horas+":"+minutos+":"+segundos+"]";
                                }else{
                                    formato="0"+dias+" ["+horas+":"+minutos+":0"+segundos+"]";
                                }
                                xAxis.setTextSize(9);
                            }else{
                                formato="0"+dias+" ["+horas+":"+minutos;
                                xAxis.setTextSize(9);
                            }
                        }else{
                            if(segundos>0){
                                if(segundos>9){
                                    formato="0"+dias+" ["+horas+":0"+minutos+":"+segundos+"]";
                                }else{
                                    formato="0"+dias+" ["+horas+":0"+minutos+":0"+segundos+"]";
                                }
                                xAxis.setTextSize(8);
                            }else{
                                formato="0"+dias+" ["+horas+":0"+minutos+"]";
                                xAxis.setTextSize(9);
                            }
                        }
                    }else{
                        formato="0"+dias+"";
                        xAxis.setTextSize(11);
                    }
                }
                return formato;
            }
        });

        YAxis LeftAxis = GLinear.getAxisLeft();
        LeftAxis.setDrawGridLines(false);
        LeftAxis.setAxisMinimum(0);
        LeftAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                NumberFormat mFormat = NumberFormat.getNumberInstance();
                return "$ "+mFormat.format(value);
            }
        });

        YAxis rigthAxis = GLinear.getAxisRight();
        rigthAxis.setEnabled(false);

        GLinear.animateY(900);
        try {
            XYMarkerViewLM mv = new XYMarkerViewLM(getActivity());
            mv.setChartView(GLinear);
            GLinear.setMarker(mv);
        }catch (Exception e){

        }

    }

}
