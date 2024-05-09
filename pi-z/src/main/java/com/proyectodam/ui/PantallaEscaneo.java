package com.proyectodam.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class PantallaEscaneo extends JFrame {

    private JTextArea textArea;
    private JButton escanear;

    public PantallaEscaneo() {
        super("Escaneo");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); 
        nuevaPantallaEscaneo();
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
        textArea = new JTextArea();
        add(textArea, gbc);


        escanear = new JButton("Escanear");
        gbc.gridx=0;
        gbc.gridy=1;
        add(escanear,gbc);

        escanear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                getTextarea();
            }
        });



        setVisible(true);

   
        
    }

    public String getTextarea(){
        return textArea.getText();
        
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PantallaEscaneo gui = new PantallaEscaneo();
                gui.setVisible(true);
            
            
                String url = gui.getTextarea();
                System.out.println(url);
            }
        });
    }

}
