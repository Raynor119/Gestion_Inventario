package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Gestion_Productos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.EditarDatosViewModel;

public class EditarProductosSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    private Context Context;
    private String Codigo,CodigoP,Nombre,TipoC;
    private double Cantidad;
    private int CosteP,Precio,Iva;
    private EditarDatosViewModel ViewModel;

    public EditarProductosSQLite(Context context, String codigo, String nombre, double cantidad, String tipoC, int costeP, int precio,int iva,String codigoP,EditarDatosViewModel viewModel) {
        super(context);
        this.Context=context;
        this.Codigo=codigo;
        this.CodigoP=codigoP;
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
                bd.execSQL("UPDATE Producto SET codigo='"+Codigo+"',nombre='"+Nombre+"',cantidad="+Cantidad+",TipoC='"+TipoC+"',CosteP="+CosteP+",precio="+Precio+",Iva="+Iva+" WHERE codigo='"+CodigoP+"'");
            }catch (Exception e){
                Toast.makeText(Context, "Error al modificar los datos del producto en la Base de Datos", Toast.LENGTH_LONG).show();
                verificar=false;
            }
            ViewModel.resultado.setValue(verificar);
            bd.close();
        }
    }
}