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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.View.Activity.Caja.Caja;

import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.List;

public class FacturaPDF {
    private Context Context;
    private File Factura;
    private String CodigoV;
    private Document document;
    private PdfWriter FacturaEscribir;
    private Paragraph paragraph;
    private Font Ftitulo,FsubTitulo,Ftext,FhighText;

    public FacturaPDF(Context context, String codigoV) {
        this.Context=context;
        this.CodigoV=codigoV;
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
            Toast.makeText(Context, "Error al Cargar el PDF: "+e, Toast.LENGTH_LONG).show();
        }
    }
    public void CerrarFactura(){
        document.close();
    }
    public void addSubTitulos(String dato){
        try {
            paragraph=new Paragraph();
            addChildP(new Paragraph(dato,Ftitulo));
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(10);
            document.add(paragraph);
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar la Titulos", Toast.LENGTH_LONG).show();
        }
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
            Toast.makeText(Context, "Error al Cargar la Factura Titulos", Toast.LENGTH_LONG).show();
        }
    }
    public void addParagraphR(String text){
        try{
            paragraph=new Paragraph();
            addChildR(new Paragraph(text,Ftext));
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);

            document.add(paragraph);
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar la Factura Texto", Toast.LENGTH_LONG).show();
        }
    }
    public void addParagraph(String text){
        try{
            paragraph=new Paragraph(text,Ftext);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);

            document.add(paragraph);
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar la Factura Texto", Toast.LENGTH_LONG).show();
        }
    }
    public void addTablaP(List<Producto> Productos){
        try {
            String [] header={"CODIGO","NOMBRE","CANTIDAD","VALOR"};
            paragraph=new Paragraph();
            paragraph.setFont(Ftext);
            PdfPTable table=new PdfPTable(header.length);
            table.setWidthPercentage(100);
            PdfPCell cell;
            int index=0;
            while(index<header.length){
                cell=new PdfPCell(new Phrase(header[index++],Ftext));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            for(int i=0;i<Productos.size();i++){
                cell=new PdfPCell(new Phrase(Productos.get(i).getCodigo(),Ftext));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell=new PdfPCell(new Phrase(Productos.get(i).getNombre(),Ftext));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                if(Productos.get(i).getTipoC().equals("peso")){
                    cell=new PdfPCell(new Phrase(Productos.get(i).getCantidad()+"",Ftext));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }else{
                    int cantidad=(int) Productos.get(i).getCantidad();
                    cell=new PdfPCell(new Phrase(cantidad+"",Ftext));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                NumberFormat formato= NumberFormat.getNumberInstance();
                cell=new PdfPCell(new Phrase(formato.format(Productos.get(i).getPrecio()),Ftext));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            paragraph.add(table);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar la Factura Tabla", Toast.LENGTH_LONG).show();
        }
    }
    private void addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }
    private void addChildR(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.add(childParagraph);
    }
    private void CrearArchivo(){

        File carpeta=new File(Environment.getExternalStorageDirectory().toString(),"Facturas");
        if(!carpeta.exists()){
            carpeta.mkdirs();
        }
        Factura=new File(carpeta,"FacturaCodigo"+CodigoV+".pdf");
    }
    public File getFactura(){
        return Factura;
    }
}
