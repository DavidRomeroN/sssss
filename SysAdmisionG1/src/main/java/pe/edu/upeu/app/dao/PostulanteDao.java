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
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
public class PostulanteDao implements PostulanteDaoI {

    ConnS instance = ConnS.getInstance();
    Connection connection = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    ErrorLogger log = new ErrorLogger(PostulanteDao.class.getName());

    @Override
    public int create(PostulanteTO d) {
        int rsId = 0;
        String[] returns = {"dni"};
        String sql = "INSERT INTO postulante(id_carrera, id_periodo, dni, "
                + " nombre, apellido_pat, apellido_mat, modalidad, estado) "
                + " values(?, ?, ?, ?, ?, ?, ?, ?);";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setInt(++i, d.getIdCarrera());
            ps.setInt(++i, d.getIdPeriodo());
            ps.setString(++i, d.getDni());
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getApellidoPat());
            ps.setString(++i, d.getApellidoMat());
            ps.setString(++i, d.getModalidad());
            ps.setString(++i, d.getEstado());
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

    @Override
    public int update(PostulanteTO d) {
        System.out.println("actualizar d.getDniruc: " + d.getDni());
        int comit = 0;
        String sql = "UPDATE postulante SET "
                + "nombre=?, "
                + "apellido_pat=?, "
                + "apellido_mat=?, "
                + "modalidad=?, "
                + "estado=?, "
                + "id_periodo=?, "
                + "id_carrera=? "
                + "WHERE dni=?";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getApellidoPat());
            ps.setString(++i, d.getApellidoMat());
            ps.setString(++i, d.getModalidad());
            ps.setString(++i, d.getEstado());
            ps.setInt(++i, d.getIdPeriodo());
            ps.setInt(++i, d.getIdCarrera());
            ps.setString(++i, d.getDni());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }

    @Override
    public List<PostulanteTO> listarTodo() {
        List<PostulanteTO> listarEntidad = new ArrayList();
        String sql = "SELECT po.*, p.nombre as nombreperiodo, c.nombrecarrera "
                + "FROM postulante po, periodo p, carrera c "
                + "WHERE p.id_periodo = po.id_periodo and po.id_carrera = c.id_carrera";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PostulanteTO cli = new PostulanteTO();
                cli.setDni(rs.getString("dni"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellidoPat(rs.getString("apellido_pat"));
                cli.setApellidoMat(rs.getString("apellido_mat"));
                cli.setModalidad(rs.getString("modalidad"));
                cli.setEstado(rs.getString("estado"));
                cli.setIdCarrera(rs.getInt("id_carrera"));
                cli.setIdPeriodo(rs.getInt("id_periodo"));
                cli.setNombrePeriodo(rs.getString("nombreperiodo"));
                cli.setNombreCarrera(rs.getString("nombrecarrera"));
                //cli.setNombreModalidad(buscarModalidadExamen(rs.getString("modalidad")));
                listarEntidad.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarEntidad;
    }

    @Override
    public int delete(String id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM postulante WHERE dni = ?";
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

    public static void main(String[] args) {
        Scanner cs = new Scanner(System.in);
        PostulanteDao po = new PostulanteDao();

        int i = 0;
        String opcion = "R";
        String mensajeOpciones = "Menu de Opciones\nR=Reportar\nC=Crear\nU=Update\nD=Delete\nX=Salir";
        do {
            switch (opcion) {
                case "C" -> {
                    PostulanteTO tox = new PostulanteTO();
                    System.out.println("Ingrese el DNI:");
                    tox.setDni(cs.next());
                    System.out.println("Ingres Nombre:");
                    tox.setNombre(cs.next());
                    System.out.println("Ingres A. Paterno:");
                    tox.setApellidoPat(cs.next());
                    System.out.println("Ingres A. Materno:");
                    tox.setApellidoMat(cs.next());
                    System.out.println("Ingrese Modalidad(E=Examen, PI=Primeros Puestos):");
                    tox.setModalidad(cs.next());
                    System.out.println("Ingrese Estado (Activo, Desactivo):");
                    tox.setEstado(cs.next());
                    System.out.println("Ingrese el Perido(1=2023-1):");
                    tox.setIdPeriodo(cs.nextInt());
                    System.out.println("Ingrese Carrera(1=Sistemas, 2=Contabilidad):");
                    tox.setIdCarrera(cs.nextInt());
                    po.create(tox);
                    po.listarPostulantes(po.listarTodo());
                }
                case "R" ->
                    po.listarPostulantes(po.listarTodo());
                case "U" -> {
                    PostulanteTO tox;
                    System.out.println("Ingrese el DNI a Modificar:");
                    String dni=cs.next();
                    tox=po.buscarEntidad(dni);
                    System.out.println("Ingres Nuevo Nombre:");
                    tox.setNombre(cs.next());
                    System.out.println("Ingres Nuevo A. Paterno:");
                    tox.setApellidoPat(cs.next());                    
                    po.update(tox);
                    po.listarPostulantes(po.listarTodo());
                }
                case "D" -> {
                    try {
                        System.out.println("Ingrese el DNI del Registro que desea eliminar:");
                        po.delete(cs.next());
                        po.listarPostulantes(po.listarTodo());
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

    public void listarPostulantes(List<PostulanteTO> lista) {
        System.out.println("DNI\t\tNombre\t\t\tApellidos\t\t\tCarrera Post.");
        for (PostulanteTO p : lista) {
            System.out.println(p.getDni() + "\t" + p.getNombre() + "\t\t\t"
                    + p.getApellidoPat() + " " + p.getApellidoMat() + "\t\t\t" + p.getNombreCarrera());
        }
    }

    @Override
    public List<PostulanteTO> listCmb(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PostulanteTO buscarEntidad(String dni) {
        PostulanteTO cli = new PostulanteTO();
        String sql = "SELECT po.*, p.nombre as nombreperiodo, c.nombrecarrera "
                + "FROM postulante po, periodo p, carrera c "
                + "WHERE p.id_periodo = po.id_periodo and po.id_carrera = c.id_carrera"
                + " and po.dni=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                cli.setDni(rs.getString("dni"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellidoPat(rs.getString("apellido_pat"));
                cli.setApellidoMat(rs.getString("apellido_mat"));
                cli.setModalidad(rs.getString("modalidad"));
                cli.setEstado(rs.getString("estado"));
                cli.setIdCarrera(rs.getInt("id_carrera"));
                cli.setIdPeriodo(rs.getInt("id_periodo"));
                cli.setNombrePeriodo(rs.getString("nombreperiodo"));
                cli.setNombreCarrera(rs.getString("nombrecarrera"));
                //cli.setNombreModalidad(buscarModalidadExamen(rs.getString("modalidad")));               
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cli;
    }

    @Override
    public List<ModeloDataAutocomplet> listAutoComplet(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ComboBoxOption> listaModalidadExamen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ComboBoxOption> listarPeriodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModeloDataAutocomplet> listAutoCompletCarrera(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String buscarModalidadExamen(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
