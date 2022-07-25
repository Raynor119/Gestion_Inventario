package com.pixels.Inventario.Model.DatosE;

import java.util.List;

public class ImportDatos {
    private List<Producto> Productos;
    private List<Venta> Ventas;
    private List<VentasProductos> VentasProductos;

    public ImportDatos(List<Producto> productos, List<Venta> ventas, List<com.pixels.Inventario.Model.DatosE.VentasProductos> ventasProductos) {
        this.Productos = productos;
        this.Ventas = ventas;
        this.VentasProductos = ventasProductos;
    }

    public List<Producto> getProductos() {
        return Productos;
    }

    public void setProductos(List<Producto> productos) {
        Productos = productos;
    }

    public List<Venta> getVentas() {
        return Ventas;
    }

    public void setVentas(List<Venta> ventas) {
        Ventas = ventas;
    }

    public List<com.pixels.Inventario.Model.DatosE.VentasProductos> getVentasProductos() {
        return VentasProductos;
    }

    public void setVentasProductos(List<com.pixels.Inventario.Model.DatosE.VentasProductos> ventasProductos) {
        VentasProductos = ventasProductos;
    }
}
