package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto.EliminarProductoViewModel;

public class EliminarProductosSQLite  extends BaseDatosSQLite implements MediadorBaseDatos {
    private String Codigo;
    private Context Context;
    private EliminarProductoViewModel ViewModel;
    public EliminarProductosSQLite(Context context,String codigo,EliminarProductoViewModel viewModel) {
        super(context);
        this.Context=context;
        this.Codigo=codigo;
        this.ViewModel=viewModel;
        ConsultaBaseDatos();
    }
    @Override
    public void ConsultaBaseDatos() {
        SQLiteDatabase bd=getWritableDatabase();
        boolean verificar=true;
        if(bd!=null)
        {
            try {
                bd.execSQL("UPDATE VentasProductos SET codigoP=NULL WHERE codigoP='"+Codigo+"'");
                bd.close();
                bd=getWritableDatabase();
                bd.execSQL("DELETE FROM Producto WHERE codigo='"+Codigo+"'");
            }catch (Exception e){
                Toast.makeText(Context, "Error al Eliminar el producto: "+e, Toast.LENGTH_LONG).show();
                verificar=false;
            }
            ViewModel.resultado.setValue(verificar);
            bd.close();
        }
    }
}
