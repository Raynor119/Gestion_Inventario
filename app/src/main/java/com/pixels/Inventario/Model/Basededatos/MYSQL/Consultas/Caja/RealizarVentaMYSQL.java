package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja;

import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;

import com.pixels.Inventario.Model.DatosE.VentaRealizada;
import com.pixels.Inventario.View.Activity.Caja.Caja;
import com.pixels.Inventario.ViewModel.Caja.Venta.RealizarVentaViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RealizarVentaMYSQL extends Conexion implements MediadorBaseDatos {

    private Caja Context;
    private int Efectivo;
    private RealizarVentaViewModel ViewModel;
    private String CodigoV="";

    public RealizarVentaMYSQL(Caja context, int efectivo, RealizarVentaViewModel viewModel) {
        super(context);
        this.Context=context;
        this.Efectivo=efectivo;
        this.ViewModel=viewModel;
        execute("");
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO Venta (Efectivo) VALUES("+Efectivo+")");
            ResultSet rs = st.executeQuery("SELECT * FROM Venta ORDER by codigo DESC LIMIT 1");
            while (rs.next()) {
                CodigoV=rs.getString(1);
            }
            for(int i=0;i< Context.Productos.size();i++){
                st.executeUpdate("INSERT INTO VentasProductos (codigoV,codigoP,CantidadV,CostePV,PrecioPV,IvaPV,EstadoDevolucion,ObservacionD) VALUES("+CodigoV+",'"+Context.Productos.get(i).getCodigo()+"',"+Context.Productos.get(i).getCantidad()+","+Context.Productos.get(i).getCosteP()+","+Context.Productos.get(i).getPrecio()+","+Context.Productos.get(i).getIva()+",'no',NULL)");
                rs = st.executeQuery("SELECT cantidad FROM Producto WHERE codigo='"+Context.Productos.get(i).getCodigo()+"'");
                double cantidaP=0;
                while (rs.next()) {
                    cantidaP=rs.getDouble(1);
                }
                double cantidadDiferente=0;
                if((cantidaP-Context.Productos.get(i).getCantidad())<0){
                    cantidadDiferente=Context.Productos.get(i).getCantidad()+(cantidaP-Context.Productos.get(i).getCantidad());
                }else{
                    cantidadDiferente=Context.Productos.get(i).getCantidad();
                }
                st.executeUpdate("UPDATE Producto SET cantidad=cantidad-"+cantidadDiferente+" WHERE codigo='"+Context.Productos.get(i).getCodigo()+"' AND cantidad!=0");
            }
            return "";
        }catch (Exception e){
            return "No se puede conectar a La Base Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("")){
            ConsultaBaseDatos();
        }else {
            Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
            List<VentaRealizada> resultado=new ArrayList<>();
            resultado.add(new VentaRealizada(false,""));
            ViewModel.resultado.setValue(resultado);
        }
    }

    @Override
    public void ConsultaBaseDatos() {
        if(CodigoV.equals("")){
            List<VentaRealizada> resultado=new ArrayList<>();
            resultado.add(new VentaRealizada(false,""));
            ViewModel.resultado.setValue(resultado);
        }else{
            List<VentaRealizada> resultado=new ArrayList<>();
            resultado.add(new VentaRealizada(true,CodigoV));
            ViewModel.resultado.setValue(resultado);
        }
    }
}
