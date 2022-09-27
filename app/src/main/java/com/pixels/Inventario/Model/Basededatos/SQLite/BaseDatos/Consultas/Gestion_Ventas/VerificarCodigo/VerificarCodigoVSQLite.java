package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.VerificarCodigo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.DatosVenta;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VerificarCodigo.VerificarCodigoFVViewModel;

import java.util.ArrayList;
import java.util.List;

public class VerificarCodigoVSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    private Context Context;
    private VerificarCodigoFVViewModel ViewModel;
    private String Codigo;

    public VerificarCodigoVSQLite(Context context, VerificarCodigoFVViewModel viewModel, String codigo) {
        super(context);
        this.Context=context;
        this.ViewModel=viewModel;
        this.Codigo=codigo;
        ConsultaBaseDatos();
    }
    public List<DatosVenta> DatosProductos(){
        List<DatosVenta> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM Venta WHERE codigo="+Codigo+"",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new DatosVenta(true,cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
    @Override
    public void ConsultaBaseDatos() {
        List<DatosVenta> ventas=DatosProductos();
        if(ventas.size()==0){
            ventas.add(new DatosVenta(false,0,"",0));
        }
        ViewModel.resultado.setValue(ventas);
    }
}
