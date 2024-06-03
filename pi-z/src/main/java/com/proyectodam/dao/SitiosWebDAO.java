package com.proyectodam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proyectodam.dto.SitiosWeb;
import com.proyectodam.ui.PantallaVerificacion;

public class SitiosWebDAO {
    // Crear nuevo Sitios_Web
    public static boolean insertarArchivo() {
        String sql = "INSERT INTO Sitios_web (url) VALUES (?)";
        
        String url = PantallaVerificacion.urlWeb();
        
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, url);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Leer Sitios_Web por ID
    public static String obtenerSitiosPorId(String url) {
        String sql = "SELECT * FROM Sitios_web WHERE url = ?";
        try (Connection conn = Conexion.getConnection();/*Dentro del try meto lo que voy a ejecutar*/
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, url);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String resultado; 

                String id = rs.getString("id");
                String urls = rs.getString("url");

                resultado = id + " " + urls;
                return resultado;
            }
         }catch (SQLException e) {/*Manejo de errores (?)*/
            e.printStackTrace();
        }
        return null;
    }

    
    /* Coger id por url */

    public static Integer obtenerIdPorUrl(String url){
        String sql = "SELECT * FROM Sitios_web WHERE url = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, url);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
    


    /* Obtener url por url introducida*/

    public static String obtenerUrlPorUrlIntro(String url){
        String sql = "SELECT url FROM Sitios_web WHERE url = ? ORDER BY url DESC";

        try (Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, url);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getString("url");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }        

        return null;
    }


    //Eliminar por url
    public static List<Integer> obtenerIdsPorUrl(String url) {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM Sitios_web WHERE url = ?";
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

    // Eliminar Sitios_web (Si)
    public static boolean eliminarSitioWeb(int id) {
        String sql = "DELETE FROM Sitios_web WHERE id = ?";
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

// Coral&Ludmila terminado