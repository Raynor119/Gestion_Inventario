package com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionBaseDatos;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class modificarbasedatosViewModel extends ViewModel {
    public modificarbasedatosViewModel(){

    }
    public void ModificarBaseDatos(Context context,String basedatos,String ip,String nbasedatos,String usuario,String ucontra){
        consultasDatos db=new consultasDatos(context);
        db.modificarbasedatos(basedatos,ip,nbasedatos,usuario,ucontra);
    }
}
