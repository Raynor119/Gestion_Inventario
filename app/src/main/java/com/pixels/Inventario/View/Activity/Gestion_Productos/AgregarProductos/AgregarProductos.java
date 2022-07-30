package com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.pixels.Inventario.R;




public class AgregarProductos extends AppCompatActivity {

    public AutoCompleteTextView spinner;
    public EditText Costop,Precio;
    public Button Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        spinner=(AutoCompleteTextView) findViewById(R.id.spinner_dropdown);
        Costop=(EditText)findViewById(R.id.CosteP);
        Precio=(EditText)findViewById(R.id.Precio);
        Button=(Button)findViewById(R.id.ButtonG);
        String [] tipoC={"Unitario(U)","Peso(Kg)"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.tipocantidad,tipoC);
        spinner.setAdapter(adapter);
        TextMoneda conversor=new TextMoneda(AgregarProductos.this);
        Costop.addTextChangedListener(conversor.moneda(Costop));
        Precio.addTextChangedListener(conversor.moneda(Precio));
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}