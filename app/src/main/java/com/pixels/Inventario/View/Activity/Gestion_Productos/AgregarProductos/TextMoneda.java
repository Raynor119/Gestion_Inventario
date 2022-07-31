package com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.pixels.Inventario.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class TextMoneda {
    public AgregarProductos Context;
    public TextMoneda(AgregarProductos context){
        this.Context=context;
    }
    public TextWatcher moneda(final EditText editText) {
        return new TextWatcher() {
            DecimalFormat dec = new DecimalFormat("0.00");
            @Override
            public void afterTextChanged(Editable arg0) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(!s.toString().equals(current) && s.toString().compareTo("")!=0){
                    s=s+".00";
                    editText.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("["+Context.getResources().getString(R.string.MonedaMonto)+",.]", "").replace(" ","");
                    double parsed = Double.parseDouble(cleanString.replaceAll("\\s","").trim());
                    DecimalFormat decimalFormat  = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
                    DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();
                    symbols.setCurrencySymbol(Context.getResources().getString(R.string.MonedaMonto)+" ");
                    decimalFormat.setDecimalFormatSymbols(symbols);
                    decimalFormat.setMaximumFractionDigits(0);
                    String formatted = decimalFormat.format((parsed/100));
                    current = formatted;
                    editText.setText(formatted);
                    editText.setSelection(formatted.length());
                    editText.addTextChangedListener(this);
                }
            }
        };
    }
}
