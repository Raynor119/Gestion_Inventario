package com.pixels.Inventario.ViewModel.Gestion_Productos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.Producto;

import java.util.List;

public class ProductosRecyclerViewModel extends ViewModel {
    private MutableLiveData<List<Producto>> resultado;
    public ProductosRecyclerViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<Producto>> getResultado(){
        return resultado;
    }
    public void buscarProductos(Context context){
        consultasDatos dinici=new consultasDatos(context);
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){

        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){

        }
    }

}
