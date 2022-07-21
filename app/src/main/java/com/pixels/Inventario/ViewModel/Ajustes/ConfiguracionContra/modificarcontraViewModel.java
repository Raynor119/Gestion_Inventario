package com.pixels.Inventario.ViewModel.Ajustes.ConfiguracionContra;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class modificarcontraViewModel extends ViewModel {
    private MutableLiveData<String> resultado;
    public modificarcontraViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<String> getResultado(){
        return resultado;
    }
    public void modificarcontra(Context context,String contrasena){
        consultasDatos db=new consultasDatos(context);
        db.modificarContra(contrasena);
    }
    public void ObtenerContra(Context context){
        consultasDatos db=new consultasDatos(context);
        String contra=db.obtenerD().get(0).getContrasena();
        resultado.setValue(contra);
    }
}
