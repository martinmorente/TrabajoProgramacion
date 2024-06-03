package com.proyectodam.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyectodam.Negocio.Busqueda;
import com.proyectodam.dto.EscaneoWeb;
import com.proyectodam.ui.PantallaVerificacion;

public class EscaneoWebDAO {
   
    public static boolean insertarEscaneoWeb(int contador) {
        String sql = "INSERT INTO Escaneo_Web (fechaEscaneo, resultado, num_escaneoWeb, nombreAntivirus,idSitiosWeb) VALUES (?,?,?,?,?)";

        Busqueda busquedas = new Busqueda();
        String[] detalles = busquedas.busquedaElementosArchivos();

        String fechaEscaneo = "";
        String antiVirus = "";
        String resultado = "";
        int idWeb = 0;

        if (detalles == null) {
            System.out.println("No se encontro nada");
        } else {
            for (String detalle : detalles) {
                fechaEscaneo = detalles[2];
                resultado = detalles[1];
                antiVirus = detalles[0];
                String url = PantallaVerificacion.urlWeb();
                idWeb = SitiosWebDAO.obtenerIdPorUrl(url);
            }
        }

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setString(1,fechaEscaneo);
           pstmt.setString(2, resultado);
           pstmt.setInt(3,contador);
           pstmt.setString(4, antiVirus);
           pstmt.setInt(5, idWeb);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
// Leer un Escaneo por ID
    public static EscaneoWeb obtenerEscaenoWebPorId(int id) {
        String sql = "SELECT * FROM Escaneo_Web WHERE = id?";/* */
        try (Connection conn = Conexion.getConnection();/*Dentro del try meto lo que voy a ejecutar*/
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new EscaneoWeb(rs.getInt("id"), rs.getString("fecha_escaneo"), rs.getString("resultado"),  rs.getInt("num_escaneoWeb"), rs.getString("nombreAntivirus"));
            }
        } catch (SQLException e) {/*Manejo de errores (?)*/
            e.printStackTrace();
        }
        return null;
    }

    
    /* Obtener contador por url */


    public static Integer obtenerContadorPorUrl(String url){
        String sql = "SELECT num_escaneoWeb FROM Escaneo_Web WHERE idSitiosWeb IN (SELECT id FROM Sitios_web WHERE url = ?) ORDER BY num_escaneoWeb DESC";
        try (Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, url);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt("num_EscaneoWeb");
            }else {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }


    // Eliminar un escaneo (Si)
    public static boolean eliminarEscaneoWeb(int id) {
        String sql = "DELETE FROM Escaneo_Web WHERE idSitiosWeb = ? ";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}//Ludmila