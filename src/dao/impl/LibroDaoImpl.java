/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Modelo.Libro;
import dao.Conexion;
import dao.LibroDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class LibroDaoImpl implements LibroDao{
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    @Override
    public boolean registarLibro(Libro lib) {
        String sql = "INSERT INTO LIBROS(AUTOR_ID,NOMBRE,FECHA_PUBLI,GENERO_ID,STOCK) VALUES(?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, lib.getAutorid());
            ps.setString(2, lib.getNombre());
            ps.setString(3,lib.getFechapubli());
            ps.setInt(4, lib.getGeneroid());
            ps.setInt(5, lib.getStock());
            
            ps.execute();
            return true;
            
        }catch (SQLException e) {
           System.out.println("Error en el registro "+e.getMessage());
            return false;
        }finally{
            try{
                con.close();
              
            }catch(SQLException e){
                System.out.println("Error al cerrar "+e.getMessage());
            }
        }
    }

    @Override
    public List listarLibro() {
        List<Libro> listaLibro = new ArrayList<>();
        
        String sql = "SELECT LIBROS.ID_LIBRO, LIBROS.AUTOR_ID, AUTOR.AUTOR, LIBROS.NOMBRE, LIBROS.FECHA_PUBLI, LIBROS.GENERO_ID, GENEROS.GENERO, LIBROS.STOCK FROM LIBROS JOIN AUTOR ON LIBROS.AUTOR_ID = AUTOR.ID_AUT JOIN GENEROS ON LIBROS.GENERO_ID = GENEROS.ID_GEN";
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Libro libro = new Libro();
            
            libro.setIdlibro(rs.getInt("ID_LIBRO"));
            libro.setAutorid(rs.getInt("AUTOR_ID"));
            libro.setAutor(rs.getString("AUTOR"));
            libro.setNombre(rs.getString("NOMBRE"));
            libro.setFechapubli(rs.getString("FECHA_PUBLI"));
            libro.setGeneroid(rs.getInt("GENERO_ID"));
            libro.setGenero(rs.getString("GENERO"));
            libro.setStock(rs.getInt("STOCK"));
            listaLibro.add(libro);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar libros "+e.getMessage());
    
        }
        return listaLibro;
    }

    @Override
    public boolean eliminarLibro(int id) {
        String sql = "DELETE FROM LIBROS WHERE ID_LIBRO = ?";

        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println("error al eliminar el libro "+e.getMessage());
            return false;
            
        }finally {
            try {
              con.close();
              
            }catch(SQLException e){
                System.out.println("error al cerrrar la conexión "+e.getMessage());

            }
        }
    }

    @Override
    public boolean editarLibro(Libro lib) {
        String sql = "UPDATE LIBROS SET AUTOR_ID = ?, NOMBRE = ?,FECHA_PUBLI = ?,GENERO_ID = ?,STOCK = ? WHERE ID_LIBRO = ?";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, lib.getAutorid());
            ps.setString(2, lib.getNombre());
            ps.setString(3, lib.getFechapubli());
            ps.setInt(4, lib.getGeneroid());
            ps.setInt(5, lib.getStock());
            ps.setInt(6,lib.getIdlibro());
            
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println("error al editar el libro "+e.getMessage());
            return false;
            
        }finally {
            try {
              con.close();
              
            }catch(SQLException e){
                System.out.println("error al cerrrar la conexión "+e.getMessage());

            }
        }
    }

    @Override
    public List buscarLibro(String tipo,String buscar) {
        List<Libro> buscarLibro = new ArrayList<>();
        
        String sql = "SELECT LIBROS.ID_LIBRO, LIBROS.AUTOR_ID, AUTOR.AUTOR, LIBROS.NOMBRE, LIBROS.FECHA_PUBLI, LIBROS.GENERO_ID, GENEROS.GENERO, LIBROS.STOCK " +
                 "FROM LIBROS " +
                 "JOIN AUTOR ON LIBROS.AUTOR_ID = AUTOR.ID_AUT " +
                 "JOIN GENEROS ON LIBROS.GENERO_ID = GENEROS.ID_GEN " +
                 "WHERE " + tipo + " LIKE ?";
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+buscar+"%");
            rs = ps.executeQuery();
            
            while (rs.next()){
            Libro libro = new Libro();
            
            libro.setIdlibro(rs.getInt("ID_LIBRO"));
            libro.setAutorid(rs.getInt("AUTOR_ID"));
            libro.setAutor(rs.getString("AUTOR"));
            libro.setNombre(rs.getString("NOMBRE"));
            libro.setFechapubli(rs.getString("FECHA_PUBLI"));
            libro.setGeneroid(rs.getInt("GENERO_ID"));
            libro.setGenero(rs.getString("GENERO"));
            libro.setStock(rs.getInt("STOCK"));
            buscarLibro.add(libro);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar libros "+e.getMessage());
    
        }
        return buscarLibro;
    }

    @Override
    public boolean agregardetLibro(int stock) {
        String sql1 = "SELECT ID_LIBRO FROM LIBROS ORDER BY ID_LIBRO DESC LIMIT 1";
        String sql = "INSERT INTO DETLIBROS(ID_LIBRO,ESTADO) VALUES(?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql1);
            rs = ps.executeQuery();
            rs.next();
            Libro libro = new Libro();
            libro.setIdlibro(rs.getInt("ID_LIBRO"));
            
            
            for(int x = 0; x < stock; x++){
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, libro.getIdlibro());
                ps.setString(2, "NUEVO");

                ps.execute();
            }
            return true;
            
        }catch (SQLException e) {
           System.out.println("Error en el registro "+e.getMessage());
            return false;
        }finally{
            try{
                con.close();
              
            }catch(SQLException e){
                System.out.println("Error al cerrar "+e.getMessage());
            }
        }
    }
}
