package com.pixels.Inventario.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.pixels.Inventario.Model.DatosE.datosI;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.ConfiguracionInicial.configuracionI;
import com.pixels.Inventario.ViewModel.Inicio.DInicioViewModel;
import com.pixels.Inventario.ViewModel.Inicio.IDInicioViewModel;

import java.util.List;


public class Inicio extends AppCompatActivity {
    private DInicioViewModel datosinicio;
    private IDInicioViewModel inicioDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                switch(getFirstTimeRun(this)) {
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
                                String contrasena=datosIS.get(0).getContrasena();
                                String basedatos=datosIS.get(0).getBasedatos();
                                String ip=datosIS.get(0).getIp();
                                String usuario=datosIS.get(0).getUsuario();
                                String ucontra=datosIS.get(0).getUcontra();
                                if(Finalizo.equals("0")){
                                    Intent intent = new Intent(Inicio.this, configuracionI.class);
                                    startActivity(intent);
                                    finish();
                                }else
                                {
                                    Toast.makeText(getApplicationContext(), "Finalizo: "+Finalizo+" contra: "+contra+" contrasena: "+contrasena+" basedatos: "+basedatos+" ip: "+ip+" usuario: "+usuario+" ucontra: "+ucontra, Toast.LENGTH_LONG).show();
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
        },1000);
    }
    public int getFirstTimeRun(Runnable contexto) {
        SharedPreferences sp = getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = 2;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;
    }
}