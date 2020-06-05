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
    private ResultSetMetaData rsetMeta;
    private String requete;
    private int ID,res;

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
                    JOptionPane.showMessageDialog(null, "Identifiants ou password incorrects try again", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Identifiants ou password incorrects try again", "Erreur", JOptionPane.ERROR_MESSAGE);

        }
        stmt.close();
        return -1;
   }  


/**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     * @param requete
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsRequete(String requete) throws SQLException {
        
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();

        // tant qu'il reste une ligne 
        while (rset.next()) {
            
            String champs;
            champs = rset.getString(1); // ajouter premier champ
            

            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
            }
            
            // ajouter un "\n" à la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
            
        }
        
        // Retourner l'ArrayList
        return liste;
    }
}