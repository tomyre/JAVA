package MAJDonnes;

import Classes_Conteneurs.DAO.DAOFactory;
import Classes_Conteneurs.DAO.SeanceDAO;
import Classes_Conteneurs.DAO.Seances_Enseignants_Manager;
import Classes_Conteneurs.DAO.Seances_Groupes_Manager;
import Classes_Conteneurs.Seance;
import java.util.ArrayList;

public class MAJSeancesEnseignants {

    public static boolean assigneEnseignantSeance(int idEnseignant, int idSeance) {
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        if (seance == null) {
            return false;
        }
        Seances_Enseignants_Manager seances_enseignants_manager = new Seances_Enseignants_Manager();
        if (Seances_Enseignants_Manager.chercherEnseignants(idSeance).size() > 0) {
            //TODO dire quil existe deja au moinsun enesignant sur cette seance
            return false;
        }
        return MAJSeancesEnseignants.pasDeConflitsPourEnseignant(idEnseignant,seance) && Seances_Enseignants_Manager.creerLiaison(idEnseignant, idSeance);

    }

    public static boolean assigneEnseignantSeance(int idEnseignant, int idEnseignantARemplacer, int idSeance) {

        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        if (seance == null) {
            return false;
        }
        Seances_Enseignants_Manager seances_enseignants_manager = new Seances_Enseignants_Manager();
        ArrayList<Integer> enseignantsSeance = Seances_Enseignants_Manager.chercherEnseignants(idSeance);
        for (Integer enseignantId : enseignantsSeance) {
            if (enseignantId.equals(idEnseignantARemplacer)) {
                boolean ancienneLiaisonSupprimee = Seances_Enseignants_Manager.supprimerLiaison(idEnseignantARemplacer, idSeance);
                return MAJSeancesEnseignants.pasDeConflitsPourEnseignant(idEnseignant,seance)&& Seances_Enseignants_Manager.creerLiaison(idEnseignant, idSeance) && ancienneLiaisonSupprimee;
            }
        }
        return false;

    }

    public static boolean ajouterEnseignantSeance(int idEnseignant, int idSeance) {
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seancePretendante = seanceDAO.chercher(idSeance);
        if (seancePretendante == null) {
            return false;
        }
        ;
        // ici on peut assigner
        Seances_Enseignants_Manager seances_enseignants_manager= new Seances_Enseignants_Manager();
        return MAJSeancesEnseignants.pasDeConflitsPourEnseignant(idEnseignant, seancePretendante) && Seances_Enseignants_Manager.creerLiaison(idEnseignant, idSeance);
    }

    public static boolean pasDeConflitsPourEnseignant(int idEnseignant, Seance seancePretendante) {
        Seances_Enseignants_Manager seancesEnseignantsManager = DAOFactory.getSeanceEnseignantManager();
        ArrayList<Integer> listeSeancesEnseigant = Seances_Enseignants_Manager.chercherSeances(idEnseignant);
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        for (Integer idSeanceCourant : listeSeancesEnseigant) {
            Seance seanceCourante = seanceDAO.chercher(idSeanceCourant);
            if (!VerificationsMAJ.pasDeConflitsEntreSeances(seanceCourante, seancePretendante)) {
                return false;
            }
        }
        return true;
    }
    public static boolean enleverEnseignantDeSeance(int idEnseignant,int idSeance) {
        ArrayList<Integer> enseignants = Seances_Enseignants_Manager.chercherEnseignants(idSeance);
        for (Integer idEnseignantCourant : enseignants) {
            if (idEnseignant == idEnseignantCourant) {
                return Seances_Enseignants_Manager.supprimerLiaison(idEnseignant, idSeance);
            }
        }
        return false;
    }
}
