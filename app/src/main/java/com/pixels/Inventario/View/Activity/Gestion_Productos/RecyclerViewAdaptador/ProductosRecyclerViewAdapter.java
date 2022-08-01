package com.pixels.Inventario.View.Activity.Gestion_Productos.RecyclerViewAdaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AlertDialog.alertEliminar;
import com.pixels.Inventario.View.Activity.Gestion_Productos.EditarProductos.EditarProducto;
import com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment.VerInventarioFragment;

import java.text.NumberFormat;
import java.util.List;

public class ProductosRecyclerViewAdapter extends RecyclerView.Adapter<ProductosRecyclerViewAdapter.ViewHolder> {
    private Context Context;
    private List<Producto> Productos;
    private VerInventarioFragment Fragment;
    private  ProductosRecyclerViewAdapter.ViewHolder botonprecionado=null;

    public ProductosRecyclerViewAdapter(Context context,List<Producto> productos,VerInventarioFragment fragment){
        this.Context=context;
        this.Productos=productos;
        this.Fragment=fragment;
    }
    @Override
    public ProductosRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_detail_container, parent, false);
        return new ProductosRecyclerViewAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ProductosRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.codigo.setText(""+Productos.get(position).getCodigo());
        holder.nombre.setText(""+Productos.get(position).getNombre());
        if(Productos.get(position).getTipoC().equals("peso")){
            holder.unidad.setText(" Kg");
            holder.cantidad.setText(""+Productos.get(position).getCantidad());
        }
        if(Productos.get(position).getTipoC().equals("unitario")){
            holder.unidad.setText("");
            int canti=(int) Productos.get(position).getCantidad();
            holder.cantidad.setText(""+canti);
        }
        NumberFormat formato= NumberFormat.getNumberInstance();
        holder.costo.setText("$ "+formato.format(Productos.get(position).getCosteP()));
        holder.precio.setText("$ "+formato.format(Productos.get(position).getPrecio()));
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
                    Fragment.iniciarRecyclerView();
                }

            }
        });
        final int poscion=position;
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertEliminar alert=new alertEliminar(Context,Productos.get(poscion).getCodigo(),Fragment);
                alert.AlertDialogEliminar();
            }
        });
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Context, EditarProducto.class);
                intent.putExtra("codigo",Productos.get(poscion).getCodigo());
                Context.startActivity(intent);
                EditarProducto.verproductos=Fragment;
            }
        });
    }
    @Override
    public int getItemCount() {
        return Productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView codigo,nombre,cantidad,unidad,costo,precio;
        final LinearLayout opciones;
        final CardView eliminar,editar;
        ViewHolder(View view) {
            super(view);
            codigo = (TextView) view.findViewById(R.id.codigo);
            nombre= (TextView) view.findViewById(R.id.nombre);
            cantidad= (TextView) view.findViewById(R.id.cantidad);
            unidad= (TextView) view.findViewById(R.id.unidad);
            costo= (TextView) view.findViewById(R.id.costeP);
            precio= (TextView) view.findViewById(R.id.precio);
            opciones= (LinearLayout) view.findViewById(R.id.opciones);
            eliminar= (CardView) view.findViewById(R.id.eliminar);
            editar= (CardView) view.findViewById(R.id.editar);
        }
    }

}
