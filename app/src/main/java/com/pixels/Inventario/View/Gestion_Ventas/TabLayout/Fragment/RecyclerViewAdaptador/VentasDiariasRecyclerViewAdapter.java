package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.RecyclerViewAdaptador;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.R;


import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.DiariasFragment;


import java.util.List;

public class VentasDiariasRecyclerViewAdapter extends RecyclerView.Adapter<VentasDiariasRecyclerViewAdapter.ViewHolder> {

    private List<TotalVentas> VentasTotales;
    private DiariasFragment Fragment;

    public VentasDiariasRecyclerViewAdapter(List<TotalVentas> ventasTotales,DiariasFragment fragment){
        this.VentasTotales=ventasTotales;
        this.Fragment=fragment;
    }
    @Override
    public VentasDiariasRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.venta_ver, parent, false);
        return new VentasDiariasRecyclerViewAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final VentasDiariasRecyclerViewAdapter.ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return VentasTotales.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView CodigoV,Fecha,CantidadPV,GananciaN,TotalP,TotalD,TotalV;
        final LinearLayout LTotalP,LTotalD;
        ViewHolder(View view) {
            super(view);

        }
    }

}
