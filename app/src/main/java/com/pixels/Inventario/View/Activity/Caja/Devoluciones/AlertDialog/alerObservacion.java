package com.pixels.Inventario.View.Activity.Caja.Devoluciones.AlertDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.View.Activity.Caja.TextWatcher.TextCodigoCaja;

public class alerObservacion {
    private devoluciones Context;
    public alerObservacion(devoluciones context){
        this.Context=context;
    }
    public void pedirObservaciones(){
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
        observacion.addTextChangedListener(verificarC.codigo(observacion,CObservacion));
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(observacion.getText().toString().equals("")){
                    CObservacion.setError("Digite las observaciones");
                }else{

                }
            }
        });
    }
}
