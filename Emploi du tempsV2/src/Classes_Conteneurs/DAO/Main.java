package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Salle;
import Classes_Conteneurs.Seance;
import Classes_Conteneurs.Utilisateur;

public class Main {
    public static void main(String[] args) {
        DAO <Seance>seanceDAO = DAOFactory.getSeanceDAO();
       Seance nouveau= seanceDAO.chercher(5);
      System.out.println( "Voici la date: "+nouveau.getDate());
      System.out.println("Voici le debut: "+nouveau.getHeureDebut());
        System.out.println("Voici la fin: "+nouveau.getHeureFin());
    }

}
