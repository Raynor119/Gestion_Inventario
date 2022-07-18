package com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */


    /**
     * The dummy content this fragment is presenting.


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VerInventarioFragment() {
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
        View rootView = inflater.inflate(R.layout.opcion_detail, container, false);

        // Show the dummy content as text in a TextView.

            ((TextView) rootView.findViewById(R.id.opcion_detail)).setText("Productos");


        return rootView;
    }
}
