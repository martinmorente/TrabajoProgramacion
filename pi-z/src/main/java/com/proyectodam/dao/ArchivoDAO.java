package com.proyectodam.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.proyectodam.Negocio.Busqueda;
import com.proyectodam.ui.PantallaEscaneo;

public class ArchivoDAO {

    public static boolean insertarArchivo(Busqueda busqueda  ) {
        String sql = "INSERT INTO Archivo (ruta,hash) VALUES (?,?)";

        Busqueda busquedas = new Busqueda();
        String[] detalles = busquedas.busquedaElementosArchivos();

        String ruta = "";
        String hash = "";

        if (detalles == null) {
            System.out.println("No se encontró nada");
        } else {
            for (String detalle : detalles) {
                ruta = PantallaEscaneo.urlArchivo();
                hash = detalles[3];
            }
        }
    
    try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ruta);
            pstmt.setString(2, hash);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer un Escaneo_archivo por ID
    public static String obtenerArchivoPorURL(String url) {
        String sql = "SELECT * FROM Archivo WHERE ruta = ?";
        try (Connection conn = Conexion.getConnection(); /* Dentro del try meto lo que voy a ejecutar */
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, url);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String ruta = rs.getString("ruta");
                String resultado = id +" "+ ruta;
                return resultado;
            }
        } catch (SQLException e) {/* Manejo de errores (?) */
            e.printStackTrace();
        }
        return null;
    }


    public static String obtenerUrlPorHashEscaneo(String hashUrl) {
        String sql = "SELECT ruta FROM Archivo WHERE hash = ? ORDER BY ruta DESC";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Establece el parámetro del hash
            pstmt.setString(1, hashUrl);
            
            // Ejecuta la consulta y obtiene el resultado
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retorna la ruta si se encuentra
                    return rs.getString("ruta");
                }
            }
        } catch (SQLException e) {
            // Manejo de excepción para SQLException
            e.printStackTrace();
        }
        // Retorna null si no se encuentra la URL asociada al hash
        return null;
    }



    /*Encontrar el id por el hash del archivo*/

    public static Integer obtenerIdPorHash(String hash) {
    String sql = "SELECT id FROM Archivo WHERE hash = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, hash);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id"); // Supongo que la columna del ID se llama "id"
            } else {
                return null; // Retornar null si no se encuentra un registro
            }
        }
    } catch (Exception e) {
        e.printStackTrace(); // Imprimir el stack trace para ayudar en la depuración
        return null; // Retornar null en caso de una excepción
    }
    }

    //Eliminar por url
     public static List<Integer> obtenerIdsPorUrl(String url) {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM Archivo WHERE ruta = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, url);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    // Eliminar Archivo (Si)
    public static boolean eliminarArchivo(int id) {
        String sql = "DELETE FROM Archivo WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
//Coral&Ludmila terminado