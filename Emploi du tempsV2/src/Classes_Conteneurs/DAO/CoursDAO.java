package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Cours;
import Classes_Conteneurs.Enseignant;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class CoursDAO extends DAO<Cours>{

    public CoursDAO(Connection conn) {

        super(conn);
    }

    @Override
    public boolean creer(Cours cours)
    {
        String requete = "INSERT INTO cours (NOM) VALUES (?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,cours.getNomCours());
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
       String requete = "DELETE FROM cours WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,cours.getIdCours());
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
        String requete = "UPDATE cours SET NOM=? WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,cours.getNomCours());
            preparedStatement.setInt(2,cours.getIdCours());
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
        Cours cours = null;
        try {
            String requete = "SELECT * FROM cours WHERE ID = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
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

    /**
     *
     * @param colonne
     * @param valeur
     * @return
     */
    public ArrayList<Cours> chercher(String colonne, String valeur) {
        ArrayList<Cours> listeCours= new ArrayList<>();
        try {
            String requete = "SELECT * FROM cours WHERE "+ colonne+ "= ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,valeur);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Cours cours= new Cours(resultat.getInt("ID"),resultat.getString("NOM"));
                listeCours.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeCours;
    }

    public ArrayList<Cours> chercher(String colonne, int valeur) {
        ArrayList<Cours> listeCours= new ArrayList<>();
        try {
            String requete = "SELECT * FROM cours WHERE "+ colonne+ "= ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,valeur);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Cours cours= new Cours(resultat.getInt("ID"),resultat.getString("NOM"));
                listeCours.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeCours;
    }
    public ArrayList<Cours> chercherToutLesCours(){
        ArrayList<Cours> listeCours= new ArrayList<>();
        try {
            String requete = "SELECT * FROM cours";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Cours cours= new Cours(resultat.getInt("ID"),resultat.getString("NOM"));
                listeCours.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeCours;
    }
}

