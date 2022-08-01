package com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos.VerificarCodigoMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos.VerificarCodigoSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;


public class VerificarCodigoViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public VerificarCodigoViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public void verificarCodigo(String codigo, Context context){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD = new VerificarCodigoSQLite(context,VerificarCodigoViewModel.this,codigo);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD = new VerificarCodigoMYSQL(context,VerificarCodigoViewModel.this,codigo);
        }
    }

}
