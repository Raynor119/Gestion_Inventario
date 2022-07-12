package com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.InicioA.Configuracion_Inicial.configuracionI;
import com.pixels.Inventario.View.Activity.InicioA.Inicio;
import com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial.VerificarConexionViewModel;
import com.pixels.Inventario.ViewModel.InicioA.Datos.DInicioViewModel;


public class RegistrarBaseDatos extends Fragment {

    private EditText ip,Nbasedatos,username,password;
    VerificarConexionViewModel veriMYSQL;
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
        Nbasedatos=(EditText) view.findViewById(R.id.nbasedatos);
        username=(EditText) view.findViewById(R.id.username);
        password= (EditText) view.findViewById(R.id.password);
        boton=(Button) view.findViewById(R.id.ButtonG);
        veriMYSQL= ViewModelProviders.of(Context).get(VerificarConexionViewModel.class);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ip.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Digite la Ip de la Base de datos" , Toast.LENGTH_LONG).show();
                }else{
                    if(Nbasedatos.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Digite la Ip de la Base de datos" , Toast.LENGTH_LONG).show();
                    }else {
                        if (username.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Digite el Usuario de la Base de datos", Toast.LENGTH_LONG).show();
                        } else {
                            if (password.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Digite la Contraseña de la Base de datos", Toast.LENGTH_LONG).show();
                            } else {
                                veriMYSQL.reset();
                                veriMYSQL.VerificarConexion(ip.getText().toString(),Nbasedatos.getText().toString(),username.getText().toString(),password.getText().toString());
                                final Observer<Boolean> observer=new Observer<Boolean>() {
                                    @Override
                                    public void onChanged(Boolean aBoolean) {
                                        if(aBoolean){
                                            Context.ip = ip.getText().toString();
                                            Context.nbasedatos= Nbasedatos.getText().toString();
                                            Context.usuario = username.getText().toString();
                                            Context.ucontra = password.getText().toString();
                                            veriMYSQL.TerminarProceso();
                                            Context.guardarDatos();
                                        }else{
                                            Toast.makeText(getActivity(), "No se puede conectar a La Base Datos", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                };
                                veriMYSQL.getResultado().observe(Context,observer);
                            }
                        }
                    }
                }
            }
        });
        return view;
    }
}