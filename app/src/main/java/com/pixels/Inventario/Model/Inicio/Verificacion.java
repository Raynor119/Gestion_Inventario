package com.pixels.Inventario.Model.Inicio;


import android.content.SharedPreferences;
import com.pixels.Inventario.View.Activity.InicioA.Inicio;
public class Verificacion {
    private Inicio Context;
    public Verificacion(Inicio context){
        this.Context=context;
    }
    public int getFirstTimeRun() {
        SharedPreferences sp = Context.getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = 2;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;
    }
}
