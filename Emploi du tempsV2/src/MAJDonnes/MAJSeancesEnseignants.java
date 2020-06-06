package MAJDonnes;

import Classes_Conteneurs.DAO.DAOFactory;
import Classes_Conteneurs.DAO.SeanceDAO;
import Classes_Conteneurs.DAO.Seances_Enseignants_Manager;
import Classes_Conteneurs.Seance;

import java.util.ArrayList;

public class MAJSeancesEnseignants {
    public static boolean assigneEnseignantSeance(int idEnseignant,int idSeance)
    {
        SeanceDAO seanceDAO =(SeanceDAO) DAOFactory.getSeanceDAO();
        Seance seancePretendante= seanceDAO.chercher(idSeance);
        if(seancePretendante==null)
        {
            return false;
        }
        Seances_Enseignants_Manager seancesEnseignantsManager=DAOFactory.getSeanceEnseignantManager();
        ArrayList<Integer> listeSeancesEnseigant=seancesEnseignantsManager.chercherSeances(idEnseignant);

        for(Integer idSeanceCourant:listeSeancesEnseigant)
        {
            Seance seanceCourante= seanceDAO.chercher(idSeanceCourant);
            if(!VerificationsMAJ.pasDeConflitsEntreSeances(seanceCourante,seancePretendante)){
                return false;
            }
        }
        // ici on peut assigner
        return seancesEnseignantsManager.creerLiaison(idEnseignant,idSeance);
    }
}
