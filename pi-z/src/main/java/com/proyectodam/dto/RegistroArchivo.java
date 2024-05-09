package com.proyectodam.dto;

public class RegistroArchivo {
    private int idRegistroArchivo;
    private String tipoAntivirusEscaneado;
    private String descripcionAntivirus;

    public RegistroArchivo() {

    }

    public RegistroArchivo(int idRegistroArchivo, String tipoAntivirusEscaneado, String descripcionAntivirus) {
        this.idRegistroArchivo = idRegistroArchivo;
        this.tipoAntivirusEscaneado=tipoAntivirusEscaneado;
        this.descripcionAntivirus=descripcionAntivirus;
    }

    public int getIdRegistroArchivo() {
        return this.idRegistroArchivo;
    }

    public void setidRegistroArchivo(int idRegistroArchivo) {
        throw new UnsupportedOperationException("No soportó");
    }

    public String getTipoAntivirusEscaneado() {
        return this.tipoAntivirusEscaneado;
    }

    public void setTipoAntivirusEscaneado(String tipoAntivirusEscaneado){
        this.tipoAntivirusEscaneado=tipoAntivirusEscaneado;
    }

    public String getDescripcionAntivirus(){
        return this.descripcionAntivirus;
    }
    public void setDescripcionAntivirus(String descripcionAntivirus) {
        this.descripcionAntivirus = descripcionAntivirus;
    }

}

//Realizado por Coral