package com.pixels.Inventario.ViewModel.Ajustes;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class VerificacionContraViewModel extends ViewModel {
    private MutableLiveData<String> resultado;

    public VerificacionContraViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<String> getResultado(){
        return resultado;
    }
    public void VerificarContra(Context context){
        //se Encarga de Revisar si hay una contraseña guardada
        consultasDatos db=new consultasDatos(context);
        String contra=db.obtenerD().get(0).getContra();
        resultado.setValue(contra);
    }
}
