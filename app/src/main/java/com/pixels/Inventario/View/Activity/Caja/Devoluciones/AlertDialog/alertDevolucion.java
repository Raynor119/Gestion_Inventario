package com.pixels.Inventario.View.Activity.Caja.Devoluciones.AlertDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;

import java.text.NumberFormat;
import java.util.ArrayList;

public class alertDevolucion {
    private int Total;
    private devoluciones Context;
    public alertDevolucion(devoluciones context,int total){
        this.Context=context;
        this.Total=total;
    }
    public void totalD(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        builder.setTitle("Devolucion Realizada");
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertventafinalizada, null);
        NumberFormat formato= NumberFormat.getNumberInstance();
        builder.setView(view);
        TextView cambio=(TextView) view.findViewById(R.id.cambio);
        TextView texto=(TextView) view.findViewById(R.id.text);
        texto.setText("El Total a Devolver es de ");
        cambio.setText("$"+formato.format(Total));
        builder.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Context.Productos=new ArrayList<>();
                Context.iniciarRecyclerView();
                Context.finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
