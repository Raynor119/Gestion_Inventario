package com.pixels.Inventario.ViewModel.InicioA.Datos;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class IDInicioViewModel extends ViewModel {
    private consultasDatos datosInicio;
    public IDInicioViewModel(){}
    public void InsertarDatos(Context context){
        datosInicio= new consultasDatos(context);
        datosInicio.Iagregar();
    }
}
