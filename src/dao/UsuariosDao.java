/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Modelo.Usuarios;
import java.util.List;

/**
 *
 * @author USER
 */
public interface UsuariosDao {
    public boolean registrarUsuario(Usuarios usu);
    
    public List listarUsuarios();
    
    public boolean eliminarUsuarios(int id);
    
    public boolean editarUsuarios(Usuarios usu);
}
