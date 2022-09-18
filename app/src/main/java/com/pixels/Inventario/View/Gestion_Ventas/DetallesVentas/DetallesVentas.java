package com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pixels.Inventario.R;

public class DetallesVentas extends AppCompatActivity {

    private String Fecha,Efectivo,Codigo;
    private TextView TCodigo,TFecha,TEfectivo,TCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ventas);
        Fecha=getIntent().getExtras().getString("fecha");
        Efectivo=getIntent().getExtras().getString("efectivo");
        Codigo=getIntent().getExtras().getString("codigo");
        TCodigo=(TextView) findViewById(R.id.CodigoV);
        TFecha=(TextView) findViewById(R.id.fecha);
        TEfectivo=(TextView) findViewById(R.id.Efectivo);

        TCambio=(TextView) findViewById(R.id.cambioT);


    }
    public void iniciarReciclerView(){

    }
}