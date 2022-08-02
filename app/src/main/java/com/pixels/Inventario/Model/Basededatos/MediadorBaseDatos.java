package com.pixels.Inventario.Model.Basededatos;

import android.content.Context;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.datosI;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.VerDatosProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto.EliminarProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.util.List;

public interface MediadorBaseDatos{
    public void ConsultaBaseDatos();
}
