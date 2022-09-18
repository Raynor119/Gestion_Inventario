package com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas.RecyclerViewAdaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.Model.DatosE.detallesPV;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas.DetallesVentas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
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
        holder.Tcodigo.setText(""+ProductosTotales.get(position).getCodigoP());
        holder.Tnombre.setText(""+ProductosTotales.get(position).getNombre());
        if(ProductosTotales.get(position).getTipoC().equals("unitario")){
            holder.TCantidadV.setText(""+((int) ProductosTotales.get(position).getCantidadV()));
            holder.TCAntidadD.setText(""+((int) ProductosTotales.get(position).getCantidadD()));
        }else{
            BigDecimal bd = new BigDecimal(ProductosTotales.get(position).getCantidadV());
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            BigDecimal bd1 = new BigDecimal(ProductosTotales.get(position).getCantidadD());
            bd1 = bd1.setScale(3, RoundingMode.HALF_UP);
            if(ProductosTotales.get(position).getTipoC().equals("peso")){
                holder.TCantidadV.setText(""+bd.doubleValue()+" Kg");
                holder.TCAntidadD.setText(""+bd1.doubleValue()+" Kg");
            }else{
                holder.TCantidadV.setText(""+bd.doubleValue()+"");
                holder.TCAntidadD.setText(""+bd1.doubleValue()+"");
            }
        }
        holder.TObservacionD.setText(""+ProductosTotales.get(position).getObservacionD());
        if(ProductosTotales.get(position).getCantidadD()==0){
            holder.LDevoC.setVisibility(ConstraintLayout.GONE);
            holder.LDevoObser.setVisibility(ConstraintLayout.GONE);
        }else{
            holder.icono.setImageResource(R.mipmap.cajad_adaptive_fore);
            if(ProductosTotales.get(position).getCantidadD()==ProductosTotales.get(position).getCantidadV()){
                holder.icono.setBackgroundResource(R.drawable.login_button_bkdt);
            }else{
                holder.icono.setBackgroundResource(R.drawable.login_button_bkerror);
            }
        }
        NumberFormat formato= NumberFormat.getNumberInstance();
        holder.Tprecio.setText("$ "+formato.format(ProductosTotales.get(position).getPrecioPV()));
    }

    @Override
    public int getItemCount() {
        return ProductosTotales.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        final public ImageView icono;
        final public TextView Tcodigo,Tnombre,TCantidadV,TCAntidadD,TObservacionD,Tprecio;
        final public LinearLayout LDevoC,LDevoObser;
        ViewHolder(View view) {
            super(view);
            icono=(ImageView) view.findViewById(R.id.imagen);
            Tcodigo=(TextView) view.findViewById(R.id.codigo);
            Tnombre=(TextView) view.findViewById(R.id.nombre);
            TCantidadV=(TextView) view.findViewById(R.id.CantidadV);
            TCAntidadD=(TextView) view.findViewById(R.id.CAntidadD);
            LDevoC=(LinearLayout) view.findViewById(R.id.LDevoC);
            TObservacionD=(TextView) view.findViewById(R.id.ObservacionD);
            LDevoObser=(LinearLayout) view.findViewById(R.id.LDevoObser);
            Tprecio=(TextView) view.findViewById(R.id.precio);
        }
    }
}
