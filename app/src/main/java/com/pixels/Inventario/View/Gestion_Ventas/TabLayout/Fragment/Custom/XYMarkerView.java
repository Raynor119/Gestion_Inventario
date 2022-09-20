package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.Custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.pixels.Inventario.Model.DatosE.DatosColumn;
import com.pixels.Inventario.R;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ViewConstructor")
public class XYMarkerView extends MarkerView {

    private final TextView tvContent;
    private final List<DatosColumn> DatosP;

    public XYMarkerView(Context context, List<DatosColumn> datosP) {
        super(context, R.layout.marker_view);
        tvContent = findViewById(R.id.tvContent);
        this.DatosP=datosP;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        int i=(int) e.getX();
        tvContent.setText("x:"+DatosP.get(i).getNombre()+" y:"+ e.getY());

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}