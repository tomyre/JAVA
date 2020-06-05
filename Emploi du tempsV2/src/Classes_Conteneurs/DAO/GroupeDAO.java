package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Cours;
import Classes_Conteneurs.Groupe;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupeDAO extends DAO<Groupe> {
    public GroupeDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Groupe groupe) {
        String nomGroupe= groupe.getNom();
        int idPromotion= groupe.getIdPromotion();
        String requete = "INSERT INTO groupe (NOM, ID_PROMOTION) VALUES ('"+nomGroupe+"',"+idPromotion+")";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        int idGroupe= groupe.getId();
        String requete = "DELETE FROM groupe WHERE ID="+idGroupe;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        String nomGroupe=groupe.getNom();
        int idPromotion=groupe.getIdPromotion();
        int idGroupe=groupe.getId();
        String requete = "UPDATE groupe SET NOM='"+nomGroupe+"', ID_PROMOTION= "+idPromotion+" WHERE ID="+idGroupe;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        Groupe groupe = new Groupe();
        try {
            String requete = "SELECT * FROM groupe WHERE ID = " + id;
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                String nomGroupe=resultat.getString("NOM");
                int idPromotion=resultat.getInt("ID_PROMOTION");
                groupe= new Groupe(id,nomGroupe,idPromotion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupe;
    }
}
