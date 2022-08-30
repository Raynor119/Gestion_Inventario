package com.pixels.Inventario.View.Ajustes.ConfiguracionContra;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Ajustes.Ajustes;
import com.pixels.Inventario.View.Ajustes.ConfiguracionContra.AlertDialog.alertcontra;
import com.pixels.Inventario.View.Ajustes.ConfiguracionContra.Fragment.ModificarContra;
import com.pixels.Inventario.View.Ajustes.ConfiguracionContra.Fragment.asignarContra;

public class configContra extends AppCompatActivity {
    private FragmentTransaction transaction;
    public Fragment asignarContra,ModificarContra;
    public static Ajustes Context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_contra);
        Bundle extra=getIntent().getExtras();
        String fragment=extra.getString("fragment");

        if(fragment.equals("1")){
            this.setTitle("Modificar la Contraseña");
            SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Context);
            boolean isalert = myPreferences.getBoolean("redimension",false);
            if(isalert){
                alertcontra alert =new alertcontra(configContra.this);
                alert.alertConfig();
            }else{
                modificarContra();
            }
        }
        if(fragment.equals("6")){
            this.setTitle("Configuracion de la Contraseña");
            asignarContra= new asignarContra(configContra.this);
            transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.configFragments,asignarContra).commit();
        }

    }
    public void modificarContra(){
        ModificarContra= new ModificarContra(configContra.this);
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.configFragments,ModificarContra).commit();
    }

}
