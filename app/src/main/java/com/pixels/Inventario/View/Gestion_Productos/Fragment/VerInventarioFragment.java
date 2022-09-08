package com.pixels.Inventario.View.Gestion_Productos.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Ajustes.Ajustes;
import com.pixels.Inventario.View.Ajustes.AlertDialog.AlertContrasenaB;
import com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Gestion_Productos.RecyclerViewAdaptador.ProductosRecyclerViewAdapter;
import com.pixels.Inventario.View.Gestion_Productos.VerInventario;
import com.pixels.Inventario.View.InicioA.Configuracion_Inicial.Fragment.InicioBlanco;
import com.pixels.Inventario.View.Menu_Inicio.MenuInicio;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra.obtenerContraViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;

import java.util.List;

/**
 * A fragment representing a single opcion detail screen.
 * This fragment is either contained in a {@link MenuInicio}
 * in two-pane mode (on tablets) or a {@link VerInventario}
 * on handsets.
 */
public class VerInventarioFragment extends Fragment {

    public AppCompatActivity Context;
    public RecyclerView reciclerView;
    public boolean verInventario=true;

    public VerInventarioFragment(){

    }
    public VerInventarioFragment(AppCompatActivity context) {
        this.Context=context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("Inventario de Productos");
                verInventario=false;
            }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventario_productos, container, false);
        reciclerView= rootView.findViewById(R.id.opcion_list);
        try {
            ImageView cargar=(ImageView) rootView.findViewById(R.id.cargar);
            cargar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iniciarRecyclerView();
                }
            });
        }catch (Exception e){

        }
        iniciarRecyclerView();
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean bloqueo = myPreferences.getBoolean("bloqueoA",false);
        if(bloqueo){
            AlertContrasenaB contra=new AlertContrasenaB(getActivity());
            obtenerContraViewModel obtenercontra= ViewModelProviders.of(getActivity()).get(obtenerContraViewModel.class);
            obtenercontra.reset();
            obtenercontra.ObtenerContra(getActivity());
            final Observer<String> observer=new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    try {
                        if (getActivity().findViewById(R.id.opcion_detail_container) != null) {
                            contra.pedircontra(s,true,verInventario);
                        }
                    }catch (Exception e){

                    }
                }
            };
            obtenercontra.getResultado().observe(getActivity(),observer);
        }
        try {
            FloatingActionButton fab = rootView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AgregarProductos.class);
                    startActivity(intent);
                    AgregarProductos.verproductos=VerInventarioFragment.this;
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
        productos.buscarProductos(getActivity());
        final Observer<List<Producto>> observer= new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                reciclerView.setAdapter(new ProductosRecyclerViewAdapter(getActivity(),productos,VerInventarioFragment.this));
            }
        };
        productos.getResultado().observe(getActivity(),observer);
    }
}
