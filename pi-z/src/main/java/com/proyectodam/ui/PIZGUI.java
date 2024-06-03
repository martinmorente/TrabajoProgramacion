package com.proyectodam.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PIZGUI extends JFrame {

    private JTextField txtUser, txtPassword;
    private JButton btnLogin;
    private JButton verifyButton;
    private JButton scanButton;
   

    public static Component frame;

    public PIZGUI() {
        super("Inicio Sesion");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Usando GridBagLayout
        inicioSesion();
        setLocationRelativeTo(null);
    }

    private void inicioSesion() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);/* Esto sería lo mismo que utilizar padding */

        gbc.gridx = 0; /* Con esto cambiariamos la posicion x e y del label usuario */
        gbc.gridy = 0;
        add(new JLabel("Usuario"), gbc);

        txtUser = new JTextField(20); // Establece el número de columnas del JTextField. Con esto se cambiaria el tamaño del recuadro
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Contraseña"), gbc);

        txtPassword = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPassword, gbc);

        btnLogin = new JButton("Acceder");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnLogin, gbc); /* Añadir el gbc para que se establezca los tamaños especificados */

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantallaAferLoguin();
                
                
            }

        });
    }

    /* */
    public  void pantallaAferLoguin() {
        getContentPane().removeAll(); // Elimina los componentes anteriores
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        verifyButton = new JButton("Verificar Pagina");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(verifyButton, gbc);

        scanButton = new JButton("Escanear Archivos");
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(scanButton, gbc);

    /*  scanButton = new JButton("Delete PaginasWeb");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(scanButton, gbc);

        scanButton = new JButton("Delete Archivos");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(scanButton, gbc);
*/
        // Vuelve a validar los componentes
        revalidate();
        /*Que lo repinte */
        repaint();

        /*Accion Escaneo */

        scanButton.addActionListener(e -> abrirPantallaEscaneo());
        
        /*Accion verificarPagina */
        
        verifyButton.addActionListener(e -> abrirPantallaVerificacion());
    }

    private void abrirPantallaVerificacion(){
        new PantallaVerificacion();
    }

    private void abrirPantallaEscaneo(){
        new PantallaEscaneo();
    }

    private void abrirInterfazCombobox(){
        new InterfazCombobox();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PIZGUI gui = new PIZGUI();
                gui.setVisible(true);
            }
        });
    }

}
/*Realizado por: Coral&Martín */