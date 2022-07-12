package com.pixels.Inventario.ViewModel.InicioA;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Inicio.Verificacion;
import com.pixels.Inventario.View.Activity.InicioA.Inicio;

public class InicioVerificacionViewModel extends ViewModel {
    private MutableLiveData<Integer> resultado;

    public InicioVerificacionViewModel(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<Integer> getResultado(){
        return resultado;
    }

    public void VerificacionInicio(Inicio context){
        Verificacion vinicio= new Verificacion(context);
        resultado.setValue(vinicio.getFirstTimeRun());
    }

}