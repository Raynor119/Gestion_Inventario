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

public class AgregarProductosSQLite  extends BaseDatosSQLite implements MediadorBaseDatos {
    private Context Context;
    private String Codigo,Nombre,TipoC;
    private double Cantidad;
    private int CosteP,Precio,Iva;
    private AgregarProductosViewModel ViewModel;

    public AgregarProductosSQLite(Context context,String codigo, String nombre, double cantidad,String tipoC, int costeP, int precio,int iva,AgregarProductosViewModel viewModel) {
        super(context);
        this.Context=context;
        this.Codigo=codigo;
        this.Nombre=nombre;
        this.Cantidad=cantidad;
        this.TipoC=tipoC;
        this.CosteP=costeP;
        this.Precio=precio;
        this.Iva=iva;
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
                bd.execSQL("INSERT INTO Producto VALUES('"+Codigo+"','"+Nombre+"',"+Cantidad+",'"+TipoC+"',"+CosteP+","+Precio+","+Iva+")");
            }catch (Exception e){
                Toast.makeText(Context, "Error al guardar los datos del producto en la Base de Datos", Toast.LENGTH_LONG).show();
                verificar=false;
            }
            ViewModel.resultado.setValue(verificar);
            bd.close();
        }
    }
}
