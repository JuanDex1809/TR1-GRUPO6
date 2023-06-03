/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Modelo.Genero;
import dao.Conexion;
import dao.GeneroDao;
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
public class GeneroDaoImpl implements GeneroDao{
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    @Override
    public boolean registarGenero(Genero gen) {
        String sql = "INSERT INTO GENEROS (GENERO) VALUES(?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, gen.getGenero());
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
    public List listarGenero() {
        List<Genero> listaGenero = new ArrayList<>();
        
        String sql = "SELECT * FROM GENEROS";
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Genero genero = new Genero();
            
            genero.setIdgenero(rs.getInt("ID_GEN"));
            genero.setGenero(rs.getString("GENERO"));
            listaGenero.add(genero);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar Generos "+e.getMessage());
    
        }
        return listaGenero;
    }

    @Override
    public boolean eliminarGenero(int id) {
        String sql = "DELETE FROM GENEROS WHERE ID_GEN = ?";

        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println("error al eliminar el genero "+e.getMessage());
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
    public boolean editarGenero(Genero gen) {
        String sql = "UPDATE GENEROS SET GENERO = ? WHERE ID_GEN = ?";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, gen.getGenero());
            ps.setInt(2, gen.getIdgenero());
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println("error al editar el genero "+e.getMessage());
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