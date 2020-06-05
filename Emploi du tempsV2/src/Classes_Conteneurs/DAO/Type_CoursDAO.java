package Classes_Conteneurs.DAO;

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
        String nomType_Cours=type_cours.getNom();
        String requete = "INSERT INTO type_cours (NOM) VALUES ('"+nomType_Cours+"')";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        int idType_Cours = type_cours.getId();
        String requete = "DELETE FROM type_cours WHERE ID="+idType_Cours;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
        String nomType_CoursMAJ=type_cours.getNom();
        int idType_CoursMAJ=type_cours.getId();
        String requete = "UPDATE type_cours SET NOM='"+nomType_CoursMAJ+"' WHERE ID="+idType_CoursMAJ;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
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
    public TypeCours chercher(int id) {
        return TypeCours.getType(id);
    }
}
