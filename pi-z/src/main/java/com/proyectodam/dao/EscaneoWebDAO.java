package com.proyectodam.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.proyectodam.dto.EscaneoWeb;

public class EscaneoWebDAO {
   
    public boolean insertarEscaneoWeb(EscaneoWeb escaenoWeb) {
        String sql = "INSERT INTO Escaneo_Web (fecha_escaeno, resultado, tipo_maldad, num_escaneoWeb, url_final) VALUES (?,?,?,?,?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setString(1,escaenoWeb.getFechaEscaneo());
           pstmt.setString(2, escaenoWeb.getResultado());
           pstmt.setString(3,escaenoWeb.getTipoMaldad());
           pstmt.setInt(4,escaenoWeb.getNumEscaneoWeb());
           pstmt.setString(5, escaenoWeb.getUrlFinal());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
// Leer un Escaneo por ID
    public EscaneoWeb obtenerEscaenoWebPorId(int id) {
        String sql = "SELECT * FROM Escaneo_Web WHERE = id_escaneo_web?";/* */
        try (Connection conn = Conexion.getConnection();/*Dentro del try meto lo que voy a ejecutar*/
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new EscaneoWeb(rs.getInt("id_escaneo_web"), rs.getString("fecha_escaneo"), rs.getString("resultado"), rs.getString("tipo_maldad"), rs.getInt("num_escaneoWeb"), rs.getString("url_Final"));
            }
        } catch (SQLException e) {/*Manejo de errores (?)*/
            e.printStackTrace();
        }
        return null;
    }
}