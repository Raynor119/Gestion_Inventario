package com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.pixels.Inventario.Model.DatosE.datosI;
import java.util.ArrayList;
import java.util.List;

public class DatosInicio extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="DatosdeInicio";
    private static final int VERSION_BD=1;
    private static final String TABLAS="CREATE TABLE INICIO(FINALIZO TEXT , CONTRA TEXT, CONTRASENA TEXT, BASEDATOS TEXT, IP TEXT, NBASEDATOS TEXT, USUARIO TEXT, UCONTRA TEXT,NIT TEXT,NOMBRE TEXT)";

    public DatosInicio(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLAS);
        sqLiteDatabase.execSQL(TABLAS);
    }
}
