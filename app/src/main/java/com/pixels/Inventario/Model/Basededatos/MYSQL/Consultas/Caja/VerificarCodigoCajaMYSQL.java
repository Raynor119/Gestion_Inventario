package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Caja.VerificarCodigo.VerificarCodigoCajaViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerificarCodigoCajaMYSQL extends Conexion implements MediadorBaseDatos {
    public List<Producto> productos;
    private VerificarCodigoCajaViewModel ViewModel;
    public String Codigo;
    private boolean verificarE=false;
    public VerificarCodigoCajaMYSQL(Context context, VerificarCodigoCajaViewModel viewModel, String codigo) {
        super(context);
        this.ViewModel=viewModel;
        this.Codigo=codigo;
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
                ResultSet rs = st.executeQuery("SELECT * FROM Producto");
                productos=new ArrayList<>();
                while (rs.next()) {
                    productos.add(new Producto(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));
                }
                return "";
            }
        }catch (Exception e){
            return "No se puede conectar a La Base Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        verificarE=true;
        if(result.equals("")){
            ConsultaBaseDatos();
        }else {
            if(result.equals("Error en la conexion")){

            }else{
                Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void ConsultaBaseDatos() {
        boolean verificar=false;
        List<Producto> productosEnviar=new ArrayList<>();
        for(int i=0;i<productos.size();i++){
            if(productos.get(i).getCodigo().equals(Codigo)){
                verificar=true;
                productosEnviar.add(new Producto(productos.get(i).getCodigo(),productos.get(i).getNombre(),productos.get(i).getCantidad(),productos.get(i).getTipoC(),productos.get(i).getCosteP(),productos.get(i).getPrecio(),productos.get(i).getIva()));
            }
        }
        ViewModel.resultado.setValue(verificar);
        if(verificar){
            ViewModel.productos.setValue(productosEnviar);
        }
    }
}
