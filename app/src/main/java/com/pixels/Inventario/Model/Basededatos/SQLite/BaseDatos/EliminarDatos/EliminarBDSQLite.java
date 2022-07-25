package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.EliminarDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;

public class EliminarBDSQLite extends BaseDatosSQLite {
    public EliminarBDSQLite(Context context) {
        super(context);
    }
    public void EliminarBasedatos(){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            bd.execSQL("DELETE FROM VentasProductos");
            bd.execSQL("DELETE FROM Producto");
            bd.execSQL("DELETE FROM Venta");
            bd.close();
        }
    }
}
