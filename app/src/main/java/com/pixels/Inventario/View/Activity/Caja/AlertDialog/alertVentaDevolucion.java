package com.pixels.Inventario.View.Activity.Caja.AlertDialog;

import android.app.AlertDialog;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.View.Activity.Caja.TextWatcher.TextCodigoCaja;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.VerificarCodigoV.VerificarCodigoVentaViewModel;


import java.util.List;

public class alertVentaDevolucion {

    private Caja Context;
    public boolean verificarEnter=true;
    public int[] i = {0};
    public AlertDialog dialog;
    public TextInputEditText Codigo;
    public TextInputLayout CCodigo;

    public alertVentaDevolucion(Caja context){
        this.Context=context;
    }
    public void alertCancelar(){
        dialog.cancel();
    }
    public void pediridventa(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        //builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertdevolucion, null);
        builder.setView(view);
        builder.setTitle("Digite el Codigo de la Factura");
        dialog = builder.create();
        dialog.show();
        CardView Escaner=(CardView) view.findViewById(R.id.Escaner);
        Codigo=(TextInputEditText) view.findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) view.findViewById(R.id.EditCodigo);
        Codigo.setFocusableInTouchMode(true);
        Codigo.requestFocus();
        TextCodigoCaja verificarC=new TextCodigoCaja(Context);
        Codigo.addTextChangedListener(verificarC.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(Codigo.getText().toString().equals("")){
                        if(verificarEnter){
                            CCodigo.setError("Digite el Codgo de la Factura");
                            Codigo.setFocusableInTouchMode(true);
                            Codigo.requestFocus();
                        }else{
                            verificarEnter=true;
                        }
                    }else{
                        VerificarCodigoVentaViewModel verificar= ViewModelProviders.of(Context).get(VerificarCodigoVentaViewModel.class);
                        verificar.reset();
                        verificar.verificarCodigo(Codigo.getText().toString(),Context);
                        Observer<Boolean> observer=new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {
                                if(aBoolean){
                                    if(i[0]==0){
                                        Observer<List<VentasProductoD>> observer1=new Observer<List<VentasProductoD>>() {
                                            @Override
                                            public void onChanged(List<VentasProductoD> ventasProductoDS) {
                                                i[0]++;
                                                verificarEnter=false;
                                                Intent intent=new  Intent(Context,devoluciones.class);
                                                intent.putExtra("codigo",Codigo.getText().toString());
                                                Context.startActivity(intent);
                                                devoluciones.ProductosV=ventasProductoDS;
                                                dialog.cancel();
                                            }
                                        };
                                        verificar.getProductos().observe(Context,observer1);
                                    }else{
                                        i[0]=0;
                                    }
                                }else{
                                    if(i[0]==0){
                                        i[0]++;
                                        Codigo.setText("");
                                        CCodigo.setError("El Codigo de la Factura no esta Registrado en la Base de Datos");
                                        Codigo.setFocusableInTouchMode(true);
                                        Codigo.requestFocus();
                                        verificarEnter=false;
                                    }else{
                                        i[0]=0;
                                    }
                                }
                            }
                        };
                        verificar.getResultado().observe(Context,observer);

                    }
                    return true;
                }
                return false;
            }
        });

        Escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context.i[0]=0;
                Context.vdevolucion=false;
                new IntentIntegrator(Context).initiateScan();
            }
        });
    }

}
