package com.pixels.Inventario.View.Caja.TextWatcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class TextCodigoCaja {
    public Context Context;
    public TextCodigoCaja(Context context){
        this.Context=context;
    }
    public TextWatcher codigo(final TextInputEditText editText, final TextInputLayout CCodigo) {
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

