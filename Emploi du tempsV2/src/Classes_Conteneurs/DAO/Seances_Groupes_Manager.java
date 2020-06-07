package Classes_Conteneurs.DAO;

import controller.Connexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Seances_Groupes_Manager {


    public Seances_Groupes_Manager() {
    }

    ;


    public static ArrayList<Integer> chercherSeances(int idGroupe) {
        ArrayList<Integer> listeSeancesId = new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_groupes WHERE ID_GROUPE = ?";
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1, idGroupe);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                listeSeancesId.add(resultat.getInt("ID_SEANCE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeSeancesId;
    }

    public static ArrayList<Integer> chercherGroupes(int idSeance) {
        ArrayList<Integer> listeGroupesId = new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_groupes WHERE ID_SEANCE = ?";
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1, idSeance);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                listeGroupesId.add(resultat.getInt("ID_GROUPE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeGroupesId;
    }

    public static boolean creerLiaison(int idGroupe, int idSeance) {
        String requete = "INSERT INTO seance_groupes (ID_SEANCE,ID_GROUPE) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1, idSeance);
            preparedStatement.setInt(2, idGroupe);
            int sortie = preparedStatement.executeUpdate();
            if (sortie == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean supprimerLiaison(int idGroupe, int idSeance) {
        String requete = "DELETE FROM seance_groupes WHERE ID_SEANCE=? AND  ID_GROUPE=?";
        try {
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1, idSeance);
            preparedStatement.setInt(2, idGroupe);
            int sortie = preparedStatement.executeUpdate();
            if (sortie == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int tailleGroupe(int idGroupe) {
        String requete = "SELECT *FROM etudiant WHERE ID_GROUPE=?";
        try {
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1, idGroupe);
            ResultSet resultat = preparedStatement.executeQuery();
            int tailleGroupe = 0;
            while (resultat.next()) {
                tailleGroupe++;
            }
            return tailleGroupe;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static int tailleSeance(int idSeance) {
        String requete = "SELECT *FROM seance_groupes WHERE ID_SEANCE=?";
        try {
            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1, idSeance);
            ResultSet resultat = preparedStatement.executeQuery();
            int tailleSeance = 0;
            while (resultat.next()) {
                tailleSeance+=Seances_Groupes_Manager.tailleGroupe(resultat.getInt("ID_GROUPE"));
            }
            return tailleSeance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
