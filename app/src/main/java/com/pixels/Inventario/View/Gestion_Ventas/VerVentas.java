package com.pixels.Inventario.View.Gestion_Ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.View.Gestion_Productos.VerInventario;
import com.pixels.Inventario.View.Gestion_Ventas.Fragment.VerVentasFragment;

public class VerVentas extends AppCompatActivity {
    private VerVentasFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ventas);
        fragment = new VerVentasFragment(VerVentas.this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.opcion_detail_container, fragment)
                .commit();
    }
}