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
    private final Statement stmt;
    private ResultSet rset, rset2, rset3, rset4, rset5;
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
    
    public String[] AfficherCours() {
    
        requete = "SELECT NOM FROM cours";
        String tab[] = new String[9];

        try {
            rset = stmt.executeQuery(requete);
        } catch (SQLException e) {
            System.out.println("erreur 1");
        }

        //parcours des données retournées
        try {
            ResultSetMetaData rsmd = rset.getMetaData();
            int nbCols = rsmd.getColumnCount();
            boolean encore = rset.next();

            while (encore) {

                for (int i = 1; i <= nbCols; i++) {
                    for (int j=0; j<9; j++) {
                        tab[j] = (rset.getString(i) + " ");
                        encore = rset.next();
                    }
                }
            }
    
        } catch (SQLException e) {
            System.out.println("erreur 2");
        }
      
        return tab;
    }
   
/**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     * @param requete
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList RemplirChampsRequete(String requete) throws SQLException {
        
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        System.out.println(rsetMeta);
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
                  
    public ArrayList RemplirChampsRequeteCours(String requete) throws SQLException {
        
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);
        rset.next();
        String ID =rset.getString("ID");
        
        query= "SELECT SEMAINE, DATE, HEURE_DEBUT, HEURE_FIN, ETAT, ID_COURS, ID_TYPE FROM seance WHERE ID_COURS ='"+ID+"'";
        
        rset2=stmt.executeQuery(query);

        // récupération du résultat de l'ordre
        rsetMeta = rset2.getMetaData();
       
        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();

        // tant qu'il reste une ligne 
        while (rset2.next()) {
            
            String champs;
            champs = rset2.getString(1); // ajouter premier champ
            

            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset2.getString(i + 1);
            }
            
            // ajouter un "\n" à la ligne des champs
            champs = champs + "\n";
            
            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
            
        }
        
        // Retourner l'ArrayList
        return liste;
    }
    
    public ArrayList RemplirChampsRequeteInfos1(String requete1) throws SQLException {
        
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete1);
        rset.next();
        String ID_UTILISATEUR =rset.getString("ID");
        
        requete2 = "SELECT ID_GROUPE FROM etudiant WHERE ID_UTILISATEUR ='" + ID_UTILISATEUR +"'";
        rset2 = stmt.executeQuery(requete2);
        requete3 = "SELECT ID_SEANCE FROM seance_groupes";
        while(rset2.next()) //boucle qui récupère tout les groupes d'une même promo
        {
            int idGroupeCourant=rset2.getInt("ID_GROUPE");
            String nouveauMorceauRequete=rset2.isFirst()?" WHERE ID_GROUPE = "+idGroupeCourant:" OR ID_GROUPE="+idGroupeCourant;
            requete3+=nouveauMorceauRequete;
        }
        rset3=stmt.executeQuery(requete3);
        requete4 = "SELECT SEMAINE, DATE, HEURE_DEBUT, HEURE_FIN, ETAT, ID_COURS, ID_TYPE FROM seance";
        while(rset3.next()) //boucle qui récupère toutes les séances des différents groupes de la même promo 
        {
            int idSeanceCourante=rset3.getInt("ID_SEANCE");
            String nouveauMorceauRequete=rset3.isFirst()?" WHERE ID = "+idSeanceCourante:" OR ID="+idSeanceCourante;
            requete4+=nouveauMorceauRequete;
           
        }
        rset4=stmt.executeQuery(requete4);
        rsetMeta=rset4.getMetaData();
         
        //calcul du nombre de colonnes du résultats
        int nbColonne= rsetMeta.getColumnCount();
         
        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        
        while(rset4.next()) //affichage tant qu'il reste un ligne à afficher
        {
            String champs="";
            if(rset4.isFirst())
            {
              
                for (int i = 1; i <= nbColonne; i++) {
                     champs += rsetMeta.getColumnName(i)+", ";
                }
              
                champs+="/n";
                liste.add(champs);
            }
            
            champs=""; // ajouter premier champ
            
            for (int i = 1; i <= nbColonne; i++) {
                champs += rset4.getString(i)+", ";
            }
            
            champs+="/n";
            liste.add(champs);
        }
           
        return liste;
    }
    
    /*
    public ArrayList RemplirChampsRequeteInfos2(String requete1) throws SQLException {
        
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete1);
        rset.next();
        String ID_ENSEIGNANT =rset.getString("ID");
        
        requete2 = "SELECT ID_ENSEIGNANT FROM seance_enseignants WHERE ID_UTILISATEUR ='" + ID_ENSEIGNANT +"'";
        rset2 = stmt.executeQuery(requete2);
        requete3 = "SELECT ID_SEANCE FROM seance_enseignants";
        while(rset2.next()) //boucle qui récupère tout les groupes d'une même promo
        {
            int idEnseignantCourant=rset2.getInt("ID_ENSEIGNANT");
            String nouveauMorceauRequete=rset2.isFirst()?" WHERE ID_ENSEIGNANT = "+idEnseignantCourant:" OR ID_GROUPE="+idEnseignantCourant;
            requete3+=nouveauMorceauRequete;
        }
        rset3=stmt.executeQuery(requete3);
        requete4 = "SELECT * FROM seance";
        while(rset3.next()) //boucle qui récupère toutes les séances des différents groupes de la même promo 
        {
            int idSeanceCourante=rset3.getInt("ID_SEANCE");
            String nouveauMorceauRequete=rset3.isFirst()?" WHERE ID = "+idSeanceCourante:" OR ID="+idSeanceCourante;
            requete4+=nouveauMorceauRequete;
           
        }
        rset4=stmt.executeQuery(requete4);
        rsetMeta=rset4.getMetaData();
         
        //calcul du nombre de colonnes du résultats
        int nbColonne= rsetMeta.getColumnCount();
         
        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        
        while(rset4.next()) //affichage tant qu'il reste un ligne à afficher
        {
            String champs="";
            if(rset4.isFirst())
            {
              
                for (int i = 1; i <= nbColonne; i++) {
                     champs += rsetMeta.getColumnName(i)+", ";
                }
              
                champs+="/n";
                liste.add(champs);
            }
            
            champs=""; // ajouter premier champ
            
            for (int i = 1; i <= nbColonne; i++) {
                champs += rset4.getString(i)+", ";
            }
            
            champs+="/n";
            liste.add(champs);
        }
           
        return liste;
    }
    */

    public ArrayList RemplirChampsRequeteInfos3(String requete1) throws SQLException {
        
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete1);
        rset.next();
        String ID_PROMO =rset.getString("ID");
        
        requete2 = "SELECT ID FROM groupe WHERE ID_PROMOTION ='" + ID_PROMO +"'";
        rset2 = stmt.executeQuery(requete2);
        requete3 = "SELECT ID_SEANCE FROM seance_groupes";
        while(rset2.next()) //boucle qui récupère tout les groupes d'une même promo
        {
            int idGroupeCourant=rset2.getInt("ID");
            String nouveauMorceauRequete=rset2.isFirst()?" WHERE ID_GROUPE="+idGroupeCourant:" OR ID_GROUPE="+idGroupeCourant;
            requete3+=nouveauMorceauRequete;
        }
        rset3=stmt.executeQuery(requete3);
        requete4 = "SELECT SEMAINE, DATE, HEURE_DEBUT, HEURE_FIN, ETAT, ID_COURS, ID_TYPE FROM seance";
        while(rset3.next()) //boucle qui récupère toutes les séances des différents groupes de la même promo 
        {
            int idSeanceCourante=rset3.getInt("ID_SEANCE");
            String nouveauMorceauRequete=rset3.isFirst()?" WHERE ID="+idSeanceCourante:" OR ID="+idSeanceCourante;
            requete4+=nouveauMorceauRequete;
           
        }
        rset4=stmt.executeQuery(requete4);
        rsetMeta=rset4.getMetaData();
         
        //calcul du nombre de colonnes du résultats
        int nbColonne= rsetMeta.getColumnCount();
         
        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        
        while(rset4.next()) //affichage tant qu'il reste un ligne à afficher
        {
            String champs="";
            if(rset4.isFirst())
            {
              
                for (int i = 1; i <= nbColonne; i++) {
                     champs += rsetMeta.getColumnName(i)+", ";
                }
              
                champs+="/n";
                liste.add(champs);
            }
            
            champs=""; // ajouter premier champ
            
            for (int i = 1; i <= nbColonne; i++) {
                champs += rset4.getString(i)+", ";
            }
            
            champs+="/n";
            liste.add(champs);
        }
           
        return liste;
    }
    
    public ArrayList RemplirChampsRequeteInfos4(String requete1) throws SQLException {
        
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete1);
        rset.next();
        String ID = rset.getString("ID");
        
        requete2 = "SELECT ID_SEANCE FROM seance_groupes WHERE ID_GROUPE = '" + ID + "'";
        rset2 = stmt.executeQuery(requete2);
        requete3 = "SELECT SEMAINE, DATE, HEURE_DEBUT, HEURE_FIN, ETAT, ID_COURS, ID_TYPE FROM seance";
        while(rset2.next()) //boucle qui récupère toutes les séances d'un même groupe
        {
            int idSeanceCourante = rset2.getInt("ID_SEANCE");
            String nouveauMorceauRequete = rset2.isFirst()?" WHERE ID = " +idSeanceCourante:" OR ID = " + idSeanceCourante;
            requete3+=nouveauMorceauRequete;
        }
        rset3 = stmt.executeQuery(requete3);
        rsetMeta = rset3.getMetaData();
        
        //calcul le nb de colonne du résultat
        int nbColonne = rsetMeta.getColumnCount();
        
        //création d'une Arraylist de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        
        while (rset3.next()) //remplissage de la liste tant qu'il reste une ligne
        {
            String champs = "";
            if(rset3.isFirst())
            {
                for (int i=1; i<= nbColonne; i++) {
                    champs += rsetMeta.getColumnName(i)+ ", ";
                }
                champs+="/n";
                liste.add(champs);
            }
            champs=""; 
            
            for (int i = 1; i <= nbColonne; i++) {
                     champs += rset3.getString(i)+", ";
                }
            champs+="/n";
            liste.add(champs);
        }
        
        return liste;
    }

}