package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Cours;
import javax.swing.*;
import java.sql.*;

public class CoursDAO extends DAO<Cours>{

    public CoursDAO(Connection conn) {

        super(conn);
    }

    @Override
    public boolean creer(Cours cours)
    {
        String nomCours= cours.getNomCours();
        String requete = "INSERT INTO cours (NOM) VALUES ('"+nomCours+"')";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cours insere", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Cours cours) {
        int idCours= cours.getIdCours();
       String requete = "DELETE FROM cours WHERE ID="+idCours;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cours supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Cours cours) {
        String nomCoursMAJ=cours.getNomCours();
        int idCoursMAJ=cours.getIdCours();
        String requete = "UPDATE cours SET NOM='"+nomCoursMAJ+"' WHERE ID="+idCoursMAJ;
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
    public Cours chercher(int id) {
        Cours cours = new Cours();
        try {
            String requete = "SELECT * FROM cours WHERE ID = " + id;
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                cours= new Cours(id,resultat.getString("NOM"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cours;
    }
}

