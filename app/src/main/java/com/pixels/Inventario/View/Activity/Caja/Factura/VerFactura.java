package com.pixels.Inventario.View.Activity.Caja.Factura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pixels.Inventario.R;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class VerFactura extends AppCompatActivity {

    private PDFView pdfView;
    private File factura;
    private FloatingActionButton fab;
    private String decision="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_factura);
        Bundle bundle=getIntent().getExtras();
        pdfView=(PDFView) findViewById(R.id.VerFactura);
        setTitle("Ver Factura");
        if(bundle!=null){
            factura=new File(bundle.getString("path",""));
            decision=bundle.getString("decision","no");
            if(decision.equals("si")){
                setTitle("Compartir Factura");
                Compartir();
            }
        }
        fab= findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Compartir();
            }
        });
        pdfView.fromFile(factura).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).enableAntialiasing(true).load();
    }
    public void Compartir(){
        try {
            Context Context=VerFactura.this;
            File documentsPath = new File(Context.getFilesDir(), "documents");
            if(!documentsPath.exists()){
                documentsPath.mkdirs();
            }
            File file = factura;
            Path origenPath = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                file = new File(documentsPath, factura.getName());
                origenPath = FileSystems.getDefault().getPath(factura.getAbsolutePath());
                Path destinoPath = FileSystems.getDefault().getPath(file.getAbsolutePath());
                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            }
            Uri uri = FileProvider.getUriForFile(VerFactura.this, "com.pixels.Inventario.fileprovider", file);
            Intent intent = ShareCompat.IntentBuilder.from(VerFactura.this)
                    .setType("application/pdf")
                    .setStream(uri)
                    .setChooserTitle("Compartir "+factura.getName())
                    .createChooserIntent()
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Context.startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al Compartir el Pdf Verifique los Permisos de Almacenamiento de la APP "+e, Toast.LENGTH_LONG).show();
        }
        if(decision.equals("si")){
            finish();
        }
    }
}