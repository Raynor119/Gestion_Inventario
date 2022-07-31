package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BaseDatosSQLite extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="Inventario";
    private static final int VERSION_BD=1;
    private static final String TABLAProducto="CREATE TABLE Producto(codigo VARCHAR(255) PRIMARY KEY NOT NULL,nombre VARCHAR(255) NOT NULL,cantidad DOUBLE NOT NULL,TipoC VARCHAR(255) NOT NULL,CosteP BIGINT NOT NULL,precio BIGINT NOT NULL)";
    private static final String TABLAVenta="CREATE TABLE Venta(codigo INTEGER PRIMARY KEY AUTOINCREMENT,Fecha TEXT NOT NULL,Efectivo BIGINT NOT NULL)";
    private static final String TABLAVentasProductos="CREATE TABLE VentasProductos(Id INTEGER PRIMARY KEY AUTOINCREMENT,codigoV INT NOT NULL,codigoP VARCHAR(255),CantidadV DOUBLE NOT NULL,CostePV BIGINT NOT NULL,PrecioPV BIGINT NOT NULL,FOREIGN KEY (codigoV) REFERENCES Venta(codigo),FOREIGN KEY (codigoP) REFERENCES Producto(codigo) ON UPDATE CASCADE)";

    public BaseDatosSQLite(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.setForeignKeyConstraintsEnabled(true);
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLAProducto);
        sqLiteDatabase.execSQL(TABLAVenta);
        sqLiteDatabase.execSQL(TABLAVentasProductos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLAProducto);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLAVenta);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLAVentasProductos);
        sqLiteDatabase.execSQL(TABLAProducto);
        sqLiteDatabase.execSQL(TABLAVenta);
        sqLiteDatabase.execSQL(TABLAVentasProductos);
    }
}
