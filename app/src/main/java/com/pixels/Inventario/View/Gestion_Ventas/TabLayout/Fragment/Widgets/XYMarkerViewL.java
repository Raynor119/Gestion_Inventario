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
public class XYMarkerViewL extends MarkerView {

    private final TextView tvContent;


    public XYMarkerViewL(Context context) {
        super(context, R.layout.marker_view);
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        float value=e.getX();
        int horas=((int) value);
        int minutos=((int) (60 * (value - horas)));

        String formato="";
        if(minutos>9){
            formato=""+horas+":"+minutos;
        }else{
            formato=""+horas+":0"+minutos;
        }
        NumberFormat mFormat = NumberFormat.getNumberInstance();
        formato="Hora: "+formato+"\nTotal Vendido: $ "+mFormat.format(e.getY());
        tvContent.setText(""+formato);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}