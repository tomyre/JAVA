package Classes_Conteneurs.DAO;
import controller.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Seances_Groupes_Manager {
    protected Connection connect;
    {
        try {
            connect = Connexion.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Seances_Groupes_Manager() {};


    public ArrayList<Integer> chercherSeances(int idGroupe) {
        ArrayList<Integer> listeSeancesId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_groupes WHERE ID_GROUPE = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,idGroupe);
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
    public ArrayList<Integer> chercherGroupes(int idSeance) {
        ArrayList<Integer> listeGroupesId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_groupes WHERE ID_SEANCE = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,idSeance);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                listeGroupesId.add(resultat.getInt("ID_GROUPE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeGroupesId;
    }
}
