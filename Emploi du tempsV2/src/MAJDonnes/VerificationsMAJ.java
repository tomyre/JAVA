package MAJDonnes;

import Classes_Conteneurs.DAO.DAOFactory;
import Classes_Conteneurs.DAO.EtudiantDAO;
import Classes_Conteneurs.DAO.SalleDAO;
import Classes_Conteneurs.DAO.Seances_Groupes_Manager;
import Classes_Conteneurs.Groupe;
import Classes_Conteneurs.Salle;
import Classes_Conteneurs.Seance;
import Recherche.RechercheGroupes;

import java.util.ArrayList;

public class VerificationsMAJ {
    public static boolean pasDeConflitsEntreSeances(Seance seance1, Seance seance2) {
        if(!seance1.getDate().equals(seance2.getDate()))
        {
            return true;
        }
        // les deux seances ont la meme date
        boolean debutSeance1AvantSeance2= seance1.getHeureDebut().before(seance2.getHeureDebut());
        boolean finSeance1AvantSeance2= seance1.getHeureFin().before(seance2.getHeureDebut());
        if(debutSeance1AvantSeance2 && finSeance1AvantSeance2)
        {
            return true;
        }
        boolean debutSeance2AvantSeance1= seance2.getHeureDebut().before(seance1.getHeureDebut());
        boolean finSeance2AvantSeance1= seance2.getHeureFin().before(seance1.getHeureDebut());
        return debutSeance2AvantSeance1  && finSeance2AvantSeance1;
    }
    public static boolean sallePasSaturee(int idSalle,int idSeance,Groupe nouveauGroupe){
        int tailleSeance=0;
        SalleDAO salleDAO= (SalleDAO) DAOFactory.getSalleDAO();
        Salle salle= salleDAO.chercher(idSalle);
        ArrayList<Integer> listeGroupeId=Seances_Groupes_Manager.chercherGroupes(idSeance);
        for(Integer idGroupe:listeGroupeId)
        {
            tailleSeance+= RechercheGroupes.tailleGroupe(idGroupe);
        }
        return tailleSeance+RechercheGroupes.tailleGroupe(nouveauGroupe.getId())<=salle.getCapacite();
    }
    public static boolean sallePasSaturee(int idSalle,int idSeance){
        int tailleSeance=0;
        ArrayList<Integer> listeGroupeId=Seances_Groupes_Manager.chercherGroupes(idSeance);
        SalleDAO salleDAO= (SalleDAO) DAOFactory.getSalleDAO();
        Salle salle= salleDAO.chercher(idSalle);
        for(Integer idGroupe:listeGroupeId)
        {
            tailleSeance+= RechercheGroupes.tailleGroupe(idGroupe);
        }
        return tailleSeance<=salle.getCapacite();
    }
}
