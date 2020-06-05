package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Etat_Seance;
import Classes_Conteneurs.Seance;
import Classes_Conteneurs.TypeCours;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Type_CoursDAO extends DAO<TypeCours> {
    public Type_CoursDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(TypeCours type_cours) {
        String requete = "INSERT INTO type_cours (NOM) VALUES (?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,type_cours.getNom());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Type_Cours insere", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(TypeCours type_cours) {
        String requete = "DELETE FROM type_cours WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,type_cours.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Type_Cours supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(TypeCours type_cours) {
        String requete = "UPDATE type_cours SET NOM=? WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setString(1,type_cours.getNom());
            preparedStatement.setInt(2,type_cours.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification typecours reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public TypeCours chercher(int id) {
        TypeCours typeCours = null;
        try {
            String requete = "SELECT * FROM seance WHERE ID = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                typeCours= new TypeCours(id,resultat.getString("NOM"));
            }
            // TODO ici remplir les tableaux de la seance

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeCours;
    }
}
