/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitario;

import dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author USER
 */
public class Grafico {
    public static void graficarPrestamos(String fecha){
        Connection con ;
        Conexion cn = new Conexion();
        PreparedStatement ps ;
        ResultSet rs;
        try {
           String sql = "SELECT COUNT(DETLIBROS.ID_LIBRO) AS TOTAL,LIBROS.NOMBRE AS NOMBRE FROM PRESTAMOS JOIN DETLIBROS  ON PRESTAMOS.ID_LIBRO = DETLIBROS.IDDETLIB JOIN LIBROS ON DETLIBROS.ID_LIBRO = LIBROS.ID_LIBRO GROUP BY LIBROS.NOMBRE;";
           con= cn.getConnection();
           ps=con.prepareStatement(sql);
          // ps.setString(1,fecha);
           rs = ps.executeQuery();
//           DefaultPieDataset dataset = new DefaultPieDataset();
//           while (rs.next()){
//               
//               dataset.setValue(rs.getString("total"),rs.getDouble("total"));
//           }
            DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
             while (rs.next()){
               
               barDataset.setValue(rs.getDouble("total"),rs.getString("TOTAL"),rs.getString("NOMBRE"));
           }
          // JFreeChart jf = ChartFactory.createPieChart("REPORTE DE VENTA", dataset);
          
            JFreeChart jf = ChartFactory.createBarChart("Libros mas prestados","FECHA: "+fecha,"TOTAL", barDataset);
          
           ChartFrame f = new ChartFrame("Libros", jf);
           f.setSize(1000,500);
           f.setLocationRelativeTo(null);
           f.setVisible(true);
       }catch (SQLException e){
             System.out.println(e.toString());
       }
    }
    
    public static void graficarUsuario(String fecha){
        Connection con ;
        Conexion cn = new Conexion();
        PreparedStatement ps ;
        ResultSet rs;
        try {
           String sql = "SELECT COUNT(PRESTAMOS.ID_USU) AS CANTIDAD, concat(USUARIO.NOMBRE, ' ', USUARIO.APELLIDO) AS NOMBRE\n" +
"FROM PRESTAMOS \n" +
"JOIN USUARIO ON PRESTAMOS.ID_USU = USUARIO.ID_USU GROUP BY PRESTAMOS.ID_USU,DNI, NOMBRE ORDER BY CANTIDAD DESC;";
           con= cn.getConnection();
           ps=con.prepareStatement(sql);
          // ps.setString(1,fecha);
           rs = ps.executeQuery();
//           DefaultPieDataset dataset = new DefaultPieDataset();
//           while (rs.next()){
//               
//               dataset.setValue(rs.getString("total"),rs.getDouble("total"));
//           }
            DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
             while (rs.next()){
               
               barDataset.setValue(rs.getDouble("CANTIDAD"),rs.getString("CANTIDAD"),rs.getString("NOMBRE"));
           }
          // JFreeChart jf = ChartFactory.createPieChart("REPORTE DE VENTA", dataset);
          
            JFreeChart jf = ChartFactory.createBarChart("Usuarios con libros mas prestados","FECHA: "+fecha,"CANTIDAD", barDataset);
          
           ChartFrame f = new ChartFrame("Usuarios", jf);
           f.setSize(1000,500);
           f.setLocationRelativeTo(null);
           f.setVisible(true);
       }catch (SQLException e){
             System.out.println(e.toString());
       }
    }
}
