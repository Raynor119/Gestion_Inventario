package com.pixels.Inventario.Model.DatosE;

public class datosI {
    private String Finalizo,Contra,Contrasena,Basedatos,Ip,Nbasedatos,Usuario,Ucontra;



    public datosI(String finalizo, String contra, String contrasena, String basedatos, String ip, String nbasedatos, String usuario, String ucontra){
        this.Finalizo=finalizo;
        this.Contra=contra;
        this.Contrasena=contrasena;
        this.Basedatos=basedatos;
        this.Ip=ip;
        this.Nbasedatos=nbasedatos;
        this.Usuario=usuario;
        this.Ucontra=ucontra;
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
}
