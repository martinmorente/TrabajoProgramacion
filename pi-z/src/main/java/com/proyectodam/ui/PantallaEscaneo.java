package com.proyectodam.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.proyectodam.Negocio.Busqueda;
import com.proyectodam.Negocio.EscaneoArchivo;
import com.proyectodam.dao.ArchivoDAO;
import com.proyectodam.dao.EscaneoArchivoDAO;

public class PantallaEscaneo extends JFrame {
    
    private static TextArea textArea; // Mueve la declaración del textArea aquí
    private JLabel textoEspera;

    public PantallaEscaneo() {
        super("Escaneo");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        nuevaPantallaEscaneo();
        setLocationRelativeTo(null); /*Centrar la pantalla*/

    }

    public void nuevaPantallaEscaneo() {
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Ingrese la ruta del archivo:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        textArea = new TextArea(2, 30);
        add(textArea, gbc);

        JButton escanear = new JButton("Escanear"); // Declara el botón aquí
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(escanear, gbc);
        
        JButton verificacionPantalla = new JButton("Escaneo Web");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(verificacionPantalla,gbc);

        escanear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textoEspera = new JLabel("Escaneando archivo...");
                gbc.gridx = 2;
                gbc.gridy = 0;
                add(textoEspera, gbc);
                revalidate(); // Es buena práctica llamar a revalidate() después de modificar el diseño de un contenedor
                repaint(); // Y también repaint()

                String url = textArea.getText();

                if (url.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Che boludo, la url (se enfada en argentino)");                    
                }else{

                    Busqueda ls = new Busqueda();

                    ls.transformacionEscaneoArchivo();

                    boolean hayVirus = verificarResultadosValidos();

                    if (hayVirus == true) {
                        JOptionPane.showMessageDialog(null, "Su archivo tiene virus eliminelo inmediatamente :=O");

                        textoEspera.setText("Verificando archivo...");
                        gbc.gridx = 0;
                        gbc.gridy = 2;
                        add(textoEspera, gbc);
                        ls.busquedaElementosArchivos();
                        int cont = contadorEscaneos();
                        ArchivoDAO.insertarArchivo(ls);
                        EscaneoArchivoDAO.insertarEscaneoArchivo(cont);
                    
                    } else {
                        JOptionPane.showMessageDialog(null, "Su archivo esta limpio :D");

                        textoEspera.setText("Verificando archivo...");
                        gbc.gridx = 0;
                        gbc.gridy = 2;
                        add(textoEspera, gbc);
                        ls.busquedaElementosArchivos();
                        int cont = contadorEscaneos();
                        ArchivoDAO.insertarArchivo(ls);
                        EscaneoArchivoDAO.insertarEscaneoArchivo(cont);

                    }

                }
            }
        });

        verificacionPantalla.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                PantallaVerificacion gui = new PantallaVerificacion();
                gui.setVisible(true);
                setVisible(false);
            }
        });

        setVisible(true);
    }

    public static String resultadoArchivo() {
        try {
            String url = textArea.getText();
            String scanId = EscaneoArchivo.scanFile(url);
            String resultado = EscaneoArchivo.getFileReport(scanId);
            return resultado;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String urlArchivo(){
        String url = textArea.getText();

        return url;
    }


    public static int contadorEscaneos() {
        String url = textArea.getText();
        int contador = 1;
        int contadorBase = 0;

        Busqueda ls = new Busqueda();

        String[] detalles = ls.busquedaElementosArchivos();

        /*For*/
        for (String detalle : detalles) {
            String hash = detalles[3];
            String urlArchivo = ""; 
            urlArchivo = ArchivoDAO.obtenerUrlPorHashEscaneo(hash);
            contadorBase =EscaneoArchivoDAO.obtenerContadorPorIdEscaneo(hash);

            if(urlArchivo == null){
                contadorBase = contador;
                return contadorBase;
            }

            if (urlArchivo.equals(url) ) {
                contadorBase++;
                return contadorBase;
            }else{
                contadorBase = contador;
                return contadorBase;
            }
        } 
            return contadorBase;
    }

    public static boolean verificarResultadosValidos() {
        try {
            // Leer el fichero JSON
            FileReader reader = new FileReader("/run/media/martin/Disco(Datos)/Grado Superior/1º DAM/Programación/3º Trimestre/Proyecto PI-Z/pi-z/src/main/java/com/proyectodam/Negocio/jsons/resultadoEscaneoArchivo.json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
    
            // Verificar si el JSON es un objeto
            if (jsonElement.isJsonObject()) {
                JsonObject rootObject = jsonElement.getAsJsonObject();
    
                // Buscar la clave "scans"
                if (rootObject.has("scans") && rootObject.get("scans").isJsonObject()) {
                    JsonObject scansObject = rootObject.getAsJsonObject("scans");
    
                    // Iterar sobre los campos del objeto "scans"
                    boolean hayResultadosValidos = false; // Variable para rastrear si hay al menos un resultado válido
                    for (Map.Entry<String, JsonElement> entry : scansObject.entrySet()) {
                        JsonObject antivirusObject = entry.getValue().getAsJsonObject();
    
                        // Obtener el campo "result"
                        JsonElement resultElement = antivirusObject.get("result");
                        if (resultElement != null && !resultElement.isJsonNull()) {
                            //EscaneoArchivoDAO.actualizarEscaneoArchivo();
                            hayResultadosValidos = true; // Marcar que al menos hay un resultado válido
                        }
                    }
                    return hayResultadosValidos; // Devolver el estado de la variable
                } else {
                    System.out.println("No se encontró la clave 'scans' o no es un objeto JSON");
                    return false;
                }
            } else {
                System.out.println("El JSON raíz no es un objeto");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void InterfazCombobox(){
        
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PantallaEscaneo gui = new PantallaEscaneo();
                gui.setVisible(true);
            }
        });
    }
}
/*Realizado por: Martín */

