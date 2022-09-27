package com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.VerificarCodigo;


import android.content.Intent;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.Model.DatosE.DatosVenta;
import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.View.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.alertVentaCodigo;
import com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas.DetallesVentas;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.VerificarCodigoV.VerificarCodigoVentaViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VerificarCodigo.VerificarCodigoFVViewModel;

import java.util.List;

public class VerificarCodigoFV {
    private alertVentaCodigo Context;
    public VerificarCodigoFV(alertVentaCodigo context){
        this.Context=context;
    }
    public void verificarcodigo(boolean verficarr){
        if(verficarr){
            VerificarCodigoFVViewModel verificar= ViewModelProviders.of(Context.Context.getActivity()).get(VerificarCodigoFVViewModel.class);
            verificar.reset();
            verificar.verificarCodigo(Context.Context.getActivity(),Context.Codigo.getText().toString());
            Observer<List<DatosVenta>> observer=new Observer<List<DatosVenta>>() {
                @Override
                public void onChanged(List<DatosVenta> datosVentas) {
                    if(datosVentas.get(0).isVerificar()){
                        if(Context.i[0]==0){
                            Context.i[0]++;
                            Intent intent=new Intent(Context.Context.getActivity(), DetallesVentas.class);
                            intent.putExtra("fecha",datosVentas.get(0).getFecha());
                            intent.putExtra("efectivo",""+datosVentas.get(0).getEfectivo());
                            intent.putExtra("codigo",""+datosVentas.get(0).getId());
                            Context.Context.getActivity().startActivity(intent);
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
            VerificarCodigoFVViewModel verificar= ViewModelProviders.of(Context.Context.getActivity()).get(VerificarCodigoFVViewModel.class);
            verificar.reset();
            verificar.verificarCodigo(Context.Context.getActivity(),Context.Codigo.getText().toString());
            Observer<List<DatosVenta>> observer=new Observer<List<DatosVenta>>() {
                @Override
                public void onChanged(List<DatosVenta> datosVentas) {
                    if(datosVentas.get(0).isVerificar()){
                        Intent intent=new Intent(Context.Context.getActivity(), DetallesVentas.class);
                        intent.putExtra("fecha",datosVentas.get(0).getFecha());
                        intent.putExtra("efectivo",""+datosVentas.get(0).getEfectivo());
                        intent.putExtra("codigo",""+datosVentas.get(0).getId());
                        Context.Context.getActivity().startActivity(intent);
                        Context.dialog.cancel();
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
