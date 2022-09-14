package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.RecyclerViewAdaptador;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.Model.DatosE.Venta;
import com.pixels.Inventario.R;


import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.DiariasFragment;
import com.pixels.Inventario.View.Gestion_Ventas.VerVentas;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

public class VentasDiariasRecyclerViewAdapter extends RecyclerView.Adapter<VentasDiariasRecyclerViewAdapter.ViewHolder> {

    private List<TotalVentas> VentasTotales;
    private Context Context;

    public VentasDiariasRecyclerViewAdapter(List<TotalVentas> ventasTotales,Context fragment){
        this.VentasTotales=ventasTotales;
        this.Context=fragment;
    }
    @Override
    public VentasDiariasRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.venta_ver, parent, false);
        return new VentasDiariasRecyclerViewAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final VentasDiariasRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.CodigoV.setText(VentasTotales.get(position).getCodigoV()+"");
        holder.Fecha.setText(VentasTotales.get(position).getFecha()+"");
        holder.CantidadPV.setText(VentasTotales.get(position).getCProductoV()+"");
        NumberFormat formato= NumberFormat.getNumberInstance();
        BigDecimal bd = new BigDecimal(VentasTotales.get(position).getGananciaNeta());
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        holder.GananciaN.setText("$ "+formato.format(bd.doubleValue()));
        if(VentasTotales.get(position).getTotalD()>0){
            try{
                holder.imagen.setImageResource(R.mipmap.ventaid_adaptive_fore);
            }catch (Exception e){

            }
            holder.TotalP.setText("$ "+formato.format(VentasTotales.get(position).getPerdidaD()));
            holder.TotalD.setText("$ "+formato.format(VentasTotales.get(position).getTotalD()));
        }else{
            holder.LTotalD.setVisibility(ConstraintLayout.GONE);
            holder.LTotalP.setVisibility(ConstraintLayout.GONE);
        }
        holder.TotalV.setText("$ "+formato.format(VentasTotales.get(position).getTotalV()));
    }
    @Override
    public int getItemCount() {
        return VentasTotales.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView CodigoV,Fecha,CantidadPV,GananciaN,TotalP,TotalD,TotalV;
        final LinearLayout LTotalP,LTotalD;
        ImageView imagen=null;
        ViewHolder(View view) {
            super(view);
            CodigoV=(TextView) view.findViewById(R.id.codigo);
            Fecha=(TextView) view.findViewById(R.id.fecha);
            CantidadPV=(TextView) view.findViewById(R.id.CPV);
            GananciaN=(TextView) view.findViewById(R.id.ganaN);
            TotalP=(TextView) view.findViewById(R.id.TotalP);
            TotalD=(TextView) view.findViewById(R.id.TotalD);
            TotalV=(TextView) view.findViewById(R.id.TotalV);

            LTotalD=(LinearLayout) view.findViewById(R.id.LtotaD);
            LTotalP=(LinearLayout) view.findViewById(R.id.LtotaP);

            try{
                imagen=(ImageView) view.findViewById(R.id.imagen);
            }catch (Exception e){

            }

        }
    }

}
