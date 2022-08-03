package com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.AlertDialog;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.configuracionI;

public class AlertConfiguracion {
    private configuracionI Context;
    public AlertConfiguracion(configuracionI context){
        this.Context=context;
    }
    public  void PreguntarNitNombre(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertdialogonitnombre, null);
        final EditText nit=(EditText) view.findViewById(R.id.Nit);
        final EditText nombre=(EditText) view.findViewById(R.id.NombreR);
        builder.setView(view);
        builder.setTitle("Datos del Usuario");
        AlertDialog dialog = builder.create();
        dialog.show();
        Button botton = (Button) view.findViewById(R.id.ButtonG);
        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean veridicar=true;
                if(nit.getText().toString().equals("")){
                    nit.setError("Digite el Nit");
                    veridicar=false;
                }
                if(nombre.getText().toString().equals("")){
                    nombre.setError("Digite el Nombre o Razon Social");
                    veridicar=false;
                }
                if(veridicar){
                    Context.nit=nit.getText().toString()+"";
                    Context.nombreR=nombre.getText().toString()+"";
                    dialog.cancel();
                    PreguntarContrasena();
                }
            }
        });
    }
    public void PreguntarContrasena(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        builder.setTitle("¿Quieres Asignar una Contraseña?");
        builder.setMessage("Se Asignara una contraseña a la Aplicacion para poder Entrar cada vez que se salga de la Aplicacion");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Context.AparecerFragmentContra();
                Context.contra="si";
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Context.contra="no";
                PreguntarBasedeDatos();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void PreguntarBasedeDatos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        builder.setTitle("Base de Datos que Usara");
        builder.setMessage("Puede Elegir entre SQLite (Guardar los datos en la Aplicacion) y MYSQL (Guardar los datos en una base de datos Local MYSQL)");
        builder.setPositiveButton("MYSQL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Context.basedatos="MYSQL";
                PreguntarBaseDatosMYSQL();
            }
        });
        builder.setNegativeButton("SQLITE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Context.basedatos="SQLITE";
                Context.guardarDatos();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void PreguntarBaseDatosMYSQL(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        builder.setTitle("Configuracion de la Base de Datos MYSQL");
        builder.setMessage("Si en el servidor de base de datos MYSQL ya tienes una base de datos creada con sus tablas puedes seleccionar guardar conexion si no selecciona crear base de datos");
        builder.setPositiveButton("Guardar Conexion", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Context.AparecerFragmentBase();
            }
        });
        builder.setNegativeButton("Crear Base de Datos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Context.AparecerFragmentCrearBase();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
