package com.pixels.Inventario.View.Activity.Ajustes.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.AjustesContent;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Ajustes.Ajustes;
import com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionBaseDatos.AlertDialog.alertcambiarbasedatos;
import com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionContra.configContra;
import com.pixels.Inventario.View.Activity.Ajustes.EliminarBaseDatos.alertEliminarBD;
import com.pixels.Inventario.View.Activity.Ajustes.ExportarDatos.ExportarDatos;
import com.pixels.Inventario.View.Activity.Ajustes.ImportarDatos.ImportarDatos;

import java.util.List;

public class AjustesRecyclerViewAdapter extends RecyclerView.Adapter<AjustesRecyclerViewAdapter.ViewHolder> {
    private final Ajustes mParentActivity;
    private final List<AjustesContent> mValues;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int Id=Integer.parseInt((view.getTag()+""));
            if(Id==1){
                Context context = view.getContext();
                Intent intent = new Intent(context,configContra.class);
                intent.putExtra("fragment","1");
                context.startActivity(intent);
                configContra.Context=mParentActivity;
            }
            if(Id==2){
                alertcambiarbasedatos alert=new alertcambiarbasedatos(mParentActivity);
                alert.CambiarBaseDatos();
            }
            if(Id==3){
                Context context = view.getContext();
                Intent intent= new Intent(context, ImportarDatos.class);
                context.startActivity(intent);
            }
            if(Id==4){
                Context context = view.getContext();
                Intent intent= new Intent(context, ExportarDatos.class);
                context.startActivity(intent);
            }
            if(Id==5){
                alertEliminarBD alert= new alertEliminarBD(mParentActivity);
                alert.EliminarBaseDatos();
            }
            if(Id==6){
                Context context = view.getContext();
                Intent intent = new Intent(context,configContra.class);
                intent.putExtra("fragment","6");
                context.startActivity(intent);
                configContra.Context=mParentActivity;
            }
        }
    };

    public AjustesRecyclerViewAdapter(Ajustes parent,
                                         List<AjustesContent> items) {
        mValues = items;
        mParentActivity = parent;
    }

    @Override
    public AjustesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ajustes_list_content, parent, false);
        return new AjustesRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AjustesRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.Ajustes.setText(mValues.get(position).getAjuste());
        holder.mContentView.setText(mValues.get(position).getContenido());
        holder.itemView.setTag(mValues.get(position).getId());
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView Ajustes,mContentView;

        ViewHolder(View view) {
            super(view);
            Ajustes= (TextView) view.findViewById(R.id.ajusteso);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}
