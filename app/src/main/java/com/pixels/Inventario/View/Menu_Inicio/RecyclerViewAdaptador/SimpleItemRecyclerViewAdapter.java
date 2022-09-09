package com.pixels.Inventario.View.Menu_Inicio.RecyclerViewAdaptador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.OpcionesContent;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Ajustes.Ajustes;
import com.pixels.Inventario.View.Caja.Caja;
import com.pixels.Inventario.View.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.View.Gestion_Productos.VerInventario;
import com.pixels.Inventario.View.Gestion_Ventas.Fragment.VerVentasFragment;
import com.pixels.Inventario.View.Gestion_Ventas.VerVentas;
import com.pixels.Inventario.View.InicioA.Configuracion_Inicial.Fragment.InicioBlanco;
import com.pixels.Inventario.View.Menu_Inicio.MenuInicio;

import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final MenuInicio mParentActivity;
    private final List<OpcionesContent> mValues;
    private final boolean mTwoPane;
    private VerInventarioFragment fragment;
    private VerVentasFragment fragment1;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int Id=Integer.parseInt((view.getTag()+""));
            if(Id==1){
                SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(mParentActivity);
                boolean bloqueo = myPreferences.getBoolean("bloqueoA",false);
                if(bloqueo){
                    if (mTwoPane) {
                        InicioBlanco fragment=new InicioBlanco();
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.opcion_detail_container, fragment)
                                .commit();
                    }
                }
                Context context = view.getContext();
                Intent intent = new Intent(context, Caja.class);
                context.startActivity(intent);
            }
            if(Id==2){
                if (mTwoPane) {
                    fragment = new VerInventarioFragment(mParentActivity);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.opcion_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerInventario.class);
                    context.startActivity(intent);
                }
            }
            if(Id==3){
                if (mTwoPane) {
                    fragment1 = new VerVentasFragment();
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.opcion_detail_container, fragment1)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerVentas.class);
                    context.startActivity(intent);
                }
            }
            if(Id==4){
                SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(mParentActivity);
                boolean bloqueo = myPreferences.getBoolean("bloqueoA",false);
                if(bloqueo){
                    if (mTwoPane) {
                        InicioBlanco fragment=new InicioBlanco();
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.opcion_detail_container, fragment)
                                .commit();
                    }
                }
                Context context = view.getContext();
                Intent intent = new Intent(context, Ajustes.class);
                context.startActivity(intent);
                Ajustes.MenuInicioA=mParentActivity;
            }
            if(Id==5){
                android.os.Process.killProcess(android.os.Process.myPid());
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
        if(mValues.get(position).getId()==1){
            holder.imagen.setImageResource(R.mipmap.caja);
        }
        if(mValues.get(position).getId()==2){
            holder.imagen.setImageResource(R.mipmap.productos);
        }
        if(mValues.get(position).getId()==3){
            holder.imagen.setImageResource(R.mipmap.ventas);
        }
        if(mValues.get(position).getId()==4){
            holder.imagen.setImageResource(R.mipmap.ajustes);
        }
        if(mValues.get(position).getId()==5){
            holder.imagen.setImageResource(R.mipmap.salir);
        }
        holder.mContentView.setText(mValues.get(position).getOpcion());
        holder.itemView.setTag(mValues.get(position).getId());
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