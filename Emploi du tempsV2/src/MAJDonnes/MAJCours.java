package MAJDonnes;

import Classes_Conteneurs.DAO.DAOFactory;
import Classes_Conteneurs.DAO.SeanceDAO;
import Classes_Conteneurs.DAO.Seances_Enseignants_Manager;
import Classes_Conteneurs.Seance;

import java.util.ArrayList;

public class MAJCours {
        public static boolean assigneEnseignantSeancMofic(int idEnseignant,int idSeance)
        {
            SeanceDAO seanceDAO =(SeanceDAO) DAOFactory.getSeanceDAO();
            Seance seancePretendante= seanceDAO.chercher(idSeance);
            if(seancePretendante==null)
            {
                return false;
            }
}
