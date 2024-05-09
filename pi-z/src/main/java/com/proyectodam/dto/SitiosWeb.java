package com.proyectodam.dto;

public class SitiosWeb {
    private int id_web;
    private String url_web;
    private String tipo_web;
    private String descripcion_web;

    public SitiosWeb(){}

    public SitiosWeb(int id_web, String url_web, String tipo_web, String descripcion_web){
        this.id_web= id_web;
        this.url_web=url_web;
        this.tipo_web=tipo_web;
        this.descripcion_web=descripcion_web;
    }

      public int getIdWeb() {
        return id_web;
    }

    public void setIdWeb(int id_web) {
        this.id_web = id_web;
    }

    public String getUrlWeb(){
        return url_web;
    }

    public void setUrlWeb(String url_web){
        this.url_web=url_web;
    }

     public String getTipoWeb(){
        return tipo_web;
    }

    public void setTipoWeb(String tipo_web){
        this.tipo_web=tipo_web;
    }

     public String getDescripcionWeb(){
        return descripcion_web;
    }

    public void setDescripcionweb(String descripcion_web){
        this.descripcion_web=descripcion_web;
    }


    
}

//Realizado por Ludmila
