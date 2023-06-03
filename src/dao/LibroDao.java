/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Modelo.Libro;
import java.util.List;

/**
 *
 * @author USER
 */
public interface LibroDao {
    public boolean registarLibro(Libro lib);
    
    public List listarLibro();
    
    public boolean eliminarLibro(int id);
    
    public boolean editarLibro(Libro lib);
    
    public List buscarLibro(String tipo,String buscar);
    
    public boolean agregardetLibro( int stock);
    
}
