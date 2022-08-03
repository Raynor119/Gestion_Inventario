package com.pixels.Inventario.ViewModel.InicioA.Datos;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.datosI;

import java.util.List;

public class  DInicioViewModel extends ViewModel {
    private MutableLiveData<List<datosI>> resultado;

    public DInicioViewModel(){
        resultado= new MutableLiveData<>();
    }
    public LiveData<List<datosI>> getResultado(){
        return resultado;
    }
    public void reset(){
        resultado= new MutableLiveData<>();
    }
    public void DatosdeInicio(Context context){
        consultasDatos datosInicio =new consultasDatos(context);
        resultado.setValue(datosInicio.obtenerD());
    }
}
