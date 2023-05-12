/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import pe.com.syscenterlife.autocomp.ModeloDataAutocomplet;
import pe.edu.upeu.app.conexion.ConnS;
import pe.edu.upeu.app.modelo.ComboBoxOption;
import pe.edu.upeu.app.modelo.PostulanteTO;
import pe.edu.upeu.app.modelo.PreguntasTO;
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author romer
 */
public class PreguntasDao {
    ConnS instance = ConnS.getInstance();
    Connection connection = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    ErrorLogger log = new ErrorLogger(PreguntasDao.class.getName());
    
    //Crear
    public int create(PreguntasTO d) {
        int rsId = 0;
        String[] returns = {"IdBancoPreguntas"};
        String sql = "INSERT INTO pregunta(idBp, id_area_periodo "
                + " pregunta, resultado) "
                + " values(?, ?, ?, ?);";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setInt(++i, d.getIdBancoPreguntas());
            ps.setInt(++i, d.getIdAreaPeriodo());
            ps.setString(++i, d.getIdBp());
            ps.setString(++i, d.getPregunta());
            ps.setString(++i, d.getResultado());
            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "create", ex);
        }
        return rsId;
    }
    
    //Actualizar
    public int update(PreguntasTO d) {
        System.out.println("actualizar d.getbp: " + d.getBp());
        int comit = 0;
        String sql = "UPDATE resultado SET "
                + "resultado=?, ";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(++i, d.getResultado());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }
    
    
    public List<PreguntasTO> listarTodo() {
        List<PreguntasTO> listarEntidad = new ArrayList();
        String sql = "SELECT po.*, ap.nombreareaperiodo as nombreareaperiodo, bp.nombrebancopreguntas "
                + "FROM preguntas, area_periodo ap , banco_preguntas bp"
                + "WHERE ap.id_area_periodo = bp.id_banco_preguntas and ap.id_area_periodo = bp.id_banco_preguntas";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PreguntasTO cli = new PreguntasTO();
                cli.setDni(rs.getString("dni"));
                cli.setIdPregunta(rs.getInt("id_pregunta"));
                cli.setIdBp(rs.getInt("id_bp"));
                cli.setNombreAreaPeriodo(rs.getString("nombreareaperiodo"));
                //cli.setNombreModalidad(buscarModalidadExamen(rs.getString("modalidad")));
                listarEntidad.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarEntidad;
    }
    
    //Eliminar
    public int delete(String id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM pregunta WHERE dni = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "delete", ex);
            throw new Exception("Detalle:" + ex.getMessage());
        }
        return comit;
    }
    
    //CRUD 
    public static void main(String[] args) {
        Scanner cs = new Scanner(System.in);
        PreguntasDao po = new PreguntasDao();

        int i = 0;
        String opcion = "R";
        String mensajeOpciones = "Menu de Opciones\nR=Reportar\nC=Crear\nU=Update\nD=Delete\nX=Salir";
        do {
            switch (opcion) {
                case "C" -> {
                    PreguntasTO tox = new PreguntasTO();
                    System.out.println("Ingrese el dni:");
                    tox.setDni(cs.next());
                    System.out.println("Ingrese Resultado:");
                    tox.setResultado(cs.next());
                    System.out.println("Ingrese Numero :");
                    tox.setNumero(cs.next());
                    System.out.println("Ingrese Pregunta:");
                    tox.setPregunta(cs.next());
                    System.out.println("Ingrese Modalidad(E=Examen, PI=Primeros Puestos):");
                }
                case "R" ->
                    po.listarPregunta(po.listarTodo());
                case "U" -> {
                    PreguntasTO tox;
                    System.out.println("Ingrese el bp a Modificar:");
                    String IdBp=cs.next();
                    tox=po.buscarEntidad(IdBp);
                    System.out.println("Ingres Area Periodo:");
                    tox.setAreaPeriodo(cs.next());
                    System.out.println("Ingres Id pregunta:");
                    tox.setPregunta(cs.next());                    
                    po.update(tox);
                    po.listarPregunta(po.listarTodo());
                }
                case "D" -> {
                    try {
                        System.out.println("Ingrese el dni del Registro que desea eliminar:");
                        po.delete(cs.next());
                        po.listarPregunta(po.listarTodo());
                    } catch (Exception e) {
                        System.err.println("Error al Eliminar");
                    }
                }
                default ->
                    System.out.println("Opcion no existe intente otra vez!");
            }
            System.out.println("Que desea hacer:\n" + mensajeOpciones);
            opcion = cs.next();
        } while (!opcion.toUpperCase().equals("X"));

        System.out.println("F1:" + (i++));
    }

    public void listarPregunta(List<PreguntasTO> lista) {
        System.out.println("Preguntas\t\tBancoPreguntas\t\t\tResultado");
        for (PreguntasTO p : lista) {
            System.out.println(p.getPreguntas() + "\t" +p.getBancoPreguntas() + "\t\t" + p.getResultado() + "\t\t\t");
        }
    }

    public PreguntasTO buscarEntidad(String IdBp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void update(PostulanteTO tox) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<PreguntasTO> listCmb(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<ModeloDataAutocomplet> listAutoComplet(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<ComboBoxOption> listaModalidadExamen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<ComboBoxOption> listarPeriodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
