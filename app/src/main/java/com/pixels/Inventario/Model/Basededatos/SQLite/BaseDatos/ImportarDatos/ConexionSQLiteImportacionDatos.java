package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.ImportarDatos;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.Venta;
import com.pixels.Inventario.Model.DatosE.VentasProductos;

import java.util.List;

public class ConexionSQLiteImportacionDatos extends BaseDatosSQLite {
    public Context Context;
    public ConexionSQLiteImportacionDatos(Context context) {
        super(context);
        this.Context=context;
    }
    public void importarDatosProductos(List<Producto> productos){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            for(int i=0;i< productos.size();i++){
                try {
                    bd.execSQL("INSERT INTO Producto VALUES('"+productos.get(i).getCodigo()+"','"+productos.get(i).getNombre()+"',"+productos.get(i).getCantidad()+",'"+productos.get(i).getTipoC()+"',"+productos.get(i).getCosteP()+","+productos.get(i).getPrecio()+","+productos.get(i).getIva()+")");
                }catch (Exception e){
                    Toast.makeText(Context, "Error al Importar los Datos de los Productos ", Toast.LENGTH_LONG).show();
                }
            }
            bd.close();
        }
    }
    public void importarDatosVentas(List<Venta> ventas){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            for(int i=0;i< ventas.size();i++){
                try {
                    bd.execSQL("INSERT INTO Venta VALUES("+ventas.get(i).getCodigo()+",'"+ventas.get(i).getFecha()+"',"+ventas.get(i).getEfectivo()+")");
                }catch (Exception e){
                    Toast.makeText(Context, "Error al Importar los Datos de las Ventas", Toast.LENGTH_LONG).show();
                }
            }
            bd.close();
        }
    }
    public void importarDatosVentasProductos(List<VentasProductos> VentasProductos){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            for(int i=0;i< VentasProductos.size();i++){
                try {
                    if(VentasProductos.get(i).getCodigoP()==null){
                        if(VentasProductos.get(i).getObservacionD()==null){
                            bd.execSQL("INSERT INTO VentasProductos VALUES("+VentasProductos.get(i).getId()+","+VentasProductos.get(i).getCodigoV()+",NULL,"+VentasProductos.get(i).getCantidadV()+","+VentasProductos.get(i).getCostePV()+","+VentasProductos.get(i).getPrecioPV()+","+VentasProductos.get(i).getIva()+",'"+VentasProductos.get(i).getEstadoDevolucion()+"',NULL)");
                        }else{
                            bd.execSQL("INSERT INTO VentasProductos VALUES("+VentasProductos.get(i).getId()+","+VentasProductos.get(i).getCodigoV()+",NULL,"+VentasProductos.get(i).getCantidadV()+","+VentasProductos.get(i).getCostePV()+","+VentasProductos.get(i).getPrecioPV()+","+VentasProductos.get(i).getIva()+",'"+VentasProductos.get(i).getEstadoDevolucion()+"','"+VentasProductos.get(i).getObservacionD()+"')");
                        }
                    }else{
                        if(VentasProductos.get(i).getObservacionD()==null){
                            bd.execSQL("INSERT INTO VentasProductos VALUES("+VentasProductos.get(i).getId()+","+VentasProductos.get(i).getCodigoV()+",'"+VentasProductos.get(i).getCodigoP()+"',"+VentasProductos.get(i).getCantidadV()+","+VentasProductos.get(i).getCostePV()+","+VentasProductos.get(i).getPrecioPV()+","+VentasProductos.get(i).getIva()+",'"+VentasProductos.get(i).getEstadoDevolucion()+"',NULL)");
                        }else{
                            bd.execSQL("INSERT INTO VentasProductos VALUES("+VentasProductos.get(i).getId()+","+VentasProductos.get(i).getCodigoV()+",'"+VentasProductos.get(i).getCodigoP()+"',"+VentasProductos.get(i).getCantidadV()+","+VentasProductos.get(i).getCostePV()+","+VentasProductos.get(i).getPrecioPV()+","+VentasProductos.get(i).getIva()+",'"+VentasProductos.get(i).getEstadoDevolucion()+"','"+VentasProductos.get(i).getObservacionD()+"')");
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(Context, "Error al Importar los Datos la tabla VentasProductos", Toast.LENGTH_LONG).show();
                }
            }
            bd.close();
        }
    }
}
