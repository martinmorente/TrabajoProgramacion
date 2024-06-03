package com.proyectodam.dto;

public class EscaneoWeb {
    private int idEscaneoWeb;
    private String fechaEscaneo;
    private String resultado;
    private int numEscaneoWeb;
    private String nombreAntivirus;


    public EscaneoWeb(){

    }


    public EscaneoWeb(int idEscaneoWeb,String fechaEscaneo,String resultado,int numEscaneoWeb,String nombreAntivirus){
        this.idEscaneoWeb = idEscaneoWeb;
        this.fechaEscaneo=fechaEscaneo;
        this.resultado=resultado;
        this.numEscaneoWeb=numEscaneoWeb;
        this.nombreAntivirus=nombreAntivirus;
    }


    public int getEscaneoWeb() {
        return idEscaneoWeb;
    }


    public void setEscaneoWeb(int idEscaneoWeb) {
        throw new UnsupportedOperationException("No se tiene que insertar el id debido que lo hace ya la base");
    }


    public String getFechaEscaneo() {
        return fechaEscaneo;
    }


    public void setFechaEscaneo(String fechaEscaneo) {
        this.fechaEscaneo = fechaEscaneo;
    }


    public String getResultado() {
        return resultado;
    }


    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


    public String getNombreAntivirus() {
        return nombreAntivirus;
    }


    public void setNombreAntivirus(String nombreAntivirus) {
        this.nombreAntivirus=nombreAntivirus;
    }


    public int getNumEscaneoWeb() {
        return numEscaneoWeb;
    }


    public void setNumEscaneoWeb(int numEscaneoWeb) {
        this.numEscaneoWeb = numEscaneoWeb;
    }



    @Override
    public String toString(){
        return "escaneoWeb{"+"id="+idEscaneoWeb+ " fecha escaneo="+fechaEscaneo+" resultado="+resultado+" numero veces escaneado="+numEscaneoWeb+" nombre del antivirus="+nombreAntivirus+"}";
    }



}
//Ludmila
