package com.proyectodam.Negocio;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.*;
import com.proyectodam.ui.PantallaEscaneo; 

public class VirusTotalScanner {
    private static final String API_KEY = "e599dea00bc7e13fd1a29ec8af2dc2a6b39e0729dd600c13ac178687c1146e4f"; // Inserta tu clave de API de VirusTotal aquí

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PantallaEscaneo escaneo = new PantallaEscaneo();

        String filePath = escaneo.getTextarea();
        System.out.println(filePath);
        try {
            System.out.println("Escaneando el archivo " + filePath + "...");
            String scanId = scanFile(filePath);
            System.out.println("El archivo ha sido enviado para su escaneo.");

            // Esperar un tiempo para que VirusTotal procese el archivo (puede variar dependiendo del tamaño del archivo)
            Thread.sleep(15000);

            System.out.println("Obteniendo el reporte del escaneo...");
            String resultado = getFileReport(scanId);
            System.out.println("Resultado del escaneo:\n" + resultado);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String scanFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("El archivo no existe.");
        }

        String url = "https://www.virustotal.com/vtapi/v2/file/scan";
        String charset = "UTF-8";

        String boundary = Long.toHexString(System.currentTimeMillis());
        String CRLF = "\r\n";
        String charsetPrefix = "--";

        URLConnection connection = new URL(url + "?apikey=" + API_KEY).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (
            OutputStream output = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true)
        ) {
            writer.append(charsetPrefix).append(boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append(CRLF);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(CRLF);
            writer.append(CRLF).flush();

            Files.copy(file.toPath(), output);
            output.flush();

            writer.append(CRLF).flush();
            writer.append(charsetPrefix).append(boundary).append(charsetPrefix).append(CRLF).flush();
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
        String scanId = parts[3];
        
        return scanId;
    }

    private static String getFileReport(String scanId) throws IOException {
        String url = "https://www.virustotal.com/vtapi/v2/file/report";
        String charset = "UTF-8";

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