package com.pixels.Inventario.View.Activity.Caja.RecyclerViewAdaptador;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;

import java.text.NumberFormat;

public class productoVRecyclerViewAdapter extends RecyclerView.Adapter<productoVRecyclerViewAdapter.ViewHolder> {

    private Caja activity;
    private  productoVRecyclerViewAdapter.ViewHolder botonprecionado=null;

    public productoVRecyclerViewAdapter(Caja fragment){
        this.activity=fragment;
    }
    @Override
    public productoVRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_caja, parent, false);
        return new productoVRecyclerViewAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final productoVRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.codigo.setText(""+activity.Productos.get(position).getCodigo());
        holder.nombre.setText(""+activity.Productos.get(position).getNombre());
        if(activity.Productos.get(position).getTipoC().equals("peso")){
            holder.cantidad.setText(""+activity.Productos.get(position).getCantidad()+" Kg");
            holder.cantidadmod.setText(""+activity.Productos.get(position).getCantidad());
        }
        if(activity.Productos.get(position).getTipoC().equals("unitario")){
            int canti=(int) activity.Productos.get(position).getCantidad();
            holder.cantidad.setText(""+canti);
            holder.cantidadmod.setText(""+canti);
        }
        NumberFormat formato= NumberFormat.getNumberInstance();
        holder.precio.setText("$ "+formato.format(activity.Productos.get(position).getPrecio()));

        holder.Subtotal.setText("$ "+formato.format(activity.Productos.get(position).getPrecio()));
        holder.opciones.setVisibility(ConstraintLayout.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.opciones.getVisibility()==ConstraintLayout.GONE){
                    if(botonprecionado!=null){
                        botonprecionado.opciones.setVisibility(ConstraintLayout.GONE);
                    }
                    holder.opciones.setVisibility(ConstraintLayout.VISIBLE);
                    botonprecionado = holder;
                }else{
                    holder.opciones.setVisibility(ConstraintLayout.GONE);
                    activity.iniciarRecyclerView();
                }

            }
        });
        final int poscion=position;
        holder.mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "mas", Toast.LENGTH_LONG).show();
            }
        });
        holder.menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "menos", Toast.LENGTH_LONG).show();
            }
        });
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.Productos.remove(poscion);
                activity.iniciarRecyclerView();
            }
        });

    }
    @Override
    public int getItemCount() {
        return activity.Productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView codigo,nombre,cantidad,precio,Subtotal,cantidadmod;
        final RelativeLayout opciones;
        final CardView eliminar,menos,mas;
        ViewHolder(View view) {
            super(view);
            codigo = (TextView) view.findViewById(R.id.codigo);
            nombre= (TextView) view.findViewById(R.id.nombre);
            cantidad= (TextView) view.findViewById(R.id.cantidad);
            precio= (TextView) view.findViewById(R.id.Precio);
            Subtotal= (TextView) view.findViewById(R.id.SubTotal);
            opciones= (RelativeLayout) view.findViewById(R.id.opciones);
            eliminar= (CardView) view.findViewById(R.id.eliminar);
            cantidadmod=(TextView) view.findViewById(R.id.cantidadmod);
            menos= (CardView) view.findViewById(R.id.menos);
            mas=(CardView) view.findViewById(R.id.mas);
        }
    }

}
