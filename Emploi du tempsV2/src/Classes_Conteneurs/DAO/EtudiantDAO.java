package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Enseignant;
import Classes_Conteneurs.Etudiant;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EtudiantDAO extends DAO<Classes_Conteneurs.Etudiant>{
    public EtudiantDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Classes_Conteneurs.Etudiant etudiant) {
        String requete = "INSERT INTO etudiant (ID_UTILISATEUR, NUMERO, ID_GROUPE) VALUES (?,?,?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,etudiant.getIdUtilisateur());
            preparedStatement.setInt(2,etudiant.getNumeroEtudiant());
            preparedStatement.setInt(3,etudiant.getIdGroupe());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Etudiant cree", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Classes_Conteneurs.Etudiant  etudiant) {
        String requete = "DELETE FROM etudiant WHERE ID_UTILISATEUR=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,etudiant.getIdUtilisateur());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Etudiant supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Classes_Conteneurs.Etudiant  etudiant) {
        String requete = "UPDATE etudiant SET ID_GROUPE =?,NUMERO=? WHERE ID_UTILISATEUR=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,etudiant.getIdGroupe());
            preparedStatement.setInt(2,etudiant.getNumeroEtudiant());
            preparedStatement.setInt(3,etudiant.getIdUtilisateur());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification etudiant reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Etudiant chercher(int etudiantId) {
        Etudiant etudiant = null;
        try {
            String requete = "SELECT * FROM etudiant WHERE ID_UTILISATEUR = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,etudiantId);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                etudiant= new Etudiant(resultat.getInt("NUMERO"),resultat.getInt("ID_GROUPE"),etudiantId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }
}
