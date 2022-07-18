package com.pixels.Inventario.View.Activity.Ajustes;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.pixels.Inventario.Model.DatosE.AjustesContent;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Ajustes.RecyclerViewAdapter.AjustesRecyclerViewAdapter;
import com.pixels.Inventario.ViewModel.Ajustes.VerificacionContraViewModel;

import java.util.ArrayList;
import java.util.List;

public class Ajustes extends AppCompatActivity {
    private List<AjustesContent> ajustes= new ArrayList<>();
    public VerificacionContraViewModel verificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        View recyclerView = findViewById(R.id.ajustes_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }
    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        verificacion = ViewModelProviders.of(Ajustes.this).get(VerificacionContraViewModel.class);
        verificacion.VerificarContra(getApplicationContext());
        final Observer<String> observer =new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                if(s.equals("si")){
                    ajustes.add(new AjustesContent(1,"Configuracion de la Contraseña","Permite cambiar o eliminar la contraseña que se tiene guardada"));

                }else{
                    ajustes.add(new AjustesContent(6,"Configuracion de la Contraseña","Permite asignar una contraseña a la aplicacion"));
                }
                ajustes.add(new AjustesContent(2,"Configuracion de la Base de Datos","Permite cambiar la base de datos que se esta usando"));
                ajustes.add(new AjustesContent(3,"Importar datos de la Aplicacion","Permite mover los datos de una base de datos local (MYSQL) hacia la Aplicacion"));
                ajustes.add(new AjustesContent(4,"Exportar datos de la Aplicacion","Permite mover los datos de la Aplicacion hacia una base de datos local (MYSQL)"));
                ajustes.add(new AjustesContent(5,"Eliminar datos de la Aplicacion","Elimina los datos almacenados que estan en la Aplicaion"));
                recyclerView.setAdapter(new AjustesRecyclerViewAdapter(Ajustes.this, ajustes));
            }
        };
        verificacion.getResultado().observe(Ajustes.this,observer);

    }
}
