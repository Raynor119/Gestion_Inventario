package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasDiariasRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class VerVentasDiariasSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    public VentasDiariasRecyclerViewModel ViewModel;
    private String Consulta="";
    public VerVentasDiariasSQLite(Context context, VentasDiariasRecyclerViewModel viewModel, String consulta) {
        super(context);
        this.ViewModel=viewModel;
        this.Consulta=consulta;
        ConsultaBaseDatos();
    }
    public List<TotalVentas> DatosProductos(){
        List<TotalVentas> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery(Consulta,null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new TotalVentas(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getString(8),cursor.getInt(9)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
    @Override
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(DatosProductos());
    }
}