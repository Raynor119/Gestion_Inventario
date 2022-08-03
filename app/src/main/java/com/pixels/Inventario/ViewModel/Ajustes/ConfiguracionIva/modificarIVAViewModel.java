package com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionIva;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class modificarIVAViewModel extends ViewModel {
    public modificarIVAViewModel(){

    }
    public void moficicarIVA(Context context,String iva){
        consultasDatos modificar=new consultasDatos(context);
        modificar.modificarIVA(iva);
    }
}
