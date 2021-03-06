package com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

public class AlertConfiguracion {
    private configuracionI Context;
    public AlertConfiguracion(configuracionI context){
        this.Context=context;
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
                Context.AparecerFragmentBase();
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
}
