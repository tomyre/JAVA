package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Etat_Seance;
import Classes_Conteneurs.Seance;
import Classes_Conteneurs.Site;
import Classes_Conteneurs.TypeCours;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SiteDAO extends DAO<Site>{

    public SiteDAO(Connection conn) {

        super(conn);
    }

    @Override
    public boolean creer(Site site) {
    String requete = "INSERT INTO site (NOM) VALUES (?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,site.getNom());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Site insere", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Site site)
    {
        String requete = "DELETE FROM site WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,site.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Site supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Site site) {
        String requete = "UPDATE site SET NOM=? WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,site.getNom());
            preparedStatement.setInt(2,site.getId());
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
    public Site chercher(int id) {
        Site site = null;
        try {
            String requete = "SELECT * FROM site WHERE ID = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                site= new Site(id,resultat.getString("NOM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return site;
    }
}
