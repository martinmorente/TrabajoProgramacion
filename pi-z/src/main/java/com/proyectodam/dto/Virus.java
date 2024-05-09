package com.proyectodam.dto;

public class Virus {
    private int id_virus_web;
    private String nombre_virus;
    private String descripcion_virus;

    public Virus(){}

    public Virus(int id_virus_web,String nombre_virus, String descripcion_virus){
        this.id_virus_web=id_virus_web;
        this.nombre_virus=nombre_virus;
        this.descripcion_virus=descripcion_virus;
    }
    
     public int getIdVirusWeb() {
        return id_virus_web;
    }

    public void setIdVirusWeb(int id_virus_web) {
        this.id_virus_web = id_virus_web;
    }

     public String getNombreVirus() {
        return nombre_virus;
    }

    public void setNombreVirus(String nombre_virus) {
        this.nombre_virus = nombre_virus;
    }

     public String getDescripcionVirus() {
        return descripcion_virus;
    }

    public void setIdWeb(String descripcion_virus) {
        this.descripcion_virus = descripcion_virus;
    }
}
//Realizado por Ludmila