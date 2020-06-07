package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Salle;
import controller.Connexion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Seances_Salles_Manager {

    public Seances_Salles_Manager() {};

    public static ArrayList<Integer> chercherSeances(int idSalle) {
        ArrayList<Integer> listeSeancesId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_salles WHERE ID_SALLE = ?";
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idSalle);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                listeSeancesId.add(resultat.getInt("ID_SEANCE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeSeancesId;
    }
    public static ArrayList<Integer> chercherSalles(int idSeance) {
        ArrayList<Integer> listeSallesId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_salles WHERE ID_SEANCE = ?";
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idSeance);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                listeSallesId.add(resultat.getInt("ID_SALLE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeSallesId;
    }
    public static boolean creerLiaison(int idSalle, int idSeance) {
        String requete = "INSERT INTO seance_salles (ID_SEANCE, ID_SALLE) VALUES (?,?)";
        try{
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idSeance);
            preparedStatement.setInt(2,idSalle);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "liaison insere", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean supprimerLiaison(int idSalle, int idSeance) {
        String requete = "DELETE FROM seance_salles WHERE ID_SEANCE=? AND ID_SALLE=?";
        try{
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idSeance);
            preparedStatement.setInt(2,idSalle);
            int sortie=preparedStatement.executeUpdate();
            if(sortie==1)
            {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static int capaciteTotaleSeance(int idSeance) {
        ArrayList<Integer> listeIdSalles= Seances_Salles_Manager.chercherSalles(idSeance);
        int capacite=0;
        for(Integer idSalle:listeIdSalles)
        {
            SalleDAO salleDAO= (SalleDAO) DAOFactory.getSalleDAO();
            Salle salle=salleDAO.chercher(idSalle);
            capacite+=salle.getCapacite();
        }
        return capacite;
    }
}
