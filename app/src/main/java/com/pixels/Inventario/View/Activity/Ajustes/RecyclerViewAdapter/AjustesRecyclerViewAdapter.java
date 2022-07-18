package com.pixels.Inventario.View.Activity.Ajustes.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pixels.Inventario.Model.DatosE.AjustesContent;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Ajustes.Ajustes;

import java.util.List;

public class AjustesRecyclerViewAdapter extends RecyclerView.Adapter<AjustesRecyclerViewAdapter.ViewHolder> {
    private final Ajustes mParentActivity;
    private final List<AjustesContent> mValues;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int Id=Integer.parseInt((view.getTag()+""));
            if(Id==1){

            }
            if(Id==2){

            }
            if(Id==3){

            }
            if(Id==4){

            }
            if(Id==5){

            }
            if(Id==6){

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
