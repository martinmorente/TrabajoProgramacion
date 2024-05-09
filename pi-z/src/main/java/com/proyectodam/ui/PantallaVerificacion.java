package com.proyectodam.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class PantallaVerificacion extends JFrame {

    public PantallaVerificacion() {
        super("Verificación");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Usando GridBagLayout
        nuevaPantallaVerificacion();
    }

    public void nuevaPantallaVerificacion() {
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Verificar"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(new JTextArea(), gbc);

        setVisible(true);
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PantallaVerificacion gui = new PantallaVerificacion();
                gui.setVisible(true);
            }
        });
    }

}
 