package com.pixels.Inventario.Model.DatosE;



public class OpcionesContent {
    private int Id;
    private String Opcion;

    public OpcionesContent(int id,String opcion) {
        this.Id=id;
        this.Opcion = opcion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOpcion() {
        return Opcion;
    }

    public void setOpcion(String opcion) {
        Opcion = opcion;
    }
}
