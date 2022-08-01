package com.pixels.Inventario.View.Activity.InicioA.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.InicioA.Inicio;
import com.pixels.Inventario.View.Activity.Menu_Inicio.MenuInicio;

public class AlertContrasena {
    private Inicio Context;
    public AlertContrasena(Inicio context){
        this.Context=context;
    }
    public void pedircontra(final String contrasena){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertdialogcontra, null);
        final EditText cont=(EditText) view.findViewById(R.id.password);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setTitle("Digite la Contraseña");
        builder.setPositiveButton("Iniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(cont.getText().toString().equals("")){
                    Toast.makeText(Context, "Digite la Contraseña" , Toast.LENGTH_LONG).show();
                    pedircontra(contrasena);
                }else {
                    if(cont.getText().toString().length()<4){
                        Toast.makeText(Context, "La contraseña debe ser de 4 o mas digitos" , Toast.LENGTH_LONG).show();
                        pedircontra(contrasena);
                    }else {
                        if(cont.getText().toString().equals(contrasena)){
                            Intent intent = new Intent(Context, MenuInicio.class);
                            Context.startActivity(intent);
                            Context.finish();
                        }else{
                            Toast.makeText(Context, "Error la contraseña no coincide", Toast.LENGTH_LONG).show();
                            pedircontra(contrasena);
                        }
                    }
                }

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Context.finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
