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
public class XYMarkerViewLM extends MarkerView {

    private final TextView tvContent;


    public XYMarkerViewLM(Context context) {
        super(context, R.layout.marker_view);
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        float value=e.getX();
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