package com.pixels.Inventario.View.Activity.Ajustes;

import android.os.Bundle;
import android.view.View;
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
    public Ajustes Context=Ajustes.this;
    private List<AjustesContent> ajustes= new ArrayList<>();
    public VerificacionContraViewModel verificacion;
    public RecyclerView reci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        View recyclerView = findViewById(R.id.ajustes_list);
        assert recyclerView != null;
        reci=(RecyclerView) recyclerView;
        setupRecyclerView(reci);

    }
    public void reiniciarRecyclerView(){
        reci.setAdapter(null);
        setupRecyclerView(reci);
    }
    public void setupRecyclerView(@NonNull final RecyclerView recyclerView) {
        ajustes=new ArrayList<>();
        verificacion = ViewModelProviders.of(Ajustes.this).get(VerificacionContraViewModel.class);
        verificacion.reset();
        verificacion.VerificarContra(getApplicationContext());
        final Observer<String> observer =new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("si")){
                    ajustes.add(new AjustesContent(1,"Configuracion de la Contraseña","Permite cambiar o eliminar la contraseña que se tiene guardada"));

                }else{
                    ajustes.add(new AjustesContent(6,"Configuracion de la Contraseña","Permite asignar una contraseña a la aplicacion"));
                }
                ajustes.add(new AjustesContent(2,"Configuracion de la Base de Datos","Permite cambiar la base de datos que se esta usando"));
                ajustes.add(new AjustesContent(3,"Importar datos de la Aplicacion","Permite mover los datos de una base de datos local (MYSQL) hacia la Aplicacion"));
                ajustes.add(new AjustesContent(4,"Exportar datos de la Aplicacion","Permite mover los datos de la Aplicacion hacia una base de datos local (MYSQL)"));
                ajustes.add(new AjustesContent(5,"Eliminar datos de la Aplicacion","Elimina los datos almacenados que estan en la Aplicaion"));
                ajustes.add(new AjustesContent(7,"Modificar datos del Usuario","Permite modificar los datos del usuario (Nit o Nombre-Razon Social)"));
                recyclerView.setAdapter(new AjustesRecyclerViewAdapter(Ajustes.this, ajustes));
            }
        };
        verificacion.getResultado().observe(Ajustes.this,observer);

    }
}
