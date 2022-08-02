package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos;


import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.EditarDatosViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EditarProductosMYSQL extends Conexion implements MediadorBaseDatos {
    private String Codigo,CodigoP,Nombre,TipoC;
    private double Cantidad;
    private int CosteP,Precio;
    private EditarDatosViewModel ViewModel;
    private boolean verficar;

    public EditarProductosMYSQL(Context context, String codigo, String nombre, double cantidad, String tipoC, int costeP, int precio,String codigoP,EditarDatosViewModel viewModel) {
        super(context);
        this.Codigo=codigo;
        this.CodigoP=codigoP;
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
            st.executeUpdate("UPDATE Producto SET codigo='"+Codigo+"',nombre='"+Nombre+"',cantidad="+Cantidad+",TipoC='"+TipoC+"',CosteP="+CosteP+",precio="+Precio+" WHERE codigo='"+CodigoP+"'");
            return "";
        }catch (Exception e){
            return "Error al guardar los datos del producto en la Base de Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("")){
            ConsultaBaseDatos();
        }else {
            verficar=false;
            ConsultaBaseDatos();
            Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(verficar);
    }
}
