package com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Gestion_Productos.VerInventario;
import com.pixels.Inventario.View.Activity.Menu_Inicio.MenuInicio;

/**
 * A fragment representing a single opcion detail screen.
 * This fragment is either contained in a {@link MenuInicio}
 * in two-pane mode (on tablets) or a {@link VerInventario}
 * on handsets.
 */
public class VerInventarioFragment extends Fragment {

    public Context Context;
    public VerInventarioFragment(){

    }
    public VerInventarioFragment(Context context) {
        this.Context=context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("Inventario de Productos");
            }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventario_productos, container, false);
        RecyclerView reciclerView= rootView.findViewById(R.id.opcion_list);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return rootView;
    }
}
