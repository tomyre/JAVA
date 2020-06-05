package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Seance_GroupeDAO {
    protected Connection connect = null;
    public Seance_GroupeDAO(Connection conn) {
        this.connect = conn;
    }


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
    public ArrayList<Integer> chercherGroupe(int idSeance) {
        return null;
    }
}
