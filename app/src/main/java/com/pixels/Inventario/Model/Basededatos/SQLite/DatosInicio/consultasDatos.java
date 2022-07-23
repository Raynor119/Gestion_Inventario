package com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pixels.Inventario.Model.DatosE.datosI;

import java.util.ArrayList;
import java.util.List;

public class consultasDatos extends DatosInicio{

    public consultasDatos(Context context) {
        super(context);
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
    public void asignarcontra(String contrasena){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            bd.execSQL("UPDATE INICIO SET CONTRA='"+"si"+"',CONTRASENA='"+contrasena+"' WHERE FINALIZO = '1'");
            bd.close();
        }
    }
    public void eliminarcontra(){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            bd.execSQL("UPDATE INICIO SET CONTRA='"+"no"+"',CONTRASENA='"+""+"' WHERE FINALIZO = '1'");
            bd.close();
        }
    }
    public void modificarContra(String contrasena){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            bd.execSQL("UPDATE INICIO SET CONTRASENA='"+contrasena+"' WHERE FINALIZO = '1'");
            bd.close();
        }
    }
    public void modificarbasedatos(String basedatos,String ip,String nbasedatos,String usuario,String ucontra){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            bd.execSQL("UPDATE INICIO SET BASEDATOS='"+basedatos+"',IP='"+ip+"',NBASEDATOS='"+nbasedatos+"',USUARIO='"+usuario+"',UCONTRA='"+ucontra+"' WHERE FINALIZO = '1'");
            bd.close();
        }
    }
}
