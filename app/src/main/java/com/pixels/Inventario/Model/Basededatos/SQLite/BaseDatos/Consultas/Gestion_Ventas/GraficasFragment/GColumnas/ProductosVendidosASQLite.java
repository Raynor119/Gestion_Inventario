package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.GraficasFragment.GColumnas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.DatosColumn;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.GraficasFragment.GColumnas.GraficaColumnARecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductosVendidosASQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    public GraficaColumnARecyclerViewModel ViewModel;
    private String Consulta="";
    public ProductosVendidosASQLite(Context context, GraficaColumnARecyclerViewModel viewModel, String consulta) {
        super(context);
        this.ViewModel=viewModel;
        this.Consulta=consulta;
        ConsultaBaseDatos();
    }
    public List<DatosColumn> DatosProductos(){
        List<DatosColumn> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery(Consulta,null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new DatosColumn(cursor.getString(0),cursor.getString(1),cursor.getInt(2)));
            }while(cursor.moveToNext());
        }
        return datos;
    }

    @Override
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(DatosProductos());
    }
}