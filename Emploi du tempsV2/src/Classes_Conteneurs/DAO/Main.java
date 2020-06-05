package Classes_Conteneurs.DAO;

import Classes_Conteneurs.Salle;
import Classes_Conteneurs.Seance;
import Classes_Conteneurs.Utilisateur;

public class Main {
    public static void main(String[] args) {
        DAO <Salle>userDao = DAOFactory.getSalleDAO();
       Salle nouveau= userDao.chercher(2);
      System.out.println( "Voici le nom: "+nouveau.getNom());
      System.out.println("Voici la capacite: "+nouveau.getCapacite());
        System.out.println("Voici le site: "+nouveau.getSite());
    }

}
