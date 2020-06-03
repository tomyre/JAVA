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
    private final Statement stmt;
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
        return -1;
   }  

    public int RecupererID(String email, String mdp) throws SQLException{
  
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
                    return rset.getInt("ID");
                     
                } 
                else
                {
                    
                    System.out.println("erreur"); //JOptionPane.showMessageDialog(null, "Identifiants ou password incorrects try again", "Erreur", JOptionPane.ERROR_MESSAGE);
                    //System.out.println("Identifiants ou password incorrects try again");
                }
            }
        } catch (SQLException e){
            System.out.println("erreur");//JOptionPane.showMessageDialog(null, "Identifiants ou password incorrects try again", "Erreur", JOptionPane.ERROR_MESSAGE);

        }
        return -1;
        
        
    }
    
    public void OperationMofidication(String table,String colonne,String ID, String modif){
              
        requete = "UPDATE "+table+" SET "+colonne+" = '"+modif+"' WHERE ID = '"+ID+"'";
               
        try{
            res =stmt.executeUpdate(requete);
            if(res==1)
            {
                JOptionPane.showMessageDialog(null, "Informations modifié", "Info", JOptionPane.INFORMATION_MESSAGE);
            } 
            else
            {
                JOptionPane.showMessageDialog(null, "Erreur  dans les saisies veuillez vous assurer que vous ne vous etes pas trompé", "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Erreur  dans les saisies veuillez vous assurer que vous ne vous etes pas trompé", "ERREUR", JOptionPane.ERROR_MESSAGE);
        }          
   }
    
    
   public void OperationSuppression(String table,String ID){
       
       requete = "DELETE FROM "+table+" WHERE 'ID' = '"+ID+"'"; 
       System.out.println(requete);
        
        try{
            PreparedStatement pst = conn.prepareStatement(requete); 
            int res = pst.executeUpdate();
            //res = stmt.executeUpdate(requete);
            if(res!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+res, "ERREUR", JOptionPane.ERROR_MESSAGE);
            } 
            else
            {
                JOptionPane.showMessageDialog(null, "Site supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Erreur 1 probleme de syntaxe dans la requete SQL", "ERREUR", JOptionPane.ERROR_MESSAGE);
        }          
   }       

    public void OperationInsertionCours(String table,String NomCours){
        requete = "INSERT INTO "+table+"(NOM) VALUES ('"+ID+"')";
        
        try{
            res=stmt.executeUpdate(requete);
            if(res==1)
            {
                JOptionPane.showMessageDialog(null, "Le cours à éte ajouté", "Info", JOptionPane.INFORMATION_MESSAGE);
            } 
            else
            {
                JOptionPane.showMessageDialog(null, "Erreur 2 dans les saisies veuillez vous assurer que vous ne vous etes pas trompé", "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Erreur 1 dans les saisies veuillez vous assurer que vous ne vous etes pas trompé", "ERREUR", JOptionPane.ERROR_MESSAGE);
        }          
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