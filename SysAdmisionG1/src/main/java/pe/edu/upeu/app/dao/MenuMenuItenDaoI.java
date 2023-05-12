package pe.edu.upeu.app.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Data_Science
 */
import java.util.List;
import java.util.Properties;
import pe.edu.upeu.app.modelo.MenuMenuItenTO;

public interface MenuMenuItenDaoI {

    public List<MenuMenuItenTO> listaAccesos(String perfil, Properties idioma);
}
