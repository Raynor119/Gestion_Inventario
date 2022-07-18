package com.pixels.Inventario.Model.DatosE;

public class AjustesContent {
    private String Ajuste,Contenido;
    private int Id;
    public AjustesContent(int id,String ajuste,String contenido){
        this.Id=id;
        this.Ajuste=ajuste;
        this.Contenido=contenido;
    }

    public String getAjuste() {
        return Ajuste;
    }

    public void setAjuste(String ajuste) {
        Ajuste = ajuste;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
