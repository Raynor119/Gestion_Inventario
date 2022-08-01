package com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.AgregarProductos;


public class TextCodigo {
    public AgregarProductos Context;
    public TextCodigo(AgregarProductos context){
        this.Context=context;
    }
    public TextWatcher codigo(final EditText editText, final TextInputLayout CCodigo) {
        return new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CCodigo.setErrorEnabled(false);
            }
        };
    }
}

