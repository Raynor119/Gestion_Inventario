package com.pixels.Inventario.Model.Factura;



import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

public class FacturaPDF {
    private Context Context;
    private File Factura;
    private String CodigoV,FechaV,BaseDatos;
    private Document document;
    private PdfWriter FacturaEscribir;
    private Paragraph paragraph;
    private Font Ftitulo,FsubTitulo,Ftext,FhighText;

    public FacturaPDF(Context context, String codigoV,String fechaV,String basedatos) {
        this.Context=context;
        this.CodigoV=codigoV;
        this.FechaV=fechaV;
        this.BaseDatos=basedatos;
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
                    cell=new PdfPCell(new Phrase(Productos.get(i).getCantidad()+"Kg",Ftext));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }else{
                    if(Productos.get(i).getTipoC().equals("unitario")){
                        int cantidad=(int) Productos.get(i).getCantidad();
                        cell=new PdfPCell(new Phrase(cantidad+"",Ftext));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }else{
                        cell=new PdfPCell(new Phrase(Productos.get(i).getCantidad()+"",Ftext));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }

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
    public void addTablaPIVA(List<Producto> Productos){
        try {
            String [] header={"CODIGO","BASE","IVA","TOTAL"};
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
            int totalbase=0,iva=0;
            NumberFormat formato= NumberFormat.getNumberInstance();
            int Total=0;
            for(int i=0;i<Productos.size();i++){
                Total=(int)(Total+(Productos.get(i).getPrecio()*Productos.get(i).getCantidad()));
                cell=new PdfPCell(new Phrase(Productos.get(i).getCodigo(),Ftext));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                double porcentajeiva=1.0+(Productos.get(i).getIva()*0.01);
                int total=(int) (Productos.get(i).getPrecio()*Productos.get(i).getCantidad());
                BigDecimal precionS = new BigDecimal((Productos.get(i).getPrecio()*Productos.get(i).getCantidad())/porcentajeiva).setScale(0, RoundingMode.HALF_UP);
                int base=(int)(precionS.doubleValue());
                totalbase=totalbase+base;
                double poriva=(Productos.get(i).getIva()*0.01);
                BigDecimal precion = new BigDecimal(base*poriva).setScale(0, RoundingMode.HALF_UP);
                double ivaa=precion.doubleValue();

                iva=iva+((int) ivaa);


                cell=new PdfPCell(new Phrase(formato.format(base),Ftext));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);


                cell=new PdfPCell(new Phrase(formato.format(ivaa),Ftext));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase(formato.format(total),Ftext));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            cell=new PdfPCell(new Phrase("TOTAL:",Ftext));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell=new PdfPCell(new Phrase(formato.format(totalbase),Ftext));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell=new PdfPCell(new Phrase(formato.format(iva),Ftext));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell=new PdfPCell(new Phrase(formato.format(Total),Ftext));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            paragraph.add(table);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar la Factura Tabla", Toast.LENGTH_LONG).show();
        }
    }
    public void addCodigoBarras(){
        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
        try {
            Bitmap bitmap=barcodeEncoder.encodeBitmap(CodigoV, BarcodeFormat.CODE_128,550,80);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            Image imagen = Image.getInstance(stream.toByteArray());
            imagen.scaleToFit(550, 80);
            imagen.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph1=new Paragraph();
            paragraph1.add(imagen);
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            paragraph=new Paragraph();
            paragraph.add(paragraph1);
            paragraph.setSpacingAfter(20);
            paragraph.setSpacingBefore(20);
            document.add(paragraph);
        }catch (Exception e){
            Toast.makeText(Context, "Error al Cargar la Factura Codgio de Barras", Toast.LENGTH_LONG).show();
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
        //se genera el diretorio en android/data/ si tiene android 8 o mas
        File carpeta;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            carpeta=new File(Context.getExternalFilesDir(null),"Facturas"+Context.getString(R.string.app_name));
        }else{
            carpeta=new File(Context.getFilesDir(),"documents");
        }
        if(!carpeta.exists()){
            carpeta.mkdirs();
        }
        File subcarpeta=new File(carpeta,BaseDatos);
        if(!subcarpeta.exists()){
            subcarpeta.mkdirs();
        }
        String Fecha="";
        for (int i=0;i<FechaV.length();i++){
            if((""+FechaV.charAt(i)).equals(" ")){
                break;
            }else{
                Fecha=Fecha+(""+FechaV.charAt(i));
            }
        }
        File carpetaFecha=new File(subcarpeta,Fecha);
        if(!carpetaFecha.exists()){
            carpetaFecha.mkdirs();
        }
        Factura=new File(carpetaFecha,"FacturaCodigo"+CodigoV+".pdf");

    }
    public File getFactura(){
        return Factura;
    }
}
