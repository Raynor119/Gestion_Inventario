package com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos.VerificarCodigoEditarMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos.VerificarCodigoEditarSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class VerificarCodigoEditarViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public VerificarCodigoEditarViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public void verificarCodigo(Context context,String codigo,String codigoE){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD = new VerificarCodigoEditarSQLite(context,VerificarCodigoEditarViewModel.this,codigo,codigoE);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD = new VerificarCodigoEditarMYSQL(context,VerificarCodigoEditarViewModel.this,codigo,codigoE);
        }
    }

}