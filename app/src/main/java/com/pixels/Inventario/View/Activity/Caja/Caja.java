package com.pixels.Inventario.View.Activity.Caja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.TextWatcher.TextCodigoCaja;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextCodigo;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

public class Caja extends AppCompatActivity {
    public TextInputEditText Codigo;
    public TextInputLayout CCodigo;
    public android.widget.Button Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja);
        Codigo=(TextInputEditText)findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) findViewById(R.id.EditCodigo);
        Button=(Button)findViewById(R.id.ButtonG);
        Codigo.setFocusableInTouchMode(true);
        Codigo.requestFocus();
        TextCodigoCaja verificar=new TextCodigoCaja(Caja.this);
        Codigo.addTextChangedListener(verificar.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Codigo.setText("");
                    Codigo.setFocusableInTouchMode(true);
                    Codigo.requestFocus();
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