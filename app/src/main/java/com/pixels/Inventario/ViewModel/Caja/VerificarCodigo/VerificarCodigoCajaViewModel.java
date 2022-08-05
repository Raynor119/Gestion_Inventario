package com.pixels.Inventario.ViewModel.Caja.VerificarCodigo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja.VerificarCodigoCajaMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.VerificarCodigoCajaSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.Producto;

import java.util.List;


public class VerificarCodigoCajaViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public MutableLiveData<List<Producto>> productos;
    public VerificarCodigoCajaViewModel(){
        this.resultado=new MutableLiveData<>();
        this.productos=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
        this.productos=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public LiveData<List<Producto>> getProductos(){
        return productos;
    }
    public void verificarCodigo(String codigo, Context context){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD = new VerificarCodigoCajaSQLite(context, VerificarCodigoCajaViewModel.this,codigo);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD = new VerificarCodigoCajaMYSQL(context, VerificarCodigoCajaViewModel.this,codigo);
        }
    }

}
