package com.proyectodam.dto;

public class Archivo {
    private int idArchivo;
    private String nombre;
    private String ruta;
    private String hash;

    public Archivo() {

    }
    
    public Archivo(int idArchivo, String nombre, String ruta, String hash) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.hash = hash;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getHash() {
        return hash;
    }
    public void setHash(String hash){
        this.hash = hash;
    }

}

//Realizado por Coral