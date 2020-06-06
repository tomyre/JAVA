package Classes_Conteneurs.DAO;

import controller.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Seances_Salles_Manager {
    protected Connection connect;
    {
        try {
            connect = Connexion.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Seances_Salles_Manager() {};

    public ArrayList<Integer> chercherSeances(int idSalle) {
        ArrayList<Integer> listeSeancesId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_salles WHERE ID_SALLE = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
    public ArrayList<Integer> chercherSalles(int idSeance) {
        ArrayList<Integer> listeSallesId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_salles WHERE ID_SEANCE = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
}
