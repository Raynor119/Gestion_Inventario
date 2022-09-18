package com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;

import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.pixels.Inventario.R;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;

public class DetallesVentas extends AppCompatActivity {

    private String Fecha,Efectivo,Codigo;
    private TextView TCodigo,TFecha,TEfectivo,TCambio,TSubTotal,TIva,TTotalV,TTextMas;
    private LinearLayout LDetalles;
    private CardView Masdetalles;
    private ImageView IcodigoBarras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ventas);
        Fecha=getIntent().getExtras().getString("fecha");
        Efectivo=getIntent().getExtras().getString("efectivo");
        Codigo=getIntent().getExtras().getString("codigo");
        TCodigo=(TextView) findViewById(R.id.CodigoV);
        TFecha=(TextView) findViewById(R.id.fecha);
        TEfectivo=(TextView) findViewById(R.id.Efectivo);
        IcodigoBarras=(ImageView) findViewById(R.id.codigoBarras);
        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
        try {
            Bitmap bitmap=barcodeEncoder.encodeBitmap(Codigo, BarcodeFormat.CODE_128,580,80);
            IcodigoBarras.setImageBitmap(bitmap);
        } catch (Exception e) {

        }
        TCodigo.setText(""+Codigo);
        TFecha.setText(""+Fecha);
        NumberFormat formato= NumberFormat.getNumberInstance();
        TEfectivo.setText("$ "+formato.format(Integer.parseInt(Efectivo)));
        TCambio=(TextView) findViewById(R.id.cambioT);
        TSubTotal=(TextView) findViewById(R.id.SubTotal);
        TIva=(TextView) findViewById(R.id.Iva);
        TTotalV=(TextView) findViewById(R.id.TotalV);
        LDetalles=(LinearLayout) findViewById(R.id.LDetalles);
        LDetalles.setVisibility(ConstraintLayout.GONE);
        Masdetalles=(CardView) findViewById(R.id.masdetalles);
        TTextMas=(TextView) findViewById(R.id.TextMas);
        Masdetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LDetalles.getVisibility()==ConstraintLayout.GONE){
                    LDetalles.setVisibility(ConstraintLayout.VISIBLE);
                    TTextMas.setText("Ocultar detalles");
                }else{
                    LDetalles.setVisibility(ConstraintLayout.GONE);
                    TTextMas.setText("Mas detalles");
                }
            }
        });
    }
    public void iniciarReciclerView(){

    }
}