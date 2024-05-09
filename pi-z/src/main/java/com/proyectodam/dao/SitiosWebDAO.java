package com.proyectodam.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.proyectodam.dto.SitiosWeb;



public class SitiosWebDAO {
    // Crear un nuevo SitioWeb
    public boolean insertarArchivo(SitiosWeb sitios_Web) {
        String sql = "INSERT INTO Sitios_web (url_web) VALUES (?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sitios_Web.getUrlWeb());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Leer un Sitio_web por ID
    public SitiosWeb obtenerAutorPorId(int id) {
        String sql = "SELECT * FROM Sitios_Web WHERE id_web = ?";/* */
        try (Connection conn = Conexion.getConnection();/*Dentro del try meto lo que voy a ejecutar*/
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new SitiosWeb(rs.getInt("id_web"), rs.getString("url"), rs.getString());
            }
        } catch (SQLException e) {/*Manejo de errores (?)*/
            e.printStackTrace();
        }
        return null;
    }

//Coral



























    // Actualizar un autor
    public boolean actualizarAutor(Autor autor) {
        String sql = "UPDATE autores SET nombre = ? WHERE autor_id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor.getNombre());
            pstmt.setInt(2, autor.getId());/*Aquí 2 porque corresponde al segundo signo de interrogación*/
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