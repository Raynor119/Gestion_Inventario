package com.pixels.Inventario.Model.DatosE;

public class datosI {
    private String Finalizo,Contra,Contrasena,Basedatos,Ip,Nbasedatos,Usuario,Ucontra,Nit,Nombre,Iva;



    public datosI(String finalizo, String contra, String contrasena, String basedatos, String ip, String nbasedatos, String usuario, String ucontra,String nit,String nombre,String iva){
        this.Finalizo=finalizo;
        this.Contra=contra;
        this.Contrasena=contrasena;
        this.Basedatos=basedatos;
        this.Ip=ip;
        this.Nbasedatos=nbasedatos;
        this.Usuario=usuario;
        this.Ucontra=ucontra;
        this.Nit=nit;
        this.Nombre=nombre;
        this.Iva=iva;
    }

    public String getFinalizo() {
        return Finalizo;
    }

    public void setFinalizo(String finalizo) {
        Finalizo = finalizo;
    }

    public String getContra() {
        return Contra;
    }

    public void setContra(String contra) {
        Contra = contra;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getBasedatos() {
        return Basedatos;
    }

    public void setBasedatos(String basedatos) {
        Basedatos = basedatos;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getNbasedatos() {
        return Nbasedatos;
    }

    public void setNbasedatos(String nbasedatos) {
        Nbasedatos = nbasedatos;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getUcontra() {
        return Ucontra;
    }

    public void setUcontra(String ucontra) {
        Ucontra = ucontra;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String nit) {
        Nit = nit;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getIva() {
        return Iva;
    }

    public void setIva(String iva) {
        Iva = iva;
    }
}
