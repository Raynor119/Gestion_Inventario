package com.pixels.Inventario.View.Activity.Gestion_Productos.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment.VerInventarioFragment;

public class alertEliminar {
    public Context Context;
    public String Codigo;
    public VerInventarioFragment Fragment;
    public alertEliminar(Context context,String codigo,VerInventarioFragment fragment){
        this.Context=context;
        this.Codigo=codigo;
        this.Fragment=fragment;
    }
    public void AlertDialogEliminar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        builder.setTitle("¿Seguro que quieres eliminar el producto?");
        builder.setMessage("Se eliminara el producto de la base de datos y ya no se podra recuperar la informacion");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Context, "Eliminar: "+Codigo, Toast.LENGTH_LONG).show();
                Fragment.iniciarRecyclerView();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
