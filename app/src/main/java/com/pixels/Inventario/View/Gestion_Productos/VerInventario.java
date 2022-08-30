package com.pixels.Inventario.View.Gestion_Productos;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Menu_Inicio.MenuInicio;
import com.pixels.Inventario.View.Gestion_Productos.Fragment.VerInventarioFragment;

/**
 * An activity representing a single opcion detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MenuInicio}.
 */
public class VerInventario extends AppCompatActivity {
    VerInventarioFragment fragment;
    public static FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_productos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        fragment = new VerInventarioFragment(VerInventario.this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.opcion_detail_container, fragment)
                    .commit();
            fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AgregarProductos.class);
                startActivity(intent);
                AgregarProductos.verproductos=fragment;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, MenuInicio.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
