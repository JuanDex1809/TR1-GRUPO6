/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.Conexion;
import dao.LoginDAO;
import Modelo.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JUANDEDIOS
 */
public class LoginDAOImpl implements LoginDAO{
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    @Override
    public Login obtenerLogin(String usuario, String password) {
        
        Login ingresar  = new Login();
            String sql = "select * from LOGIN where USUARIO = ? and PASS = ? ";
            try {                
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, usuario);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                  ingresar.setIdlogin(rs.getInt("id"));
                  ingresar.setRol(rs.getString("rol"));
                  ingresar.setUsuario(rs.getString("usuario"));
                  ingresar.setPassword(rs.getString("pass"));
                }  else {
                ingresar= null;
                }      
            
            }catch(Exception e) {
                ingresar = null;
                System.out.println("ERROR AL INGRESAR " + e.getMessage());
            }
            
            return ingresar;
        
    }
}
