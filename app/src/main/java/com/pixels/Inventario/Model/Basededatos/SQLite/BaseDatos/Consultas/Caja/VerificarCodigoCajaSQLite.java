package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Caja.VerificarCodigo.VerificarCodigoCajaViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.util.ArrayList;
import java.util.List;

public class VerificarCodigoCajaSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    private Context Context;
    private VerificarCodigoCajaViewModel ViewModel;
    private String Codigo;

    public VerificarCodigoCajaSQLite(Context context, VerificarCodigoCajaViewModel viewModel, String codigo) {
        super(context);
        this.Context=context;
        this.ViewModel=viewModel;
        this.Codigo=codigo;
        ConsultaBaseDatos();
    }
    public List<Producto> DatosProductos(){
        List<Producto> datos=new ArrayList<>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM Producto WHERE codigo='"+Codigo+"'",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new Producto(cursor.getString(0),cursor.getString(1),cursor.getDouble(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
    @Override
    public void ConsultaBaseDatos() {
        boolean verificar=true;
        List<Producto> productos=DatosProductos();
        List<Producto> productosEnviar=new ArrayList<>();

        if(productos.size()==0){
            verificar=false;
        }else{
            productosEnviar.add(new Producto(productos.get(0).getCodigo(),productos.get(0).getNombre(),productos.get(0).getCantidad(),productos.get(0).getTipoC(),productos.get(0).getCosteP(),productos.get(0).getPrecio(),productos.get(0).getIva()));
        }
        ViewModel.resultado.setValue(verificar);
        if(verificar){
            ViewModel.productos.setValue(productosEnviar);
        }
    }
}
