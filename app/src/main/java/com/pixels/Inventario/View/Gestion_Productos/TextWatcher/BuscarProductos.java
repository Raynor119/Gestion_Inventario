package com.pixels.Inventario.View.Gestion_Productos.TextWatcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.View.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.View.Gestion_Productos.RecyclerViewAdaptador.ProductosRecyclerViewAdapter;

import java.util.List;

public class BuscarProductos {
    public Context Context;
    public List<Producto> Productos;
    public VerInventarioFragment Activity;
    public RecyclerView ReciclerView;
    public BuscarProductos(Context context, List<Producto> productos, VerInventarioFragment activity, RecyclerView reciclerView){
        this.Context=context;
        this.Productos=productos;
        this.Activity=activity;
        this.ReciclerView=reciclerView;
    }
    public TextWatcher buscador(final EditText editText) {

        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(editText.getText().toString().equals("")){
                    ReciclerView.setAdapter(new ProductosRecyclerViewAdapter(Context,Productos,Activity));
                }else{
                    ReciclerView.setAdapter(null);
                }
            }
        };
    }
}
