package com.pixels.Inventario.View.Activity.Caja.RecyclerViewAdaptador;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.AlertDialog.alertbuscar;
import com.pixels.Inventario.View.Activity.Caja.AlertDialog.alertpeso;
import com.pixels.Inventario.View.Activity.Caja.Caja;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class alertRecyclerViewAdapter extends RecyclerView.Adapter<alertRecyclerViewAdapter.ViewHolder> {
    private List<Producto> Productos;
    private Caja Fragment;
    private  AlertDialog Dialog;
    private alertbuscar Alerbuscar;

    public alertRecyclerViewAdapter(List<Producto> productos, Caja fragment, AlertDialog dialog, alertbuscar alertbuscar){
        this.Productos=productos;
        this.Fragment=fragment;
        this.Dialog=dialog;
        this.Alerbuscar=alertbuscar;
    }
    @Override
    public alertRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_ver, parent, false);
        return new alertRecyclerViewAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final alertRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.codigo.setText(""+Productos.get(position).getCodigo());
        holder.nombre.setText(""+Productos.get(position).getNombre());
        NumberFormat formato= NumberFormat.getNumberInstance();
        holder.precio.setText("$ "+formato.format(Productos.get(position).getPrecio()));
        int positionn=position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean productorepi=false;
                List<Producto> productoslist=new ArrayList<>();
                productoslist.add(new Producto(Productos.get(positionn).getCodigo(),Productos.get(positionn).getNombre(),Productos.get(positionn).getCantidad(),Productos.get(positionn).getTipoC(),Productos.get(positionn).getCosteP(),Productos.get(positionn).getPrecio(),Productos.get(positionn).getIva()));
                int posicion=0;
                for (int b=0;b<Fragment.Productos.size();b++){
                    if(Fragment.Productos.get(b).getCodigo().equals(productoslist.get(0).getCodigo())){
                        productorepi=true;
                        posicion=b;
                    }
                }

                if(productorepi){
                    if(productoslist.get(0).getTipoC().equals("peso")){
                        Dialog.cancel();
                        alertpeso pedir=new alertpeso(Fragment,productoslist,Fragment.Productos.get(posicion).getCantidad(),false,posicion);
                        pedir.pedircantidad();
                    }
                    if(productoslist.get(0).getTipoC().equals("unitario")){
                        double cantiR=Fragment.Productos.get(posicion).getCantidad()+1;
                        int canti=(int) cantiR;
                        Fragment.Productos.get(posicion).setCantidad(canti);
                    }
                }else{
                    if(productoslist.get(0).getTipoC().equals("peso")){
                        Dialog.cancel();
                        alertpeso pedir=new alertpeso(Fragment,productoslist,0,true,0);
                        pedir.pedircantidad();
                    }
                    if(productoslist.get(0).getTipoC().equals("unitario")){
                        Fragment.Productos.add(new Producto(productoslist.get(0).getCodigo(),productoslist.get(0).getNombre(),1,productoslist.get(0).getTipoC(),productoslist.get(0).getCosteP(),productoslist.get(0).getPrecio(),productoslist.get(0).getIva()));
                    }
                }
                Fragment.iniciarRecyclerView();
                Fragment.Codigo.setText("");
                Fragment.Codigo.setFocusableInTouchMode(true);
                Fragment.Codigo.requestFocus();
                Dialog.cancel();
            }
        });
    }
    @Override
    public int getItemCount() {
        return Productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView codigo,nombre,precio;
        ViewHolder(View view) {
            super(view);
            codigo = (TextView) view.findViewById(R.id.codigo);
            nombre= (TextView) view.findViewById(R.id.nombre);
            precio= (TextView) view.findViewById(R.id.precio);
        }
    }

}