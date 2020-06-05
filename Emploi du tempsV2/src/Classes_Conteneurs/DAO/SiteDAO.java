package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Site;

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
    String nomSite=site.getNom();
    String requete = "INSERT INTO site (NOM) VALUES ('"+nomSite+"')";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        int idSite = site.getId();
        String requete = "DELETE FROM site WHERE ID="+idSite;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        String nomSiteMAJ=site.getNom();
        int idSiteMAJ=site.getId();
        String requete = "UPDATE site SET NOM='"+nomSiteMAJ+"' WHERE ID="+idSiteMAJ;
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
    public Site chercher(int id) {
        return Site.getSite(id);
    }
}
