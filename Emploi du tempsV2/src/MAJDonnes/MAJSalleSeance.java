package MAJDonnes;

import Classes_Conteneurs.DAO.*;
import Classes_Conteneurs.Salle;
import Classes_Conteneurs.Seance;

import java.util.ArrayList;

public class MAJSalleSeance {

    public static boolean assigneSalleSeance(int idSalle, int idSeance) {
        SalleDAO salleDAO = (SalleDAO) DAOFactory.getSalleDAO();
        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        Salle salle = salleDAO.chercher(idSalle);
        Seance seance = seanceDAO.chercher(idSeance);
        ArrayList<Integer> listeSeancesSalleId = Seances_Salles_Manager.chercherSeances(idSalle);
        for (Integer idSeanceCourante : listeSeancesSalleId) {
            Seance seanceCourante = seanceDAO.chercher(idSeanceCourante);
            if (!VerificationsMAJ.pasDeConflitsEntreSeances(seanceCourante, seance)) {
                return false;
            }
        }
        /// Checker la capacite suffisante
        int capaciteSalle = salle.getCapacite();
        int tailleSeance = Seances_Groupes_Manager.tailleSeance(seance.getId());
        if (tailleSeance > capaciteSalle) {
            return false;
        }
        // faire la liaison
        ArrayList<Integer> listeSalleDeLaSeance=Seances_Salles_Manager.chercherSalles(idSeance);
        for(Integer idSalleCourante:listeSalleDeLaSeance){
            Seances_Salles_Manager.supprimerLiaison(idSalleCourante,idSeance);
        }
        return Seances_Salles_Manager.creerLiaison(idSalle, idSeance);
    }
}
