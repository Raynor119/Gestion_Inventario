package com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionContra.AlertDialog;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionContra.configContra;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra.EliminarContraViewModel;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra.obtenerContraViewModel;

public class alertcontra {
   private configContra Context;
   public alertcontra(configContra context){
       this.Context=context;
   }
   public void alertConfig(){
       AlertDialog.Builder builder = new AlertDialog.Builder(Context);
       builder.setTitle("Configuracion de la Contraseña");
       builder.setMessage("Puedes modificar la contraseña o eliminarla en caso contrario puedes cancelar la configuracion y volver a la opciones de ajustes");
       builder.setCancelable(false);
       builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Context);
               SharedPreferences.Editor myEditor = myPreferences.edit();
               myEditor.putBoolean("redimension", false);
               myEditor.commit();
               Context.modificarContra();
           }
       });
       builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               Context.finish();
           }
       });
       builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               obtenerContraViewModel obtenercontra= ViewModelProviders.of(Context).get(obtenerContraViewModel.class);
               obtenercontra.reset();
               obtenercontra.ObtenerContra(Context);
               final Observer<String> observer=new Observer<String>() {
                   @Override
                   public void onChanged(String s) {
                       pedirContra(s);
                   }
               };
               obtenercontra.getResultado().observe(Context,observer);
           }
       });
       try{
           AlertDialog dialog = builder.create();
           dialog.show();
       }catch (Exception e){

       }
   }
   public void pedirContra(final String contrasena){

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
                   pedirContra(contrasena);
               }else {
                   if(cont.getText().toString().length()<4){
                       Toast.makeText(Context, "La contraseña debe ser de 4 o mas digitos" , Toast.LENGTH_LONG).show();
                       pedirContra(contrasena);
                   }else {
                       if(cont.getText().toString().equals(contrasena)){
                           EliminarContraViewModel eliminar=ViewModelProviders.of(Context).get(EliminarContraViewModel.class);
                           eliminar.Eliminarcontra(Context);
                           Context.Context.reiniciarRecyclerView();
                           Context.finish();
                       }else{
                           Toast.makeText(Context, "Error la contraseña no coincide", Toast.LENGTH_LONG).show();
                           pedirContra(contrasena);
                       }
                   }
               }

           }
       });
       builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               alertConfig();
           }
       });
       AlertDialog dialog = builder.create();
       dialog.show();

   }

}
