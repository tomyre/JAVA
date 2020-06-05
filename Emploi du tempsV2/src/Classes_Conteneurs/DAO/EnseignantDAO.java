package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Cours;
import Classes_Conteneurs.Enseignant;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnseignantDAO extends DAO<Enseignant> {
    public EnseignantDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Enseignant enseignant) {
        String requete = "INSERT INTO enseignant (ID_UTILISATEUR, ID_COURS) VALUES (?,?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,enseignant.getIdUtilisateur());
            preparedStatement.setInt(2,enseignant.getIdCours());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enseignant creer", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Enseignant enseignant) {
        String requete = "DELETE FROM enseignant WHERE ID_UTILISATEUR=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,enseignant.getIdUtilisateur());
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
        String requete = "UPDATE enseignant SET ID_COURS=? WHERE ID_UTILISATEUR=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,enseignant.getIdCours());
            preparedStatement.setInt(2,enseignant.getIdUtilisateur());
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
            String requete = "SELECT * FROM enseignant WHERE ID_UTILISATEUR = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                enseignant= new Enseignant(id,resultat.getInt("ID_COURS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enseignant;
    }

    @Override
    public ArrayList<Enseignant> chercher(String colonne, String valeur) {
        ArrayList<Enseignant> listeEnsignants= new ArrayList<>();
        try {
            String requete = "SELECT * FROM enseignant WHERE "+ colonne+ "= ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,valeur);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Enseignant enseignant= new Enseignant(resultat.getInt("ID_UTILISATEUR"),resultat.getInt("ID_COURS"));
                listeEnsignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEnsignants;
    }
    public ArrayList<Enseignant> chercher(String colonne, int valeur) {
        ArrayList<Enseignant> listeEnsignants= new ArrayList<>();
        try {
            String requete = "SELECT * FROM enseignant WHERE "+ colonne+ "= ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,valeur);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Enseignant enseignant= new Enseignant(resultat.getInt("ID_UTILISATEUR"),resultat.getInt("ID_COURS"));
                listeEnsignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEnsignants;
    }
}
