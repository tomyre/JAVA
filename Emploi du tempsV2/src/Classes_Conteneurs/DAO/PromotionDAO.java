package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Promotion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionDAO extends DAO<Promotion> {
    public PromotionDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Promotion promotion) {
        String nomPromotion=promotion.getNom();
        String requete = "INSERT INTO promotion (NOM) VALUES ('"+nomPromotion+"')";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        int idPromotion = promotion.getId();
        String requete = "DELETE FROM promotion WHERE ID="+idPromotion;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        String nomPromotionMAJ=promotion.getNom();
        int idPromotionMAJ=promotion.getId();
        String requete = "UPDATE promotion SET NOM='"+nomPromotionMAJ+"' WHERE ID="+idPromotionMAJ;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        Promotion promotion = new Promotion();
        try {
            String requete = "SELECT * FROM promotion WHERE ID = " + id;
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
}
