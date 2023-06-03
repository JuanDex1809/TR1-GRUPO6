/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitario;

/**
 *
 * @author USER
 */

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dao.Conexion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class reportPDF {
    public void reportePrestamos(){
        
        // Obtiene la fecha actual
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaTexto = fechaActual.format(formatter);
        
        // Ruta del archivo PDF de salida
        String ruta = System.getProperty("user.home");
        String rutaArchivo = ruta+"/Desktop/reportPrestamos ("+fechaTexto+").pdf";

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            document.open();

            Paragraph empresa = new Paragraph("BIBLIOTECA MUNICIPALIDAD DE LIMA"/*, new Font(Font.FontFamily.COURIER, 32, Font.BOLD)*/);
            empresa.setAlignment(Element.ALIGN_CENTER);
            empresa.setSpacingAfter(50f);
            
            document.add(empresa);
            
            /*
            // Escala la imagen para ajustarse al tamaño deseado
            Image imagen = Image.getInstance("src/img/logoLog.png");
            imagen.scaleToFit(200, 200);
            
            PdfPTable table = new PdfPTable(1);
            PdfPCell cell = new PdfPCell(imagen);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);

            table.addCell(cell);

            document.add(table);*/

            // Agrega la fecha actual al documento PDF
            Paragraph fecha = new Paragraph("Fecha de creación: " + fechaTexto, new Font(Font.FontFamily.TIMES_ROMAN, 12));
            fecha.setAlignment(Element.ALIGN_LEFT);
            document.add(fecha);

            // Agrega la tabla al documento PDF
            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER); 
            float[] anchosColumnas = {1.5f, 2.5f, 1.5f, 1.5f, 1.7f, 1.3f};
            tabla.setWidths(anchosColumnas);

            Font font = new Font(Font.FontFamily.HELVETICA, 9);

            // Agregar encabezados de columna con el tamaño de letra especificado
            tabla.addCell(new Phrase("ID PRESTAMOS", font));
            tabla.addCell(new Phrase("ID USUARIO", font));
            tabla.addCell(new Phrase("ID LIBRO", font));
            tabla.addCell(new Phrase("FECHA PRESTAMOS", font));
            tabla.addCell(new Phrase("FECHA DEV", font));
            tabla.addCell(new Phrase("DEVUELTO", font));

            Connection cn ;
            Conexion conexion = new Conexion();
            cn= conexion.getConnection();
            PreparedStatement ps ; 
            String sql = "SELECT * FROM PRESTAMOS";
            ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()){
                    tabla.addCell(new Phrase(rs.getInt(1)+"", font));
                    tabla.addCell(new Phrase(rs.getString(2)+"", font));
                    tabla.addCell(new Phrase(rs.getString(3)+"", font));
                    tabla.addCell(new Phrase(rs.getString(4)+"", font));
                    tabla.addCell(new Phrase(rs.getString(5)+"", font));
                    tabla.addCell(new Phrase(rs.getString(6)+"", font));
            }
            
            document.add(tabla);
            document.close();
            JOptionPane.showMessageDialog(null, "Reporte Pdf creado correctamente en su escritorio!", "OK",JOptionPane.INFORMATION_MESSAGE);
            
        }catch (DocumentException | SQLException e){
          System.out.println("Error en el reporte de clientes "+e.getMessage());
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(reportPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(reportPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
