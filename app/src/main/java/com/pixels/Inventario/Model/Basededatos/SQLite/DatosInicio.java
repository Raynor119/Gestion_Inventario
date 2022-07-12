package com.pixels.Inventario.Model.Basededatos.SQLite;

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
    private static final String TABLAS="CREATE TABLE INICIO(FINALIZO TEXT , CONTRA TEXT, CONTRASENA TEXT, BASEDATOS TEXT, IP TEXT, NBASEDATOS TEXT, USUARIO TEXT, UCONTRA TEXT)";

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
    public void Iagregar(){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            String nada="";
            bd.execSQL("INSERT INTO INICIO VALUES('"+"0"+"','"+nada+"','"+nada+"','"+nada+"','"+nada+"','"+nada+"','"+nada+"','"+nada+"')");
            bd.close();
        }
    }
    public List<datosI> obtenerD(){
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM INICIO",null);
        List<datosI> datos=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                datos.add(new datosI(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6), cursor.getString(7)));


            }while(cursor.moveToNext());
        }
        return datos;
    }

    public void Imodificar(String finalizo ,String contra,String contrasena,String basedatos,String ip,String nbasedatos,String usuario,String ucontra){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            bd.execSQL("UPDATE INICIO SET FINALIZO='"+finalizo+"',CONTRA='"+contra+"',CONTRASENA='"+contrasena+"',BASEDATOS='"+basedatos+"',IP='"+ip+"',NBASEDATOS='"+nbasedatos+"',USUARIO='"+usuario+"',UCONTRA='"+ucontra+"'");
            bd.close();
        }
    }

}
