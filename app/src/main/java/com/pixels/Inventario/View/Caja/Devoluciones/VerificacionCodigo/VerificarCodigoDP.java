package com.pixels.Inventario.View.Caja.Devoluciones.VerificacionCodigo;

import com.pixels.Inventario.View.Caja.Devoluciones.AlertDialog.alertCantidadD;
import com.pixels.Inventario.View.Caja.Devoluciones.devoluciones;

public class VerificarCodigoDP {

    private devoluciones Context;

    public VerificarCodigoDP(devoluciones context){
        this.Context=context;
    }
    public void verificarcodigo(boolean editext){
        boolean vercodigo=false;
        for(int b=0;b<Context.ProductosV.size();b++){
            if(Context.ProductosV.get(b).getCodigoP().equals(Context.Codigo.getText().toString())){
                vercodigo=true;
                Context.indexProducto=b;
            }
        }
        if(vercodigo){
            if(Context.i[0]==0){
                Context.i[0]++;
                boolean productorepi=false;
                int indexP=0;
                for (int b=0;b<Context.Productos.size();b++){
                    if(Context.Productos.get(b).getCodigoP().equals(Context.ProductosV.get(Context.indexProducto).getCodigoP())){
                        productorepi=true;
                        indexP=b;
                    }
                }
                if(productorepi){
                    int aux=Context.indexProducto;
                    Context.indexProducto=indexP;
                    indexP=aux;
                    Context.verificarEnter=editext;
                    alertCantidadD alert=new alertCantidadD(Context);
                    alert.pedirCantidadD(true,indexP);
                    Context.Codigo.setText("");
                    Context.Codigo.setFocusableInTouchMode(true);
                    Context.Codigo.requestFocus();
                }else{
                    if(Context.ProductosV.get(Context.indexProducto).getEstadoDevolucion().equals("si") && (Context.ProductosV.get(Context.indexProducto).getCantidadD()==Context.ProductosV.get(Context.indexProducto).getCantidadV())){
                        Context.verificarEnter=editext;
                        Context.Codigo.setText("");
                        Context.CCodigo.setError("El producto ya ha sido devuelto en su totalidad");
                        Context.Codigo.setFocusableInTouchMode(true);
                        Context.Codigo.requestFocus();
                    }else{
                        Context.verificarEnter=editext;
                        alertCantidadD alert=new alertCantidadD(Context);
                        alert.pedirCantidadD(false,Context.indexProducto);
                        Context.Codigo.setText("");
                        Context.Codigo.setFocusableInTouchMode(true);
                        Context.Codigo.requestFocus();
                    }
                }
            }else{
                Context.i[0]=0;
            }
        }else{
            if(Context.i[0]==0){
                Context.i[0]++;
                Context.Codigo.setText("");
                Context.CCodigo.setError("El Codigo del Producto no esta Registrado en la Venta");
                Context.Codigo.setFocusableInTouchMode(true);
                Context.verificarEnter=editext;
                Context.Codigo.requestFocus();
            }else{
                Context.i[0]=0;
            }
        }
    }
}
