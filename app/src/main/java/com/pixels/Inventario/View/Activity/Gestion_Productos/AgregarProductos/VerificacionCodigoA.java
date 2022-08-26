package com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

public class VerificacionCodigoA {
    public AgregarProductos Context;
    public VerificacionCodigoA(AgregarProductos context){
        this.Context=context;
    }
    public void verificarCodigo(){
        VerificarCodigoViewModel verificar= ViewModelProviders.of(Context).get(VerificarCodigoViewModel.class);
        verificar.reset();
        verificar.verificarCodigo(Context.Codigo.getText().toString(),Context);
        Observer<Boolean> observer=new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Context.Codigo.clearFocus();
                }else {
                    Context.CCodigo.setError("Error el codigo ya esta registrado en la base de datos");
                    Context.Codigo.setFocusableInTouchMode(true);
                    Context.Codigo.requestFocus();
                }
            }
        };
        verificar.getResultado().observe(Context,observer);
    }
}
