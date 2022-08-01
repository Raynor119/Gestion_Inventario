package com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos.VerDatosProductoMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos.VerDatosProductoSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.Producto;

import java.util.List;

public class VerDatosProductoViewModel extends ViewModel {
    public MutableLiveData<List<Producto>> resultado;
    public VerDatosProductoViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<List<Producto>> getResultado(){
        return resultado;
    }
    public void VerDatosProducto(Context context,String codigo){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD = new VerDatosProductoSQLite(context,VerDatosProductoViewModel.this,codigo);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD = new VerDatosProductoMYSQL(context,VerDatosProductoViewModel.this,codigo);
        }
    }
}
