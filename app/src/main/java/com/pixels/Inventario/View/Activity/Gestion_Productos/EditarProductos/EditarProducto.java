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
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextCodigo;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextMoneda;
import com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.EditarDatosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.VerDatosProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoEditarViewModel;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EditarProducto extends AppCompatActivity {
    public AutoCompleteTextView spinner;
    public TextView Titulo;
    public EditText Codigo,Nombre,Cantidad,Costop,Precio,Iva;
    public android.widget.Button Button;
    public TextInputLayout CCodigo,TipoC;
    public CardView Escaner;
    public static VerInventarioFragment verproductos;
    private String codigo="";

    private String nombreG="";
    private String cantidadG="";
    private String TipoCG="";
    private String CostePG="";
    private String PrecioG="";
    private String IvaG="";


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
        Iva=(EditText)findViewById(R.id.Iva);
        Button=(Button)findViewById(R.id.ButtonG);
        Titulo.setText("Editar Producto");
        Codigo.setText(""+codigo);
        Cantidad.setEnabled(false);
        final boolean[] verificarspinnerU = {true};
        final boolean[] verificarspinnerK = {true};
        final boolean[] verificarspinnerg = {true};
        final boolean[] verifi = {true};
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getText().toString().equals("Unitario(U)")){
                    if(verificarspinnerU[0]){
                        Cantidad.setEnabled(true);
                        Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
                        Cantidad.setText("");
                        verificarspinnerU[0] =false;
                        verificarspinnerK[0] =true;
                        verificarspinnerg[0] = true;
                        verifi[0] =false;
                    }
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
                                double conversion=Double.parseDouble(Cantidad.getText().toString());
                                double canntidadconvertida=conversion*(0.001);
                                BigDecimal bd = new BigDecimal(canntidadconvertida);
                                bd = bd.setScale(3, RoundingMode.HALF_UP);
                                Cantidad.setText(""+bd.doubleValue());
                                verificarspinnerK[0] =false;
                                verificarspinnerU[0] =true;
                                verificarspinnerg[0] = true;
                                verifi[0] =true;
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
                                double conversion=Double.parseDouble(Cantidad.getText().toString());
                                double canntidadconvertida=conversion*(1000);
                                BigDecimal bd = new BigDecimal(canntidadconvertida);
                                bd = bd.setScale(3, RoundingMode.HALF_UP);
                                Cantidad.setText(""+bd.doubleValue());
                                verificarspinnerg[0] =false;
                                verificarspinnerU[0] =true;
                                verificarspinnerK[0] =true;
                                verifi[0] =true;
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
                        Codigo.setFocusableInTouchMode(true);
                        Codigo.requestFocus();
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
                                    Codigo.setFocusableInTouchMode(true);
                                    Codigo.requestFocus();
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
        Button.setText("Modificar Datos");
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
                        VerificarCodigoEditarViewModel verificar= ViewModelProviders.of(EditarProducto.this).get(VerificarCodigoEditarViewModel.class);
                        verificar.reset();
                        verificar.verificarCodigo(EditarProducto.this,codigo,Codigo.getText().toString());
                        Observer<Boolean> observer=new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {
                                if(aBoolean){
                                    if(verificacion[0]){
                                        if(Codigo.getText().toString().equals(codigo) && Nombre.getText().toString().equals(nombreG) && Cantidad.getText().toString().equals(cantidadG) && spinner.getText().toString().equals(TipoCG) && Costop.getText().toString().equals(CostePG) && Precio.getText().toString().equals(PrecioG) && Iva.getText().toString().equals(IvaG)){
                                            Toast.makeText(EditarProducto.this, "Tienes que modificar por lo menos un dato del producto", Toast.LENGTH_LONG).show();
                                        }else{
                                            EditarDatosViewModel Editar= ViewModelProviders.of(EditarProducto.this).get(EditarDatosViewModel.class);
                                            Editar.reset();
                                            int iva=Integer.parseInt(Iva.getText().toString()+"");
                                            Editar.EditarProducto(EditarProducto.this,Codigo.getText().toString(),Nombre.getText().toString(),Cantidad.getText().toString(),spinner.getText().toString(),Costop.getText().toString(),Precio.getText().toString(),codigo,iva);
                                            Observer<Boolean> observer1=new Observer<Boolean>() {
                                                @Override
                                                public void onChanged(Boolean aBoolean) {
                                                    if(aBoolean){
                                                        Toast.makeText(EditarProducto.this, "Se modifico los datos del producto", Toast.LENGTH_LONG).show();
                                                        finish();
                                                        verproductos.iniciarRecyclerView();
                                                    }
                                                }
                                            };
                                            Editar.getResultado().observe(EditarProducto.this,observer1);
                                        }
                                    }
                                }else {
                                    CCodigo.setError("Error el codigo ya esta registrado en la base de datos");
                                    Codigo.setFocusableInTouchMode(true);
                                    Codigo.requestFocus();
                                    verificacion[0] =false;
                                }
                            }
                        };
                        verificar.getResultado().observe(EditarProducto.this,observer);
                    }
                }

            }
        });
        VerDatosProductoViewModel datosproducto= ViewModelProviders.of(EditarProducto.this).get(VerDatosProductoViewModel.class);
        datosproducto.reset();
        datosproducto.VerDatosProducto(EditarProducto.this,codigo);
        Observer<List<Producto>> observer=new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                Nombre.setText(""+productos.get(0).getNombre());
                if(productos.get(0).getTipoC().equals("unitario")){
                    spinner.setText("Unitario(U)");
                    Cantidad.setEnabled(true);
                    Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
                    int canti=(int) productos.get(0).getCantidad();
                    Cantidad.setText(""+canti);
                    cantidadG=Cantidad.getText().toString();
                }
                if(productos.get(0).getTipoC().equals("peso")){
                    spinner.setText("Peso(Kg)");
                    Cantidad.setEnabled(true);
                    Cantidad.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    Cantidad.setText(""+productos.get(0).getCantidad());
                    cantidadG=Cantidad.getText().toString();
                }
                Costop.setText(""+productos.get(0).getCosteP());
                Precio.setText(""+productos.get(0).getPrecio());
                Iva=(EditText)findViewById(R.id.Iva);
                try{
                    Iva.setText(""+productos.get(0).getIva());
                }catch (Exception e){

                }

                String [] tipoC={"Unitario(U)","Peso(Kg)","Peso(g)"};
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(EditarProducto.this, R.layout.tipocantidad,tipoC);
                spinner.setAdapter(adapter);
                nombreG=productos.get(0).getNombre();
                TipoCG=spinner.getText().toString();
                CostePG=Costop.getText().toString();
                PrecioG=Precio.getText().toString();
                IvaG=productos.get(0).getIva()+"";
            }
        };
        datosproducto.getResultado().observe(EditarProducto.this,observer);
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
                            Codigo.setFocusableInTouchMode(true);
                            Codigo.requestFocus();
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
