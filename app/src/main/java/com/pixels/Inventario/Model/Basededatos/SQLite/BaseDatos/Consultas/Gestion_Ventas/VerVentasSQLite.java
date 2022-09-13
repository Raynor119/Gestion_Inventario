package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class VerVentasSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    public VentasRecyclerViewModel ViewModel;
    private String Consulta="";
    public VerVentasSQLite(Context context, VentasRecyclerViewModel viewModel,String consulta) {
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
                datos.add(new TotalVentas(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getString(8)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
    @Override
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(DatosProductos());
    }
}