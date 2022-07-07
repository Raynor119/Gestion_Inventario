package com.pixels.Inventario.ViewModel.Inicio;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.DatosInicio;

public class IDInicioViewModel extends ViewModel {
    private DatosInicio datosInicio;
    public IDInicioViewModel(){}
    public void InsertarDatos(Context context){
        datosInicio= new DatosInicio(context);
        datosInicio.Iagregar();
    }
}
