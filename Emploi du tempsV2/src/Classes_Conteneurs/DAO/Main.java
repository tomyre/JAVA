package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Salle;
import Classes_Conteneurs.Seance;
import Classes_Conteneurs.TypeCours;
import Classes_Conteneurs.Utilisateur;

public class Main {
    public static void main(String[] args) {
        DAO <TypeCours>seanceDAO = DAOFactory.getTypeCours();
       TypeCours nouveau= seanceDAO.chercher(6);
       System.out.println("Le cours d'id ="+nouveau.getId()+" s'appelle->> "+nouveau.getNom());
    }

}
