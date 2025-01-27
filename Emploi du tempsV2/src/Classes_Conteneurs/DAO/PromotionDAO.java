package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Cours;
import Classes_Conteneurs.Promotion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PromotionDAO extends DAO<Promotion> {
    public PromotionDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Promotion promotion) {
        String requete = "INSERT INTO promotion (NOM) VALUES (?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,promotion.getNom());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Promotion insere", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Promotion promotion) {
        String requete = "DELETE FROM promotion WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,promotion.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Promotion supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Promotion promotion) {
        String requete = "UPDATE promotion SET NOM=? WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,promotion.getNom());
            preparedStatement.setInt(2,promotion.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Promotion chercher(int id) {
        Promotion promotion = null;
        try {
            String requete = "SELECT * FROM promotion WHERE ID = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                promotion= new Promotion(id,resultat.getString("NOM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotion;
    }

    public ArrayList<Promotion> chercher(String colonne, int valeur) {
        ArrayList<Promotion> listePromotion= new ArrayList<>();
        try {
            String requete = "SELECT * FROM promotion WHERE "+ colonne+ "= ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,valeur);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Promotion promotion= new Promotion(resultat.getInt("ID"),resultat.getString("NOM"));
                listePromotion.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listePromotion;
    }
    public ArrayList<Promotion> chercher(String colonne, String valeur) {
        ArrayList<Promotion> listePromotion= new ArrayList<>();
        try {
            String requete = "SELECT * FROM promotion WHERE "+ colonne+ "= ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,valeur);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Promotion promotion= new Promotion(resultat.getInt("ID"),resultat.getString("NOM"));
                listePromotion.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listePromotion;
    }
    public ArrayList<Promotion> chercherToutesLesPromotions() {
        ArrayList<Promotion> listePromotion= new ArrayList<>();
        try {
            String requete = "SELECT * FROM promotion";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Promotion promotion= new Promotion(resultat.getInt("ID"),resultat.getString("NOM"));
                listePromotion.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listePromotion;
    }
}
