package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.Devoluciones;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;

import com.pixels.Inventario.Model.DatosE.VentasProductoD;

import com.pixels.Inventario.ViewModel.Caja.Devoluciones.VerificarCodigoV.VerificarCodigoVentaViewModel;


import java.util.ArrayList;
import java.util.List;

public class VerificarCodigoVentaSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    private Context Context;
    private VerificarCodigoVentaViewModel ViewModel;
    private String Codigo;

    public VerificarCodigoVentaSQLite(Context context, VerificarCodigoVentaViewModel viewModel, String codigo) {
        super(context);
        this.Context=context;
        this.ViewModel=viewModel;
        this.Codigo=codigo;
        ConsultaBaseDatos();
    }
    public List<VentasProductoD> DatosProductos(){
        List<VentasProductoD> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT VentasProductos.Id,VentasProductos.codigoV,VentasProductos.codigoP,Producto.nombre,VentasProductos.CantidadV,VentasProductos.CostePV,VentasProductos.PrecioPV,VentasProductos.IvaPV,VentasProductos.EstadoDevolucion,VentasProductos.ObservacionD FROM VentasProductos INNER JOIN Producto ON VentasProductos.codigoP=Producto.codigo WHERE VentasProductos.codigoV="+Codigo+"",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new VentasProductoD(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getDouble(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getString(8),cursor.getString(9)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
    @Override
    public void ConsultaBaseDatos() {
        boolean verificar=false;
        List<VentasProductoD> productos=DatosProductos();
        List<VentasProductoD> productosEnviar=new ArrayList<>();
        for(int i=0;i<productos.size();i++){
            if((productos.get(i).getCodigoV()+"").equals(Codigo)){
                verificar=true;
                productosEnviar.add(new VentasProductoD(productos.get(i).getId(),productos.get(i).getCodigoV(),productos.get(i).getCodigoP(),productos.get(i).getNombre(),productos.get(i).getCantidadV(),productos.get(i).getCostePV(),productos.get(i).getPrecioPV(),productos.get(i).getIva(),productos.get(i).getEstadoDevolucion(),productos.get(i).getObservacionD()));
            }
        }
        ViewModel.resultado.setValue(verificar);
        if(verificar){
            ViewModel.productos.setValue(productosEnviar);
        }
    }
}
