package com.pixels.Inventario.View.Caja.AlertDialog.TextWatcher;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.View.Caja.AlertDialog.alertbuscar;
import com.pixels.Inventario.View.Caja.Caja;
import com.pixels.Inventario.View.Caja.RecyclerViewAdaptador.alertRecyclerViewAdapter;
import com.pixels.Inventario.View.Gestion_Productos.RecyclerViewAdaptador.ProductosRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Buscarproduct {
    public Caja Context;
    public List<Producto> Productos;
    public RecyclerView ReciclerView;
    public alertbuscar Activity;

    public AlertDialog Dialog;

    public Buscarproduct(Caja context, List<Producto> productos, alertbuscar activity, RecyclerView reciclerView,AlertDialog dialog){
        this.Context=context;
        this.Productos=productos;
        this.Activity=activity;
        this.ReciclerView=reciclerView;
        this.Dialog=dialog;
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
                    ReciclerView.setAdapter(new alertRecyclerViewAdapter(Productos,Context,Dialog,Activity));
                }else{
                    List<Producto> listaF=new ArrayList<>();
                    String Sfiltro=editText.getText().toString();
                    ReciclerView.setAdapter(null);
                    for (int ii=0;ii<Productos.size();ii++){
                        String codigo=Productos.get(ii).getCodigo();
                        String nombre=Productos.get(ii).getNombre();
                        boolean verificarC=codigo.contains(Sfiltro);
                        boolean verificarN=nombre.contains(Sfiltro);
                        if(verificarC || verificarN){
                            listaF.add(new Producto(Productos.get(ii).getCodigo(),Productos.get(ii).getNombre(),Productos.get(ii).getCantidad(),Productos.get(ii).getTipoC(),Productos.get(ii).getCosteP(),Productos.get(ii).getPrecio(),Productos.get(ii).getIva()));
                        }
                    }
                    ReciclerView.setAdapter(new alertRecyclerViewAdapter(listaF,Context,Dialog,Activity));
                }
            }

        };
    }
}
