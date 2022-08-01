package com.pixels.Inventario.Model.Basededatos;

import android.content.Context;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.datosI;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.util.List;

public interface MediadorBaseDatos{
    public void ObtenerProductos(ProductosRecyclerViewModel viewModel,List<Producto> productos);
    public void verificarCodigoProducto(VerificarCodigoViewModel viewModel,List<Producto> productos,String codigo);
}
