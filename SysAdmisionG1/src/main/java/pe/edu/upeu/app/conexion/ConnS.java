package pe.edu.upeu.app.conexion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.upeu.app.util.UtilsX;

public class ConnS {

    private static volatile ConnS instance;
    private static volatile Connection connection;
    UtilsX util = new UtilsX();

    private ConnS() {
        try {
            Class.forName("org.sqlite.JDBC");
            //Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnS.class.getName()).log(Level.SEVERE,
                    null, ex);

        }
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create ");
        }
        if (connection != null) {
            throw new RuntimeException(
                    "Use getConnection() method to create ");

        }
    }

    public static ConnS getInstance() {
        if (instance == null) {
            synchronized (ConnS.class) {
                if (instance == null) {
                    instance = new ConnS();
                    System.out.println("Se instancio ConnS");
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {

        if (connection == null) {
            synchronized (ConnS.class) {
                if (connection == null) {
                    try {
                        //String dbUrl="jdbc:sqlite:data/db_ventas.db?foreign_keys=on;";

                        String dbUrl= "jdbc:sqlite:" + util.getFileExterno("data",
                                        "admision_db.db").getAbsolutePath() + "?foreign_keys=on;";
                        //String dbUrl="jdbc:sqlite:" + getFile("admision_db.db").getAbsolutePath() + "?foreign_keys=on;";

                        connection = DriverManager.getConnection(dbUrl);
                        System.out.println("Conenccion exitosa!!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
    
    /*public static void main(String[] args) {
        ConnS con=getInstance();
        con.getConnection();
    }*/
}
