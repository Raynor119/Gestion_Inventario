package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.View.Activity.Caja.Caja;
import com.pixels.Inventario.ViewModel.Caja.Venta.RealizarVentaViewModel;

public class RealizarVentaSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    private Caja Context;
    private int Efectivo;
    private RealizarVentaViewModel ViewModel;
    private String CodigoV="";

    public RealizarVentaSQLite(Caja context, int efectivo, RealizarVentaViewModel viewModel) {
        super(context);
        this.Context=context;
        this.Efectivo=efectivo;
        this.ViewModel=viewModel;
        VentaSQlite();
    }
    public void VentaSQlite(){
        SQLiteDatabase bd=getReadableDatabase();

    }

    @Override
    public void ConsultaBaseDatos() {

    }
}
