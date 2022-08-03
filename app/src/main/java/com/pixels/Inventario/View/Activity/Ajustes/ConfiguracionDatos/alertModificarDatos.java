package com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionDatos;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pixels.Inventario.Model.DatosE.datosI;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Ajustes.Ajustes;
import com.pixels.Inventario.View.Activity.Gestion_Productos.EditarProductos.EditarProducto;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionDatos.ModificarDatosViewModel;
import com.pixels.Inventario.ViewModel.InicioA.Datos.DInicioViewModel;

import java.util.List;

public class alertModificarDatos {
    private Ajustes Context;
    private String Nit,Nombre;
    public alertModificarDatos(Ajustes context){
        this.Context=context;
    }
    public void PreguntaModificarDatos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertdialogonitnombre, null);
        final EditText nit=(EditText) view.findViewById(R.id.Nit);
        final EditText nombre=(EditText) view.findViewById(R.id.NombreR);
        builder.setView(view);
        builder.setTitle("Modificar Datos del Usuario");
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        Button botton = (Button) view.findViewById(R.id.ButtonG);
        botton.setText("Modificar");
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
                    if(nit.getText().toString().equals(Nit) && nombre.getText().toString().equals(Nombre)){
                        Toast.makeText(Context, "Tienes que modificar por lo menos un dato", Toast.LENGTH_LONG).show();
                    }else{
                        ModificarDatosViewModel datos= ViewModelProviders.of(Context).get(ModificarDatosViewModel.class);
                        datos.modificarDatos(Context,nit.getText().toString(),nombre.getText().toString());
                        dialog.cancel();
                    }
                }
            }
        });
        DInicioViewModel datos= ViewModelProviders.of(Context).get(DInicioViewModel.class);
        datos.reset();
        datos.DatosdeInicio(Context);
        Observer<List<datosI>> observer=new Observer<List<datosI>>() {
            @Override
            public void onChanged(List<datosI> datosIS) {
                Nit=datosIS.get(0).getNit();
                Nombre=datosIS.get(0).getNombre();
                nit.setText(""+datosIS.get(0).getNit());
                nombre.setText(""+datosIS.get(0).getNombre());
            }
        };
        datos.getResultado().observe(Context,observer);
    }
}
