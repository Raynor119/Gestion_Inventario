package com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos.AgregarProductosMYSQL;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos.AgregarProductosSQLite;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;


public class AgregarProductosViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public AgregarProductosViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public void GuardarProducto(String codigo, String nombre, double cantidad, String tipoC, String costeP, String precio,int iva, Context context){
        consultasDatos dinici=new consultasDatos(context);
        ConvertirModenaINT convertir=new ConvertirModenaINT();
        String TipoC="";
        if(tipoC.equals("Unitario(U)")){
            TipoC="unitario";
        }
        if(tipoC.equals("Peso(Kg)")){
            TipoC="peso";
        }
        MediadorBaseDatos BD;
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD = new AgregarProductosSQLite(context,codigo,nombre,cantidad,TipoC,convertir.Convertir(costeP),convertir.Convertir(precio),iva,AgregarProductosViewModel.this);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD = new AgregarProductosMYSQL(context,codigo,nombre,cantidad,TipoC,convertir.Convertir(costeP),convertir.Convertir(precio),iva,AgregarProductosViewModel.this);
        }
    }
}
