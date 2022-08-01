package com.pixels.Inventario.View.Activity.Gestion_Productos.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Activity.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto.EliminarProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

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
                EliminarProductoViewModel Eliminar= ViewModelProviders.of(Fragment).get(EliminarProductoViewModel.class);
                Eliminar.reset();
                Eliminar.EliminarProducto(Context,Codigo);
                Observer<Boolean> observer=new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){
                            Toast.makeText(Context, "Se Elimino el producto de la Base de Datos", Toast.LENGTH_LONG).show();
                            Fragment.iniciarRecyclerView();
                        }
                    }
                };
                Eliminar.getResultado().observe(Fragment,observer);
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
