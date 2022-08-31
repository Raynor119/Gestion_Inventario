package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.ExportarDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.Venta;
import com.pixels.Inventario.Model.DatosE.VentasProductos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;


public class ExportarDatosSQLite extends AsyncTask<String, Void , String> {
    private String Url,Usuario,Contra;
    private Context Context;

    public ExportarDatosSQLite(String ip, String nbasedatos, String usuario, String contra, Context context){
        this.Url="jdbc:mysql://"+ip+"/"+nbasedatos;
        this.Usuario=usuario;
        this.Contra=contra;
        this.Context=context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.setLoginTimeout(10);
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            Statement st = connection.createStatement();
            ConexionSQLiteExportacionDatos exportacion=new ConexionSQLiteExportacionDatos(Context);
            List<Producto> productos=exportacion.exportarDatosProductos();
            for(int i=0;i<productos.size();i++){
                st.executeUpdate("INSERT INTO Producto VALUES('"+productos.get(i).getCodigo()+"','"+productos.get(i).getNombre()+"',"+productos.get(i).getCantidad()+",'"+productos.get(i).getTipoC()+"',"+productos.get(i).getCosteP()+","+productos.get(i).getPrecio()+","+productos.get(i).getIva()+")");
            }
            List<Venta> ventas=exportacion.exportarDatosVentas();
            for(int i=0;i<ventas.size();i++){
                st.executeUpdate("INSERT INTO Venta VALUES("+ventas.get(i).getCodigo()+",'"+ventas.get(i).getFecha()+"',"+ventas.get(i).getEfectivo()+")");
            }
            List<VentasProductos> ventasProductos=exportacion.exportarDatosVentasProductos();
            for(int i=0;i<ventasProductos.size();i++){
                if(ventasProductos.get(i).getCodigoP()==null){
                    if(ventasProductos.get(i).getObservacionD()==null){
                        st.executeUpdate("INSERT INTO VentasProductos VALUES("+ventasProductos.get(i).getId()+","+ventasProductos.get(i).getCodigoV()+",NULL,"+ventasProductos.get(i).getCantidadV()+","+ventasProductos.get(i).getCantidadD()+","+ventasProductos.get(i).getCostePV()+","+ventasProductos.get(i).getPrecioPV()+","+ventasProductos.get(i).getIva()+",'"+ventasProductos.get(i).getEstadoDevolucion()+"',NULL)");
                    }else{
                        st.executeUpdate("INSERT INTO VentasProductos VALUES("+ventasProductos.get(i).getId()+","+ventasProductos.get(i).getCodigoV()+",NULL,"+ventasProductos.get(i).getCantidadV()+","+ventasProductos.get(i).getCantidadD()+","+ventasProductos.get(i).getCostePV()+","+ventasProductos.get(i).getPrecioPV()+","+ventasProductos.get(i).getIva()+",'"+ventasProductos.get(i).getEstadoDevolucion()+"','"+ventasProductos.get(i).getObservacionD()+"')");
                    }
                }else{
                    if(ventasProductos.get(i).getObservacionD()==null){
                        st.executeUpdate("INSERT INTO VentasProductos VALUES("+ventasProductos.get(i).getId()+","+ventasProductos.get(i).getCodigoV()+",'"+ventasProductos.get(i).getCodigoP()+"',"+ventasProductos.get(i).getCantidadV()+","+ventasProductos.get(i).getCantidadD()+","+ventasProductos.get(i).getCostePV()+","+ventasProductos.get(i).getPrecioPV()+","+ventasProductos.get(i).getIva()+",'"+ventasProductos.get(i).getEstadoDevolucion()+"',NULL)");
                    }else{
                        st.executeUpdate("INSERT INTO VentasProductos VALUES("+ventasProductos.get(i).getId()+","+ventasProductos.get(i).getCodigoV()+",'"+ventasProductos.get(i).getCodigoP()+"',"+ventasProductos.get(i).getCantidadV()+","+ventasProductos.get(i).getCantidadD()+","+ventasProductos.get(i).getCostePV()+","+ventasProductos.get(i).getPrecioPV()+","+ventasProductos.get(i).getIva()+",'"+ventasProductos.get(i).getEstadoDevolucion()+"','"+ventasProductos.get(i).getObservacionD()+"')");
                    }
                }
            }
            return "Se exporto los datos Exitosamente";
        }catch (Exception e){
            return "Error al exportar los datos : "+e;
        }
    }
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
    }
}
