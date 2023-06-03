/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Modelo.Empleados;
import dao.Conexion;
import dao.EmpleadosDao;
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
public class EmpleadoDaoImpl implements EmpleadosDao{
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    @Override
    public boolean registrarEmpleado(Empleados emp) {
        String sql = "INSERT INTO LOGIN(ROL,USUARIO,PASS) VALUES (?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getRol());
            ps.setString(2, emp.getUsuario());
            ps.setString(3,emp.getPass());
            
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
    public List listarEmpleado() {
        List<Empleados> listaEmpleados = new ArrayList<>();
        
        String sql = "SELECT * FROM LOGIN";
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Empleados empleado = new Empleados();
            
            empleado.setIdempleado(rs.getInt("ID"));
            empleado.setRol(rs.getString("ROL"));
            empleado.setUsuario(rs.getString("USUARIO"));
            empleado.setPass(rs.getString("PASS"));
            listaEmpleados.add(empleado);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar libros "+e.getMessage());
    
        }
        return listaEmpleados;
    }

    @Override
    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM LOGIN WHERE ID = ?";

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
    public boolean editarEmpleado(Empleados emp) {
        String sql = "UPDATE LOGIN SET ROL = ?, USUARIO = ?,PASS = ? WHERE ID = ?";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, emp.getRol());
            ps.setString(2, emp.getUsuario());
            ps.setString(3, emp.getPass());
            ps.setInt(4, emp.getIdempleado());
            
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
    
}
