package com.pixels.Inventario.View.Ajustes.AlertDialog;

import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceManager;

import com.pixels.Inventario.View.Ajustes.Ajustes;


public class AlertDesactivar {
    private Ajustes Context;
    public  AlertDesactivar(Ajustes context){
        this.Context=context;
    }
    public void desactivar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setTitle("Desactivar Bloqueo de Administrador");
        builder.setMessage("Estas Seguro de Desactivar el Bloqueo de Administrador");
        builder.setCancelable(false);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Context);
                SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putBoolean("bloqueoA", false);
                myEditor.commit();
                Context.MenuInicioA.recreate();
                Context.reiniciarRecyclerView();
            }
        });
        builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
