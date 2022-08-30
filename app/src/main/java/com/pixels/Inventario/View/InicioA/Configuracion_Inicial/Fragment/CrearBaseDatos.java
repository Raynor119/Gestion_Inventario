package com.pixels.Inventario.View.InicioA.Configuracion_Inicial.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.InicioA.Configuracion_Inicial.configuracionI;
import com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial.CrearBaseVerificarViewModel;

public class CrearBaseDatos extends Fragment {

    private EditText ip,Nbasedatos,username,password;
    private CrearBaseVerificarViewModel veriMYSQL;
    private Button boton;
    private configuracionI Context;

    public CrearBaseDatos(configuracionI context) {
        this.Context=context;
    }
    public CrearBaseDatos() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crear_base_datos, container, false);
        ip=(EditText) view.findViewById(R.id.ip);
        Nbasedatos=(EditText) view.findViewById(R.id.nbasedatos);
        username=(EditText) view.findViewById(R.id.username);
        password= (EditText) view.findViewById(R.id.password);
        boton=(Button) view.findViewById(R.id.ButtonG);
        boton.setText("Crear Base de Datos");
        veriMYSQL= ViewModelProviders.of(Context).get(CrearBaseVerificarViewModel.class);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean verificar=true;
                if(ip.getText().toString().equals("")){
                    ip.setError("Digite la Ip de la Base de datos");
                    verificar=false;
                }
                if(Nbasedatos.getText().toString().equals("")){
                    Nbasedatos.setError("Digite el Nombre de la Base de datos");
                    verificar=false;
                }else{
                    for(int i=0;i<Nbasedatos.getText().toString().length();i++){
                        if((""+Nbasedatos.getText().toString().charAt(i)).equals(" ")){
                            Nbasedatos.setError("El Nombre de la Base de datos no puede tener espacios");
                            verificar=false;
                        }
                    }
                }
                if (username.getText().toString().equals("")) {
                    username.setError("Digite el Usuario de la Base de datos");
                    verificar=false;
                }
                if (password.getText().toString().equals("")) {
                    password.setError("Digite la Contraseña de la Base de datos");
                    verificar=false;
                }
                if(verificar){
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
                                Toast.makeText(getActivity(), "No se puede Crear la Base de Datos", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    veriMYSQL.getResultado().observe(Context,observer);
                }
            }
        });
        return view;
    }
}