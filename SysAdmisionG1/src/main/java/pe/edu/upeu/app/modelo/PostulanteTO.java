/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.modelo;

import lombok.Data;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
@Data
public class PostulanteTO {
    public int idPostulante, idCarrera, idPeriodo;
    public String dni, nombre, apellidoPat,apellidoMat,modalidad, estado;
    //Adicionales por cada forany key
    public String nombrePeriodo, nombreCarrera, nombreModalidad;

    public void setAreaPeriodo(String next) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setPregunta(String next) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
