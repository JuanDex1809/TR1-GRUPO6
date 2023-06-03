/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Modelo.Autor;
import java.util.List;

/**
 *
 * @author USER
 */
public interface AutorDao {
    public boolean registrarAutor (Autor aut);
    
    public List listarAutor();
    
    public boolean eliminarAutor(int id);
    
    public boolean editarAutor(Autor aut);
    
}
