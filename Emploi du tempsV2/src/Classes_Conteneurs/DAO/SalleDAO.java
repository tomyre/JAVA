package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Promotion;
import Classes_Conteneurs.Salle;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalleDAO extends DAO<Salle>{
    public SalleDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Salle salle) {
        String requete = "INSERT INTO salle (NOM, CAPACITE, ID_SITE) VALUES (?,?,?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,salle.getNom());
            preparedStatement.setInt(2,salle.getCapacite());
            preparedStatement.setInt(3,salle.getSite());
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
        String requete = "DELETE FROM salle WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,salle.getId());
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
        String requete = "UPDATE salle SET NOM=?,CAPACITE=?,ID_SITE=? WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,salle.getNom());
            preparedStatement.setInt(1,salle.getCapacite());
            preparedStatement.setInt(1,salle.getSite());
            preparedStatement.setInt(1,salle.getId());
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
        Salle salle = null;
        try {
            String requete = "SELECT * FROM salle WHERE ID =?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                salle= new Salle(id,resultat.getInt("CAPACITE"),resultat.getString("NOM"),resultat.getInt("ID_SITE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salle;
    }

    public ArrayList<Salle> chercher(String colonne, String valeur) {
        ArrayList<Salle> listeSalle= new ArrayList<>();
        try {
            String requete = "SELECT * FROM salle WHERE "+ colonne+ "= ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,valeur);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Salle salle= new Salle(resultat.getInt("ID"),resultat.getInt("CAPACITE"),resultat.getString("CAPACITE"),resultat.getInt("ID_SITE"));
                listeSalle.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeSalle;
    }

    public ArrayList<Salle> chercher(String colonne, int valeur) {
        ArrayList<Salle> listeSalle= new ArrayList<>();
        try {
            String requete = "SELECT * FROM salle WHERE "+ colonne+ "= ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,valeur);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Salle salle= new Salle(resultat.getInt("ID"),resultat.getInt("CAPACITE"),resultat.getString("CAPACITE"),resultat.getInt("ID_SITE"));
                listeSalle.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeSalle;
    }

    public ArrayList<Salle> chercherToutesLesSalles() {
        ArrayList<Salle> listeSalle= new ArrayList<>();
        try {
            String requete = "SELECT * FROM salle";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                Salle salle= new Salle(resultat.getInt("ID"),resultat.getInt("CAPACITE"),resultat.getString("NOM"),resultat.getInt("ID_SITE"));
                listeSalle.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeSalle;
    }
}



