package com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class asigcontraViewModel extends ViewModel {
    public  asigcontraViewModel(){

    }
    public void agsinarcontra(Context context,String contrasena){
        consultasDatos bdI=new consultasDatos(context);
        bdI.asignarcontra(contrasena);
    }
}
