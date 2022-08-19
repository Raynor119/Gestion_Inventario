package com.pixels.Inventario.ViewModel.Caja.Devoluciones.VerificarCodigoV;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja.Devoluciones.VerificarCodigoVentaMYSQL;
import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja.VerificarCodigoCajaMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.Devoluciones.VerificarCodigoVentaSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.VerificarCodigoCajaSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.Model.DatosE.VentasProductos;

import java.util.List;


public class VerificarCodigoVentaViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public MutableLiveData<List<VentasProductoD>> productos;
    public VerificarCodigoVentaViewModel(){
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
    public LiveData<List<VentasProductoD>> getProductos(){
        return productos;
    }
    public void verificarCodigo(String codigo, Context context){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD = new VerificarCodigoVentaSQLite(context, VerificarCodigoVentaViewModel.this,codigo);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD = new VerificarCodigoVentaMYSQL(context, VerificarCodigoVentaViewModel.this,codigo);
        }
    }

}
