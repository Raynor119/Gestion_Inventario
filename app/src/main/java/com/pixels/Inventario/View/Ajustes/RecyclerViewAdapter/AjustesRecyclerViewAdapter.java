package com.pixels.Inventario.View.Ajustes.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.Inventario.Model.DatosE.AjustesContent;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Ajustes.Ajustes;
import com.pixels.Inventario.View.Ajustes.ConfiguracionBaseDatos.AlertDialog.alertcambiarbasedatos;
import com.pixels.Inventario.View.Ajustes.ConfiguracionContra.configContra;
import com.pixels.Inventario.View.Ajustes.ConfiguracionDatos.alertModificarDatos;
import com.pixels.Inventario.View.Ajustes.EliminarBaseDatos.alertEliminarBD;
import com.pixels.Inventario.View.Ajustes.ExportarDatos.ExportarDatos;
import com.pixels.Inventario.View.Ajustes.ImportarDatos.ImportarDatos;
import com.pixels.Inventario.View.InicioA.Configuracion_Inicial.Fragment.InicioBlanco;
import com.pixels.Inventario.View.Menu_Inicio.MenuInicio;

import java.util.List;

public class AjustesRecyclerViewAdapter extends RecyclerView.Adapter<AjustesRecyclerViewAdapter.ViewHolder> {
    private final Ajustes mParentActivity;
    private final MenuInicio MenuInicioA;
    private final List<AjustesContent> mValues;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int Id=Integer.parseInt((view.getTag()+""));
            if(Id==1){
                SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(mParentActivity);
                SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putBoolean("redimension", true);
                myEditor.commit();
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
            if(Id==7){
                alertModificarDatos modatos=new alertModificarDatos(mParentActivity);
                modatos.PreguntaModificarDatos();
            }
            if(Id==8){
                SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(mParentActivity);
                boolean bloqueo = myPreferences.getBoolean("bloqueoA",false);
                SharedPreferences.Editor myEditor = myPreferences.edit();
                if(bloqueo){
                    myEditor.putBoolean("bloqueoA", false);
                    myEditor.commit();
                }else{
                    myEditor.putBoolean("bloqueoA", true);
                    myEditor.commit();
                    MenuInicioA.recreate();
                }
                mParentActivity.reiniciarRecyclerView();
            }
        }
    };

    public AjustesRecyclerViewAdapter(Ajustes parent,
                                         List<AjustesContent> items,MenuInicio menuInicioA) {
        mValues = items;
        mParentActivity = parent;
        MenuInicioA= menuInicioA;
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
