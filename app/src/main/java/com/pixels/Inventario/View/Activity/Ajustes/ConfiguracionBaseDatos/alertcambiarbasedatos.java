package com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionBaseDatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Ajustes.Ajustes;
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
                    //dialog.cancel();
                    Intent intent=new Intent(Context,cambiarMySQL.class);
                    Context.startActivity(intent);
                    cambiarMySQL.alert=dialog;
                    mysql.setChecked(false);
                    sqlite.setChecked(true);
                }else if(i == R.id.RadioSQLiteButton){
                    modificarbasedatosViewModel modificar= ViewModelProviders.of(Context).get(modificarbasedatosViewModel.class);
                    modificar.ModificarBaseDatos(Context,"SQLITE","","","","");
                    dialog.cancel();
                }
            }
        });
    }
}
