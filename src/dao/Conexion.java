/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USER
 */
public class Conexion {
    Connection con;
    
    public Connection getConnection(){
        try{
          String myBd = "jdbc:mysql://localhost:3306/DBBIBLIOTECA?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
          con = DriverManager.getConnection(myBd,"root","");
            return con;
        }catch (Exception e){
        System.out.println("Error de Conexion "+ e.getMessage() );
        return null;
        }
    }
}
