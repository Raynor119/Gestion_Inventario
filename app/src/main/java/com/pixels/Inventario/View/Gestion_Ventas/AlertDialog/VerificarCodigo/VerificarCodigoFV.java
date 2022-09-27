package com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.VerificarCodigo;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.View.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.alertVentaCodigo;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.VerificarCodigoV.VerificarCodigoVentaViewModel;

import java.util.List;

public class VerificarCodigoFV {
    private alertVentaCodigo Context;
    public VerificarCodigoFV(alertVentaCodigo context){
        this.Context=context;
    }
    public void verificarcodigo(boolean verficarr){
        if(verficarr){
            VerificarCodigoVentaViewModel verificar= ViewModelProviders.of(Context.Context.getActivity()).get(VerificarCodigoVentaViewModel.class);
            verificar.reset();
            verificar.verificarCodigo(Context.Codigo.getText().toString(),Context.Context.getActivity());
            Observer<Boolean> observer=new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){
                        if(Context.i[0]==0){
                            Context.i[0]++;

                            Context.verificarEnter=false;
                            Context.dialog.cancel();
                        }else{
                            Context.i[0]=0;
                        }
                    }else{
                        if(Context.i[0]==0){
                            Context.i[0]++;
                            Context.Codigo.setText("");
                            Context.CCodigo.setError("El Codigo de la Factura no esta Registrado en la Base de Datos");
                            Context.Codigo.setFocusableInTouchMode(true);
                            Context.Codigo.requestFocus();
                            Context.verificarEnter=false;
                        }else{
                            Context.i[0]=0;
                        }
                    }
                }
            };
            verificar.getResultado().observe(Context.Context,observer);
        }else{
            VerificarCodigoVentaViewModel verificar= ViewModelProviders.of(Context.Context).get(VerificarCodigoVentaViewModel.class);
            verificar.reset();

            verificar.verificarCodigo(Context.Codigo.getText().toString(),Context.Context.getActivity());

            Observer<Boolean> observer=new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){

                    }else{
                        Context.Codigo.setText("");
                        Context.CCodigo.setError("El Codigo de la Factura no esta Registrado en la Base de Datos");
                        Context.Codigo.setFocusableInTouchMode(true);
                        Context.Codigo.requestFocus();
                    }
                }
            };
            verificar.getResultado().observe(Context.Context,observer);
        }
    }
}
