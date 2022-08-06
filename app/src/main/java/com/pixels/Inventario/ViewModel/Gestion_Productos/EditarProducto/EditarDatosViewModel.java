package com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos.EditarProductosMYSQL;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos.EditarProductosSQLite;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.ConvertirModenaINT;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class EditarDatosViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    public EditarDatosViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public void EditarProducto(Context context,String codigo,String nombre,String cantidad,String spinner,String CosteP,String Precio,String CodigoP,int iva){
        consultasDatos dinici=new consultasDatos(context);
        MediadorBaseDatos BD;
        ConvertirModenaINT convertir=new ConvertirModenaINT();
        String TipoC="";
        if(spinner.equals("Unitario(U)")){
            TipoC="unitario";
        }
        if(spinner.equals("Peso(Kg)")){
            TipoC="peso";
        }
        if(spinner.equals("Peso(g)")){
            TipoC="peso";
        }
        double Cantidad=Double.parseDouble(cantidad);
        if(spinner.equals("Peso(g)")){
            Cantidad=Cantidad*(0.001);
        }
        BigDecimal bd = new BigDecimal(Cantidad);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        Cantidad=bd.doubleValue();
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            BD= new EditarProductosSQLite(context,codigo,nombre,Cantidad,TipoC,convertir.Convertir(CosteP),convertir.Convertir(Precio),iva,CodigoP,EditarDatosViewModel.this);
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            BD= new EditarProductosMYSQL(context,codigo,nombre,Cantidad,TipoC,convertir.Convertir(CosteP),convertir.Convertir(Precio),iva,CodigoP,EditarDatosViewModel.this);
        }
    }
}
