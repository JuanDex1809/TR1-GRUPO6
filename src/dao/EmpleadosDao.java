/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import Modelo.Empleados;
import java.util.List;

/**
 *
 * @author USER
 */
public interface EmpleadosDao {
    public boolean registrarEmpleado(Empleados emp);
    
    public List listarEmpleado();
    
    public boolean eliminarEmpleado(int id);
    
    public boolean editarEmpleado(Empleados emp);
}
