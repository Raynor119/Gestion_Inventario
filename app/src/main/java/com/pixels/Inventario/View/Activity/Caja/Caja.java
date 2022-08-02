package com.pixels.Inventario.View.Activity.Caja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.R;

public class Caja extends AppCompatActivity {
    public EditText Codigo;
    public TextInputLayout CCodigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja);
        Codigo=(EditText)findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) findViewById(R.id.EditCodigo);
        CCodigo.setFocusableInTouchMode(true);
        CCodigo.requestFocus();
    }
}