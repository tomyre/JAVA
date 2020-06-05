package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Cours;
import Classes_Conteneurs.Droit;
import Classes_Conteneurs.Utilisateur;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurDAO extends DAO<Utilisateur>{
    public UtilisateurDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Utilisateur utilisateur) {
        String nomUtilisateur= utilisateur.getNom();
        String email=utilisateur.getMail();
        String mdp=utilisateur.getMotDePasse();
        String nom=utilisateur.getNom();
        String prenom= utilisateur.getPrenom();
        Droit droit= utilisateur.getDroit();
        String requete = "INSERT INTO utilisateur (EMAIL, PASSWD, NOM, PRENOM, DROIT) VALUES ('"+email+"','"+mdp+"','"+nom+"','"+prenom+"',"+droit.getDroit()+")";
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cours cree", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimer(Utilisateur utilisateur) {
        int idUtilisateur= utilisateur.getId();
        String requete = "DELETE FROM utilisateur WHERE ID="+idUtilisateur;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Utilisateur supprimé", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean miseAJour(Utilisateur utilisateur) {
        String email=utilisateur.getMail();
        int idUtilisateur=utilisateur.getId();
        String mdp=utilisateur.getMotDePasse();
        String nom=utilisateur.getNom();
        String prenom=utilisateur.getPrenom();
        int droit=utilisateur.getDroit().getDroit();
        String requete = "UPDATE utilisateur SET EMAIL='"+email+"', PASSWD="+mdp+", NOM= "+nom+", PRENOM= "+prenom+", DROIT="+droit+" WHERE ID="+idUtilisateur;
        try{
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            int sortie=preparedStatement.executeUpdate();
            if(sortie!=1)
            {
                JOptionPane.showMessageDialog(null, "Erreur 2: valeur retour: "+sortie, "ERREUR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Modification Utilisateur reussie", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Utilisateur chercher(int id) {
        Utilisateur utilisateur = new Utilisateur();
        try {
            String requete = "SELECT * FROM utilisateur WHERE ID = " + id;
            PreparedStatement preparedStatement = connect.prepareStatement(requete);
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.first())
            {
                String nom= resultat.getString("NOM");
                String prenom= resultat.getString("PRENOM");
                String mdp=resultat.getString("PASSWD");
                String mail=resultat.getString("EMAIL");
                int droit =resultat.getInt("DROIT");
                utilisateur= new Utilisateur(id,nom,prenom,mail,mdp,Droit.getDroit(droit));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
}
