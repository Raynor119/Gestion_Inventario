package com.pixels.Inventario.View.Gestion_Productos.EditarProductos.VerificacionCodigo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.View.Gestion_Productos.EditarProductos.EditarProducto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoEditarViewModel;

public class VerificacionCodigoE {
    private EditarProducto Context;

    public VerificacionCodigoE(EditarProducto context){
        this.Context=context;
    }
    public void verificarCodigo(){
        VerificarCodigoEditarViewModel verificar= ViewModelProviders.of(Context).get(VerificarCodigoEditarViewModel.class);
        verificar.reset();
        verificar.verificarCodigo(Context,Context.codigo,Context.Codigo.getText().toString());
        Observer<Boolean> observer=new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!aBoolean){
                    Context.CCodigo.setError("Error el codigo ya esta registrado en la base de datos");
                    Context.Codigo.setFocusableInTouchMode(true);
                    Context.Codigo.requestFocus();
                }
            }
        };
        verificar.getResultado().observe(Context,observer);
    }

}
