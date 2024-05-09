package com.proyectodam.dto;

public class Escaneo_archivo{
    private int id_escaneo_archivo;
    private String fecha_escaneo;
    private String resultado;
    private String nivel_amenaza;
    private int numero_escaneoArchivo;

    public Escaneo_archivo(){}

    public Escaneo_archivo(int id_escaneo_archivo,String fecha_escaneo, String resultado, String nivel_amenaza, int numero_escaneoArchivo){
        this.id_escaneo_archivo = id_escaneo_archivo;
        this.fecha_escaneo = fecha_escaneo;
        this.resultado = resultado;
        this.nivel_amenaza = nivel_amenaza;
        this.numero_escaneoArchivo = numero_escaneoArchivo;
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

    public String getNivelAmenaza(){
        return nivel_amenaza;
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

    public void setNivelAmenaza(String nivel_amenaza){
        this.nivel_amenaza = nivel_amenaza;
    }

    public void setNumeeroEscaneoArchivo(int numero_escaneoArchivo){
        this.numero_escaneoArchivo = numero_escaneoArchivo;
    }

    
}