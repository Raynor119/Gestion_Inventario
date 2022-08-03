package com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.AlertDialog.AlertConfiguracion;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.Fragment.AsignarContrasena;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.Fragment.CrearBaseDatos;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.Fragment.InicioBlanco;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.Fragment.RegistrarBaseDatos;
import com.pixels.Inventario.View.Activity.Menu_Inicio.MenuInicio;
import com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial.datosInicioViewModel;

public class configuracionI extends AppCompatActivity {
    private FragmentTransaction transaction;
    private Fragment fragmentAContrasena,fragmentRBaseDatos,Inicio,fragmentCrear;
    private datosInicioViewModel Mdatosincio;
    public AlertConfiguracion alert;

    public static String contra="",contrasena="",basedatos="",ip="",nbasedatos="",usuario="",ucontra="",nit="",nombreR="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_i);
        Mdatosincio = ViewModelProviders.of(configuracionI.this).get(datosInicioViewModel.class);
        Inicio= new InicioBlanco();
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedirFragments,Inicio).commit();
        alert=new AlertConfiguracion(configuracionI.this);
        alert.PreguntarNitNombre();
    }
    public void AparecerFragmentContra(){
        fragmentAContrasena= new AsignarContrasena(configuracionI.this);
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedirFragments,fragmentAContrasena).commit();
    }
    public void AparecerFragmentBase(){
        fragmentRBaseDatos= new RegistrarBaseDatos(configuracionI.this);
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedirFragments,fragmentRBaseDatos).commit();
    }
    public void AparecerFragmentCrearBase(){
        fragmentCrear= new CrearBaseDatos(configuracionI.this);
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedirFragments,fragmentCrear).commit();
    }
    public void guardarDatos(){
        Mdatosincio.mandarDatos(configuracionI.this,contra,contrasena,basedatos,ip,nbasedatos,usuario,ucontra,nit,nombreR);
        Intent intent = new Intent(configuracionI.this, MenuInicio.class);
        startActivity(intent);
        finish();
    }

}