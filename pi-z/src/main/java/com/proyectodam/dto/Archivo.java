package com.proyectodam.dto;

public class Archivo {
    private int idArchivo;
    private String nombre;
    private String ruta;
    private int tamano;
    private String tipoArchivo;

    public Archivo() {

    }
    
    public Archivo(int idArchivo, String nombre, String ruta, int tamano, String tipoArchivo) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tamano = tamano;
        this.tipoArchivo = tipoArchivo;
    }

    public int getIdArchivo() {
        return this.idArchivo;
    }

    public void setidArchivo(int idArchivo) {
        throw new UnsupportedOperationException("No soportó");
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getRuta(){
        return this.ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getTamano(int tamano){
        return this.tamano;
    }

    public void setTamano(int tamano){
        this.tamano=tamano;
    }
    public String getTipoArchivo(){
        return this.tipoArchivo;
    }
    public void setTipoArchivo(String tipoArchivo){
        this.tipoArchivo=tipoArchivo;
    }

}

//Realizado por Coral