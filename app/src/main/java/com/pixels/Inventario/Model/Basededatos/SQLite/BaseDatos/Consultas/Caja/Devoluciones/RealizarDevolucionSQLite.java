package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.Devoluciones;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.RealizarDevolucionViewModel;

public class RealizarDevolucionSQLite extends BaseDatosSQLite implements MediadorBaseDatos{
    private devoluciones Context;
    private RealizarDevolucionViewModel ViewModel;
    private String CodigoV;
    private boolean Verificar=true;

    public RealizarDevolucionSQLite(devoluciones context,RealizarDevolucionViewModel viewModel,String codigoV) {
        super(context);
        this.Context=context;
        this.ViewModel=viewModel;
        this.CodigoV=codigoV;
        DevolucionSQLite();
    }

    public void DevolucionSQLite(){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            try{
                for(int i=0;i<Context.Productos.size();i++){
                    bd.execSQL("UPDATE VentasProductos SET EstadoDevolucion='si', ObservacionD='"+Context.Productos.get(i).getObservacionD()+"' WHERE codigoV="+CodigoV+" AND Id="+Context.Productos.get(i).getId()+"");
                }
                ConsultaBaseDatos();
            }catch (Exception e){
                Toast.makeText(Context, "Error en La Base Datos", Toast.LENGTH_LONG).show();
                Verificar=false;
                ConsultaBaseDatos();
            }
            bd.close();
        }
    }

    @Override
    public void ConsultaBaseDatos() {
        if(Verificar){
            ViewModel.resultado.setValue(true);
        }else{
            ViewModel.resultado.setValue(false);
        }
    }
}