package com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.TextWatcher;

import android.content.Context;
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
    public Context Context;
    public TextMoneda(Context context){
        this.Context=context;
    }
    public TextWatcher moneda(final EditText editText) {
        return new TextWatcher() {
            private String current = "";
            private String maximo="";
            private int select=-1;
            @Override
            public void afterTextChanged(Editable arg0) {
                if(editText.getText().toString().equals("")){
                    select=-1;
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText.getText().toString().length()>23){
                    Toast.makeText(Context, "ya a superado la maxima cantidad de dinero que puede digitar", Toast.LENGTH_LONG).show();
                    editText.setText(maximo);
                    editText.setSelection(editText.getText().toString().length());
                }else{
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
                        int ver=editText.getSelectionStart();
                        if(select!=-1){
                            if(select!=1){
                                if(editText.getSelectionStart()==1){
                                    select=formatted.length();
                                }else{
                                    select=editText.getSelectionStart();
                                }
                            }else{
                                select=formatted.length();
                            }
                        }else{
                            select=formatted.length();
                        }
                        editText.setText(formatted);
                        try{
                            editText.setSelection(select);
                        }catch (Exception e){
                            editText.setSelection(formatted.length());
                        }
                        editText.addTextChangedListener(this);
                        //Toast.makeText(Context, ""+editText.getText().toString().length(), Toast.LENGTH_LONG).show();
                        //editText.getSelectionStart();
                        editText.setSelection(editText.getText().toString().length());
                        maximo=editText.getText().toString();
                    }
                }
            }
        };
    }
}
