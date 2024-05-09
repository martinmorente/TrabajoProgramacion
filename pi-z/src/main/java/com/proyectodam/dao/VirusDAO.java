package com.proyectodam.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.proyectodam.dto.Virus;

public class VirusDAO {
    // Crear un nuevo autor
    public boolean insertarVirus(Virus virus) {
        String sql = "INSERT INTO Virus_web (nombre_virus, descripcion_virus) VALUES (?,?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, virus.getNombreVirus());
            pstmt.setString(2,virus.getDescripcionVirus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
// Leer un Virus por ID
    public Virus obtenerVirusPorId(int id) {
        String sql = "SELECT * FROM Virus_web WHERE id_Virus_Web= ?";/* */
        try (Connection conn = Conexion.getConnection();/*Dentro del try meto lo que voy a ejecutar*/
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Virus(rs.getInt("id_Virus_Web"), rs.getString("nombre_virus"), rs.getString("descripcion_virus"));
            }
        } catch (SQLException e) {/*Manejo de errores (?)*/
            e.printStackTrace();
        }
        return null;
    }
}