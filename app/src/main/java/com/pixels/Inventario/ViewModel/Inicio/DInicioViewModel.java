package com.pixels.Inventario.ViewModel.Inicio;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio;
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
    public void DatosdeInicio(Context context){
        DatosInicio datosInicio =new DatosInicio(context);
        resultado.setValue(datosInicio.obtenerD());
    }
}
