package com.pixels.Inventario.Model.Basededatos.MYSQL;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas extends Conexion{
    public Consultas(Context context) {
        super(context);
    }

}
