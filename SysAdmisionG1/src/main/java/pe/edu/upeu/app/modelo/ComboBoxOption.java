/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.modelo;

import lombok.Data;

/**
 *
 * @author Data_Science
 */
@Data
public class ComboBoxOption {
    private String key;
    private String value;

    public ComboBoxOption(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String toString(){
        return value;
    }
            

}
