package com.pixels.Inventario.View.Activity.Ajustes.ImportarDatos;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.R;
import com.pixels.Inventario.ViewModel.Ajustes.ImportarDatos.ImportarDatosViewModel;
import com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial.VerificarConexionViewModel;

public class ImportarDatos extends AppCompatActivity {

    private EditText ip,Nbasedatos,username,password;
    private VerificarConexionViewModel veriMYSQL;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar_datos);
        this.setTitle("Importar datos de la Aplicacion");
        ip=(EditText) findViewById(R.id.ip);
        Nbasedatos=(EditText) findViewById(R.id.nbasedatos);
        username=(EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        boton=(Button) findViewById(R.id.ButtonG);
        boton.setText("Importar Datos");
        veriMYSQL= ViewModelProviders.of(ImportarDatos.this).get(VerificarConexionViewModel.class);
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
                                ImportarDatosViewModel importar= ViewModelProviders.of(ImportarDatos.this).get(ImportarDatosViewModel.class);
                                importar.importarDatos(ip.getText().toString(),Nbasedatos.getText().toString(),username.getText().toString(),password.getText().toString(),ImportarDatos.this);
                                finish();
                            }else{
                                ip.setError("No se puede conectar a La Base Datos");
                                Toast.makeText(ImportarDatos.this, "No se puede conectar a La Base Datos", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    veriMYSQL.getResultado().observe(ImportarDatos.this,observer);
                }
            }
        });
    }
}
