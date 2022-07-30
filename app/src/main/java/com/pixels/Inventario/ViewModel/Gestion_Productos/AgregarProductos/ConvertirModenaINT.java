package com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos;

public class ConvertirModenaINT {
    public int Convertir(String Costop){
        String costo="";
        for (int i=0;i<Costop.length();i++){
            if((""+Costop.charAt(i)).equals("$") || (""+Costop.charAt(i)).equals(" ") || (""+Costop.charAt(i)).equals(",")){

            }else{
                if((""+Costop.charAt(i)).equals(".")){
                    break;
                }
                costo=costo+Costop.charAt(i);
            }
        }
        int costoPC=Integer.parseInt(costo);
        return costoPC;
    }
}
