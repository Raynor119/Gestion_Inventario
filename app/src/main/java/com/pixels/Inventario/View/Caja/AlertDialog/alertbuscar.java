package com.pixels.Inventario.View.Caja.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Caja.AlertDialog.TextWatcher.Buscarproduct;
import com.pixels.Inventario.View.Caja.Caja;
import com.pixels.Inventario.View.Caja.RecyclerViewAdaptador.alertRecyclerViewAdapter;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;

import java.util.List;

public class alertbuscar {
    public Caja Context;
    public RecyclerView recycler;

    public TextInputEditText Buscardor;
    public TextInputLayout BBuscador;

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
        recycler= view.findViewById(R.id.opcion_list);
        Buscardor = view.findViewById(R.id.codigoB);
        BBuscador = view.findViewById(R.id.EditCodigoB);
        ProductosRecyclerViewModel productos= ViewModelProviders.of(Context).get(ProductosRecyclerViewModel.class);
        productos.reset();
        productos.buscarProductos(Context);
        final Observer<List<Producto>> observer= new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                new android.os.Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if(Buscardor.getText().toString().equals("")){
                            recycler.setAdapter(new alertRecyclerViewAdapter(productos,Context,dialog,alertbuscar.this));
                            Buscarproduct buscar=new Buscarproduct(Context,productos,alertbuscar.this,recycler,dialog);
                            Buscardor.addTextChangedListener(buscar.buscador(Buscardor));
                            reiniciar(productos,dialog);
                        }else{
                            Buscarproduct buscar=new Buscarproduct(Context,productos,alertbuscar.this,recycler,dialog);
                            Buscardor.addTextChangedListener(buscar.buscador(Buscardor));
                            reiniciar(productos,dialog);
                        }
                    }
                },100);
            }
        };
        productos.getResultado().observe(Context,observer);
        dialog.show();

    }
    public void reiniciar(List<Producto> Productos,AlertDialog dialog){
        recycler.setAdapter(new alertRecyclerViewAdapter(Productos,Context,dialog,alertbuscar.this));
        Buscarproduct buscar=new Buscarproduct(Context,Productos,alertbuscar.this,recycler,dialog);
        Buscardor.addTextChangedListener(buscar.buscador(Buscardor));
    }
}
