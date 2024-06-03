package com.proyectodam.Negocio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class EscaneoPaginaWeb {

    private static final String API_KEY = "e599dea00bc7e13fd1a29ec8af2dc2a6b39e0729dd600c13ac178687c1146e4f";


 
    public static String scanURL(String urlToScan) throws IOException {
        String url = "https://www.virustotal.com/vtapi/v2/url/scan";
        String charset = "UTF-8";

        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

        try (
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), charset)
        ) {
            String query = String.format("apikey=%s&url=%s",
                URLEncoder.encode(API_KEY, charset),
                URLEncoder.encode(urlToScan, charset)
            );
            writer.write(query);
        }

        InputStream response = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response));

        StringBuilder responseData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseData.append(line);
        }

        // Parsear la respuesta para obtener el scan_id
        String[] parts = responseData.toString().split("\"");
        String scanId = parts[7]; // Asegúrate de que el índice es correcto según la respuesta de la API
        
        return scanId;
    }

    public static String getURLReport(String scanId) throws IOException {
        String url = "https://www.virustotal.com/vtapi/v2/url/report";

        try {
            URLConnection connection = new URL(url + "?apikey=" + API_KEY + "&resource=" + scanId).openConnection();
            InputStream response = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));

            StringBuilder responseData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseData.append(line);
            }

            return responseData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al obtener el reporte.";
        }
    }
    
}
/*Realizado por: Martín*/ 