package com.pixels.Inventario.View.Caja.Devoluciones.AlertDialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.R;

import com.pixels.Inventario.View.Caja.Devoluciones.RecyclerViewAdaptador.alertRecyclerViewAdapterD;
import com.pixels.Inventario.View.Caja.Devoluciones.devoluciones;

public class alertbuscarPV {
    public devoluciones Context;
    public RecyclerView recycler;


    public alertbuscarPV(devoluciones context){
        this.Context=context;
    }
    public void buscarproductos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        //builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertverproductos, null);
        builder.setView(view);
        builder.setTitle("Buscar Productos");
        AlertDialog dialog = builder.create();
        recycler= view.findViewById(R.id.opcion_list);
        new android.os.Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                recycler.setAdapter(new alertRecyclerViewAdapterD(Context.ProductosV,Context,dialog, alertbuscarPV.this));
            }
        },100);
        dialog.show();

    }
}
