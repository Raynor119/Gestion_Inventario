package com.pixels.Inventario.View.Gestion_Ventas.AlertDialog;

import android.app.AlertDialog;

import android.content.DialogInterface;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;


import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;

import com.pixels.Inventario.R;

import com.pixels.Inventario.View.Caja.TextWatcher.TextCodigoCaja;
import com.pixels.Inventario.View.Caja.VerificacionCodigo.VerificarCodigoD;
import com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.VerificarCodigo.VerificarCodigoFV;
import com.pixels.Inventario.View.Gestion_Ventas.Fragment.VerVentasFragment;

public class alertVentaCodigo {

    public VerVentasFragment Context;
    public boolean verificarEnter=true;
    public int[] i = {0};
    public AlertDialog dialog;
    public TextInputEditText Codigo;
    public TextInputLayout CCodigo;
    public int controlador=0;

    public alertVentaCodigo(VerVentasFragment context){
        this.Context=context;
    }
    public void alertCancelar(){
        dialog.cancel();
    }
    public void pediridventa(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context.getActivity());
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertdevolucion, null);
        builder.setView(view);
        builder.setTitle("Digite el Codigo de la Factura");
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });
        dialog = builder.create();
        dialog.show();
        CardView Escaner=(CardView) view.findViewById(R.id.Escaner);
        Codigo=(TextInputEditText) view.findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) view.findViewById(R.id.EditCodigo);
        Codigo.setFocusableInTouchMode(true);
        Codigo.requestFocus();
        TextCodigoCaja verificarC=new TextCodigoCaja(Context.getActivity());
        Codigo.addTextChangedListener(verificarC.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(Codigo.getText().toString().equals("")){
                        if(verificarEnter){
                            CCodigo.setError("Digite el Codigo de la Factura");
                            Codigo.setFocusableInTouchMode(true);
                            Codigo.requestFocus();
                        }else{
                            verificarEnter=true;
                        }
                    }else{
                        if(controlador==0){
                            i[0]=0;
                            controlador++;
                            VerificarCodigoFV codigo=new VerificarCodigoFV(alertVentaCodigo.this);
                            codigo.verificarcodigo(true);
                        }else{
                            controlador=0;
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        Escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context.getContext();
                new IntentIntegrator(Context.getActivity()).initiateScan();
            }
        });
    }


}
