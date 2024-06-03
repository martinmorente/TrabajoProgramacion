package com.proyectodam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyectodam.Negocio.Busqueda;

public class EscaneoArchivoDAO {

    public static boolean insertarEscaneoArchivo(int contador) {
        String sql = "INSERT INTO EscaneoArchivo (fecha_Escaneo,resultado,num_Escaneo, nombreAntivirus, id_archivo) VALUES (?,?,?,?,?)";
        // for que recorra el array y retorne el resultado individual de cada uno

        Busqueda busquedas = new Busqueda();
        String[] detalles = busquedas.busquedaElementosArchivos();

        String fechaEscaneo = "";
        String antiVirus = "";
        String resultado = "";
        int idArchivo = 0;

        if (detalles == null) {
            System.out.println("No se encontro nada");
        } else {
            for (String detalle : detalles) {
                fechaEscaneo = detalles[2];
                resultado = detalles[1];
                antiVirus = detalles[0];
                String hash = detalles[3];
                idArchivo = ArchivoDAO.obtenerIdPorHash(hash);
            }

        }

        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(3, contador);
            pstmt.setString(1, fechaEscaneo);
            pstmt.setString(2, resultado);
            pstmt.setString(4, antiVirus);
            pstmt.setInt(5, idArchivo);
            System.out.println("Su consulta se realizo con exito");
         
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    // Leer un Escaneo_archivo por ID
    public static String obtenerEscaneoArchivoPorIdArchivo(int id_archivo) {
        String sql = "SELECT * FROM EscaneoArchivo WHERE id_archivo = (SELECT id FROM Archivo WHERE id = ?)";
        try (Connection conn = Conexion.getConnection(); /* Dentro del try meto lo que voy a ejecutar */
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_archivo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String idArchivo = rs.getString("id_archivo");
                String fechaEscaneo = rs.getString("fecha_Escaneo");
                String resultadoEscaneo;
                if(rs.getString("resultado") == null){
                    resultadoEscaneo = "Archivo limpio"; 
                }else{
                    resultadoEscaneo = "Archivo infectado";
                }
                String resultadoBusqueda = id + " " + idArchivo + " " + fechaEscaneo + " "+resultadoEscaneo;
                return resultadoBusqueda;
            }
        } catch (SQLException e) {/* Manejo de errores (?) */
            e.printStackTrace();
        }
        return null;
    }

    public static Integer obtenerContadorPorIdEscaneo(String hash) {
        String sql = "SELECT num_Escaneo FROM EscaneoArchivo WHERE id_archivo IN (SELECT id FROM Archivo WHERE hash = ?) ORDER BY num_Escaneo DESC";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hash);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("num_Escaneo");
            } else {
                // Retornar 1 si no se encuentra el valor
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Retornar 1 en caso de error
            return 1;
        }
    }
    



    // Eliminar Escaneo_archivo (Si)
    public static boolean eliminarEscaneoArchivo(int id) {
        String sql = "DELETE  FROM EscaneoArchivo WHERE id_archivo = ?";
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

// Coral terminado