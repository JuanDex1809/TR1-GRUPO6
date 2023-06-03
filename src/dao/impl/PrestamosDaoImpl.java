/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import Modelo.Genero;
import Modelo.Prestamos;
import dao.Conexion;
import dao.PrestamosDao;
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
public class PrestamosDaoImpl  implements PrestamosDao{
    Connection con ;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    @Override
    public boolean registrarPrestamo(Prestamos pres) {
        String sql = "insert into prestamos(id_usu,id_libro,fecha_pres,devuelto) VALUES(?,?,?,'NO')";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pres.getIdusu());
            ps.setInt(2, pres.getIdlibro());
            ps.setString(3, pres.getFechapres());
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
    public List listarPrestamo() {
        List<Prestamos> listaPrestamos = new ArrayList<>();
        
        String sql = "SELECT * FROM PRESTAMOS ORDER BY ID_PRES DESC";
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Prestamos prestamos = new Prestamos();
            
            prestamos.setIdpres(rs.getInt("ID_PRES"));
            prestamos.setIdusu(rs.getInt("ID_USU"));
            prestamos.setIdlibro(rs.getInt("ID_LIBRO"));
            prestamos.setFechapres(rs.getString("FECHA_PRES"));
            
            listaPrestamos.add(prestamos);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar Prestamos "+e.getMessage());
    
        }
        return listaPrestamos;
    }

    @Override
    public boolean registrarDevoluciones(Prestamos pres) {
        String sql = "UPDATE PRESTAMOS SET FECHA_DEV = ?, DEVUELTO = 'SI' WHERE ID_USU = ? AND ID_LIBRO = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pres.getFechadev());
            ps.setInt(2, pres.getIdusu());
            ps.setInt(3, pres.getIdlibro());
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
    public List listarDevoluciones() {
        List<Prestamos> listaDevolucciones = new ArrayList<>();
        
        String sql = "SELECT * FROM PRESTAMOS WHERE DEVUELTO = 'SI' ORDER BY ID_PRES DESC";
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Prestamos prestamos = new Prestamos();
            
            prestamos.setIdpres(rs.getInt("ID_PRES"));
            prestamos.setIdusu(rs.getInt("ID_USU"));
            prestamos.setIdlibro(rs.getInt("ID_LIBRO"));
            prestamos.setFechapres(rs.getString("FECHA_PRES"));
            prestamos.setFechadev(rs.getString("FECHA_DEV"));
            
            listaDevolucciones.add(prestamos);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar Devoluciones "+e.getMessage());
    
        }
        return listaDevolucciones;
    }

    @Override
    public List listarInformesPrestamos(String muestra) {
        List<Prestamos> listaInformesprestamos = new ArrayList<>();
        
        if(muestra.equals("ASCENDENTE")){
            muestra = "ASC";
        }else{
            muestra = "DESC";
        }
        
        String sql ="SELECT COUNT(DETLIBROS.ID_LIBRO)AS CANTIDAD,LIBROS.NOMBRE, LIBROS.FECHA_PUBLI\n" +
                    "FROM PRESTAMOS \n" +
                    "JOIN DETLIBROS ON PRESTAMOS.ID_LIBRO = DETLIBROS.IDDETLIB \n" +
                    "JOIN LIBROS ON DETLIBROS.ID_LIBRO = LIBROS.ID_LIBRO \n" +
                    "GROUP BY LIBROS.NOMBRE,LIBROS.FECHA_PUBLI ORDER BY CANTIDAD "+muestra;
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Prestamos prestamos = new Prestamos();
            
            prestamos.setIdlibro(rs.getInt("CANTIDAD"));
            prestamos.setLibro(rs.getString("NOMBRE"));
            prestamos.setFechapres(rs.getString("FECHA_PUBLI"));
            
            listaInformesprestamos.add(prestamos);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar Informes de prestamos "+e.getMessage());
    
        }
        return listaInformesprestamos;
    }

    @Override
    public List listarInformesUsuario(String muestra) {
        List<Prestamos> listaInformesusuario = new ArrayList<>();
        
        if(muestra.equals("ASCENDENTE")){
            muestra = "ASC";
        }else{
            muestra = "DESC";
        }
        
        String sql = "SELECT COUNT(PRESTAMOS.ID_USU) AS CANTIDAD, USUARIO.DNI, concat(USUARIO.NOMBRE,' ',USUARIO.APELLIDO) AS NOMBRE "+
                    "FROM PRESTAMOS "+ "JOIN USUARIO ON PRESTAMOS.ID_USU = USUARIO.ID_USU GROUP BY PRESTAMOS.ID_USU, DNI, NOMBRE ORDER BY CANTIDAD "+muestra;
        try{
        
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            Prestamos prestamos = new Prestamos();
            
            prestamos.setIdlibro(rs.getInt("CANTIDAD"));
            prestamos.setLibro(rs.getString("DNI"));
            prestamos.setUsuario(rs.getString("NOMBRE"));
            
            listaInformesusuario.add(prestamos);
            }
            
        }catch(SQLException e){
            System.out.print("Error al listar Informes de prestamos "+e.getMessage());
    
        }
        return listaInformesusuario;
    }
    
}
