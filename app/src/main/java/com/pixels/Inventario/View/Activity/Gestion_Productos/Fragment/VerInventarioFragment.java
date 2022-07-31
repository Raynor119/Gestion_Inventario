package com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Activity.Gestion_Productos.RecyclerViewAdaptador.ProductosRecyclerViewAdapter;
import com.pixels.Inventario.View.Activity.Gestion_Productos.VerInventario;
import com.pixels.Inventario.View.Activity.Menu_Inicio.MenuInicio;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial.VerificarConexionViewModel;

import java.util.List;

/**
 * A fragment representing a single opcion detail screen.
 * This fragment is either contained in a {@link MenuInicio}
 * in two-pane mode (on tablets) or a {@link VerInventario}
 * on handsets.
 */
public class VerInventarioFragment extends Fragment {

    public Context Context;
    public RecyclerView reciclerView;
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
        reciclerView= rootView.findViewById(R.id.opcion_list);

        if(Context!=null){
            iniciarRecyclerView();
        }else {
            //si se redimensiona
            Context=getActivity();
            iniciarRecyclerView();
        }
        try {
            FloatingActionButton fab = rootView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Context, AgregarProductos.class);
                    startActivity(intent);
                }
            });
            if(VerInventario.fab != null){
                VerInventario.fab.setVisibility(View.INVISIBLE);
            }
        }catch (Exception e){

        }
        return rootView;
    }
    public void iniciarRecyclerView(){
        reciclerView.setAdapter(null);
        ProductosRecyclerViewModel productos= ViewModelProviders.of(getActivity()).get(ProductosRecyclerViewModel.class);
        productos.reset();
        productos.buscarProductos(Context);
        final Observer<List<Producto>> observer= new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                reciclerView.setAdapter(new ProductosRecyclerViewAdapter(Context,productos,VerInventarioFragment.this));
            }
        };
        productos.getResultado().observe(getActivity(),observer);
    }
}
