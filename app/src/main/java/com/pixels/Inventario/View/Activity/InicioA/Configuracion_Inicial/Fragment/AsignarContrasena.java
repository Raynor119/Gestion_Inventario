package com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.configuracionI;


public class AsignarContrasena extends Fragment {

    private EditText password;
    private Button boton;
    private configuracionI Context;

    public AsignarContrasena(configuracionI context) {
        this.Context=context;
    }
    public AsignarContrasena() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_agsinarcontrasena, container, false);
        password= (EditText) view.findViewById(R.id.password);
        boton=(Button) view.findViewById(R.id.ButtonG);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals("")){
                    password.setError("Digite la Contraseña");
                }else {
                    if(password.getText().toString().length()<4){
                        password.setError("La contraseña debe ser de 4 o mas digitos");
                    }else {
                        Context.contrasena = password.getText().toString();
                        Context.alert.PreguntarBasedeDatos();
                    }
                }
            }
        });
        return view;
    }
}