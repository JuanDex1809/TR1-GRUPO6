/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Modelo.Usuarios;
import dao.Conexion;
import dao.UsuariosDao;
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
public class UsuariosDaoImpl implements UsuariosDao{
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    @Override
    public boolean registrarUsuario(Usuarios usu) {
        String sql = "INSERT INTO USUARIO(DNI,NOMBRE,APELLIDO,ESTADO) VALUES (?,?,?,'BIEN')";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getDni());
            ps.setString(2, usu.getNombre());
            ps.setString(3,usu.getApellido());
            
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
    public List listarUsuarios() {
        List<Usuarios> listaUsuarios = new ArrayList<>();
        
        String sql = "SELECT * FROM Usuario";
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Usuarios usuario = new Usuarios();
            
            usuario.setIdusu(rs.getInt("ID_USU"));
            usuario.setDni(rs.getString("DNI"));
            usuario.setNombre(rs.getString("NOMBRE"));
            usuario.setApellido(rs.getString("APELLIDO"));
            listaUsuarios.add(usuario);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar libros "+e.getMessage());
    
        }
        return listaUsuarios;
    }

    @Override
    public boolean eliminarUsuarios(int id) {
        String sql = "DELETE FROM USUARIO WHERE ID_USU = ?";

        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println("error al eliminar el usuario "+e.getMessage());
            return false;
            
        }finally {
            try {
              con.close();
              
            }catch(SQLException e){
                System.out.println("error al cerrrar la conexión "+e.getMessage());
                
            }
        }}

    @Override
    public boolean editarUsuarios(Usuarios usu) {
        
    String sql = "UPDATE USUARIO SET DNI = ?, NOMBRE = ?,APELLIDO = ? WHERE ID_USU = ?";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, usu.getDni());
            ps.setString(2, usu.getNombre());
            ps.setString(3, usu.getApellido());
            ps.setInt(4, usu.getIdusu());
            
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println("error al editar el Usuario "+e.getMessage());
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
