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
    public boolean creer(Classes_Conteneurs.Etudiant etudiant ) {
        int idGroupe=etudiant.getIdGroupe();
        int idUtilisateur=etudiant.getIdUtilisateur();
        int numero=etudiant.getNumeroEtudiant();
        String requete = "INSERT INTO etudiant (ID_UTILISATEUR, NUMERO, ID_GROUPE) VALUES ("+idUtilisateur+","+numero+","+idGroupe+")";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        int idUtilisateur= etudiant.getIdUtilisateur();
        String requete = "DELETE FROM etudiant WHERE ID_UTILISATEUR="+idUtilisateur;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        int idUtilisateur=etudiant.getIdUtilisateur();
        int idGroupe=etudiant.getIdGroupe();
        int numeroEtudiant=etudiant.getNumeroEtudiant();
        String requete = "UPDATE etudiant SET ID_GROUPE ="+idGroupe+",NUMERO="+numeroEtudiant+" WHERE ID_UTILISATEUR="+idUtilisateur;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        Etudiant etudiant = new Etudiant();
        try {
            String requete = "SELECT * FROM etudiant WHERE ID_UTILISATEUR = " + etudiantId;
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                int idGroupe=resultat.getInt("ID_GROUPE");
                int numero=resultat.getInt("NUMERO");
                etudiant= new Etudiant(numero,idGroupe,etudiantId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }
}
