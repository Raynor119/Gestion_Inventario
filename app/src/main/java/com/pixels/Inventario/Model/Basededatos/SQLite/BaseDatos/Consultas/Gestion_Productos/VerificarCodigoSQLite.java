package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.VerDatosProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto.EliminarProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.util.ArrayList;
import java.util.List;

public class VerificarCodigoSQLite extends BaseDatosSQLite implements MediadorBaseDatos {
    public VerificarCodigoSQLite(Context context,VerificarCodigoViewModel viewModel,String codigo) {
        super(context);
        verificarCodigoProducto(viewModel,DatosProductos(),codigo);
    }
    public List<Producto> DatosProductos(){
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
    @Override
    public void ObtenerProductos(ProductosRecyclerViewModel viewModel, List<Producto> productos) {
    }
    @Override
    public void verificarCodigoProducto(VerificarCodigoViewModel viewModel, List<Producto> productos,String codigo) {
        boolean verificar=true;
        for(int i=0;i<productos.size();i++){
            if(productos.get(i).getCodigo().equals(codigo)){
                verificar=false;
            }
        }
        viewModel.resultado.setValue(verificar);
    }

    @Override
    public void agregarproductobasedatos(AgregarProductosViewModel viewModel) {
    }
    @Override
    public void EliminarProducto(EliminarProductoViewModel viewModel) {
    }

    @Override
    public void VerDatosProductoCodigo(VerDatosProductoViewModel viewModel, List<Producto> producto) {

    }
}
