package MAJDonnes;

import Classes_Conteneurs.DAO.DAOFactory;
import Classes_Conteneurs.DAO.SeanceDAO;
import Classes_Conteneurs.DAO.Seances_Salles_Manager;
import Classes_Conteneurs.Seance;

import java.util.ArrayList;

public class MAJSalleSeance {

    public static boolean assigneSalleSeance(int idSalle, int idSeance) {
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seancePretendante = seanceDAO.chercher(idSeance);
        if (seancePretendante == null) {
            return false;
        }
        Seances_Salles_Manager seancesSallesManager = DAOFactory.getSeanceSalleManager();
        ArrayList<Integer> listeSeancesSalle = seancesSallesManager.chercherSeances(idSalle);

        for (Integer idSeanceCourant : listeSeancesSalle) {
            Seance seanceCourante = seanceDAO.chercher(idSeanceCourant);
            if (!VerificationsMAJ.pasDeConflitsEntreSeances(seanceCourante, seancePretendante)) {
                return false;
            }
            //
            if (!VerificationsMAJ.sallePasSaturee(idSalle, idSeance)) {
                return false;
            }
            return seancesSallesManager.creerLiaison(idSalle, idSeance);
        }
        return false;
    }
}
