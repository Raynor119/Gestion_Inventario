package com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class EliminarContraViewModel extends ViewModel {
    public  EliminarContraViewModel(){

    }
    public void Eliminarcontra(Context context){
        consultasDatos bdI=new consultasDatos(context);
        bdI.eliminarcontra();
    }
}
