package com.pixels.Inventario.View.Activity.Caja.Devoluciones.RecyclerViewAdaptador;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.R;

import com.pixels.Inventario.View.Activity.Caja.Devoluciones.AlertDialog.alerObservacion;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.AlertDialog.alertbuscarPV;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;

import java.text.NumberFormat;
import java.util.ArrayList;
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
                .inflate(R.layout.producto_ver, parent, false);
        return new alertRecyclerViewAdapterD.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final alertRecyclerViewAdapterD.ViewHolder holder, int position) {
        holder.codigo.setText(""+Productos.get(position).getCodigoP());
        holder.nombre.setText(""+Productos.get(position).getNombre());
        NumberFormat formato= NumberFormat.getNumberInstance();
        holder.titulo.setText("Total: ");
        if(Productos.get(position).getEstadoDevolucion().equals("si")){
            holder.titulo.setText("");
            holder.precio.setText(" Ya se Devolvio");
        }else {
            holder.precio.setText("$ "+formato.format(Productos.get(position).getPrecioPV()*Productos.get(position).getCantidadV()));
        }
        int positionn=position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean productorepi=false;

                for (int b=0;b<Fragment.Productos.size();b++){
                    if(Fragment.Productos.get(b).getCodigoP().equals(Productos.get(positionn).getCodigoP())){
                        productorepi=true;

                    }
                }

                if(productorepi){
                    Fragment.Codigo.setText("");
                    Fragment.CCodigo.setError("El producto ya esta regitrado para la devolucion");
                    Fragment.Codigo.setFocusableInTouchMode(true);
                    Fragment.Codigo.requestFocus();
                }else{
                    if(Productos.get(positionn).getEstadoDevolucion().equals("si")){
                        Fragment.Codigo.setText("");
                        Fragment.CCodigo.setError("El producto ya ha sido devuelto");
                        Fragment.Codigo.setFocusableInTouchMode(true);
                        Fragment.Codigo.requestFocus();
                    }else{
                        Fragment.indexProducto=positionn;
                        alerObservacion observacion=new alerObservacion(Fragment);
                        observacion.pedirObservaciones();
                        Fragment.iniciarRecyclerView();
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
        final TextView codigo,nombre,precio,titulo;
        ViewHolder(View view) {
            super(view);
            codigo = (TextView) view.findViewById(R.id.codigo);
            nombre= (TextView) view.findViewById(R.id.nombre);
            precio= (TextView) view.findViewById(R.id.precio);
            titulo= (TextView) view.findViewById(R.id.titulo);
        }
    }

}
