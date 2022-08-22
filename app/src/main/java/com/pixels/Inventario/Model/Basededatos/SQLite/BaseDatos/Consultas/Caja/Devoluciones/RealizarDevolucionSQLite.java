package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja.Devoluciones;

import android.content.Context;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.RealizarDevolucionViewModel;

public class RealizarDevolucionSQLite extends BaseDatosSQLite implements MediadorBaseDatos{
    private devoluciones Context;
    private RealizarDevolucionViewModel ViewModel;

    public RealizarDevolucionSQLite(devoluciones context,RealizarDevolucionViewModel viewModel) {
        super(context);
        this.Context=context;
        this.ViewModel=viewModel;
    }

    public void DevolucionSQLite(){

    }

    @Override
    public void ConsultaBaseDatos() {

    }
}
