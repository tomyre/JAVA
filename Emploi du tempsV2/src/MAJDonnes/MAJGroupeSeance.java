package MAJDonnes;

import Classes_Conteneurs.DAO.*;
import Classes_Conteneurs.Enseignant;
import Classes_Conteneurs.Groupe;
import Classes_Conteneurs.Seance;

import java.util.ArrayList;

public class MAJGroupeSeance {

    public static boolean assigneGroupeSeance(int idGroupe, int idSeance) {
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        if (seance == null) {
            return false;
        }
        if (Seances_Groupes_Manager.chercherGroupes(idSeance).size() > 0) {
            //TODO dire quil existe deja au moins un groupe sur cette seance
            return false;
        }
        return Seances_Groupes_Manager.creerLiaison(idGroupe, idSeance);
    }

    public static boolean assigneGroupeSeance(int idGroupe, int idGroupeARemplacer, int idSeance) {

        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        if (seance == null) {
            return false;
        }
        ArrayList<Integer> groupesSeanceId = Seances_Groupes_Manager.chercherGroupes(idSeance);
        for (Integer groupeId : groupesSeanceId) {
            if (groupeId.equals(idGroupeARemplacer)) {
                boolean ancienneLiaisonSupprimee = Seances_Groupes_Manager.supprimerLiaison(idGroupeARemplacer, idSeance);
                return MAJGroupeSeance.pasDeConflitsPourGroupe(idGroupe, seance) && Seances_Groupes_Manager.creerLiaison(idGroupe, idSeance) && ancienneLiaisonSupprimee;
            }
        }
        return false;

    }

    public static boolean ajouterGroupeSeance(int idGroupe, int idSeance) {
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seancePretendante = seanceDAO.chercher(idSeance);
        if (seancePretendante == null) {
            return false;
        }
        // ici on peut assigner
        return MAJGroupeSeance.pasDeConflitsPourGroupe(idGroupe, seancePretendante) && MAJGroupeSeance.capaciteSallesSeancePasSaturee(idGroupe,seancePretendante) && Seances_Groupes_Manager.creerLiaison(idGroupe, idSeance);
    }


    /**
     * Methode static permettant de verifier si le groupe d'id idGroupe peut participer a seancePretandante
     *
     * @param idGroupe
     * @param seancePretendante
     * @return
     */
    public static boolean pasDeConflitsPourGroupe(int idGroupe, Seance seancePretendante) {
        Seances_Groupes_Manager seances_groupes_manager = DAOFactory.getSeanceGroupeManager();
        ArrayList<Integer> listeSeancesGroupe = Seances_Groupes_Manager.chercherSeances(idGroupe);
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        for (Integer idSeanceCourant : listeSeancesGroupe) {
            Seance seanceCourante = seanceDAO.chercher(idSeanceCourant);
            if (!VerificationsMAJ.pasDeConflitsEntreSeances(seanceCourante, seancePretendante)) {
                return false;
            }
        }
        return true;
    }

    public static boolean capaciteSallesSeancePasSaturee(int idNouveauGroupe, Seance seance) {
        if (MAJGroupeSeance.groupeAppartientASeance(idNouveauGroupe, seance)) {
            return false;
        }
        int tailleNouveauGroupe = Seances_Groupes_Manager.tailleGroupe(idNouveauGroupe);
        int capaciteTotaleSeance = Seances_Salles_Manager.capaciteTotaleSeance(seance.getId());
        int tailleSeanceActuelle = Seances_Groupes_Manager.tailleSeance(seance.getId());
        return capaciteTotaleSeance >= tailleSeanceActuelle + tailleNouveauGroupe;
    }

    public static boolean groupeAppartientASeance(int idGroupe, Seance seance) {
        ArrayList<Integer> groupes = Seances_Groupes_Manager.chercherGroupes(seance.getId());
        for (Integer idGroupeCourant : groupes) {
            if (idGroupe == idGroupeCourant) {
                return true;
            }
        }
        return false;

    }

    public static boolean enleverGroupeSeance (int idGroupe, int idSeance){
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seance = seanceDAO.chercher(idSeance);
        GroupeDAO groupeDAO = (GroupeDAO) DAOFactory.getGroupeDAO();
//        Groupe groupeAEnlever= groupeDAO.chercher(seance.getListeGroupes());
//        groupeAEnlever.s
return false;


    }


}
