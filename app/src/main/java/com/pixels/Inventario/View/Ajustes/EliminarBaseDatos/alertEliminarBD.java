package com.pixels.Inventario.View.Ajustes.EliminarBaseDatos;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import com.pixels.Inventario.View.Ajustes.Ajustes;

import com.pixels.Inventario.ViewModel.Ajustes.EliminarBaseDatos.EliminarBDSQLiteViewModel;


public class alertEliminarBD {
    private Ajustes Context;
    public alertEliminarBD(Ajustes context){
        this.Context=context;
    }
    public void EliminarBaseDatos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setTitle("Eliminacion de los datos de la Aplicacion");
        builder.setMessage("Precaucion se eliminaran todos los datos de la aplicacion y no se podran Recuperar, antes de proceder a eliminar los datos haga una copia de seguridad exportando los datos a una base de datos local MYSQL");
        builder.setCancelable(false);
        builder.setPositiveButton("ELiminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EliminarBDSQLiteViewModel eliminarBD= ViewModelProviders.of(Context).get(EliminarBDSQLiteViewModel.class);
                eliminarBD.EliminarBaseDatos(Context);
            }
        });
        builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
