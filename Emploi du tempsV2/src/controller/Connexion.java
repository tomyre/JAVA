/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/*
 * 
 * Librairies importées
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
/**
 * 
 * Connexion a votre BDD locale ou à distance sur le serveur de l'ECE via le tunnel SSH
 * 
 * @author tomyre
 */
public class Connexion {

    private final Connection conn;
    private Statement stmt;
    private ResultSet rset = null;
    private ResultSet  rset2, rset3, rset4, rset5;
    private ResultSetMetaData rsetMeta;
    private String requete,requete2, requete3, requete4, requete5, query;
    private int ID,res;
    public ComboBoxModel AfficherCours;

    /**
     * Constructeur avec 3 paramètres : nom, login et password de la BDD locale
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        // chargement driver "com.mysql.jdbc.Driver"
       Class.forName("com.mysql.jdbc.Driver");

       String urlDatabase = "jdbc:mysql://localhost/" + nameDatabase;

       //création d'une connexion JDBC à la base
       conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);;
       // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
        //System.out.println("connexion reussi");
        }
        public static Connection getInstance() throws ClassNotFoundException {
            // chargement driver "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver");
            String urlDatabase = "jdbc:mysql://localhost/bddjava";
            //création d'une connexion JDBC à la base
            try {
                return DriverManager.getConnection(urlDatabase, "Adam", "Adam");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
        }
}