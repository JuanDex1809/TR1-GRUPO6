/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author USER
 */
public class Libro {
    private int idlibro;
    private int autorid;
    private String autor;
    private String nombre;
    private String fechapubli;
    private int generoid;
    private String genero;
    private int stock;
    private int cantpres;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public int getAutorid() {
        return autorid;
    }

    public void setAutorid(int autorid) {
        this.autorid = autorid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechapubli() {
        return fechapubli;
    }

    public void setFechapubli(String fechapubli) {
        this.fechapubli = fechapubli;
    }

    public int getGeneroid() {
        return generoid;
    }

    public void setGeneroid(int generoid) {
        this.generoid = generoid;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantpres() {
        return cantpres;
    }

    public void setCantpres(int cantpres) {
        this.cantpres = cantpres;
    }
    
}
