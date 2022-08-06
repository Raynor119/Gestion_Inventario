package com.pixels.Inventario.View.Activity.Caja.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;
import com.pixels.Inventario.View.Activity.Caja.RecyclerViewAdaptador.alertRecyclerViewAdapter;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;

import java.util.List;

public class alertbuscar {
    public Caja Context;
    public alertbuscar(Caja context){
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
        RecyclerView recycler= view.findViewById(R.id.opcion_list);
        ProductosRecyclerViewModel productos= ViewModelProviders.of(Context).get(ProductosRecyclerViewModel.class);
        productos.reset();
        productos.buscarProductos(Context);
        final Observer<List<Producto>> observer= new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                recycler.setAdapter(new alertRecyclerViewAdapter(productos,Context,dialog));
            }
        };
        productos.getResultado().observe(Context,observer);
        dialog.show();
    }
}
