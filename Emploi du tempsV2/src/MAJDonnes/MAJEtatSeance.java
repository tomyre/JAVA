package MAJDonnes;

import Classes_Conteneurs.DAO.DAOFactory;
import Classes_Conteneurs.DAO.SeanceDAO;
import Classes_Conteneurs.DAO.Seances_Enseignants_Manager;
import Classes_Conteneurs.Etat_Seance;
import Classes_Conteneurs.Seance;
import java.util.ArrayList;

public class MAJEtatSeance {

    public static boolean annuleSeance(int idSeance){
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        seance.setEtat( Etat_Seance.ANNULEE);
        return seanceDAO.miseAJour(seance);
    }
    public static boolean valideeSeance(int idSeance) {
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        seance.setEtat( Etat_Seance.VALIDEE);
        return seanceDAO.miseAJour(seance);
    }

}
