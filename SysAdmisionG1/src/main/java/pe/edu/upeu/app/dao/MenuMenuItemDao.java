/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

/**
 *
 * @author Data_Science
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import pe.edu.upeu.app.modelo.MenuMenuItenTO;

public class MenuMenuItemDao implements MenuMenuItenDaoI {

    @Override
    public List<MenuMenuItenTO> listaAccesos(String perfil, Properties idioma) {
        List<MenuMenuItenTO> lista = new ArrayList<>();
        lista.add(new MenuMenuItenTO(idioma.getProperty("menu.nombre.archivo"), "", "mifile"));
        lista.add(new MenuMenuItenTO("Edit", "cut", "micut"));
        lista.add(new MenuMenuItenTO("Edit", "copy", "micopy"));
        lista.add(new MenuMenuItenTO("Edit", "paste", "mipaste"));
        lista.add(new MenuMenuItenTO("Edit",
                idioma.getProperty("menuitem.nombre.postulante"), "miselectall"));
        lista.add(new MenuMenuItenTO("Help", "Ver1", "miver1"));
        lista.add(new MenuMenuItenTO("Help", "Ver2", "miver2"));
        
        List<MenuMenuItenTO> accesoReal = new ArrayList<>();
        switch (perfil) {
            case "Administrador":
                accesoReal.add(lista.get(0));
                accesoReal.add(lista.get(1));
                accesoReal.add(lista.get(2));
                accesoReal.add(lista.get(3));
                accesoReal.add(lista.get(4));
                accesoReal.add(lista.get(5));
                accesoReal.add(lista.get(6));
                break;
            case "Root":
                accesoReal = lista;
                break;
            case "Reporte":
                accesoReal.add(lista.get(0));
                accesoReal.add(lista.get(5));
                accesoReal.add(lista.get(6));
                break;
            default:
                throw new AssertionError();
        }
        return accesoReal;
    }
}
