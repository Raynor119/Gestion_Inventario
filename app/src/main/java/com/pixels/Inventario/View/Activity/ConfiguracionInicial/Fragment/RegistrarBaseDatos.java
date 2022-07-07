package com.pixels.Inventario.View.Activity.ConfiguracionInicial.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.ConfiguracionInicial.configuracionI;


public class RegistrarBaseDatos extends Fragment {

    private EditText ip,username,password;
    private Button boton;
    private configuracionI Context;

    public RegistrarBaseDatos(configuracionI context) {
        this.Context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registrar_base_datos, container, false);
        ip=(EditText) view.findViewById(R.id.ip);
        username=(EditText) view.findViewById(R.id.username);
        password= (EditText) view.findViewById(R.id.password);
        boton=(Button) view.findViewById(R.id.ButtonG);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ip.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Digite la Ip de la Base de datos" , Toast.LENGTH_LONG).show();
                }else{
                    if(username.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Digite el Usuario de la Base de datos" , Toast.LENGTH_LONG).show();
                    }else{
                        if(password.getText().toString().equals("")){
                            Toast.makeText(getActivity(), "Digite la Contraseña de la Base de datos" , Toast.LENGTH_LONG).show();
                        }else{
                            Context.ip=ip.getText().toString();
                            Context.usuario=username.getText().toString();
                            Context.ucontra=password.getText().toString();
                            Context.guardarDatos();
                        }
                    }
                }
            }
        });
        return view;
    }
}