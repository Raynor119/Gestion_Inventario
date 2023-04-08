package com.pixels.Inventario.View.Gestion_Productos.TextWatcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.View.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.View.Gestion_Productos.RecyclerViewAdaptador.ProductosRecyclerViewAdapter;

import java.util.ArrayList;
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
                    List<Producto> listaF=new ArrayList<>();
                    String Sfiltro=editText.getText().toString();
                    ReciclerView.setAdapter(null);
                    for (int ii=0;ii<Productos.size();ii++){
                        String codigo=Productos.get(ii).getCodigo();
                        String auxiliarc="";
                        String nombre=Productos.get(ii).getNombre();
                        String auxiliar="";
                        boolean verificarC=false;
                        boolean verificarN=false;
                        if(codigo.length()>=Sfiltro.length()){
                            for(int b=0;b<Sfiltro.length();b++){
                                auxiliarc=auxiliarc+codigo.charAt(b);
                            }
                            if(Sfiltro.equals(auxiliarc+"")){
                                verificarC=true;
                            }
                        }
                        if(nombre.length()>=Sfiltro.length()){
                            for(int b=0;b<Sfiltro.length();b++){
                                auxiliar=auxiliar+nombre.charAt(b);
                            }
                            if (Sfiltro.equals(auxiliar+"")){
                                verificarN=true;
                            }
                        }
                        if(verificarC || verificarN){
                            listaF.add(new Producto(Productos.get(ii).getCodigo(),Productos.get(ii).getNombre(),Productos.get(ii).getCantidad(),Productos.get(ii).getTipoC(),Productos.get(ii).getCosteP(),Productos.get(ii).getPrecio(),Productos.get(ii).getIva()));
                        }
                    }
                    ReciclerView.setAdapter(new ProductosRecyclerViewAdapter(Context,listaF,Activity));
                }
            }
        };
    }
    public void filtro(final EditText editText){
        List<Producto> listaF=new ArrayList<>();
        String Sfiltro=editText.getText().toString();
        ReciclerView.setAdapter(null);
        for (int ii=0;ii<Productos.size();ii++){
            String codigo=Productos.get(ii).getCodigo();
            String auxiliarc="";
            String nombre=Productos.get(ii).getNombre();
            String auxiliar="";
            boolean verificarC=false;
            boolean verificarN=false;
            if(codigo.length()>=Sfiltro.length()){
                for(int b=0;b<Sfiltro.length();b++){
                    auxiliarc=auxiliarc+codigo.charAt(b);
                }
                if(Sfiltro.equals(auxiliarc+"")){
                    verificarC=true;
                }
            }
            if(nombre.length()>=Sfiltro.length()){
                for(int b=0;b<Sfiltro.length();b++){
                    auxiliar=auxiliar+nombre.charAt(b);
                }
                if (Sfiltro.equals(auxiliar+"")){
                    verificarN=true;
                }
            }
            if(verificarC || verificarN){
                listaF.add(new Producto(Productos.get(ii).getCodigo(),Productos.get(ii).getNombre(),Productos.get(ii).getCantidad(),Productos.get(ii).getTipoC(),Productos.get(ii).getCosteP(),Productos.get(ii).getPrecio(),Productos.get(ii).getIva()));
            }
        }
        ReciclerView.setAdapter(new ProductosRecyclerViewAdapter(Context,listaF,Activity));
    }
}
