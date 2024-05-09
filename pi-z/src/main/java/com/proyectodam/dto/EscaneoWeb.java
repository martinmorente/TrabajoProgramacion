package com.proyectodam.dto;

public class EscaneoWeb {
    private int idEscaneoWeb;
    private String fechaEscaneo;
    private String resultado;
    private String tipoMaldad;
    private int numEscaneoWeb;
    private String urlFinal;


    public EscaneoWeb(){

    }


    public EscaneoWeb(int idEscaneoWeb,String fechaEscaneo,String resultado,String tipoMaldad,int numEscaneoWeb,String urlFinal){
        this.idEscaneoWeb = idEscaneoWeb;
        this.fechaEscaneo=fechaEscaneo;
        this.resultado=resultado;
        this.tipoMaldad=tipoMaldad;
        this.numEscaneoWeb=numEscaneoWeb;
        this.urlFinal=urlFinal;
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


    public String getTipoMaldad() {
        return tipoMaldad;
    }


    public void setTipoMaldad(String tipoMaldad) {
        this.tipoMaldad=tipoMaldad;
    }


    public int getNumEscaneoWeb() {
        return numEscaneoWeb;
    }


    public void setNumEscaneoWeb(int numEscaneoWeb) {
        this.numEscaneoWeb = numEscaneoWeb;
    }


    public String getUrlFinal() {
        return urlFinal;
    }


    public void setUrlFinal(String urlFinal) {
        this.urlFinal = urlFinal;
    }

    @Override
    public String toString(){
        return "escaneoWeb{"+"id="+idEscaneoWeb+ " fecha escaneo="+fechaEscaneo+" resultado="+resultado+" tipo maldad="+tipoMaldad+" numero veces escaneado="+numEscaneoWeb+" url final"+urlFinal+"}";
    }



}
