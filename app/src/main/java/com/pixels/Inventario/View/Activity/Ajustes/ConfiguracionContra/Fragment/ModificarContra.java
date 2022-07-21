package com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionContra.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Ajustes.ConfiguracionContra.configContra;
import com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra.modificarcontraViewModel;

public class ModificarContra extends Fragment {
    private EditText passwordViejo,passwordNuevo;
    private Button boton;
    private configContra Context;
    public ModificarContra(configContra context){
        this.Context=context;
    }
    public ModificarContra(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_modificarcontra, container, false);
        passwordNuevo=(EditText) view.findViewById(R.id.password2);
        passwordViejo= (EditText) view.findViewById(R.id.password);
        boton=(Button) view.findViewById(R.id.ButtonG);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordViejo.getText().toString().equals("")){
                    passwordViejo.setError("Digite la Contraseña");
                }else {
                    if(passwordViejo.getText().toString().length()<4){
                        passwordViejo.setError("La contraseña debe ser de 4 o mas digitos");
                    }else {
                        final modificarcontraViewModel modificar= ViewModelProviders.of(Context).get(modificarcontraViewModel.class);
                        modificar.reset();
                        modificar.ObtenerContra(Context);
                        Observer<String> observer=new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                if (passwordViejo.getText().toString().equals(s)){
                                    if(passwordNuevo.getText().toString().equals("")){
                                        passwordNuevo.setError("Digite la Contraseña");
                                    }else {
                                        if (passwordNuevo.getText().toString().length() < 4) {
                                            passwordNuevo.setError("La contraseña debe ser de 4 o mas digitos");
                                        } else {
                                            if(passwordNuevo.getText().toString().equals(s)){
                                                passwordNuevo.setError("La Nueva contraseña no puede ser la misma que la Antigua contraseña");
                                            }else{
                                                modificar.modificarcontra(Context,passwordNuevo.getText().toString());
                                                Context.Context.reiniciarRecyclerView();
                                                Context.finish();
                                            }
                                        }
                                    }

                                }else{
                                    passwordViejo.setError("La contraseña es Incorrecta");
                                }
                            }
                        };
                        modificar.getResultado().observe(Context,observer);


                    }
                }
            }
        });
        return view;
    }
}
