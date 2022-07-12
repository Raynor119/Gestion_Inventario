package com.pixels.Inventario.ViewModel.InicioA.Datos;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio;

public class IDInicioViewModel extends ViewModel {
    private DatosInicio datosInicio;
    public IDInicioViewModel(){}
    public void InsertarDatos(Context context){
        datosInicio= new DatosInicio(context);
        datosInicio.Iagregar();
    }
}
