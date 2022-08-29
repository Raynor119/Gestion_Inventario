package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.VerDatosProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto.EliminarProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgregarProductosMYSQL  extends Conexion implements MediadorBaseDatos {

    private String Codigo,Nombre,TipoC;
    private double Cantidad;
    private int CosteP,Precio,Iva;
    private AgregarProductosViewModel ViewModel;
    private boolean verficar;
    private boolean verificarE=false;
    public AgregarProductosMYSQL(Context context,String codigo, String nombre, double cantidad,String tipoC, int costeP, int precio,int iva,AgregarProductosViewModel viewModel) {
        super(context);
        this.Codigo=codigo;
        this.Nombre=nombre;
        this.Cantidad=cantidad;
        this.TipoC=tipoC;
        this.CosteP=costeP;
        this.Precio=precio;
        this.Iva=iva;
        this.ViewModel=viewModel;
        this.verficar=true;
        execute("");
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                if(verificarE){
                    onCancelled();
                }else{
                    onCancelled("No se puede conectar a La Base Datos");
                    verificarE=true;
                }
            }
        },10000);
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            if(verificarE){
                return "Error en la conexion";
            }else{
                Statement st = connection.createStatement();
                st.executeUpdate("INSERT INTO Producto VALUES('"+Codigo+"','"+Nombre+"',"+Cantidad+",'"+TipoC+"',"+CosteP+","+Precio+","+Iva+")");
                return "";
            }
        }catch (Exception e){
            return "Error al guardar los datos del producto en la Base de Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        verificarE=true;
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
