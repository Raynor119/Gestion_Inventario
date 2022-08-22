package com.pixels.Inventario.ViewModel.Caja.Devoluciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja.RealizarVentaMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.Devoluciones.RealizarDevolucionSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.RealizarVentaSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;

public class RealizarDevolucionViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public RealizarDevolucionViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public void realizarDevolucion(devoluciones Context,String CodigoV){
        consultasDatos dinici=new consultasDatos(Context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD =new RealizarDevolucionSQLite(Context,RealizarDevolucionViewModel.this,CodigoV);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD =new RealizarVentaMYSQL(Context,Efectivo,RealizarVentaViewModel.this);
        }
    }
}
