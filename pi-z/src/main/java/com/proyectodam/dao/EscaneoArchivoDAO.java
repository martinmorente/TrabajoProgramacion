package com.proyectodam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.proyectodam.dto.Escaneo_archivo;

public class EscaneoArchivoDAO {

    public boolean insertarArchivo(Escaneo_archivo Escaneo_archivo) {
        String sql = "INSERT INTO Archivo (fecha_escaneo,resultado,nivel_amenaza,numero_escaneoArchivo) VALUES (4)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Escaneo_archivo.getFechaEscaneo());
            pstmt.setString(2, Escaneo_archivo.getResultado());
            pstmt.setInt(3, Escaneo_archivo.getNivelAmenaza());
            pstmt.setString(4, Escaneo_archivo.getNumeroEscaneoArchivo());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer un Escaneo_archivo por ID
    public Autor obtenerAutorPorId(int id) {
        String sql = "SELECT * FROM autores WHERE autor_id = ?";/* */
        try (Connection conn = Conexion.getConnection(); /* Dentro del try meto lo que voy a ejecutar */
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Autor(rs.getInt("autor_id"), rs.getString("nombre"));
            }
        } catch (SQLException e) {/* Manejo de errores (?) */
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar un autor
    public boolean actualizarAutor(Autor autor) {
        String sql = "UPDATE autores SET nombre = ? WHERE autor_id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor.getNombre());
            pstmt.setInt(2, autor.getId());/* Aquí 2 porque corresponde al segundo signo de interrogación */
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un autor
    public boolean eliminarAutor(int id) {
        String sql = "DELETE FROM autores WHERE autor_id = ?";
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

//Coral