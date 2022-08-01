package com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class EliminarProductoViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public EliminarProductoViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public void EliminarProducto(Context context,String Codigo){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
        }
    }
}
