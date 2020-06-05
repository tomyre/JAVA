package Classes_Conteneurs.DAO;

import Classes_Conteneurs.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CoursDAO coursDAO = (CoursDAO) DAOFactory.getCoursDAO();
        ArrayList<Cours> userRecherche= coursDAO.chercher("NOM","Informatique");
        Cours cours=userRecherche.get(0);
        System.out.println("ID: "+cours.getIdCours());
        System.out.println("NOM: "+cours.getNomCours());
    }

}
