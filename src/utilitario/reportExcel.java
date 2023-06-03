/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitario;


import dao.Conexion;
import java.sql.Connection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author USER
 */
public class reportExcel {
    public void reportePrestamos(){
        
        // Obtiene la fecha actual
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaTexto = fechaActual.format(formatter);
        
        // Ruta del archivo Excel de salida
        String ruta = System.getProperty("user.home");
        String rutaArchivo = ruta + "/Desktop/reportePrestamos ("+ fechaTexto +").xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte Prestamos");

            // Estilo de celda para el encabezado
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            
            /*int imgIndex;
            try ( // Obtener la ruta de la imagen
                InputStream is = new FileInputStream("")) {
                byte[] bytes = IOUtils.toByteArray(is);
                imgIndex = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            }

            CreationHelper help = workbook.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchorImg = help.createClientAnchor();
            anchorImg.setCol1(9);
            anchorImg.setRow1(4);
            Picture pict = draw.createPicture(anchorImg, imgIndex);
            pict.resize(7, 17);*/
            
            Row dateRow = sheet.createRow(1);
            dateRow.createCell(0).setCellValue("Fecha de creación: "+fechaTexto);
            
            sheet.setColumnWidth(0, 4000); 
            sheet.setColumnWidth(1, 4000); 
            sheet.setColumnWidth(2, 4000); 
            sheet.setColumnWidth(3, 4000); 
            sheet.setColumnWidth(4, 5000); 
            sheet.setColumnWidth(5, 3000); 
            
            // Agrega el encabezado al documento Excel
            Row headerRow = sheet.createRow(3);
            String[] headers = {"ID PRESTAMOS", "ID USUARIO", "ID LIBRO", "FECHA PRESTAMO", "FECHA DEVOLUCION", "DEVUELTO"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            Connection cn;
            Conexion conexion = new Conexion();
            cn = (Connection) conexion.getConnection();
            PreparedStatement ps;

            String sql = "SELECT * FROM PRESTAMOS";

            ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            CellStyle contentStyle = workbook.createCellStyle();
            contentStyle.setBorderRight(BorderStyle.THIN);
            contentStyle.setAlignment(HorizontalAlignment.CENTER);
            contentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            contentStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Recorre las filas del contenido y aplica el estilo a cada celda
            int rowNum = 4;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getInt(1));
                row.createCell(1).setCellValue(rs.getString(2));
                row.createCell(2).setCellValue(rs.getString(3));
                row.createCell(3).setCellValue(rs.getString(4));
                row.createCell(4).setCellValue(rs.getString(5));
                row.createCell(5).setCellValue(rs.getString(6));

                for (int i = 0; i < headers.length; i++) {
                    Cell cell = row.getCell(i);
                    cell.setCellStyle(contentStyle);
                }
            }

            // Guarda el archivo Excel en la ubicación especificada
            try (FileOutputStream fileOut = new FileOutputStream(rutaArchivo)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Reporte Excel creado correctamente en su escritorio!", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo Excel: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error en el reporte de prestamos " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al crear el archivo Excel: " + e.getMessage());
        }

    }
}
