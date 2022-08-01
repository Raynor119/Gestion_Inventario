package com.pixels.Inventario.View.Activity.Gestion_Productos.EditarProductos;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextCodigo;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextMoneda;
import com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoEditarViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

public class EditarProducto extends AppCompatActivity {
    public AutoCompleteTextView spinner;
    public TextView Titulo;
    public EditText Codigo,Nombre,Cantidad,Costop,Precio;
    public android.widget.Button Button;
    public TextInputLayout CCodigo,TipoC;
    public CardView Escaner;
    public static VerInventarioFragment verproductos;
    private String codigo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        codigo=getIntent().getExtras().getString("codigo");
        Titulo=(TextView) findViewById(R.id.titulo);
        spinner=(AutoCompleteTextView) findViewById(R.id.spinner_dropdown);
        Escaner=(CardView) findViewById(R.id.Escaner);
        TipoC=(TextInputLayout)findViewById(R.id.spinner);
        Codigo=(EditText)findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) findViewById(R.id.EditCodigo);
        Nombre=(EditText)findViewById(R.id.nombre) ;
        Cantidad=(EditText)findViewById(R.id.cantidad);
        Costop=(EditText)findViewById(R.id.CosteP);
        Precio=(EditText)findViewById(R.id.Precio);
        Button=(Button)findViewById(R.id.ButtonG);
        Titulo.setText("Editar Producto");
        Codigo.setText(""+codigo);
        String [] tipoC={"Unitario(U)","Peso(Kg)"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.tipocantidad,tipoC);
        spinner.setAdapter(adapter);
        Cantidad.setEnabled(false);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getText().toString().equals("Unitario(U)")){
                    Cantidad.setEnabled(true);
                    Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
                    Cantidad.setText("");
                }
                if(spinner.getText().toString().equals("Peso(Kg)")){
                    Cantidad.setEnabled(true);
                    Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    Cantidad.setText("");
                }
                TipoC.setErrorEnabled(false);
            }
        });
        TextMoneda conversor=new TextMoneda(EditarProducto.this);
        Costop.addTextChangedListener(conversor.moneda(Costop));
        Precio.addTextChangedListener(conversor.moneda(Precio));
        TextCodigo verificar=new TextCodigo(EditarProducto.this);
        Codigo.addTextChangedListener(verificar.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(Codigo.getText().toString().equals("")){
                        CCodigo.setError("Digite el Codigo del Producto");
                        CCodigo.setFocusableInTouchMode(true);
                        CCodigo.requestFocus();
                    }else{
                        VerificarCodigoEditarViewModel verificar= ViewModelProviders.of(EditarProducto.this).get(VerificarCodigoEditarViewModel.class);
                        verificar.reset();
                        verificar.verificarCodigo(EditarProducto.this,codigo,Codigo.getText().toString());
                        Observer<Boolean> observer=new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {
                                if(aBoolean){

                                }else {
                                    CCodigo.setError("Error el codigo ya esta registrado en la base de datos");
                                    CCodigo.setFocusableInTouchMode(true);
                                    CCodigo.requestFocus();
                                }
                            }
                        };
                        verificar.getResultado().observe(EditarProducto.this,observer);
                    }
                    return true;
                }
                return false;
            }
        });
        Escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(EditarProducto.this).initiateScan();
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
                if(Codigo.getText().toString().equals("")){
                    Codigo.setError("Digite el Codigo del Producto");
                    verificacion[0] =false;
                }else {
                    try {
                        CCodigo.setError(CCodigo.getError().toString()+"");
                        verificacion[0] =false;
                    }catch (Exception e){
                        VerificarCodigoEditarViewModel verificar= ViewModelProviders.of(EditarProducto.this).get(VerificarCodigoEditarViewModel.class);
                        verificar.reset();
                        verificar.verificarCodigo(EditarProducto.this,codigo,Codigo.getText().toString());
                        Observer<Boolean> observer=new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {
                                if(aBoolean){
                                    if(verificacion[0]){
                                        Toast.makeText(EditarProducto.this, "Se Modiico el Producto en la Base de Datos", Toast.LENGTH_LONG).show();
                                    }
                                }else {
                                    CCodigo.setError("Error el codigo ya esta registrado en la base de datos");
                                    CCodigo.setFocusableInTouchMode(true);
                                    CCodigo.requestFocus();
                                    verificacion[0] =false;
                                }
                            }
                        };
                        verificar.getResultado().observe(EditarProducto.this,observer);
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
                VerificarCodigoEditarViewModel verificar= ViewModelProviders.of(EditarProducto.this).get(VerificarCodigoEditarViewModel.class);
                verificar.reset();
                verificar.verificarCodigo(EditarProducto.this,codigo,Codigo.getText().toString());
                Observer<Boolean> observer=new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){

                        }else {
                            CCodigo.setError("Error el codigo ya esta registrado en la base de datos");
                            CCodigo.setFocusableInTouchMode(true);
                            CCodigo.requestFocus();
                        }
                    }
                };
                verificar.getResultado().observe(EditarProducto.this,observer);
            }else{
                CCodigo.setError("Error al escanear el código de barras");
                Codigo.setText("");
            }
    }
}
