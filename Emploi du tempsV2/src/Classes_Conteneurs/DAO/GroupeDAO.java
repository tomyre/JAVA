package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Cours;
import Classes_Conteneurs.Groupe;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupeDAO extends DAO<Groupe> {
    public GroupeDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Groupe groupe) {
        String requete = "INSERT INTO groupe (NOM, ID_PROMOTION) VALUES (?,?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,groupe.getNom());
            preparedStatement.setInt(2,groupe.getIdPromotion());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Groupe insere", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Groupe groupe) {
        String requete = "DELETE FROM groupe WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,groupe.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Groupe supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Groupe groupe) {
        String requete = "UPDATE groupe SET NOM=?, ID_PROMOTION=? WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,groupe.getNom());
            preparedStatement.setInt(2,groupe.getIdPromotion());
            preparedStatement.setInt(3,groupe.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification Groupe reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Groupe chercher(int id) {
        Groupe groupe = null;
        try {
            String requete = "SELECT * FROM groupe WHERE ID = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                groupe= new Groupe(id,resultat.getString("NOM"),resultat.getInt("ID_PROMOTION"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupe;
    }

    @Override
    public ArrayList<Groupe> chercher(String colonne, String valeur) {
        return null;
    }
}
