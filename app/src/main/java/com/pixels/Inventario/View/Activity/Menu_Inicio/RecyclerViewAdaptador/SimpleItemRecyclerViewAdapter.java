package com.pixels.Inventario.View.Activity.Menu_Inicio.RecyclerViewAdaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.opciones.OpcionesContent;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.View.Activity.Gestion_Productos.VerInventario;
import com.pixels.Inventario.View.Activity.Menu_Inicio.MenuInicio;

import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final MenuInicio mParentActivity;
    private final List<OpcionesContent> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            if((view.getTag()+"").equals("Caja")){

            }
            if((view.getTag()+"").equals("Ver Inventario de Productos")){
                if (mTwoPane) {
                    VerInventarioFragment fragment = new VerInventarioFragment();
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.opcion_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerInventario.class);
                    context.startActivity(intent);
                }
            }
            if((view.getTag()+"").equals("Ventas Realizadas")){

            }
            if((view.getTag()+"").equals("Ajustes")){

            }
            if((view.getTag()+"").equals("Salir")){
                mParentActivity.finish();
            }
        }
    };

    public SimpleItemRecyclerViewAdapter(MenuInicio parent,
                                         List<OpcionesContent> items,
                                         boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.opcion_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mValues.get(position).getOpcion().equals("Caja")){
            holder.imagen.setImageResource(R.mipmap.caja);
        }
        if(mValues.get(position).getOpcion().equals("Ver Inventario de Productos")){
            holder.imagen.setImageResource(R.mipmap.productos);
        }
        if(mValues.get(position).getOpcion().equals("Ventas Realizadas")){
            holder.imagen.setImageResource(R.mipmap.ventas);
        }
        if(mValues.get(position).getOpcion().equals("Ajustes")){
            holder.imagen.setImageResource(R.mipmap.ajustes);
        }
        if(mValues.get(position).getOpcion().equals("Salir")){
            holder.imagen.setImageResource(R.mipmap.salir);
        }
        holder.mContentView.setText(mValues.get(position).getOpcion());
        holder.itemView.setTag(mValues.get(position).getOpcion());
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mContentView;
        final ImageView imagen;

        ViewHolder(View view) {
            super(view);
            imagen=(ImageView) view.findViewById(R.id.imagen);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}