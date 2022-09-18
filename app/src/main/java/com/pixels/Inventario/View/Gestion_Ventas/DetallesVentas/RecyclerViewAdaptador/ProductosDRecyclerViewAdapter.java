package com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas.RecyclerViewAdaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.Model.DatosE.detallesPV;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas.DetallesVentas;

import java.util.List;


public class ProductosDRecyclerViewAdapter extends RecyclerView.Adapter<ProductosDRecyclerViewAdapter.ViewHolder> {
    private List<detallesPV> ProductosTotales;
    private DetallesVentas Context;
    public ProductosDRecyclerViewAdapter(DetallesVentas context,List<detallesPV> productos){
        this.Context=context;
        this.ProductosTotales=productos;
    }
    @Override
    public ProductosDRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productovendido, parent, false);
        return new ProductosDRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosDRecyclerViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ProductosTotales.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }
}
