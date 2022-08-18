package com.pixels.Inventario.View.Activity.Caja.Devoluciones.AlertDialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;

import com.pixels.Inventario.View.Activity.Caja.Devoluciones.RecyclerViewAdaptador.alertRecyclerViewAdapterD;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;

import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;

import java.util.List;

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
        ProductosRecyclerViewModel productos= ViewModelProviders.of(Context).get(ProductosRecyclerViewModel.class);
        productos.reset();
        productos.buscarProductos(Context);
        final Observer<List<Producto>> observer= new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                new android.os.Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        recycler.setAdapter(new alertRecyclerViewAdapterD(productos,Context,dialog, alertbuscarPV.this));
                        reiniciar(productos,dialog);
                    }
                },100);
            }
        };
        productos.getResultado().observe(Context,observer);
        dialog.show();

    }
    public void reiniciar(List<Producto> Productos,AlertDialog dialog){
        recycler.setAdapter(new alertRecyclerViewAdapterD(Productos,Context,dialog,alertbuscarPV.this));
    }
}
