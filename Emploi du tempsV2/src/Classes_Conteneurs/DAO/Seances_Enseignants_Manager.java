package Classes_Conteneurs.DAO;

import controller.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Seances_Enseignants_Manager {
    protected Connection connect;
    {
        try {
            connect = Connexion.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Seances_Enseignants_Manager() {};

    public ArrayList<Integer> chercherSeances(int idEnseignant) {
        ArrayList<Integer> listeSeancesId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_enseignants WHERE ID_ENSEIGNANT = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,idEnseignant);
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
    public ArrayList<Integer> chercherEnseignants(int idSeance) {
        ArrayList<Integer> listeEnseignantsId=new ArrayList<>();
        try {
            String requete = "SELECT * FROM seance_groupes WHERE ID_SEANCE = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,idSeance);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                listeEnseignantsId.add(resultat.getInt("ID_ENSEIGNANTS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEnseignantsId;
    }
}
