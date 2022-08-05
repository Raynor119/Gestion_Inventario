package com.pixels.Inventario.View.Activity.Caja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.TextWatcher.TextCodigoCaja;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextCodigo;
import com.pixels.Inventario.ViewModel.Caja.VerificarCodigo.VerificarCodigoCajaViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.util.List;

public class Caja extends AppCompatActivity {
    public TextInputEditText Codigo;
    public TextInputLayout CCodigo;
    public android.widget.Button Button;
    public boolean verificarEnter=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja);
        Codigo=(TextInputEditText)findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) findViewById(R.id.EditCodigo);
        Button=(Button)findViewById(R.id.ButtonG);
        Codigo.setFocusableInTouchMode(true);
        Codigo.requestFocus();
        TextCodigoCaja verificarC=new TextCodigoCaja(Caja.this);
        Codigo.addTextChangedListener(verificarC.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(Codigo.getText().toString().equals("")){
                        if(verificarEnter){
                            CCodigo.setError("Digite el Codgo del Producto");
                            Codigo.setFocusableInTouchMode(true);
                            Codigo.requestFocus();
                        }else{
                            verificarEnter=true;
                        }
                    }else{
                        VerificarCodigoCajaViewModel verificar= ViewModelProviders.of(Caja.this).get(VerificarCodigoCajaViewModel.class);
                        verificar.reset();
                        verificar.verificarCodigo(Codigo.getText().toString(),Caja.this);
                        Observer<Boolean> observer=new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {
                                if(aBoolean){
                                    Observer<List<Producto>> observer1 = new Observer<List<Producto>>() {
                                        @Override
                                        public void onChanged(List<Producto> productos) {
                                            Toast.makeText(getApplicationContext(), ""+productos.get(0).getCodigo(), Toast.LENGTH_LONG).show();
                                            Codigo.setText("");
                                            verificarEnter=false;
                                            Codigo.setFocusableInTouchMode(true);
                                            Codigo.requestFocus();
                                        }
                                    };
                                    verificar.getProductos().observe(Caja.this,observer1);
                                }else{
                                    CCodigo.setError("El Codigo del Producto no esta Registrado en la Base de Datos");
                                    Codigo.setFocusableInTouchMode(true);
                                    Codigo.requestFocus();
                                }
                            }
                        };
                        verificar.getResultado().observe(Caja.this,observer);
                    }
                    return true;
                }
                return false;
            }
        });
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}