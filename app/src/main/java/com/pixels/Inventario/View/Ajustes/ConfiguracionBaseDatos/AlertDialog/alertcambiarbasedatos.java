package com.pixels.Inventario.View.Ajustes.ConfiguracionBaseDatos.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Ajustes.Ajustes;
import com.pixels.Inventario.View.Ajustes.ConfiguracionBaseDatos.CrearBaseDatosMYSQL;
import com.pixels.Inventario.View.Ajustes.ConfiguracionBaseDatos.cambiarMySQL;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionBaseDatos.VerBaseDatosViewModel;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionBaseDatos.modificarbasedatosViewModel;


public class alertcambiarbasedatos {
    private Ajustes Context;
    public alertcambiarbasedatos(Ajustes context){
        this.Context=context;
    }
    public void CambiarBaseDatos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertcambiobasedatos, null);
        final RadioButton mysql=(RadioButton) view.findViewById(R.id.RadioMysqlButton);
        final RadioButton sqlite=(RadioButton) view.findViewById(R.id.RadioSQLiteButton);
        final RadioGroup cont=(RadioGroup) view.findViewById(R.id.radiogroup);
        VerBaseDatosViewModel basedatos= ViewModelProviders.of(Context).get(VerBaseDatosViewModel.class);
        basedatos.reset();
        basedatos.VerBaseDatos(Context);
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("MYSQL")){
                    mysql.setChecked(true);
                }
                if(s.equals("SQLITE")){
                    sqlite.setChecked(true);
                }
            }
        };
        basedatos.getResultado().observe(Context,observer);

        builder.setView(view);
        builder.setTitle("Configuracion de la Base de Datos");
        builder.setCancelable(false);
        builder.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
        cont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.RadioMysqlButton){
                    mysql.setChecked(false);
                    sqlite.setChecked(true);
                    dialog.cancel();
                    PreguntarBaseDatosMYSQL(dialog);
                }else if(i == R.id.RadioSQLiteButton){
                    modificarbasedatosViewModel modificar= ViewModelProviders.of(Context).get(modificarbasedatosViewModel.class);
                    modificar.ModificarBaseDatos(Context,"SQLITE","","","","");
                    dialog.cancel();
                }
            }
        });
    }
    public void PreguntarBaseDatosMYSQL(final AlertDialog dialog){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        builder.setTitle("Configuracion de la Base de Datos MYSQL");
        builder.setMessage("Si en el servidor de base de datos MYSQL ya tienes una base de datos creada con sus tablas puedes seleccionar guardar conexion si no selecciona crear base de datos");
        builder.setPositiveButton("Guardar Conexion", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(Context, cambiarMySQL.class);
                Context.startActivity(intent);
                cambiarMySQL.alert=dialog;
            }
        });
        builder.setNegativeButton("Crear Base de Datos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(Context, CrearBaseDatosMYSQL.class);
                Context.startActivity(intent);
                CrearBaseDatosMYSQL.alert=dialog;
            }
        });
        AlertDialog dialog2 = builder.create();
        dialog2.show();
    }
}
