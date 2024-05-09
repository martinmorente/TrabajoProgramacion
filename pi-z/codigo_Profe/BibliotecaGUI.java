import com.proyectodam.biblioteca.negocio.GestorLibros;
import com.proyectodam.biblioteca.dto.Libro;            /* Los imports de com.proyectodam son para coger los metodos de las clases  (las carpetas superiores a esta) */
import com.proyectodam.biblioteca.dto.Autor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;/* Accion por ejemplo añadir */

public class BibliotecaGUI extends JFrame {
    private GestorLibros gestorLibros = new GestorLibros(); /*Objeto Gestor Libros*/ 

    private JTextField txtAutorId, txtAutorNombre, txtLibroId, txtLibroTitulo, txtLibroAno; /*Recuadros de texto en blanco */
    private JButton btnAgregarAutor;/*Boton 1 */
    private JButton btnAgregarLibro;/*Boton 2 */

    public BibliotecaGUI() {
        super("Sistema de Gestión de Biblioteca");/* Titulo del JFrame que esta dentro del swing*/
        setSize(600, 400);/*Tamaños de la pantalla */
        setDefaultCloseOperation(EXIT_ON_CLOSE);/*Exit_ON_Close -> Boton de x de cerrar el programa  */
        setLayout(new GridLayout(0, 2, 10, 10));/* Al utilizar  GridLayout(0, 2, 10, 10) , El primer parámetro  0  indica que el  GridLayout  tendrá un número indefinido de filas y se configura la disposición de los componentes en la ventana de la biblioteca para que se distribuyan en 2 columnas con un espacio de 10 píxeles entre ellos y alrededor de ellos. */
        initUI();/*Inicializar la interfaz */
    }

    private void initUI() {
        // Sección de autores
        add(new JLabel("Autor ID:"));
        txtAutorId = new JTextField();
        add(txtAutorId);

        add(new JLabel("Nombre del Autor:"));
        txtAutorNombre = new JTextField();
        add(txtAutorNombre);

        btnAgregarAutor = new JButton("Agregar Autor");
        btnAgregarAutor.addActionListener(this::agregarAutor);/* */
        add(btnAgregarAutor);

        // Sección de libros
        add(new JLabel("Libro ID:"));
        txtLibroId = new JTextField();
        add(txtLibroId);

        add(new JLabel("Título del Libro:"));
        txtLibroTitulo = new JTextField();
        add(txtLibroTitulo);

        add(new JLabel("Año de Publicación:"));
        txtLibroAno = new JTextField();
        add(txtLibroAno);

        btnAgregarLibro = new JButton("Agregar Libro");
        btnAgregarLibro.addActionListener(this::agregarLibro);
        add(btnAgregarLibro);

        // Sección de Asociaciones
        add(new JLabel("Asociar Autor con Libro:"));
        JButton btnAsociarAutorLibro = new JButton("Asociar");
        btnAsociarAutorLibro.addActionListener(this::asociarAutorConLibro);
        add(btnAsociarAutorLibro);
    }

/**/
    private void agregarAutor(ActionEvent event) {
        String nombre = txtAutorNombre.getText();
        Autor autor = new Autor(0, nombre);
        if (gestorLibros.agregarAutor(autor)) {
            JOptionPane.showMessageDialog(this, "Autor agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar autor.");
        }
    }

    private void agregarLibro(ActionEvent event) {
        String titulo = txtLibroTitulo.getText();
        int ano = Integer.parseInt(txtLibroAno.getText());
        Libro libro = new Libro(0, titulo, ano);
        if (gestorLibros.agregarLibro(libro)) {
            JOptionPane.showMessageDialog(this, "Libro agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar libro.");
        }
    }

    private void asociarAutorConLibro(ActionEvent event) {
        try {
            int autorId = Integer.parseInt(txtAutorId.getText());
            int libroId = Integer.parseInt(txtLibroId.getText());
            boolean resultado = gestorLibros.asociarAutorConLibro(autorId, libroId);
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Autor asociado con libro exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al asociar autor con libro.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, asegúrate de que los IDs de autor y libro sean numéricos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaGUI().setVisible(true));
    }
}
