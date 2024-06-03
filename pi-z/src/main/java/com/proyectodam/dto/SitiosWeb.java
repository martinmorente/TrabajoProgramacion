package com.proyectodam.dto;

public class SitiosWeb {
    private int id;
    private String url;
    

    public SitiosWeb(){}

    public SitiosWeb(int id, String url){
        this.id= id;
        this.url=url;
        
    }

      public int getIdWeb() {
        return id;
    }

    public void setIdWeb(int id) {
        this.id = id;
    }

    public String getUrlWeb(){
        return url;
    }

    public void setUrlWeb(String url){
        this.url=url;
    }

    


    
}

//Realizado por Ludmila
