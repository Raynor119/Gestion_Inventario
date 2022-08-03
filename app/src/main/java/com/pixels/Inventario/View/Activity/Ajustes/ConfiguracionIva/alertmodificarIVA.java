package com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionIva;

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
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionDatos.ModificarDatosViewModel;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionIva.modificarIVAViewModel;
import com.pixels.Inventario.ViewModel.InicioA.Datos.DInicioViewModel;

import java.util.List;

public class alertmodificarIVA {
    private Ajustes Context;
    private String IVA;
    public alertmodificarIVA(Ajustes context){
        this.Context=context;
    }
    public void modificarIVA(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertiva, null);
        final EditText iva=(EditText) view.findViewById(R.id.iva);
        builder.setView(view);
        builder.setTitle("Modificar el IVA");
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
                if(iva.getText().toString().equals("")){
                    iva.setError("Digite el IVa");
                    veridicar=false;
                }
                if(veridicar){
                    if(iva.getText().toString().equals(IVA)){
                        iva.setError("Tienes que modificar el Iva");
                    }else{
                        modificarIVAViewModel datos= ViewModelProviders.of(Context).get(modificarIVAViewModel.class);
                        datos.moficicarIVA(Context,iva.getText().toString());
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
                IVA=datosIS.get(0).getIva();
                iva.setText(""+datosIS.get(0).getIva());
            }
        };
        datos.getResultado().observe(Context,observer);
    }
}
