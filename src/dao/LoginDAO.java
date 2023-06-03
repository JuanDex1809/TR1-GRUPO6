/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Modelo.Login;

/**
 *
 * @author JUANDEDIOS
 */
public interface LoginDAO {
    public Login obtenerLogin(String usuario,String password);
    
}
