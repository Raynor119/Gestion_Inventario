package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Ventas.DetallesVentas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.Model.DatosE.detallesPV;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.DetallesVentas.DetallesVentasViewModel;

import java.util.ArrayList;
import java.util.List;

public class VerDetallesVentasSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    private DetallesVentasViewModel ViewModel;
    public List<detallesPV> ProductosVendidos;
    private String Codigo;

    public VerDetallesVentasSQLite(Context context,DetallesVentasViewModel viewModel,String codigo) {
        super(context);
        this.ViewModel=viewModel;
        this.Codigo=codigo;
        ConsultaBaseDatos();
    }
    public List<detallesPV> DatosProductos(){
        List<detallesPV> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT VentasProductos.Id,VentasProductos.codigoV,VentasProductos.codigoP,Producto.nombre,Producto.TipoC,VentasProductos.CantidadV,VentasProductos.CantidadD,VentasProductos.CostePV,VentasProductos.PrecioPV,VentasProductos.IvaPV,VentasProductos.ObservacionD FROM VentasProductos INNER JOIN Producto on VentasProductos.codigoP=Producto.codigo WHERE VentasProductos.codigoV="+Codigo+"",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new detallesPV(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getDouble(5),cursor.getDouble(6),cursor.getInt(7),cursor.getInt(8),cursor.getInt(9),cursor.getString(10)));
            }while(cursor.moveToNext());
        }
        cursor=bd.rawQuery("SELECT * FROM VentasProductos WHERE codigoV="+Codigo+" AND codigoP IS NULL",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new detallesPV(cursor.getInt(0),cursor.getInt(1),"null","Producto Desconocido","null",cursor.getDouble(3),cursor.getDouble(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getString(8)));
            }while(cursor.moveToNext());
        }
        return datos;
    }

    @Override
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(DatosProductos());
    }
}
