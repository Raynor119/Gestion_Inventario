package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GLineas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.Widgets.XYMarkerViewLM;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class GraficaLinearA extends Fragment {
    public static List<TotalVentas> VentasD;

    private LineChart GLinear;
    private ArrayList<Entry> data;

    public static Context Context;

    public GraficaLinearA(){

    }
    public GraficaLinearA(List<TotalVentas> ventasD,Context context){
        this.VentasD=ventasD;
        this.Context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentgraficalinear, container, false);
        GLinear = (LineChart) rootView.findViewById(R.id.chart_bottom);
        GenerarGrafica();
        return rootView;
    }
    public void GenerarGrafica(){
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
                float mes=(float)((date.getMonth()+1)+(dia*0.0328767));
                data.add(new Entry(mes,((float)VentasD.get(i).getTotalV())));
                if(mes>10){
                    verificar=false;
                }
                if(mes<1){
                    verificarI=true;
                }
            }catch (Exception e){

            }
        }

        if(verificarI){
            data.remove(0);
        }
        if(verificar){
            data.add(new Entry(12,0));
        }
        LineDataSet lineDataSet=new LineDataSet(data,"Total Vendido");
        lineDataSet.setColors(ColorTemplate.rgb("0090FD"));
        lineDataSet.setCircleColors(ColorTemplate.rgb("0090FD"));
        lineDataSet.setCircleRadius(6f);
        lineDataSet.setValueTextSize(9);
        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                NumberFormat mFormat = NumberFormat.getNumberInstance();
                if(value==0){
                    return "";
                }else{
                    return "$ "+mFormat.format(value);
                }
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
        xAxis.setAxisMaximum(12);
        xAxis.setAxisMinimum(1);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int mes=((int) value);
                int dias=((int) (30.4167 *(value - mes)));
                double auxD=(30.4167 *(value - mes));
                int horas=((int) (24 * (auxD - dias)));
                double auxH=(24 * (auxD - dias));
                int minutos=((int) (60 * ( auxH - horas)));
                double auxm=60 * ( auxH - horas);
                int segundos=((int) (60 * ( auxm - minutos)));
                String formato="";
                if(dias>0){
                    if(dias>9){
                        if(minutos>0){
                            if(minutos>9){
                                if(segundos>0){
                                    if (segundos>9){
                                        formato=dias+" ["+horas+":"+minutos+":"+segundos+"]";
                                    }else{
                                        formato=dias+" ["+horas+":"+minutos+":0"+segundos+"]";
                                    }
                                    xAxis.setTextSize(6);
                                }else{
                                    formato=dias+" ["+horas+":"+minutos+"]";
                                    xAxis.setTextSize(7);
                                }
                            }else{
                                if(segundos>0){
                                    if(segundos>9){
                                        formato=dias+" ["+horas+":0"+minutos+":"+segundos+"]";
                                    }else{
                                        formato=dias+" ["+horas+":0"+minutos+":0"+segundos+"]";
                                    }
                                    xAxis.setTextSize(6);
                                }else{
                                    formato=dias+" ["+horas+":0"+minutos+"]";
                                    xAxis.setTextSize(7);
                                }
                            }
                        }else{
                            formato=dias+"";
                            xAxis.setTextSize(8);
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
                                    xAxis.setTextSize(6);
                                }else{
                                    formato="0"+dias+" ["+horas+":"+minutos;
                                    xAxis.setTextSize(7);
                                }
                            }else{
                                if(segundos>0){
                                    if(segundos>9){
                                        formato="0"+dias+" ["+horas+":0"+minutos+":"+segundos+"]";
                                    }else{
                                        formato="0"+dias+" ["+horas+":0"+minutos+":0"+segundos+"]";
                                    }
                                    xAxis.setTextSize(6);
                                }else{
                                    formato="0"+dias+" ["+horas+":0"+minutos+"]";
                                    xAxis.setTextSize(7);
                                }
                            }
                        }else{
                            formato="0"+dias+"";
                            xAxis.setTextSize(8);
                        }
                    }
                    if(mes==1){
                        formato="Ene"+" "+formato;
                    }
                    if(mes==2){
                        formato="Feb"+" "+formato;
                    }
                    if(mes==3){
                        formato="Mar"+" "+formato;
                    }
                    if(mes==4){
                        formato="Abr"+" "+formato;
                    }
                    if(mes==5){
                        formato="May"+" "+formato;
                    }
                    if(mes==6){
                        formato="Jun"+" "+formato;
                    }
                    if(mes==7){
                        formato="Jul"+" "+formato;
                    }
                    if(mes==8){
                        formato="Ago"+" "+formato;
                    }
                    if(mes==9){
                        formato="Sep"+" "+formato;
                    }
                    if(mes==10){
                        formato="Oct"+" "+formato;
                    }
                    if(mes==11){
                        formato="Nov"+" "+formato;
                    }
                    if(mes==12){
                        formato="Dic"+" "+formato;
                    }
                }else{
                    xAxis.setTextSize(10);
                    if(mes==1){
                        formato="Ene";
                    }
                    if(mes==2){
                        formato="Feb";
                    }
                    if(mes==3){
                        formato="Mar";
                    }
                    if(mes==4){
                        formato="Abr";
                    }
                    if(mes==5){
                        formato="May";
                    }
                    if(mes==6){
                        formato="Jun";
                    }
                    if(mes==7){
                        formato="Jul";
                    }
                    if(mes==8){
                        formato="Ago";
                    }
                    if(mes==9){
                        formato="Sep";
                    }
                    if(mes==10){
                        formato="Oct";
                    }
                    if(mes==11){
                        formato="Nov";
                    }
                    if(mes==12){
                        formato="Dic";
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
