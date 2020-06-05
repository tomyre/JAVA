package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Salle;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalleDAO extends DAO<Salle>{
    public SalleDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Salle salle) {
        String nomSalle= salle.getNom();
        int capacite=salle.getCapacite();
        int id_site=salle.getSite();
        String requete = "INSERT INTO salle (NOM, CAPACITE, ID_SITE) VALUES ("+nomSalle+","+capacite+","+id_site+")";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Salle insere", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Salle salle) {
        int idSalle= salle.getId();
        String requete = "DELETE FROM salle WHERE ID="+idSalle;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Salle supprimée", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Salle salle) {
        String nomSalle=salle.getNom();
        int capacite=salle.getCapacite();
        int idSite=salle.getSite();
        int idSalle=salle.getId();
        String requete = "UPDATE salle SET NOM='"+nomSalle+"', CAPACITE="+capacite+", ID_SITE="+idSite+" WHERE ID="+idSalle;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification Salle reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Salle chercher(int id) {
        Salle salle = new Salle();
        try {
            String requete = "SELECT * FROM salle WHERE ID = " + id;
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                int capacite=resultat.getInt("CAPACITE");
                String nom=resultat.getString("NOM");
                int idSite=resultat.getInt("ID_SITE");
                salle= new Salle(id,capacite,nom,idSite);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salle;
    }
}



