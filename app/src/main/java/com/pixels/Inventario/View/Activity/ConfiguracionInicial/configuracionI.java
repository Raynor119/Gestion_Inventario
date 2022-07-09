package com.pixels.Inventario.View.Activity.ConfiguracionInicial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.ConfiguracionInicial.Fragment.AsignarContrasena;
import com.pixels.Inventario.View.Activity.ConfiguracionInicial.Fragment.InicioBlanco;
import com.pixels.Inventario.View.Activity.ConfiguracionInicial.Fragment.RegistrarBaseDatos;
import com.pixels.Inventario.View.Activity.Inicio;
import com.pixels.Inventario.ViewModel.ConfiguracionInicial.datosInicioViewModel;
import com.pixels.Inventario.ViewModel.Inicio.IDInicioViewModel;

public class configuracionI extends AppCompatActivity {
    private static FragmentTransaction transaction;
    private Fragment fragmentAContrasena,fragmentRBaseDatos,Inicio;
    private datosInicioViewModel Mdatosincio;

    public static String contra="",contrasena="",basedatos="",ip="",usuario="",ucontra="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_i);
        Mdatosincio = ViewModelProviders.of(configuracionI.this).get(datosInicioViewModel.class);
        fragmentAContrasena= new AsignarContrasena(configuracionI.this);
        fragmentRBaseDatos= new RegistrarBaseDatos(configuracionI.this);
        Inicio= new InicioBlanco();
        //getSupportFragmentManager().beginTransaction().replace(R.id.contenedirFragments,Inicio).commit();
        PreguntarContrasena();
    }
    public void PreguntarContrasena(){
        transaction=getSupportFragmentManager().beginTransaction();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("¿Quieres Asignar una Contraseña?");
        builder.setMessage("Se Asignara una contraseña a la Aplicacion para poder Entrar cada vez que se salga de la Aplicacion");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                transaction.replace(R.id.contenedirFragments,fragmentAContrasena).commit();
                contra="si";
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                contra="no";
                PreguntarBasedeDatos();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void PreguntarBasedeDatos(){
        transaction=getSupportFragmentManager().beginTransaction();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Base de Datos que Usara");
        builder.setMessage("Puede Elegir entre SQLite (Guardar los datos en la Aplicacion) y MYSQL (Guardar los datos en una base de datos Local MYSQL)");
        builder.setPositiveButton("MYSQL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                basedatos="MYSQL";
                transaction.replace(R.id.contenedirFragments,fragmentRBaseDatos).commit();
            }
        });
        builder.setNegativeButton("SQLITE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                basedatos="SQLITE";
                guardarDatos();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void guardarDatos(){
        Mdatosincio.mandarDatos(configuracionI.this,contra,contrasena,basedatos,ip,usuario,ucontra);
        finish();
    }

}