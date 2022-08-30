package com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.TextWatcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;


public class TextCodigo {
    public Context Context;
    public TextCodigo(Context context){
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

