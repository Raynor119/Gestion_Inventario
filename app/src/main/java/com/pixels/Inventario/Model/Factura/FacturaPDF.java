package com.pixels.Inventario.Model.Factura;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pixels.Inventario.View.Activity.Caja.Caja;

import java.io.File;
import java.io.FileOutputStream;

public class FacturaPDF {
    private Caja Context;
    private File Factura;
    private String CodigoV;
    private int Efectivo;
    private Document document;
    private PdfWriter FacturaEscribir;
    private Paragraph paragraph;
    private Font Ftitulo,FsubTitulo,Ftext,FhighText;

    public FacturaPDF(Caja context, String codigoV,int efectivo) {
        this.Context=context;
        this.CodigoV=codigoV;
        this.Efectivo=efectivo;
        this.Ftitulo=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
        this.FsubTitulo=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
        this.Ftext=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
        this.FhighText=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD, BaseColor.BLUE);
    }

    public void AbrirFactura(){
        CrearArchivo();
        try{
            document=new Document(PageSize.A4);
            FacturaEscribir=PdfWriter.getInstance(document,new FileOutputStream(Factura));
            document.open();
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar el PDF", Toast.LENGTH_LONG).show();
        }
    }
    public void CerrarFactura(){
        document.close();
    }
    public void addDatos(String titulo,String subject,String author){
        document.addTitle(titulo);
        document.addSubject(subject);
        document.addAuthor(author);
    }
    public void addTitulos(String titulo,String subTitulo,String datos){
        try{
            paragraph=new Paragraph();
            addChildP(new Paragraph(titulo,Ftitulo));
            addChildP(new Paragraph(subTitulo,FsubTitulo));
            addChildP(new Paragraph(datos,Ftext));
            paragraph.setSpacingAfter(5);
            document.add(paragraph);
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar la Factura", Toast.LENGTH_LONG).show();
        }
    }
    public void addParagraph(String text){
        try{
            paragraph=new Paragraph(text,Ftext);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar la Factura", Toast.LENGTH_LONG).show();
        }
    }
    private void addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }
    private void CrearArchivo(){
        File carpeta=new File(Environment.getExternalStorageDirectory().toString(),"Facturas");
        if(!carpeta.exists()){
            carpeta.mkdirs();
        }
        Factura=new File(carpeta,"Factura_Codigo_"+CodigoV);

    }
}
