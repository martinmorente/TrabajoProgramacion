package com.proyectodam.ui;

import javax.swing.*;
import com.proyectodam.Negocio.EscaneoArchivo;
import com.proyectodam.dao.ArchivoDAO;
import com.proyectodam.dao.EscaneoArchivoDAO;
import com.proyectodam.dao.EscaneoWebDAO;
import com.proyectodam.dao.SitiosWebDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfazCombobox extends JFrame {
    public JComboBox<String> comboBox;

    public InterfazCombobox() {
        // Configurar el título y el comportamiento de cierre del JFrame
        GridBagConstraints gbc = new GridBagConstraints();

        setTitle("Borrado Escaneo");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        // Crear un array de opciones para el JComboBox
        String[] options = {"Archivo", "Sitios_web"};
        comboBox = new JComboBox<>(options);
        comboBox.setSelectedIndex(0);  // Seleccionar la primera opción por defecto

        // Crear un JButton y configurar su acción
        JButton button = new JButton("Seleccionar");
        JButton pantallaEscaneo = new JButton("Escaneo Archivo");
        JButton pantallaVerificacion = new JButton("Escaneo Pagina Web");
        JButton button2 = new JButton("Mostrar elementos");

        // Añadir los componentes al JFrame
        add(comboBox);
        add(button);
        add(pantallaEscaneo);
        add(pantallaVerificacion);
        add(button2);

        // Acción para el botón "Seleccionar"
        button.addActionListener(e -> InterfazComboboxNueva());

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

    public void InterfazComboboxNueva() {
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

        setTitle("JComboBox Escaneo_PIZ");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton button1 = new JButton("Borrar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(button1, gbc);
        


        button1.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            String url = interfaz.getText(); // Obtener la URL del JTextField
            borrarPorUrl(selectedOption, url);
        });

        

        // Hacer visible el JFrame
        setVisible(true);
    }

    public void borrarPorUrl(String selectedOption, String url) {
        boolean borrado = false;
        if ("Archivo".equals(selectedOption)) {
            List<Integer> ids = ArchivoDAO.obtenerIdsPorUrl(url);
            for (int id : ids) {
                EscaneoArchivoDAO.eliminarEscaneoArchivo(id);
                ArchivoDAO.eliminarArchivo(id);
                borrado = true;
            }
            if (borrado) {
                JOptionPane.showMessageDialog(null, "Se borró correctamente el archivo");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo con la URL especificada");
            }
        } else if ("Sitios_web".equals(selectedOption)) {
            List<Integer> ids = SitiosWebDAO.obtenerIdsPorUrl(url);
            for (int id : ids) {
                EscaneoWebDAO.eliminarEscaneoWeb(id);
                SitiosWebDAO.eliminarSitioWeb(id);
                borrado = true;
            }
            if (borrado) {
                JOptionPane.showMessageDialog(null, "Se borró correctamente el sitio web");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el sitio web con la URL especificada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al borrar");
        }
    }

    public static void main(String[] args) {
        // Ejecutar la aplicación
        SwingUtilities.invokeLater(InterfazCombobox::new);
    }
}
