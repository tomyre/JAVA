package Classes_Conteneurs.DAO;

import controller.Connexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Seances_Enseignants_Manager {

    public Seances_Enseignants_Manager() {};

    public static ArrayList<Integer> chercherSeances(int idEnseignant) {
        ArrayList<Integer> listeSeancesId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_enseignants WHERE ID_ENSEIGNANT = ?";
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idEnseignant);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                listeSeancesId.add(resultat.getInt("ID_SEANCE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeSeancesId;
    }
    public static ArrayList<Integer> chercherEnseignants(int idSeance) {
        ArrayList<Integer> listeEnseignantsId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_groupes WHERE ID_SEANCE = ?";
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idSeance);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                listeEnseignantsId.add(resultat.getInt("ID_ENSEIGNANTS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEnseignantsId;
    }
    public static boolean creerLiaison(int idEnseigant, int idSeance)    {
        String requete = "INSERT INTO seance_enseignants (ID_SEANCE, ID_ENSEIGNANT) VALUES (?,?)";
        try{
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idSeance);
            preparedStatement.setInt(2,idEnseigant);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "liaison insere2", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean supprimerLiaison(int idEnseigant, int idSeance) {
        String requete = "DELETE FROM seance_enseignants WHERE ID_SEANCE=? AND  ID_ENSEIGNANT=?";
        try{
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idSeance);
            preparedStatement.setInt(2,idEnseigant);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "liaison supprime", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
