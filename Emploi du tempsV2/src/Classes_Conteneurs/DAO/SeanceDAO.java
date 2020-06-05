package Classes_Conteneurs.DAO;
import Classes_Conteneurs.Etat_Seance;
import Classes_Conteneurs.Seance;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeanceDAO extends DAO<Seance> {
    public SeanceDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Seance seance) {
        String requete = "INSERT INTO seance (SEMAINE, DATE, HEURE_DEBUT, HEURE_FIN, ETAT, ID_COURS, ID_TYPE) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,seance.getSemaine());
            preparedStatement.setDate(2, seance.getDate());
            preparedStatement.setTime(3, seance.getHeureDebut());
            preparedStatement.setTime(4,seance.getHeureFin());
            preparedStatement.setInt(5,seance.getEtat().getIndiceEtat());
            preparedStatement.setInt(6,seance.getCours());
            preparedStatement.setInt(7,seance.getTypeCours());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Seance insere", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Seance seance) {
        String requete = "DELETE FROM seance WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,seance.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Seance supprimée", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Seance seance) {
        String requete="UPDATE seance SET SEMAINE=?,DATE=?,HEURE_DEBUT=?,HEURE_FIN=?,ETAT=?,ID_COURS=?,ID_TYPE=? WHERE ID=?";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,seance.getSemaine());
            preparedStatement.setDate(2, seance.getDate());
            preparedStatement.setTime(3, seance.getHeureDebut());
            preparedStatement.setTime(4,seance.getHeureFin());
            preparedStatement.setInt(5,seance.getEtat().getIndiceEtat());
            preparedStatement.setInt(6,seance.getCours());
            preparedStatement.setInt(7,seance.getTypeCours());
            preparedStatement.setInt(8,seance.getId());
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification de la seance reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Seance chercher(int id) {
        Seance seance = null;
        try {
            String requete = "SELECT * FROM seance WHERE ID = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            preparedStatement.setInt(1,id);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                seance= new Seance(id,resultat.getInt("SEMAINE"),resultat.getDate("DATE"),resultat.getTime("HEURE_DEBUT"),resultat.getTime("HEURE_FIN"),Etat_Seance.getEtat(resultat.getInt("ETAT")),resultat.getInt("ID_COURS"), resultat.getInt("ID_TYPE"));
            }
            // TODO ici remplir les tableaux de la seance

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seance;
    }
}
