package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.ExportarDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.Venta;
import com.pixels.Inventario.Model.DatosE.VentasProductos;


import java.util.ArrayList;
import java.util.List;

public class ConexionSQLiteExportacionDatos extends BaseDatosSQLite {
    private Context Context;
    public ConexionSQLiteExportacionDatos(Context context) {
        super(context);
        this.Context=context;
    }
    public List<Producto> exportarDatosProductos(){
        List<Producto> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM Producto",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new Producto(cursor.getString(0),cursor.getString(1),cursor.getDouble(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
    public List<Venta> exportarDatosVentas(){
        List<Venta> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM Venta",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new Venta(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
    public List<VentasProductos> exportarDatosVentasProductos(){
        List<VentasProductos> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM VentasProductos",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new VentasProductos(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getDouble(3),cursor.getInt(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
}
