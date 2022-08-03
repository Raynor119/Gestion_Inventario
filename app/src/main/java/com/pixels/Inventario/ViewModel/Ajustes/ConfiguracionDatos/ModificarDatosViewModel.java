package com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionDatos;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class ModificarDatosViewModel extends ViewModel {
    public ModificarDatosViewModel(){

    }
    public void modificarDatos(Context context,String nit,String nombre){
        consultasDatos modificar=new consultasDatos(context);
        modificar.modificarDatos(nit,nombre);
    }
}
