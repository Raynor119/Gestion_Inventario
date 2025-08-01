package com.pixels.Inventario.View.Gestion_Productos.AgregarProductos;

import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;


import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.TextWatcher.TextCodigo;
import com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.TextWatcher.TextMoneda;
import com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.VerificacionCodigo.VerificacionCodigoA;
import com.pixels.Inventario.View.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.ConvertirModenaINT;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;


public class AgregarProductos extends AppCompatActivity {

    public AutoCompleteTextView spinner;
    public EditText Nombre,Cantidad,Costop,Precio,Iva;
    public Button Button;
    public TextInputEditText Codigo;
    public TextInputLayout CCodigo,TipoC;
    public TextView ganacia;
    public CardView Escaner;
    public static VerInventarioFragment verproductos;
    public boolean[] verificarspinnerU = {true};
    public boolean[] verificarspinnerK = {true};
    public boolean[] verificarspinnerg = {true};
    public boolean[] verifi = {true};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        spinner=(AutoCompleteTextView) findViewById(R.id.spinner_dropdown);
        Escaner=(CardView) findViewById(R.id.Escaner);
        TipoC=(TextInputLayout)findViewById(R.id.spinner);
        Codigo=(TextInputEditText)findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) findViewById(R.id.EditCodigo);
        Nombre=(EditText)findViewById(R.id.nombre) ;
        Cantidad=(EditText)findViewById(R.id.cantidad);
        Costop=(EditText)findViewById(R.id.CosteP);
        Precio=(EditText)findViewById(R.id.Precio);
        Iva=(EditText)findViewById(R.id.Iva);
        ganacia=(TextView) findViewById(R.id.ganacia);
        Button=(Button)findViewById(R.id.ButtonG);
        String [] tipoC={"Unitario(U)","Peso(Kg)","Peso(g)"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.tipocantidad,tipoC);
        spinner.setAdapter(adapter);
        Cantidad.setText("");
        Cantidad.setEnabled(false);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getText().toString().equals("Unitario(U)")){
                    if(verificarspinnerU[0]){
                        if(Cantidad.isEnabled()){
                            Cantidad.setEnabled(true);
                            Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
                            Cantidad.setText("");
                            verificarspinnerU[0] =false;
                            verificarspinnerK[0] =true;
                            verificarspinnerg[0] = true;
                            verifi[0] =false;
                        }else{
                            Cantidad.setEnabled(true);
                            Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
                            verificarspinnerU[0] =false;
                            verificarspinnerK[0] =true;
                            verificarspinnerg[0] = true;
                            verifi[0] =false;
                        }
                    }
                    String [] tipoC={"Unitario(U)","Peso(Kg)","Peso(g)"};
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(AgregarProductos.this, R.layout.tipocantidad,tipoC);
                    spinner.setAdapter(adapter);
                }
                if(spinner.getText().toString().equals("Peso(Kg)")){
                    if(verificarspinnerK[0]){
                        if(verifi[0]){
                            if(Cantidad.getText().toString().equals("")){
                                Cantidad.setEnabled(true);
                                Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                Cantidad.setText("");
                                verificarspinnerK[0] =false;
                                verificarspinnerU[0] =true;
                                verificarspinnerg[0] = true;
                                verifi[0] =true;
                            }else{
                                if(Cantidad.isEnabled()){
                                    Cantidad.setEnabled(true);
                                    double conversion=Double.parseDouble(Cantidad.getText().toString());
                                    double canntidadconvertida=conversion*(0.001);
                                    BigDecimal bd = new BigDecimal(canntidadconvertida);
                                    bd = bd.setScale(3, RoundingMode.HALF_UP);
                                    Cantidad.setText(""+bd.doubleValue());
                                    verificarspinnerK[0] =false;
                                    verificarspinnerU[0] =true;
                                    verificarspinnerg[0] = true;
                                    verifi[0] =true;
                                }else{
                                    Cantidad.setEnabled(true);
                                    verificarspinnerK[0] =false;
                                    verificarspinnerU[0] =true;
                                    verificarspinnerg[0] = true;
                                    verifi[0] =true;
                                }

                            }
                        }else{
                            Cantidad.setEnabled(true);
                            Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                            Cantidad.setText("");
                            verificarspinnerK[0] =false;
                            verificarspinnerU[0] =true;
                            verificarspinnerg[0] = true;
                            verifi[0] =true;
                        }
                    }
                    String [] tipoC={"Unitario(U)","Peso(Kg)","Peso(g)"};
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(AgregarProductos.this, R.layout.tipocantidad,tipoC);
                    spinner.setAdapter(adapter);
                }
                if(spinner.getText().toString().equals("Peso(g)")){
                    if(verificarspinnerg[0]){
                        if(verifi[0]){
                            if(Cantidad.getText().toString().equals("")){
                                Cantidad.setEnabled(true);
                                Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                Cantidad.setText("");
                                verificarspinnerg[0] =false;
                                verificarspinnerU[0] =true;
                                verificarspinnerK[0] =true;
                                verifi[0] =true;
                            }else{
                                if(Cantidad.isEnabled()){
                                    Cantidad.setEnabled(true);
                                    double conversion=Double.parseDouble(Cantidad.getText().toString());
                                    double canntidadconvertida=conversion*(1000);
                                    BigDecimal bd = new BigDecimal(canntidadconvertida);
                                    bd = bd.setScale(3, RoundingMode.HALF_UP);
                                    Cantidad.setText(""+bd.doubleValue());
                                    verificarspinnerg[0] =false;
                                    verificarspinnerU[0] =true;
                                    verificarspinnerK[0] =true;
                                    verifi[0] =true;
                                }else{
                                    Cantidad.setEnabled(true);
                                    verificarspinnerg[0] =false;
                                    verificarspinnerU[0] =true;
                                    verificarspinnerK[0] =true;
                                    verifi[0] =true;
                                }
                            }
                        }else{
                            Cantidad.setEnabled(true);
                            Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                            Cantidad.setText("");
                            verificarspinnerg[0] =false;
                            verificarspinnerU[0] =true;
                            verificarspinnerK[0] =true;
                            verifi[0] =true;
                        }
                    }
                    String [] tipoC={"Unitario(U)","Peso(Kg)","Peso(g)"};
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(AgregarProductos.this, R.layout.tipocantidad,tipoC);
                    spinner.setAdapter(adapter);
                }
                TipoC.setErrorEnabled(false);
            }
        });
        TextMoneda conversor=new TextMoneda(AgregarProductos.this);
        Costop.addTextChangedListener(conversor.moneda(Costop));
        Precio.addTextChangedListener(conversor.moneda(Precio));
        TextCodigo verificar=new TextCodigo(AgregarProductos.this);
        Codigo.addTextChangedListener(verificar.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if (((event.getAction() ==  KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))) {
                    if(Codigo.getText().toString().equals("")){
                        CCodigo.setError("Digite el Codigo del Producto");
                        Codigo.setFocusableInTouchMode(true);
                        Codigo.requestFocus();
                    }else{
                       VerificacionCodigoA codigo=new VerificacionCodigoA(AgregarProductos.this);
                       codigo.verificarCodigo();
                    }
                    return true;
                }
                return false;
            }
        });
        Escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(AgregarProductos.this).initiateScan();
            }
        });
        ganacia.setText("Calcular Ganancia");
        ganacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean verifica=true;
                if(Costop.getText().toString().equals("")){
                    Costop.setError("Digite el Costo del Producto");
                    verifica=false;
                }
                if(Precio.getText().toString().equals("")){
                    Precio.setError("Digite el Precio del Producto");
                    verifica=false;
                }
                if(Iva.getText().toString().equals("")){
                    Iva.setError("Digite la Tasa de IVA(%)");
                    verifica=false;
                }
                if(verifica){
                    ConvertirModenaINT convertir=new ConvertirModenaINT();
                    NumberFormat formato= NumberFormat.getNumberInstance();
                    int costoq=convertir.Convertir(Costop.getText().toString());
                    int precii=convertir.Convertir(Precio.getText().toString());
                    double ivapor=Double.parseDouble("1."+Iva.getText().toString());
                    double preciosin=precii/ivapor;
                    ganacia.setText("La ganancia del Producto es: $"+formato.format((int) preciosin-costoq));
                }
            }
        });
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean[] verificacion = {true};
                if(Nombre.getText().toString().equals("")){
                    Nombre.setError("Digite el nombre del Producto");
                    verificacion[0] =false;
                }
                if(Cantidad.getText().toString().equals("")){
                    Cantidad.setError("Digite la cantidad del Producto");
                    verificacion[0] =false;
                }
                if(spinner.getText().toString().equals("")){
                    TipoC.setError("Seleccione el Tipo de Cantidad");
                    verificacion[0] =false;
                }
                if(Costop.getText().toString().equals("")){
                    Costop.setError("Digite el Costo del Producto");
                    verificacion[0] =false;
                }
                if(Precio.getText().toString().equals("")){
                    Precio.setError("Digite el Precio del Producto");
                    verificacion[0] =false;
                }
                if(Iva.getText().toString().equals("")){
                    Iva.setError("Digite la Tasa de IVA(%)");
                    verificacion[0] =false;
                }
                if(Codigo.getText().toString().equals("")){
                    Codigo.setError("Digite el Codigo del Producto");
                    verificacion[0] =false;
                }else {
                    try {
                        CCodigo.setError(CCodigo.getError().toString()+"");
                        verificacion[0] =false;
                    }catch (Exception e){
                        VerificarCodigoViewModel verificar= ViewModelProviders.of(AgregarProductos.this).get(VerificarCodigoViewModel.class);
                        verificar.reset();
                        verificar.verificarCodigo(Codigo.getText().toString(),AgregarProductos.this);
                        Observer<Boolean> observer=new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {
                                if(aBoolean){
                                    if(verificacion[0]){
                                        AgregarProductosViewModel agregar= ViewModelProviders.of(AgregarProductos.this).get(AgregarProductosViewModel.class);
                                        agregar.reset();
                                        double cantidad=Double.parseDouble(Cantidad.getText().toString()+"");
                                        if(spinner.getText().toString().equals("Peso(g)")){
                                            cantidad=cantidad*(0.001);
                                        }
                                        BigDecimal bd = new BigDecimal(cantidad);
                                        bd = bd.setScale(3, RoundingMode.HALF_UP);
                                        cantidad=bd.doubleValue();
                                        int iva=Integer.parseInt(Iva.getText().toString());
                                        agregar.GuardarProducto(Codigo.getText().toString(),Nombre.getText().toString(),cantidad,spinner.getText().toString(),Costop.getText().toString(),Precio.getText().toString(),iva,AgregarProductos.this);
                                        Observer<Boolean> observer1= new Observer<Boolean>() {
                                            @Override
                                            public void onChanged(Boolean aBoolean) {
                                                if(aBoolean){
                                                    try{
                                                        Toast.makeText(AgregarProductos.this, "Se guardo el Producto en la Base de Datos", Toast.LENGTH_LONG).show();
                                                        finish();
                                                        verproductos.iniciarRecyclerView();
                                                    }catch (Exception e){
                                                        Toast.makeText(AgregarProductos.this, "Error al guardar el Producto en la Base de Datos", Toast.LENGTH_LONG).show();

                                                        finish();
                                                    }
                                                }
                                            }
                                        };
                                        agregar.getResultado().observe(AgregarProductos.this,observer1);
                                    }
                                }else {
                                    CCodigo.setError("Error el codigo ya esta registrado en la base de datos");
                                    Codigo.setFocusableInTouchMode(true);
                                    Codigo.requestFocus();
                                    verificacion[0] =false;
                                }
                            }
                        };
                        verificar.getResultado().observe(AgregarProductos.this,observer);
                    }
                }

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
            if (result.getContents() != null){
                Codigo.setText(result.getContents()+"");
                VerificacionCodigoA codigo=new VerificacionCodigoA(AgregarProductos.this);
                codigo.verificarCodigo();
            }else{
                CCodigo.setError("Error al escanear el código de barras");
                Codigo.setText("");
                Codigo.setFocusableInTouchMode(true);
                Codigo.requestFocus();
            }
    }
}