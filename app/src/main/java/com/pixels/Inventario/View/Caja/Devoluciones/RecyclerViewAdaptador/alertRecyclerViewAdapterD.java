package com.pixels.Inventario.View.Caja.Devoluciones.RecyclerViewAdaptador;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.R;

import com.pixels.Inventario.View.Caja.Devoluciones.AlertDialog.alerObservacion;
import com.pixels.Inventario.View.Caja.Devoluciones.AlertDialog.alertCantidadD;
import com.pixels.Inventario.View.Caja.Devoluciones.AlertDialog.alertbuscarPV;
import com.pixels.Inventario.View.Caja.Devoluciones.devoluciones;

import java.text.NumberFormat;
import java.util.List;


public class alertRecyclerViewAdapterD extends RecyclerView.Adapter<alertRecyclerViewAdapterD.ViewHolder> {
    private List<VentasProductoD> Productos;
    private devoluciones Fragment;
    private AlertDialog Dialog;
    private alertbuscarPV Alerbuscar;

    public alertRecyclerViewAdapterD(List<VentasProductoD> productos, devoluciones fragment, AlertDialog dialog, alertbuscarPV alertbuscar){
        this.Productos=productos;
        this.Fragment=fragment;
        this.Dialog=dialog;
        this.Alerbuscar=alertbuscar;
    }
    @Override
    public alertRecyclerViewAdapterD.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productod_ver, parent, false);
        return new alertRecyclerViewAdapterD.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final alertRecyclerViewAdapterD.ViewHolder holder, int position) {
        holder.codigo.setText(""+Productos.get(position).getCodigoP());
        holder.nombre.setText(""+Productos.get(position).getNombre());
        NumberFormat formato= NumberFormat.getNumberInstance();
        holder.titulo.setText("Total: ");
        holder.cantidadV.setText(""+Productos.get(position).getCantidadV());
        holder.cantidadD.setText(""+Productos.get(position).getCantidadD());
        if(Productos.get(position).getEstadoDevolucion().equals("si") && (Productos.get(position).getCantidadD()==Productos.get(position).getCantidadV())){
            holder.titulo.setText("");
            holder.precio.setText(" Ya todo se Devolvio");
            holder.LcantidadD.setVisibility(ConstraintLayout.GONE);
            holder.LcantidadV.setVisibility(ConstraintLayout.GONE);
        }else {
            holder.precio.setText("$ "+formato.format(Productos.get(position).getPrecioPV()*Productos.get(position).getCantidadV()));
        }
        int positionn=position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean productorepi=false;
                int indexP=0;
                for (int b=0;b<Fragment.Productos.size();b++){
                    if((Fragment.Productos.get(b).getId()+"").equals(Productos.get(positionn).getId()+"")){
                        productorepi=true;
                        indexP=b;
                    }
                }
                if(productorepi){
                    Fragment.indexProducto=indexP;
                    alertCantidadD alert=new alertCantidadD(Fragment);
                    alert.pedirCantidadD(true,positionn);
                    Fragment.Codigo.setText("");
                    Fragment.Codigo.setFocusableInTouchMode(true);
                    Fragment.Codigo.requestFocus();
                }else{
                    if(Productos.get(positionn).getEstadoDevolucion().equals("si") && (Productos.get(positionn).getCantidadD()==Productos.get(positionn).getCantidadV())){
                        Fragment.Codigo.setText("");
                        Fragment.CCodigo.setError("El producto ya ha sido devuelto en su totalidad");
                        Fragment.Codigo.setFocusableInTouchMode(true);
                        Fragment.Codigo.requestFocus();
                    }else{
                        Fragment.indexProducto=positionn;
                        alertCantidadD alert=new alertCantidadD(Fragment);
                        alert.pedirCantidadD(false,positionn);
                        Fragment.Codigo.setText("");
                        Fragment.Codigo.setFocusableInTouchMode(true);
                        Fragment.Codigo.requestFocus();
                    }
                }
                Dialog.cancel();
            }
        });
    }
    @Override
    public int getItemCount() {
        return Productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView codigo,nombre,cantidadV,cantidadD,precio,titulo;
        final LinearLayout LcantidadV,LcantidadD;
        ViewHolder(View view) {
            super(view);
            codigo = (TextView) view.findViewById(R.id.codigo);
            nombre= (TextView) view.findViewById(R.id.nombre);
            cantidadV= (TextView) view.findViewById(R.id.cantidad);
            cantidadD= (TextView) view.findViewById(R.id.cantidadD);
            LcantidadV= (LinearLayout) view.findViewById(R.id.canti);
            LcantidadD= (LinearLayout) view.findViewById(R.id.cantiD);
            precio= (TextView) view.findViewById(R.id.precio);
            titulo= (TextView) view.findViewById(R.id.titulo);
        }
    }

}
