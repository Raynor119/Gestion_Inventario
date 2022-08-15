package com.pixels.Inventario.View.Activity.Caja.Factura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.pixels.Inventario.R;

import java.io.File;

public class VerFactura extends AppCompatActivity {

    private PDFView pdfView;
    private File factura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_factura);
        Bundle bundle=getIntent().getExtras();
        pdfView=(PDFView) findViewById(R.id.VerFactura);
        if(bundle!=null){
            factura=new File(bundle.getString("path",""));
        }
        pdfView.fromFile(factura).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).enableAntialiasing(true).load();
    }
    public void Compartir(String decicion){

    }
}