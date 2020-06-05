package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Cours;
import Classes_Conteneurs.Droit;
import Classes_Conteneurs.Utilisateur;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UtilisateurDAO extends DAO<Utilisateur>{
    public UtilisateurDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Utilisateur utilisateur) {
        String requete = "INSERT INTO utilisateur (EMAIL, PASSWD, NOM, PRENOM, DROIT) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,utilisateur.getMail());
            preparedStatement.setString(2,utilisateur.getMotDePasse());
            preparedStatement.setString(3,utilisateur.getNom());
            preparedStatement.setString(4,utilisateur.getPrenom());
            preparedStatement.setInt(5,utilisateur.getDroit().getDroit());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cours cree", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Utilisateur utilisateur) {
        String requete = "DELETE FROM utilisateur WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,utilisateur.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Utilisateur supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Utilisateur utilisateur) {
        String requete = "UPDATE utilisateur SET EMAIL=?,PASSWD=?,NOM=?,PRENOM=?,DROIT=? WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,utilisateur.getMail());
            preparedStatement.setString(2,utilisateur.getMotDePasse());
            preparedStatement.setString(3,utilisateur.getNom());
            preparedStatement.setString(4,utilisateur.getPrenom());
            preparedStatement.setInt(5,utilisateur.getDroit().getDroit());
            preparedStatement.setInt(6,utilisateur.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification Utilisateur reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Utilisateur chercher(int id) {
        Utilisateur utilisateur = null;
        try {
            String requete = "SELECT * FROM utilisateur WHERE ID =?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                String nom= resultat.getString("NOM");
                String prenom= resultat.getString("PRENOM");
                String mdp=resultat.getString("PASSWD");
                String mail=resultat.getString("EMAIL");
                int droit =resultat.getInt("DROIT");
                utilisateur= new Utilisateur(id,nom,prenom,mail,mdp,Droit.getDroit(droit));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    @Override
    public ArrayList<Utilisateur> chercher(String colonne, String valeur) {
        return null;
    }

    public Utilisateur chercher(String email){
        Utilisateur retour= null;
        try {
            String requete = "SELECT * FROM utilisateur WHERE EMAIL =?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,email);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                String nom= resultat.getString("NOM");
                String prenom= resultat.getString("PRENOM");
                String mdp=resultat.getString("PASSWD");
                String mail=resultat.getString("EMAIL");
                int droit =resultat.getInt("DROIT");
                int id= resultat.getInt("ID");
                retour= new Utilisateur(id,nom,prenom,mail,mdp,Droit.getDroit(droit));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retour;

    }
}
