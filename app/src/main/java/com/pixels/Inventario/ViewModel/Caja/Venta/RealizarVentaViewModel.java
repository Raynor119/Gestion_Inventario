package com.pixels.Inventario.ViewModel.Caja.Venta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja.RealizarVentaMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.RealizarVentaSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.VentaRealizada;
import com.pixels.Inventario.View.Caja.Caja;

import java.util.List;


public class RealizarVentaViewModel extends ViewModel {
    public MutableLiveData<List<VentaRealizada>> resultado;
    public RealizarVentaViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<List<VentaRealizada>> getResultado(){
        return resultado;
    }
    public void realizarVenta(Caja Context,int Efectivo){
        consultasDatos dinici=new consultasDatos(Context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD =new RealizarVentaSQLite(Context,Efectivo,RealizarVentaViewModel.this);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD =new RealizarVentaMYSQL(Context,Efectivo,RealizarVentaViewModel.this);
        }
    }
}
