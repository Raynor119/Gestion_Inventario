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
        Cursor cursor=bd.rawQuery("SELECT * FROM Producto",null);
        if(cursor.moveToFirst()){
            do{
                datos.add(new Producto(cursor.getString(0),cursor.getString(1),cursor.getDouble(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6)));
            }while(cursor.moveToNext());
        }
        return datos;
    }
    @Override
    public void ConsultaBaseDatos() {
        boolean verificar=false;
        List<Producto> productos=DatosProductos();
        List<Producto> productosEnviar=new ArrayList<>();
        for(int i=0;i<productos.size();i++){
            if(productos.get(i).getCodigo().equals(Codigo)){
                verificar=true;
                productosEnviar.add(new Producto(productos.get(i).getCodigo(),productos.get(i).getNombre(),productos.get(i).getCantidad(),productos.get(i).getTipoC(),productos.get(i).getCosteP(),productos.get(i).getPrecio(),productos.get(i).getIva()));
            }
        }
        ViewModel.resultado.setValue(verificar);
        if(verificar){
            ViewModel.productos.setValue(productosEnviar);
        }
    }
}
