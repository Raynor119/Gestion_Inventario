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
            Observer<List<VentasProductoD>> observer1=new Observer<List<VentasProductoD>>() {
                @Override
                public void onChanged(List<VentasProductoD> ventasProductoDS) {
                    if(Context.i[0]==0){
                        Context.i[0]++;
                        Context.verificarEnter=false;
                       // Intent intent=new  Intent(Context.Context,devoluciones.class);
                      //  intent.putExtra("codigo",Context.Codigo.getText().toString());
                       // Context.Context.startActivity(intent);
                        devoluciones.ProductosV=ventasProductoDS;
                        devoluciones.verificarEnter=false;
                        Context.dialog.cancel();
                    }
                }
            };
            Observer<Boolean> observer=new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){
                        if(Context.i[0]==0){
                            verificar.getProductos().observe(Context.Context,observer1);
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
            Observer<List<VentasProductoD>> observer1=new Observer<List<VentasProductoD>>() {
                @Override
                public void onChanged(List<VentasProductoD> ventasProductoDS) {
                    if(Context.i[0]==0) {
                        Context.i[0]++;
                        //Intent intent = new Intent(Context.Context, devoluciones.class);
                    //  Context.Context.startActivity(intent);
                        devoluciones.ProductosV = ventasProductoDS;
                        Context.alertCancelar();
                       // Context.Context.vdevolucion = true;
                    }
                }
            };
            Observer<Boolean> observer=new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){
                        if(Context.i[0]==0){
                            verificar.getProductos().observe(Context.Context,observer1);
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
                        }else{
                            Context.i[0]=0;
                        }
                    }
                }
            };
            verificar.getResultado().observe(Context.Context,observer);
        }
    }
}
