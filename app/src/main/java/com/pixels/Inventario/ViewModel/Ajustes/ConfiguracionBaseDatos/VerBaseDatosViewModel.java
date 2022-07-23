package com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionBaseDatos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class VerBaseDatosViewModel extends ViewModel {
    private MutableLiveData<String> resultado;

    public VerBaseDatosViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<String> getResultado(){
        return resultado;
    }
    public void VerBaseDatos(Context context){
        //se Encarga de mandar que base de datos esta Usando Actualmente
        consultasDatos db=new consultasDatos(context);
        String basedatos=db.obtenerD().get(0).getBasedatos();
        resultado.setValue(basedatos);
    }
}
