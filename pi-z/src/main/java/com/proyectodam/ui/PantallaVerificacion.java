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
import com.proyectodam.Negocio.EscaneoPaginaWeb;
import com.proyectodam.dao.EscaneoWebDAO;
import com.proyectodam.dao.SitiosWebDAO;

public class PantallaVerificacion extends JFrame {

    private JButton verificar;
    private static TextArea areaTexto;
    private JLabel textoEspera;
    public JButton comboboxDeleteButton;
    public JButton selectButton;

    public PantallaVerificacion() {
        super("Verificación");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        nuevaPantallaVerificacion();
        setLocationRelativeTo(null);
    }

    public void nuevaPantallaVerificacion() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        verificar = new JButton("Verificar");
        areaTexto = new TextArea(2, 30);
        textoEspera = new JLabel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Verificar"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(areaTexto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(verificar, gbc);

        JButton escaneoPantalla = new JButton("Escanear archivo");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(escaneoPantalla, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(textoEspera, gbc);

        JButton comboboxDeleteButton = new JButton("Borrar elementos");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(comboboxDeleteButton,gbc);

        JButton selectButton = new JButton("Mostrar elementos");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(selectButton,gbc);

        verificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = areaTexto.getText();

                if (url.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una URL válida.");
                    return;
                }

                Busqueda ls = new Busqueda();
                ls.transformacionEscaneoWeb();

                boolean hayVirus = verificarResultadosValidos();
                textoEspera.setText("Verificando página web...");

                if (hayVirus == true) {
                    JOptionPane.showMessageDialog(null, "Deje de visitar esa pagina tiene virus");
                    ls.busquedaElementosWeb();
                    int cont = contadorEscaneos();
                    SitiosWebDAO.insertarArchivo();
                    EscaneoWebDAO.insertarEscaneoWeb(cont);
                } else {
                    JOptionPane.showMessageDialog(null, "La pagina esta limpia ");
                    ls.busquedaElementosArchivos();
                    int cont = contadorEscaneos();
                    SitiosWebDAO.insertarArchivo();
                    EscaneoWebDAO.insertarEscaneoWeb(cont);
                }
            }
        });
        setVisible(true);
    
        escaneoPantalla.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                PantallaEscaneo gui = new PantallaEscaneo();
                gui.setVisible(true);
                setVisible(false);
            }
        });

        comboboxDeleteButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                com.proyectodam.ui.InterfazCombobox gui = new InterfazCombobox();
                gui.setVisible(true);
                setVisible(false);
            }
        });
    
        setVisible(true);


        selectButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                InterfazSelect gui = new InterfazSelect();
                gui.setVisible(true);
                setVisible(false);
            }
        });
    
        setVisible(true);
    
    
    }

    public static String resultadoWeb() {
        try {
            String url = areaTexto.getText();
            String scanId = EscaneoPaginaWeb.scanURL(url);
            return EscaneoPaginaWeb.getURLReport(scanId);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String urlWeb() {
        return areaTexto.getText();
    }

    public static int contadorEscaneos() {
        String url = areaTexto.getText();
        String urlBase = SitiosWebDAO.obtenerUrlPorUrlIntro(url);

        int contador = 1;
        int  contadorBase = EscaneoWebDAO.obtenerContadorPorUrl(url);

        if(urlBase == null){
            contadorBase = contador;
            return contadorBase;
        }

        if(urlBase.equals(url)){
           contadorBase++;
           return contadorBase;
        }else{
            contadorBase = contador;
            return contadorBase;
        }
    }

    public boolean verificarResultadosValidos() {
        try (FileReader reader = new FileReader("/run/media/martin/Disco(Datos)/Grado Superior/1º DAM/Programación/3º Trimestre/Proyecto PI-Z/pi-z/src/main/java/com/proyectodam/Negocio/jsons/resultadoEscaneoWeb.json")) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
    
            if (jsonElement.isJsonObject()) {
                JsonObject rootObject = jsonElement.getAsJsonObject();
    
                if (rootObject.has("scans") && rootObject.get("scans").isJsonObject()) {
                    JsonObject scansObject = rootObject.getAsJsonObject("scans");
                    for (Map.Entry<String, JsonElement> entry : scansObject.entrySet()) {
                        JsonObject antivirusObject = entry.getValue().getAsJsonObject();
                        JsonElement resultElement = antivirusObject.get("result");
    
                        // Verifica si el resultado no es "clean site" ni "unrated site"
                        if (resultElement != null && !resultElement.isJsonNull()) {
                            String result = resultElement.getAsString();
                            if ("clean site".equals(result) || "unrated site".equals(result)) {
                                return false; // Retorna true si se encuentra un resultado diferente
                            }
                        }
                    }
                } else {
                    System.out.println("No se encontró la clave 'scans' o no es un objeto JSON");
                }
            } else {
                System.out.println("El JSON raíz no es un objeto");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; // Retorna false si no se encuentra ningún resultado diferente
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PantallaVerificacion().setVisible(true));
    }
}


/*Realizado por: Coral&Martín */
