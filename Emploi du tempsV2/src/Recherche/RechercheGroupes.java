package Recherche;

import Classes_Conteneurs.DAO.DAOFactory;
import Classes_Conteneurs.DAO.GroupeDAO;
import Classes_Conteneurs.Droit;
import Classes_Conteneurs.Utilisateur;
import controller.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RechercheGroupes {
    public static int tailleGroupe(int idGroupe){
        int tailleGroupe=0;
        try {
            String requete = "SELECT * FROM etudiant WHERE ID_GROUPE =?";

            PreparedStatement preparedStatement = DAOFactory.conn.prepareStatement(requete);
            preparedStatement.setInt(1,idGroupe);
            ResultSet resultat=preparedStatement.executeQuery();
            while (resultat.next())
            {
                tailleGroupe++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tailleGroupe;
    }
}
