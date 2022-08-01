package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto.EliminarProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.util.List;

public class AgregarProductosSQLite  extends BaseDatosSQLite implements MediadorBaseDatos {
    public Context Context;
    public String Codigo,Nombre,TipoC;
    public double Cantidad;
    public int CosteP,Precio;

    public AgregarProductosSQLite(Context context,String codigo, String nombre, double cantidad,String tipoC, int costeP, int precio,AgregarProductosViewModel viewModel) {
        super(context);
        this.Codigo=codigo;
        this.Nombre=nombre;
        this.Cantidad=cantidad;
        this.TipoC=tipoC;
        this.CosteP=costeP;
        this.Precio=precio;
        agregarproductobasedatos(viewModel);
    }
    @Override
    public void ObtenerProductos(ProductosRecyclerViewModel viewModel, List<Producto> productos) {
    }
    @Override
    public void verificarCodigoProducto(VerificarCodigoViewModel viewModel, List<Producto> productos, String codigo) {
    }
    @Override
    public void agregarproductobasedatos(AgregarProductosViewModel viewModel) {
        SQLiteDatabase bd=getWritableDatabase();
        boolean verificar=true;
        if(bd!=null)
        {
            try {
                bd.execSQL("INSERT INTO Producto VALUES('"+Codigo+"','"+Nombre+"',"+Cantidad+",'"+TipoC+"',"+CosteP+","+Precio+")");
            }catch (Exception e){
                Toast.makeText(Context, "Error al guardar los datos del producto en la Base de Datos", Toast.LENGTH_LONG).show();
                verificar=false;
            }
            viewModel.resultado.setValue(verificar);
            bd.close();
        }
    }
    @Override
    public void EliminarProducto(EliminarProductoViewModel viewModel) {
    }
}
