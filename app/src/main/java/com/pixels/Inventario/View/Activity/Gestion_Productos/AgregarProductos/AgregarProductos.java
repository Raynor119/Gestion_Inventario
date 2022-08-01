package com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;


import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextCodigo;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextMoneda;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;


public class AgregarProductos extends AppCompatActivity {

    public AutoCompleteTextView spinner;
    public EditText Codigo,Nombre,Cantidad,Costop,Precio;
    public Button Button;
    public TextInputLayout CCodigo,TipoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        spinner=(AutoCompleteTextView) findViewById(R.id.spinner_dropdown);
        TipoC=(TextInputLayout)findViewById(R.id.spinner);
        Codigo=(EditText)findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) findViewById(R.id.EditCodigo);
        Nombre=(EditText)findViewById(R.id.nombre) ;
        Cantidad=(EditText)findViewById(R.id.cantidad);
        Costop=(EditText)findViewById(R.id.CosteP);
        Precio=(EditText)findViewById(R.id.Precio);
        Button=(Button)findViewById(R.id.ButtonG);
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
        TextMoneda conversor=new TextMoneda(AgregarProductos.this);
        Costop.addTextChangedListener(conversor.moneda(Costop));
        Precio.addTextChangedListener(conversor.moneda(Precio));
        TextCodigo verificar=new TextCodigo(AgregarProductos.this);
        Codigo.addTextChangedListener(verificar.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // modifica por tu método
                    if(Codigo.getText().toString().equals("")){
                        CCodigo.setError("Digite el Codigo del Producto");
                        CCodigo.setFocusableInTouchMode(true);
                        CCodigo.requestFocus();
                    }else{
                        VerificarCodigoViewModel verificar= ViewModelProviders.of(AgregarProductos.this).get(VerificarCodigoViewModel.class);
                        verificar.reset();
                        verificar.verificarCodigo(Codigo.getText().toString(),AgregarProductos.this);
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
                        verificar.getResultado().observe(AgregarProductos.this,observer);
                    }
                    return true;
                }
                return false;
            }
        });
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean[] verificacion = {true};
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

                                }else {
                                    CCodigo.setError("Error el codigo ya esta registrado en la base de datos");
                                    CCodigo.setFocusableInTouchMode(true);
                                    CCodigo.requestFocus();
                                    verificacion[0] =false;
                                }
                            }
                        };
                        verificar.getResultado().observe(AgregarProductos.this,observer);
                    }
                }
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
                if(verificacion[0]){
                    Toast.makeText(getApplicationContext(), "entro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}