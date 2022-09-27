package com.pixels.Inventario.View.Gestion_Ventas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.View.Gestion_Productos.VerInventario;
import com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.alertVentaCodigo;
import com.pixels.Inventario.View.Gestion_Ventas.Fragment.VerVentasFragment;

public class VerVentas extends AppCompatActivity {
    public static alertVentaCodigo alertdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ventas);
        VerVentasFragment fragment= new VerVentasFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.opcion_detail_container, fragment)
                .commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null){
                alertdialog.Codigo.setText(""+result.getContents());
            }else{
                alertdialog.CCodigo.setError("Error al escanear el código de barras");
                alertdialog.Codigo.setText("");
                alertdialog.Codigo.setFocusableInTouchMode(true);
                alertdialog.Codigo.requestFocus();
            }
        }
    }
}