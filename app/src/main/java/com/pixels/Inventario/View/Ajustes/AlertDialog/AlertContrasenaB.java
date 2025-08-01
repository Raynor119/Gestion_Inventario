package com.pixels.Inventario.View.Ajustes.AlertDialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.TextWatcher.TextCodigo;
import com.pixels.Inventario.View.InicioA.Configuracion_Inicial.Fragment.InicioBlanco;

public class AlertContrasenaB {
    private AppCompatActivity Context;
    private FragmentActivity Context1;
    private  AlertDialog dialog;
    public AlertContrasenaB(AppCompatActivity context){
        this.Context=context;
    }
    public AlertContrasenaB(FragmentActivity context){
        this.Context1=context;
    }
    public void pedircontra(String contrasena,boolean verifragment,boolean veriaccion){
        Activity Context;
        if(verifragment){
            Context=this.Context1;
        }else{
            Context=this.Context;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertdialogcontrab, null);
        final TextInputLayout Ccont=(TextInputLayout) view.findViewById(R.id.CContra);
        final TextInputEditText cont=(TextInputEditText) view.findViewById(R.id.password);
        TextCodigo verificar=new TextCodigo(Context);
        cont.addTextChangedListener(verificar.codigo(cont,Ccont));
        builder.setView(view);
        builder.setCancelable(false);
        builder.setTitle("Bloqueo Activado Digite la Contraseña");
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(veriaccion){
                    InicioBlanco fragment=new InicioBlanco();
                    Context1.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.opcion_detail_container, fragment)
                            .commit();
                }else{

                    if(verifragment){
                        Context1.finish();
                    }else{
                        Context.finish();
                    }
                }
            }
        });
        dialog = builder.create();
        dialog.show();
        Button ButtonG=(Button) view.findViewById(R.id.ButtonG);
        ButtonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cont.getText().toString().equals("")){
                    Ccont.setError("Digite la Contraseña");
                }else {
                    if(cont.getText().toString().length()<4){
                        Ccont.setError("La contraseña debe ser de 4 o mas digitos");
                    }else {
                        if(cont.getText().toString().equals(contrasena)){
                            dialog.cancel();
                        }else{
                            Ccont.setError("Error la contraseña no coincide");
                        }
                    }
                }
            }
        });
    }
}
