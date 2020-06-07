package MAJDonnes;

import Classes_Conteneurs.Cours;
import Classes_Conteneurs.DAO.*;
import Classes_Conteneurs.Seance;


public class MAJCours {
    public static boolean modificationTypeCours(int idSeance, int nouveauIdType) {
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        seance.setTypeCours(nouveauIdType);
        return seanceDAO.miseAJour(seance);
    }

    public static boolean modificationNomCours(int idSeance, String nouveauNomCours) {
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        CoursDAO coursDAO = (CoursDAO) DAOFactory.getCoursDAO();
        Cours coursAModifier = coursDAO.chercher(seance.getCours());
        coursAModifier.setNomCours(nouveauNomCours);
        return coursDAO.miseAJour(coursAModifier);
    }
}
