package com.pixels.Inventario.View.Activity.Gestion_Productos.RecyclerViewAdaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;

import java.util.List;

public class ProductosRecyclerViewAdapter extends RecyclerView.Adapter<ProductosRecyclerViewAdapter.ViewHolder> {
    private Context Context;
    private List<Producto> Productos;

    public ProductosRecyclerViewAdapter(Context context,List<Producto> productos){
        this.Context=context;
        this.Productos=productos;
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
        holder.cantidad.setText(""+Productos.get(position).getCantidad());
        if(Productos.get(position).getTipoC().equals("peso")){
            holder.unidad.setText(" Kg");
        }
        if(Productos.get(position).getTipoC().equals("unidad")){
            holder.unidad.setText("");
        }
        holder.costo.setText(""+Productos.get(position).getCosteP());
        holder.precio.setText(""+Productos.get(position).getPrecio());
    }
    @Override
    public int getItemCount() {
        return Productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView codigo,nombre,cantidad,unidad,costo,precio;
        ViewHolder(View view) {
            super(view);
            codigo = (TextView) view.findViewById(R.id.codigo);
            nombre= (TextView) view.findViewById(R.id.nombre);
            cantidad= (TextView) view.findViewById(R.id.cantidad);
            unidad= (TextView) view.findViewById(R.id.unidad);
            costo= (TextView) view.findViewById(R.id.costeP);
            precio= (TextView) view.findViewById(R.id.precio);
        }
    }

}
