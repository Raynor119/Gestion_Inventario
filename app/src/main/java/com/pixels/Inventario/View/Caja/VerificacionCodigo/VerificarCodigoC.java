package com.pixels.Inventario.View.Caja.VerificacionCodigo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.View.Caja.AlertDialog.alertpeso;
import com.pixels.Inventario.View.Caja.Caja;
import com.pixels.Inventario.ViewModel.Caja.VerificarCodigo.VerificarCodigoCajaViewModel;

import java.util.List;

public class VerificarCodigoC {
    private Caja Context;
    public VerificarCodigoC(Caja context){
        this.Context=context;
    }
    public void verificarCodigo(boolean verificarr){
        if(verificarr){
            VerificarCodigoCajaViewModel verificar= ViewModelProviders.of(Context).get(VerificarCodigoCajaViewModel.class);
            verificar.reset();
            verificar.verificarCodigo(Context.Codigo.getText().toString(),Context);
            Observer<Boolean> observer=new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){

                    }else{
                        Context.Codigo.setText("");
                        Context.CCodigo.setError("El Codigo del Producto no esta Registrado en la Base de Datos");
                        Context.Codigo.setFocusableInTouchMode(true);
                        Context.Codigo.requestFocus();
                        Context.verificarEnter=false;
                    }
                }
            };
            Observer<List<Producto>> observer1 = new Observer<List<Producto>>() {
                @Override
                public void onChanged(List<Producto> productos) {
                    boolean productorepi=false;
                    int posicion=0;
                    for (int b=0;b<Context.Productos.size();b++){
                        if(Context.Productos.get(b).getCodigo().equals(productos.get(0).getCodigo())){
                            productorepi=true;
                            posicion=b;
                        }
                    }

                    if(productorepi){
                        if(productos.get(0).getTipoC().equals("peso")){
                            alertpeso pedir=new alertpeso(Context,productos,Context.Productos.get(posicion).getCantidad(),false,posicion);
                            pedir.pedircantidad();
                        }
                        if(productos.get(0).getTipoC().equals("unitario")){
                            double cantiR=Context.Productos.get(posicion).getCantidad()+1;
                            int canti=(int) cantiR;
                            Context.Productos.get(posicion).setCantidad(canti);
                        }
                    }else{
                        if(productos.get(0).getTipoC().equals("peso")){
                            alertpeso pedir=new alertpeso(Context,productos,0,true,0);
                            pedir.pedircantidad();
                        }
                        if(productos.get(0).getTipoC().equals("unitario")){
                            Context.Productos.add(new Producto(productos.get(0).getCodigo(),productos.get(0).getNombre(),1,productos.get(0).getTipoC(),productos.get(0).getCosteP(),productos.get(0).getPrecio(),productos.get(0).getIva()));
                        }
                    }
                    Context.iniciarRecyclerView();
                    Context.Codigo.setText("");
                    Context.verificarEnter=false;
                    Context.Codigo.setFocusableInTouchMode(true);
                    Context.Codigo.requestFocus();

                }
            };
            verificar.getResultado().observe(Context,observer);
            verificar.getProductos().observe(Context,observer1);
            Context.verificarEnter=false;
        }else{
            VerificarCodigoCajaViewModel verificar= ViewModelProviders.of(Context).get(VerificarCodigoCajaViewModel.class);
            verificar.reset();
            verificar.verificarCodigo(Context.Codigo.getText().toString(),Context);
            Observer<Boolean> observer=new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){

                    }else{
                        Context.Codigo.setText("");
                        Context.CCodigo.setError("El Codigo del Producto no esta Registrado en la Base de Datos");
                        Context.Codigo.setFocusableInTouchMode(true);
                        Context.Codigo.requestFocus();
                    }
                }
            };
            Observer<List<Producto>> observer1 = new Observer<List<Producto>>() {
                @Override
                public void onChanged(List<Producto> productos) {
                    if(Context.i[0]==0){
                        Context.i[0]++;
                        boolean productorepi=false;
                        int posicion=0;
                        for (int b=0;b<Context.Productos.size();b++){
                            if(Context.Productos.get(b).getCodigo().equals(productos.get(0).getCodigo())){
                                productorepi=true;
                                posicion=b;
                            }
                        }

                        if(productorepi){
                            if(productos.get(0).getTipoC().equals("peso")){
                                alertpeso pedir=new alertpeso(Context,productos,Context.Productos.get(posicion).getCantidad(),false,posicion);
                                pedir.pedircantidad();
                            }
                            if(productos.get(0).getTipoC().equals("unitario")){
                                double cantiR=Context.Productos.get(posicion).getCantidad()+1;
                                int canti=(int) cantiR;
                                Context.Productos.get(posicion).setCantidad(canti);
                            }
                        }else{
                            if(productos.get(0).getTipoC().equals("peso")){
                                alertpeso pedir=new alertpeso(Context,productos,0,true,0);
                                pedir.pedircantidad();
                            }
                            if(productos.get(0).getTipoC().equals("unitario")){
                                Context.Productos.add(new Producto(productos.get(0).getCodigo(),productos.get(0).getNombre(),1,productos.get(0).getTipoC(),productos.get(0).getCosteP(),productos.get(0).getPrecio(),productos.get(0).getIva()));
                            }
                        }
                        Context.iniciarRecyclerView();
                        Context.Codigo.setText("");
                        Context.Codigo.setFocusableInTouchMode(true);
                        Context.Codigo.requestFocus();
                    }else{
                        Context.i[0]=0;
                    }
                }
            };
            verificar.getResultado().observe(Context,observer);
            verificar.getProductos().observe(Context,observer1);

        }
    }
}
