/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Modelo.Genero;
import java.util.List;

/**
 *
 * @author USER
 */
public interface GeneroDao {
    
    public boolean registarGenero (Genero gen);
    
    public List listarGenero();
    
    public boolean eliminarGenero(int id);
    
    public boolean editarGenero(Genero gen);
}
