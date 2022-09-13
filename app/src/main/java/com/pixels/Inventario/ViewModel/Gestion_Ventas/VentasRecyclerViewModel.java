package com.pixels.Inventario.ViewModel.Gestion_Ventas;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos.VerProductosMYSQL;
import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.VerVentasMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;

import java.util.List;

public class VentasRecyclerViewModel extends ViewModel {
    public MutableLiveData<List<TotalVentas>> resultado;
    public VentasRecyclerViewModel(){
        resultado=new MutableLiveData<>();
    }
    public void reset(){
        resultado=new MutableLiveData<>();
    }
    public LiveData<List<TotalVentas>> getResultado(){
        return resultado;
    }
    public void buscarVentas(Context context,String Consulta){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
           // BD= new VerProductosSQLite(context, ProductosRecyclerViewModel.this);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD= new VerVentasMYSQL(context,VentasRecyclerViewModel.this,Consulta);
        }
    }
}
