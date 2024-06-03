package com.proyectodam.Negocio;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.proyectodam.ui.PantallaEscaneo;
import com.proyectodam.ui.PantallaVerificacion;



public class Busqueda {

    /*Funcion pasar un String a un Json */

    public void transformacionEscaneoArchivo(){
     
        String cadena = PantallaEscaneo.resultadoArchivo();
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/com/proyectodam/Negocio/jsons/resultadoEscaneoArchivo.json");
            fileWriter.write(cadena);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void transformacionEscaneoWeb(){
        String cadena = PantallaVerificacion.resultadoWeb();
        try { 
            FileWriter fileWriter = new FileWriter("src/main/java/com/proyectodam/Negocio/jsons/resultadoEscaneoWeb.json");
            fileWriter.write(cadena);
            fileWriter.close();
            System.out.println("Archivo JSON creado correctamente.");
            System.out.println(fileWriter);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }



    /*Funcion busqueda elementos */

    public String[] busquedaElementosArchivos() {
        
        try {
            // Leer el fichero JSON
            FileReader reader = new FileReader("src/main/java/com/proyectodam/Negocio/jsons/resultadoEscaneoArchivo.json");
            JsonElement jsonElement = JsonParser.parseReader(reader);

            // Verificar si el JSON es un objeto
            if (jsonElement.isJsonObject()) {
                JsonObject rootObject = jsonElement.getAsJsonObject();

                // Buscar la clave "scans"
                if (rootObject.has("scans") && rootObject.get("scans").isJsonObject()) {
                    JsonObject scansObject = rootObject.getAsJsonObject("scans");

                    // Iterar sobre los campos del objeto "scans"
                    for (Map.Entry<String, JsonElement> entry : scansObject.entrySet()) {
                        String antivirus = entry.getKey();
                        JsonObject antivirusObject = entry.getValue().getAsJsonObject();

                          // Obtener el campo "scan_date"
                          JsonElement scanDateElement = rootObject.get("scan_date");
                          String scanDate = (scanDateElement != null && !(scanDateElement instanceof JsonNull)) ? scanDateElement.getAsString() : null;                          
                     
                         // Obtener el hash del archivo

                            JsonElement hashElemento = rootObject.get("resource");
                            String hash = (hashElemento != null && ! (hashElemento instanceof JsonNull)) ? hashElemento.getAsString() : null;


                          // Obtener el campo "result"
                        JsonElement resultElement = antivirusObject.get("result");
                        String result = (resultElement != null && !(resultElement instanceof JsonNull)) ? resultElement.getAsString() : null;                       
  
                        
                        return new String[] {antivirus, result, scanDate,hash};                    
                    }
                } else {
                    System.out.println("No se encontró la clave 'scans' o no es un objeto JSON");
                    return null;
                }

            } else {
                System.out.println("El JSON raíz no es un objeto");
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /* Función de retornar resultado escaneo web */
    public String[] busquedaElementosWeb() {
        
        try {
            // Leer el fichero JSON
            FileReader reader = new FileReader("src/main/java/com/proyectodam/Negocio/jsons/resultadoEscaneoWeb.json");
            JsonElement jsonElement = JsonParser.parseReader(reader);

            // Verificar si el JSON es un objeto
            if (jsonElement.isJsonObject()) {
                JsonObject rootObject = jsonElement.getAsJsonObject();

                // Buscar la clave "scans"
                if (rootObject.has("scans") && rootObject.get("scans").isJsonObject()) {
                    JsonObject scansObject = rootObject.getAsJsonObject("scans");

                    // Iterar sobre los campos del objeto "scans"
                    for (Map.Entry<String, JsonElement> entry : scansObject.entrySet()) {
                        String antivirus = entry.getKey();
                        JsonObject antivirusObject = entry.getValue().getAsJsonObject();

                        System.out.println("Nombre: " + antivirus);

                          // Obtener el campo "result"
                          JsonElement resultElement = antivirusObject.get("result");
                          String result = resultElement != null ? resultElement.getAsString() : null;
  
                          // Obtener el campo "scan_date"
                          JsonElement scanDateElement = rootObject.get("scan_date");
                          String scanDate = scanDateElement != null ? scanDateElement.getAsString() : null;
                       
                        
                        return new String[] {antivirus, result, scanDate};                    
                    }
                } else {
                    System.out.println("No se encontró la clave 'scans' o no es un objeto JSON");
                    return null;
                }

            } else {
                System.out.println("El JSON raíz no es un objeto");
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}


/*Realizado por: Martín*/ 