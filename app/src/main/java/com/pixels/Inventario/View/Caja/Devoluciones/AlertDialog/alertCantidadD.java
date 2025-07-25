package com.pixels.Inventario.View.Caja.Devoluciones.AlertDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.View.Caja.TextWatcher.TextCodigoCaja;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class alertCantidadD {
    private devoluciones Context;
    public alertCantidadD(devoluciones context){
        this.Context=context;
    }
    public void pedirCantidadD(boolean verifi,int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertcantidadd, null);
        builder.setView(view);
        double cantidadMax=0;
        boolean tipoc=false;
        if(verifi){
            cantidadMax=Context.Productos.get(Context.indexProducto).getCantidadV()-Context.ProductosV.get(index).getCantidadD();
            if(Context.ProductosV.get(index).getTipoC().equals("unitario")){
                builder.setMessage("(La cantidad devuelta no puede ser mayor de "+((int)(cantidadMax))+")");
                tipoc=true;
            }else{
                BigDecimal bd = new BigDecimal(cantidadMax);
                bd = bd.setScale(3, RoundingMode.HALF_UP);
                cantidadMax=bd.doubleValue();
                if(Context.ProductosV.get(index).getTipoC().equals("peso")){
                    builder.setMessage("(La cantidad devuelta no puede ser mayor de "+cantidadMax+" Kg)");
                }else{
                    builder.setMessage("(La cantidad devuelta no puede ser mayor de "+cantidadMax+")");
                }
            }
        }else{
            cantidadMax=Context.ProductosV.get(Context.indexProducto).getCantidadV()-Context.ProductosV.get(Context.indexProducto).getCantidadD();
            if(Context.ProductosV.get(Context.indexProducto).getTipoC().equals("unitario")){
                builder.setMessage("(La cantidad devuelta no puede ser mayor de "+((int)(cantidadMax))+")");
                tipoc=true;
            }else{
                BigDecimal bd = new BigDecimal(cantidadMax);
                bd = bd.setScale(3, RoundingMode.HALF_UP);
                cantidadMax=bd.doubleValue();
                if(Context.ProductosV.get(Context.indexProducto).getTipoC().equals("peso")){
                    builder.setMessage("(La cantidad devuelta no puede ser mayor de "+cantidadMax+" Kg)");
                }else{
                    builder.setMessage("(La cantidad devuelta no puede ser mayor de "+cantidadMax+")");
                }
            }
        }
        builder.setTitle("Digite la cantidad que se va a devolver");
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        Button buton=(Button) view.findViewById(R.id.ButtonG);
        TextInputEditText Cantidad=(TextInputEditText) view.findViewById(R.id.Cantidad);
        TextInputLayout CCantidad=(TextInputLayout) view.findViewById(R.id.CCantidad);

        if(verifi){
            if(Context.ProductosV.get(index).getTipoC().equals("unitario")){
                Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
                Cantidad.setText(""+((int)(Context.Productos.get(Context.indexProducto).getCantidadD())));
            }else{
                Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                Cantidad.setText(""+Context.Productos.get(Context.indexProducto).getCantidadD());
            }
        }else{
            if(Context.ProductosV.get(Context.indexProducto).getTipoC().equals("unitario")){
                Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
            }else{
                Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }
        }

        CCantidad.setHint("Cantidad Devuelta");

        TextCodigoCaja verificarC=new TextCodigoCaja(Context);
        Cantidad.addTextChangedListener(verificarC.codigo(Cantidad,CCantidad));
        double finalCantidadMax = cantidadMax;
        boolean finalTipoc = tipoc;
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Cantidad.getText().toString().equals("")){
                    CCantidad.setError("Digite la Cantidad Devuelta");
                }else{
                    double cantidadDD=Double.parseDouble(Cantidad.getText().toString());
                    if(cantidadDD> finalCantidadMax){
                        if(finalTipoc){
                            CCantidad.setError("La cantidad devuelta no puede superar a la cantidad Vendida: "+ ((int)finalCantidadMax) +"");
                        }else{
                            CCantidad.setError("La cantidad devuelta no puede superar a la cantidad Vendida: "+ finalCantidadMax +"");
                        }
                    }else{
                        if(cantidadDD==0){
                            CCantidad.setError("La cantidad devuelta no puede ser 0");
                        }else{
                            BigDecimal bd = new BigDecimal(cantidadDD);
                            bd = bd.setScale(3, RoundingMode.HALF_UP);
                            cantidadDD=bd.doubleValue();
                            if(cantidadDD!=0.0){
                                alerObservacion observacion=new alerObservacion(Context);
                                observacion.pedirObservaciones(verifi,cantidadDD);
                                dialog.cancel();
                            }else{
                                CCantidad.setError("la cantidad devuelta es muy pequeña");
                            }
                        }
                    }
                }
            }
        });

    }
}
