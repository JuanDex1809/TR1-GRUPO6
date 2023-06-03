/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Modelo.Prestamos;
import java.util.List;

/**
 *
 * @author USER
 */
public interface PrestamosDao {
    public boolean registrarPrestamo (Prestamos pres);
    
    public List listarPrestamo();
    
    public boolean registrarDevoluciones (Prestamos pres);
    
    public List listarDevoluciones();
    
    public List listarInformesPrestamos(String muestra);
    
    public List listarInformesUsuario(String muestra);
}
