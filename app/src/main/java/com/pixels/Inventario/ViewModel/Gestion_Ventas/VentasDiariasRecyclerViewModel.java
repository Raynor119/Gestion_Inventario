package com.pixels.Inventario.ViewModel.Gestion_Ventas;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.VerVentasDiariasMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;

import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.VerVentasDiariasSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

import com.pixels.Inventario.Model.DatosE.TotalVentas;

import java.util.List;

public class VentasDiariasRecyclerViewModel extends ViewModel {

    public MutableLiveData<List<TotalVentas>> resultado;

    public VentasDiariasRecyclerViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<TotalVentas>> getResultado(){
        return resultado;
    }

    public void buscarVentas(Context context,String Consulta){
        String ConsultaP="SELECT venta.codigo,COUNT(ventasproductos.codigoV) as CProductosV,sum(ventasproductos.CantidadV*ventasproductos.PrecioPV) as TotalV," +
                "(sum(((ventasproductos.CantidadV-ventasproductos.CantidadD)*ventasproductos.PrecioPV)/(1.0+(ventasproductos.IvaPV*0.01)))-sum((ventasproductos.CantidadV-ventasproductos.CantidadD)*ventasproductos.CostePV)) as GananciaNeta," +
                "(sum((((ventasproductos.CantidadV-ventasproductos.CantidadD)*ventasproductos.PrecioPV)/(1.0+(ventasproductos.IvaPV*0.01))*ventasproductos.IvaPV*0.01))) as TotalIvaP," +
                "(sum((ventasproductos.CantidadV)*ventasproductos.CostePV)) as CostoV," +
                "(SUM(ventasproductos.CantidadD*ventasproductos.CostePV)) as PerdidaD," +
                "(SUM(ventasproductos.CantidadD*ventasproductos.PrecioPV)) as TotalD" +
                ",venta.Fecha,venta.Efectivo FROM ventasproductos INNER JOIN venta ON ventasproductos.codigov=venta.codigo "+Consulta;
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD= new VerVentasDiariasSQLite(context, VentasDiariasRecyclerViewModel.this,ConsultaP);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD= new VerVentasDiariasMYSQL(context, VentasDiariasRecyclerViewModel.this,ConsultaP);
        }
    }
}
