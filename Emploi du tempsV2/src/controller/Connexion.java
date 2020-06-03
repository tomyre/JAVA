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

/**
 * 
 * Connexion a votre BDD locale ou à distance sur le serveur de l'ECE via le tunnel SSH
 * 
 * @author tomyre
 */
public class Connexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private final Connection conn;
    private Statement stmt;
    private ResultSet rset = null, res;
    private ResultSetMetaData rsetMeta;
    private String requete,query;



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
       conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
       
       // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
        System.out.println("connexion reussi");
        }

   public int RecupererDonnees(String email, String mdp) throws SQLException{
  
      requete = "SELECT * FROM utilisateur WHERE EMAIL='"+email+"'";
    
        try{
            rset =stmt.executeQuery(requete);
            rset.next();
            if(rset!=null)
            {
                String mdpBD=rset.getString("PASSWD");
                boolean motDepasseValide=(mdpBD.equals(mdp));
                if(motDepasseValide)
                {
                    return rset.getInt("DROIT");
                   
                } 
                else
                {
                    // recommncer loperation de connexion
                    System.out.println("Identifiants incorrects try again");
                }
            }
        } catch (SQLException e){
            System.out.println("Erreur lors de la requete");

        }
        return -1;
        
        /*try{
            rsetMeta=rset.getMetaData();
         
            //int nbCols = rsetMeta.getColumnCount();
            boolean encore= rset.next();
 
            /*while (encore){
                for(int i=1;i <= nbCols;i++)
                    System.out.println(rset.getString(i) + "");
                System.out.println();
                encore=rset.next();
            }
            rset.close();
        }catch(SQLException e){
          System.out.println(e.getMessage()); 
        }*/
    }
}
        
      
