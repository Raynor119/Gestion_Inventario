package com.pixels.Inventario.View.Activity.ConfiguracionInicial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.ConfiguracionInicial.Fragment.AsignarContrasena;
import com.pixels.Inventario.View.Activity.ConfiguracionInicial.Fragment.RegistrarBaseDatos;

public class configuracionI extends AppCompatActivity {
    FragmentTransaction transaction;
    Fragment fragmentAContrasena,fragmentRBaseDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_i);
        fragmentAContrasena= new AsignarContrasena();
        fragmentRBaseDatos= new RegistrarBaseDatos();
        PreguntarContrasena();
    }
    public void PreguntarContrasena(){
        transaction=getSupportFragmentManager().beginTransaction();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("¿Quieres Asignar una Contraseña?");
        builder.setMessage("se Asignara una contraseña a la Aplicacion para poder Entrar cada vez que se salga de la Aplicacion");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                transaction.replace(R.id.contenedirFragments,fragmentAContrasena).commit();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               PregunarBasedeDatos();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void PregunarBasedeDatos(){
        transaction=getSupportFragmentManager().beginTransaction();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Base de Datos que Usara");
        builder.setMessage("Puede Elegir entre SQLite (Guardar los datos en la Aplicacion) y MYSQL (Guardar los datos en una base de datos Local MYSQL)");
        builder.setPositiveButton("MYSQL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                transaction.replace(R.id.contenedirFragments,fragmentRBaseDatos).commit();
            }
        });
        builder.setNegativeButton("SQLITE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Finalizo" , Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}