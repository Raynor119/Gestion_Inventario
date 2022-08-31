package com.pixels.Inventario.Model.Basededatos.MYSQL.VerificacionI;

import android.os.AsyncTask;
import com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial.CrearBaseVerificarViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class VerificarCreacionBaseDatos extends AsyncTask<String, Void , Boolean> {

    private String Url,Usuario,Contra,NBaseDatos,Ip;
    private CrearBaseVerificarViewModel Context;
    private boolean verificarE=false;
    public VerificarCreacionBaseDatos(String Ip, String NBasedatos, String usuario, String contra, CrearBaseVerificarViewModel context){
        this.Url="jdbc:mysql://"+Ip+"/mysql";
        this.Ip=Ip;
        this.NBaseDatos=NBasedatos;
        this.Usuario=usuario;
        this.Contra=contra;
        this.Context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                if(verificarE){

                }else{
                    Context.resultado.setValue(false);
                    verificarE=true;
                }
            }
        },11000);
    }
    @Override
    protected Boolean doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.setLoginTimeout(10);
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            Statement st=connection.createStatement();
            st.execute("CREATE DATABASE "+NBaseDatos);
            connection.close();
            String Url="jdbc:mysql://"+Ip+"/"+NBaseDatos;
            connection= DriverManager.getConnection(Url,Usuario,Contra);
            st=connection.createStatement();
            st.execute("CREATE TABLE Producto(codigo VARCHAR(255) PRIMARY KEY NOT NULL,nombre VARCHAR(255) NOT NULL,cantidad DOUBLE NOT NULL,TipoC VARCHAR(255) NOT NULL,CosteP BIGINT NOT NULL,precio BIGINT NOT NULL,Iva INT NOT NULL)");
            st.execute("CREATE TABLE Venta(codigo INT AUTO_INCREMENT,Fecha DATETIME NOT NULL DEFAULT NOW(),Efectivo BIGINT NOT NULL,PRIMARY KEY (codigo))");
            st.execute("CREATE TABLE VentasProductos(Id INT AUTO_INCREMENT,codigoV INT NOT NULL,codigoP VARCHAR(255),CantidadV DOUBLE NOT NULL,CantidadD DOUBLE NOT NULL,CostePV BIGINT NOT NULL,PrecioPV BIGINT NOT NULL,IvaPV INT NOT NULL,EstadoDevolucion VARCHAR(255) NOT NULL,ObservacionD VARCHAR(255),PRIMARY KEY (Id),FOREIGN KEY (codigoV) REFERENCES Venta(codigo),FOREIGN KEY (codigoP) REFERENCES Producto(codigo) ON UPDATE CASCADE)");
            connection.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    protected void onPostExecute(Boolean result) {
        if(verificarE){

        }else{
            verificarE=true;
            Context.resultado.setValue(result);
        }
    }
}