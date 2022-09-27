package com.pixels.Inventario.View.Menu_Inicio;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixels.Inventario.Model.DatosE.OpcionesContent;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Productos.VerInventario;
import com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.VerificarCodigo.VerificarCodigoFV;
import com.pixels.Inventario.View.Gestion_Ventas.AlertDialog.alertVentaCodigo;
import com.pixels.Inventario.View.InicioA.Configuracion_Inicial.Fragment.InicioBlanco;
import com.pixels.Inventario.View.Menu_Inicio.RecyclerViewAdaptador.SimpleItemRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * An activity representing a list of opciones. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link VerInventario} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MenuInicio extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private List<OpcionesContent> menuopciones= new ArrayList<>();
    public static alertVentaCodigo alertdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuinicio);
        if (findViewById(R.id.opcion_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        View recyclerView = findViewById(R.id.opcion_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        menuopciones.add(new OpcionesContent(1,"Caja"));
        menuopciones.add(new OpcionesContent(2,"Ver Inventario de Productos"));
        menuopciones.add(new OpcionesContent(3,"Ventas Realizadas"));
        menuopciones.add(new OpcionesContent(4,"Ajustes"));
        menuopciones.add(new OpcionesContent(5,"Salir"));
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, menuopciones, mTwoPane));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null){
                alertdialog.Codigo.setText(""+result.getContents());
                VerificarCodigoFV codigo=new VerificarCodigoFV(alertdialog);
                codigo.verificarcodigo(false);
            }else{
                alertdialog.CCodigo.setError("Error al escanear el código de barras");
                alertdialog.Codigo.setText("");
                alertdialog.Codigo.setFocusableInTouchMode(true);
                alertdialog.Codigo.requestFocus();
            }
        }
    }
}
