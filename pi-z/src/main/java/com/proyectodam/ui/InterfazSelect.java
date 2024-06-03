package com.proyectodam.ui;

import javax.swing.*;
import com.proyectodam.Negocio.EscaneoArchivo;
import com.proyectodam.dao.ArchivoDAO;
import com.proyectodam.dao.EscaneoArchivoDAO;
import com.proyectodam.dao.EscaneoWebDAO;
import com.proyectodam.dao.SitiosWebDAO;
import com.proyectodam.ui.InterfazCombobox;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfazSelect extends JFrame {

    public InterfazSelect() {
        // Configurar el título y el comportamiento de cierre del JFrame
        setTitle("Mostrado de elementos");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);


        // Crear un JButton y configurar su acción
        JButton button = new JButton("Mostrar elementos Archivo");
        JButton button2 = new JButton("Mostrar elementos Web");
        JButton pantallaEscaneo = new JButton("Escaneo Archivo");
        JButton pantallaVerificacion = new JButton("Escaneo Pagina Web");
        JButton interfazCombobox = new JButton("Borrar elementos");
        

        // Añadir los componentes al JFrame
        add(button);
        add(button2);
        add(pantallaEscaneo);
        add(pantallaVerificacion);
        add(interfazCombobox);
        

        // Acción para el botón "Seleccionar"
        button.addActionListener(e -> InterfazSelectNueva());

        button2.addActionListener(e -> InterfazSelectNueva2());

        // Acción para el botón "Escaneo Archivo"
        pantallaEscaneo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaEscaneo gui = new PantallaEscaneo();
                gui.setVisible(true);
                setVisible(false);
            }
        });

        pantallaVerificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaVerificacion gui = new PantallaVerificacion();
                gui.setVisible(true);
                setVisible(false);
            }
        });

        interfazCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazCombobox gui = new InterfazCombobox();
                gui.setVisible(true);
                setVisible(false);
            }
        });

        // Hacer visible el JFrame
        setVisible(true);
    }

    public void InterfazSelectNueva() {
        getContentPane().removeAll();
        revalidate();
        repaint();
        JTextField interfaz;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        interfaz = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(interfaz, gbc);

        setTitle("Mostrar elementos de Escaneo_PIZ");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton button1 = new JButton("Mostrar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(button1, gbc);

        JButton button2= new JButton("Regresar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(button2, gbc);

        button1.addActionListener(e -> {
            String url = interfaz.getText(); // Obtener la URL del JTextField
            mostrarPorUrl(url);
            
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazSelect gui = new InterfazSelect();
                gui.setVisible(true);
                setVisible(false);
            }
        });
    

        // Hacer visible el JFrame
        setVisible(true);
    }


    public void InterfazSelectNueva2() {
        getContentPane().removeAll();
        revalidate();
        repaint();
        JTextField interfaz;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        interfaz = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(interfaz, gbc);

        setTitle("Mostrar elementos de Escaneo_PIZ");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton button1 = new JButton("Mostrar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(button1, gbc);

        JButton button2= new JButton("Regresar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(button2, gbc);

        button1.addActionListener(e -> {
            String url = interfaz.getText(); // Obtener la URL del JTextField
            mostrarPorUrlWeb(url);
            
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazSelect gui = new InterfazSelect();
                gui.setVisible(true);
                setVisible(false);
            }
        });
    

        // Hacer visible el JFrame
        setVisible(true);
    }


   public void mostrarPorUrl(String url) {
    // Obtener la información del archivo por la URL
    String resultado = ArchivoDAO.obtenerArchivoPorURL(url);
    if (resultado != null) {
        // Mostrar la información en la interfaz
        JTextArea textArea = new JTextArea(resultado);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        JOptionPane.showMessageDialog(null, scrollPane, "Información del Archivo", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "No se encontró información para la URL especificada");
    }
}

public void mostrarPorUrlWeb(String url) {
    // Obtener la información del archivo por la URL
    String resultado = SitiosWebDAO.obtenerSitiosPorId(url);
    if (resultado != null) {
        // Mostrar la información en la interfaz
        JTextArea textArea = new JTextArea(resultado);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        JOptionPane.showMessageDialog(null, scrollPane, "Información del Archivo", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "No se encontró información para la URL especificada");
    }
}

    public static void main(String[] args) {
        // Ejecutar la aplicación
        SwingUtilities.invokeLater(InterfazSelect::new);
    }
}
