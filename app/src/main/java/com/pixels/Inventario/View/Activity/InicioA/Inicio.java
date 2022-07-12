package com.pixels.Inventario.View.Activity.InicioA;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.pixels.Inventario.Model.DatosE.datosI;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.configuracionI;
import com.pixels.Inventario.View.Activity.Menu_Inicio.MenuInicio;
import com.pixels.Inventario.ViewModel.InicioA.Datos.DInicioViewModel;
import com.pixels.Inventario.ViewModel.InicioA.Datos.IDInicioViewModel;
import com.pixels.Inventario.ViewModel.InicioA.InicioVerificacionViewModel;

import java.util.List;


public class Inicio extends AppCompatActivity {
    private DInicioViewModel datosinicio;
    private IDInicioViewModel inicioDatos;
    private InicioVerificacionViewModel verificacioninico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        verificacioninico=ViewModelProviders.of(Inicio.this).get(InicioVerificacionViewModel.class);
        verificacioninico.VerificacionInicio(Inicio.this);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Observer<Integer> veriInicio= new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        switch(integer) {
                            case 0:
                                inicioDatos = ViewModelProviders.of(Inicio.this).get(IDInicioViewModel.class);
                                inicioDatos.InsertarDatos(getApplicationContext());
                                Intent intent =new Intent(Inicio.this, configuracionI.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 1:
                                datosinicio = ViewModelProviders.of(Inicio.this).get(DInicioViewModel.class);
                                datosinicio.DatosdeInicio(getApplicationContext());
                                final Observer<List<datosI>> observer = new Observer<List<datosI>>() {
                                    @Override
                                    public void onChanged(List<datosI> datosIS) {
                                        String Finalizo=datosIS.get(0).getFinalizo();
                                        String contra=datosIS.get(0).getContra();
                                        final String contrasena=datosIS.get(0).getContrasena();
                                        if(Finalizo.equals("0")){
                                            Intent intent = new Intent(Inicio.this, configuracionI.class);
                                            startActivity(intent);
                                            finish();
                                        }else
                                        {
                                            if(contra.equals("si")){
                                                AlertContrasena alerta=new AlertContrasena(Inicio.this);
                                                alerta.pedircontra(contrasena);
                                            }else{
                                                Intent intent = new Intent(Inicio.this, MenuInicio.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                        }
                                    }
                                };
                                datosinicio.getResultado().observe(Inicio.this,observer);
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };
                verificacioninico.getResultado().observe(Inicio.this,veriInicio);
            }
        },1000);
    }
}