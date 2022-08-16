package com.pixels.Inventario.View.Activity.Caja.Factura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pixels.Inventario.R;

import java.io.File;
import java.io.FileOutputStream;

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
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(factura);
            fOut.flush();
            fOut.close();
            factura.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(factura));
            intent.setType("factura/pdf");
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al enviar la Factura asegurese de tener los permisos de almacenamiento", Toast.LENGTH_LONG).show();
        }
        if(decision.equals("si")){
            finish();
        }
    }
}