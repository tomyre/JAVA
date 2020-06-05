package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Cours;
import Classes_Conteneurs.Enseignant;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnseignantDAO extends DAO<Enseignant> {
    public EnseignantDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Enseignant enseignant) {
        int idCours= enseignant.getIdCours();
        int idUtilisateur=enseignant.getIdUtilisateur();
        String requete = "INSERT INTO enseignant (ID_COURS) VALUES ("+idCours+")";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enseignant crree", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Enseignant enseignant) {
        int idUtilisateur= enseignant.getIdUtilisateur();
        String requete = "DELETE FROM enseignant WHERE ID_UTILISATEUR="+idUtilisateur;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enseignant supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Enseignant enseignant) {
        int idUtilisateur=enseignant.getIdUtilisateur();
        int idCours=enseignant.getIdCours();
        String requete = "UPDATE enseignant SET ID_COURS="+idCours+" WHERE ID_UTILISATEUR="+idUtilisateur;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification enseignant reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Enseignant chercher(int id) {
        Enseignant enseignant = null;
        try {
            String requete = "SELECT * FROM enseignant WHERE ID_UTILISATEUR = " + id;
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                int idCours=resultat.getInt("ID_COURS");
                enseignant= new Enseignant(id,idCours);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enseignant;
    }
}
