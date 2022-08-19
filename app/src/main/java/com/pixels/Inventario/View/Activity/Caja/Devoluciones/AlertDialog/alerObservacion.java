package com.pixels.Inventario.View.Activity.Caja.Devoluciones.AlertDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;

public class alerObservacion {
    private devoluciones Context;
    public alerObservacion(devoluciones context){
        this.Context=context;
    }
    public void pedirObservaciones(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertobservaciones, null);
        builder.setView(view);
        builder.setTitle("Digite las Observaciones o Razones por las cuales se devuelve el producto");
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
