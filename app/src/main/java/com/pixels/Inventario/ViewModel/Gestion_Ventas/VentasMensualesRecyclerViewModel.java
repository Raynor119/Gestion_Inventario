package com.pixels.Inventario.ViewModel.Gestion_Ventas;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.VerVentasDiariasMYSQL;
import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.VerVentasMensualesMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.VerVentasDiariasSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.VerVentasMensualesSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.TotalVentas;

import java.util.List;

public class VentasMensualesRecyclerViewModel extends ViewModel {

    public MutableLiveData<List<TotalVentas>> resultado;

    public VentasMensualesRecyclerViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<TotalVentas>> getResultado(){
        return resultado;
    }

    public void buscarVentas(Context context, String Consulta){
        String ConsultaP="SELECT Venta.codigo,COUNT(VentasProductos.codigoV) as CProductosV,sum(VentasProductos.CantidadV*VentasProductos.PrecioPV) as TotalV," +
                "(sum(((VentasProductos.CantidadV-VentasProductos.CantidadD)*VentasProductos.PrecioPV)/(1.0+(VentasProductos.IvaPV*0.01)))-sum((VentasProductos.CantidadV-VentasProductos.CantidadD)*VentasProductos.CostePV)) as GananciaNeta," +
                "(sum((((VentasProductos.CantidadV-VentasProductos.CantidadD)*VentasProductos.PrecioPV)/(1.0+(VentasProductos.IvaPV*0.01))*VentasProductos.IvaPV*0.01))) as TotalIvaP," +
                "(sum((VentasProductos.CantidadV)*VentasProductos.CostePV)) as CostoV," +
                "(SUM(VentasProductos.CantidadD*VentasProductos.CostePV)) as PerdidaD," +
                "(SUM(VentasProductos.CantidadD*VentasProductos.PrecioPV)) as TotalD" +
                ",Venta.Fecha,Venta.Efectivo FROM VentasProductos INNER JOIN Venta ON VentasProductos.codigov=Venta.codigo "+Consulta;
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD= new VerVentasMensualesSQLite(context, VentasMensualesRecyclerViewModel.this,ConsultaP);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD= new VerVentasMensualesMYSQL(context, VentasMensualesRecyclerViewModel.this,ConsultaP);
        }
    }
}
