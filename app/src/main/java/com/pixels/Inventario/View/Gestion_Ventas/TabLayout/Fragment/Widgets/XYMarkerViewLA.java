package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.Widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.pixels.Inventario.R;

import java.text.NumberFormat;


@SuppressLint("ViewConstructor")
public class XYMarkerViewLA extends MarkerView {

    private final TextView tvContent;


    public XYMarkerViewLA(Context context) {
        super(context, R.layout.marker_view);
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        float value=e.getX();
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
                        }else{
                            formato=dias+" ["+horas+":"+minutos+"]";
                        }
                    }else{
                        if(segundos>0){
                            if(segundos>9){
                                formato=dias+" ["+horas+":0"+minutos+":"+segundos+"]";
                            }else{
                                formato=dias+" ["+horas+":0"+minutos+":0"+segundos+"]";
                            }
                        }else{
                            formato=dias+" ["+horas+":0"+minutos+"]";
                        }
                    }
                }else{
                    formato=dias+"";
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
                        }else{
                            formato="0"+dias+" ["+horas+":"+minutos;
                        }
                    }else{
                        if(segundos>0){
                            if(segundos>9){
                                formato="0"+dias+" ["+horas+":0"+minutos+":"+segundos+"]";
                            }else{
                                formato="0"+dias+" ["+horas+":0"+minutos+":0"+segundos+"]";
                            }
                        }else{
                            formato="0"+dias+" ["+horas+":0"+minutos+"]";
                        }
                    }
                }else{
                    formato="0"+dias+"";
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
        NumberFormat mFormat = NumberFormat.getNumberInstance();
        formato="Fecha: "+formato+"\nTotal Vendido: $ "+mFormat.format(e.getY());
        tvContent.setText(""+formato);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}