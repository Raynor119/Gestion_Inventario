package com.pixels.Inventario.ViewModel.Gestion_Ventas.DetallesVentas;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.DetallesVentas.VerDetallesVentasMYSQL;
import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.VerVentasAnualesMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.DetallesVentas.VerDetallesVentasSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.VerVentasAnualesSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.detallesPV;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasAnualesRecyclerViewModel;

import java.util.List;

public class DetallesVentasViewModel extends ViewModel {
    public MutableLiveData<List<detallesPV>> resultado;
    public DetallesVentasViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<detallesPV>> getResultado(){
        return resultado;
    }
    public void VerDetallerVentas(Context context,String codigoV){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD= new VerDetallesVentasSQLite(context, DetallesVentasViewModel.this,codigoV);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD= new VerDetallesVentasMYSQL(context, DetallesVentasViewModel.this,codigoV);
        }
    }
}
