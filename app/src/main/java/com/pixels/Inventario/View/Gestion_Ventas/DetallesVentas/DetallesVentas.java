package com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pixels.Inventario.R;

public class DetallesVentas extends AppCompatActivity {

    private String Fecha,Efectivo,Codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ventas);
        Fecha=getIntent().getExtras().getString("fecha");
        Efectivo=getIntent().getExtras().getString("efectivo");
        Codigo=getIntent().getExtras().getString("codigo");
    }
}