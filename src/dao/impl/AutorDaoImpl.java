package dao.impl;


import Modelo.Autor;
import dao.AutorDao;
import dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class AutorDaoImpl implements AutorDao {

    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    @Override
    public boolean registrarAutor(Autor aut) {
        String sql = "INSERT INTO AUTOR(AUTOR) VALUES(?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, aut.getAutor());
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
    public List listarAutor() {
        List<Autor> listaAutor = new ArrayList<>();
        
        String sql = "SELECT * FROM AUTOR";
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Autor autor = new Autor();
            
            autor.setIdautor(rs.getInt("ID_AUT"));
            autor.setAutor(rs.getString("AUTOR"));
            listaAutor.add(autor);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar Generos "+e.getMessage());
    
        }
        return listaAutor;
    }

    @Override
    public boolean eliminarAutor(int id) {
        String sql = "DELETE FROM AUTOR WHERE ID_AUT = ?";

        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println("error al eliminar el autor "+e.getMessage());
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
    public boolean editarAutor(Autor aut) {
        String sql = "UPDATE AUTOR SET AUTOR = ? WHERE ID_AUT = ?";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, aut.getAutor());
            ps.setInt(2, aut.getIdautor());
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println("error al editar el autor "+e.getMessage());
            return false;
            
        }finally {
            try {
              con.close();
              
            }catch(SQLException e){
                System.out.println("error al cerrrar la conexión "+e.getMessage());

            }
        }
    }
    
}
