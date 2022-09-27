package com.pixels.Inventario.ViewModel.Gestion_Ventas.VerificarCodigo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.VerificarCodigo.VerificarCodigoVMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;

import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.VerificarCodigo.VerificarCodigoVSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.DatosVenta;


import java.util.List;

public class VerificarCodigoFVViewModel extends ViewModel {
    public MutableLiveData<List<DatosVenta>> resultado;
    public VerificarCodigoFVViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<DatosVenta>> getResultado(){
        return resultado;
    }
    public void verificarCodigo(Context context,String codigoV){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD= new VerificarCodigoVSQLite(context, VerificarCodigoFVViewModel.this,codigoV);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD= new VerificarCodigoVMYSQL(context, VerificarCodigoFVViewModel.this,codigoV);
        }
    }
}
