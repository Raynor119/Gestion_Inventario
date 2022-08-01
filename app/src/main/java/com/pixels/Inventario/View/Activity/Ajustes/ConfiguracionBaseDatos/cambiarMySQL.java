package com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionBaseDatos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Ajustes.Ajustes;
import com.pixels.Inventario.View.Activity.Menu_Inicio.MenuInicio;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionBaseDatos.modificarbasedatosViewModel;
import com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial.VerificarConexionViewModel;

public class cambiarMySQL extends AppCompatActivity {
    private EditText ip,Nbasedatos,username,password;
    private VerificarConexionViewModel veriMYSQL;
    private Button boton;

    public static AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_my_sql);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
                finish();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.setTitle("Configuracion de la Base de Datos");
        ip=(EditText) findViewById(R.id.ip);
        Nbasedatos=(EditText) findViewById(R.id.nbasedatos);
        username=(EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        boton=(Button) findViewById(R.id.ButtonG);
        veriMYSQL= ViewModelProviders.of(cambiarMySQL.this).get(VerificarConexionViewModel.class);
        boton=(Button) findViewById(R.id.ButtonG);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean verificar=true;
                if(ip.getText().toString().equals("")){
                    ip.setError("Digite la Ip de la Base de datos");
                    verificar=false;
                }
                if(Nbasedatos.getText().toString().equals("")){
                    Nbasedatos.setError("Digite el Nombre de la Base de datos");
                    verificar=false;
                }
                if (username.getText().toString().equals("")) {
                    username.setError("Digite el Usuario de la Base de datos");
                    verificar=false;
                }
                if (password.getText().toString().equals("")) {
                    password.setError("Digite la Contraseña de la Base de datos");
                    verificar=false;
                }
                if(verificar){
                    veriMYSQL.reset();
                    veriMYSQL.VerificarConexion(ip.getText().toString(),Nbasedatos.getText().toString(),username.getText().toString(),password.getText().toString());
                    final Observer<Boolean> observer=new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                modificarbasedatosViewModel modificar=ViewModelProviders.of(cambiarMySQL.this).get(modificarbasedatosViewModel.class);
                                modificar.ModificarBaseDatos(cambiarMySQL.this,"MYSQL",ip.getText().toString(),Nbasedatos.getText().toString(),username.getText().toString(),password.getText().toString());
                                finish();
                            }else{
                                ip.setError("No se puede conectar a La Base Datos");
                                Toast.makeText(cambiarMySQL.this, "No se puede conectar a La Base Datos", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    veriMYSQL.getResultado().observe(cambiarMySQL.this,observer);
                }
            }
        });
    }

}