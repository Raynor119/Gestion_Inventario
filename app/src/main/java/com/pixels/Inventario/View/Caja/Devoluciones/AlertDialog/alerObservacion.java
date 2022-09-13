package com.pixels.Inventario.View.Caja.Devoluciones.AlertDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.View.Caja.TextWatcher.TextCodigoCaja;

public class alerObservacion {
    private devoluciones Context;
    public alerObservacion(devoluciones context){
        this.Context=context;
    }
    public void pedirObservaciones(boolean verifi,double CantidadD){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertobservaciones, null);
        builder.setView(view);
        builder.setTitle("Digite las Observaciones o Razones por las cuales se devuelve el producto");
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        Button buton=(Button) view.findViewById(R.id.ButtonG);
        TextInputEditText observacion=(TextInputEditText) view.findViewById(R.id.Observacion);
        TextInputLayout CObservacion=(TextInputLayout) view.findViewById(R.id.CObservacion);
        TextCodigoCaja verificarC=new TextCodigoCaja(Context);
        if(verifi){
            observacion.setText(Context.Productos.get(Context.indexProducto).getObservacionD());
        }else{
            // si ya a sido devuelto se colocara las observaciones que se dieron en la anterior devolucion
            if(Context.ProductosV.get(Context.indexProducto).getCantidadD()>0){
                observacion.setText(Context.ProductosV.get(Context.indexProducto).getObservacionD());
            }
        }
        observacion.addTextChangedListener(verificarC.codigo(observacion,CObservacion));
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(observacion.getText().toString().equals("")){
                    CObservacion.setError("Digite las observaciones");
                }else{
                    if(verifi){
                        Context.Productos.get(Context.indexProducto).setCantidadD(CantidadD);
                        Context.Productos.get(Context.indexProducto).setObservacionD(observacion.getText().toString());
                        Context.iniciarRecyclerView();
                    }else{
                        Context.Productos.add(new VentasProductoD(Context.ProductosV.get(Context.indexProducto).getId(),Context.ProductosV.get(Context.indexProducto).getCodigoV(),Context.ProductosV.get(Context.indexProducto).getCodigoP(),Context.ProductosV.get(Context.indexProducto).getNombre(),Context.ProductosV.get(Context.indexProducto).getCantidadV(),CantidadD,Context.ProductosV.get(Context.indexProducto).getTipoC(),Context.ProductosV.get(Context.indexProducto).getCostePV(),Context.ProductosV.get(Context.indexProducto).getPrecioPV(),Context.ProductosV.get(Context.indexProducto).getIva(),observacion.getText().toString()));
                        Context.iniciarRecyclerView();
                    }
                    dialog.cancel();
                }
            }
        });
    }
}
