package com.proyectodam.dto;

public class Escaneo_archivo{
    private int id_escaneo_archivo;
    private String fecha_escaneo;
    private String resultado;
    private int numero_escaneoArchivo;
    private String nombreAntivirus;

    public Escaneo_archivo(){}

    public Escaneo_archivo(int id_escaneo_archivo,String fecha_escaneo, String resultado, int numero_escaneoArchivo, String nombreAntivirus){
        this.id_escaneo_archivo = id_escaneo_archivo;
        this.fecha_escaneo = fecha_escaneo;
        this.resultado = resultado;
        this.numero_escaneoArchivo = numero_escaneoArchivo;
        this.nombreAntivirus = nombreAntivirus;
    }


    public int getId_escaneo_archivo(){
        return id_escaneo_archivo;
    }

    public String getFechaEscaneo(){
        return fecha_escaneo;
    }

    public String getResultado(){
        return resultado;
    }

    public String getNombreAntivirus(){
        return nombreAntivirus;
    }

    public int getNumeroEscaneoArchivo(){
        return numero_escaneoArchivo;
    }
    
    public void setIdEscaneoArchivo(int id_escaneo_archivo) {
        throw new UnsupportedOperationException("No soportó");
    }
    public void setFechaEscaneo(String fecha_escaneo){
        this.fecha_escaneo = fecha_escaneo;
    }

    public void setResultado(String resultado){
        this.resultado = resultado;
    }

    public void setNombreAntivirus(String nombreAntivirus){
        this.nombreAntivirus = nombreAntivirus;
    }

    public void setNumeeroEscaneoArchivo(int numero_escaneoArchivo){
        this.numero_escaneoArchivo = numero_escaneoArchivo;
    }

    
}

//Coral terminado