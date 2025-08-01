package com.pixels.Inventario.ViewModel.Gestion_Ventas.GraficasFragment.GColumnas;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.GraficasFragment.GColumnas.ProductosVendidosAMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.GraficasFragment.GColumnas.ProductosVendidosASQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.DatosColumn;

import java.util.List;

public class GraficaColumnARecyclerViewModel extends ViewModel {
    public MutableLiveData<List<DatosColumn>> resultado;

    public GraficaColumnARecyclerViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<DatosColumn>> getResultado(){
        return resultado;
    }
    public void buscarVProductos(Context context, String Consulta){
        String ConsultaP="SELECT VentasProductos.codigoP,Producto.nombre,SUM((VentasProductos.CantidadV-VentasProductos.CantidadD)*VentasProductos.PrecioPV)as TotalV "+
                "FROM (VentasProductos INNER JOIN Venta ON VentasProductos.codigov=Venta.codigo) INNER JOIN Producto ON VentasProductos.codigoP=Producto.codigo "+
                Consulta;
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD= new ProductosVendidosASQLite(context, GraficaColumnARecyclerViewModel.this,ConsultaP);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD= new ProductosVendidosAMYSQL(context, GraficaColumnARecyclerViewModel.this,ConsultaP);
        }
    }
}
