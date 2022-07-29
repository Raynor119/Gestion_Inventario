package com.pixels.Inventario.Model.Basededatos;

import android.content.Context;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.datosI;

import java.util.List;

public interface MediadorBaseDatos{
    public List<Producto> ObtenerProductos(Context context,List<datosI> datos);
}
