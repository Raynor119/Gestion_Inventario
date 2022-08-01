package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.VerDatosProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto.EliminarProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.util.List;

public class EliminarProductosSQLite  extends BaseDatosSQLite implements MediadorBaseDatos {
    private String Codigo;
    private Context Context;
    public EliminarProductosSQLite(Context context,String codigo,EliminarProductoViewModel viewModel) {
        super(context);
        this.Context=context;
        this.Codigo=codigo;
        EliminarProducto(viewModel);
    }
    @Override
    public void ObtenerProductos(ProductosRecyclerViewModel viewModel, List<Producto> productos) {
    }
    @Override
    public void verificarCodigoProducto(VerificarCodigoViewModel viewModel, List<Producto> productos, String codigo) {
    }
    @Override
    public void agregarproductobasedatos(AgregarProductosViewModel viewModel) {
    }
    @Override
    public void EliminarProducto(EliminarProductoViewModel viewModel) {
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
            viewModel.resultado.setValue(verificar);
            bd.close();
        }
    }
    @Override
    public void VerDatosProductoCodigo(VerDatosProductoViewModel viewModel, List<Producto> producto) {
    }
}
