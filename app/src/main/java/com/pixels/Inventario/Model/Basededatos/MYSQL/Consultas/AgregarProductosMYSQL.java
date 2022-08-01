package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgregarProductosMYSQL  extends Conexion implements MediadorBaseDatos {
    public String Codigo,Nombre,TipoC;
    public double Cantidad;
    public int CosteP,Precio;
    private AgregarProductosViewModel ViewModel;
    private boolean verficar;

    public AgregarProductosMYSQL(Context context,String codigo, String nombre, double cantidad,String tipoC, int costeP, int precio,AgregarProductosViewModel viewModel) {
        super(context);
        this.Codigo=codigo;
        this.Nombre=nombre;
        this.Cantidad=cantidad;
        this.TipoC=tipoC;
        this.CosteP=costeP;
        this.Precio=precio;
        this.ViewModel=viewModel;
        this.verficar=true;
        execute("");
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO Producto VALUES('"+Codigo+"','"+Nombre+"',"+Cantidad+",'"+TipoC+"',"+CosteP+","+Precio+")");
            return "";
        }catch (Exception e){
            return "Error al guardar los datos del producto en la Base de Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("")){
            agregarproductobasedatos(ViewModel);
        }else {
            verficar=false;
            agregarproductobasedatos(ViewModel);
            Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void ObtenerProductos(ProductosRecyclerViewModel viewModel, List<Producto> productos) {
    }
    @Override
    public void verificarCodigoProducto(VerificarCodigoViewModel viewModel, List<Producto> productos, String codigo) {
    }
    @Override
    public void agregarproductobasedatos(AgregarProductosViewModel viewModel) {
        viewModel.resultado.setValue(verficar);
    }
}
