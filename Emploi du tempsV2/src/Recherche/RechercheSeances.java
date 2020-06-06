package Recherche;

import Classes_Conteneurs.*;
import Classes_Conteneurs.DAO.*;

import java.util.ArrayList;

public class RechercheSeances {
    public static ArrayList<Seance> rechercherSeancesEtudiant(int numeroEtudiant, int semaine) {
        EtudiantDAO etudiantDAO = (EtudiantDAO) DAOFactory.getEtudiantDAO();
        ArrayList<Etudiant> etudiantListe = etudiantDAO.chercher("NUMERO", numeroEtudiant);
        if (etudiantListe.isEmpty()) {
            return null;
        }
        Etudiant etudiantRecherche = etudiantListe.get(0);

        // chercher le groupe de l'etudiant
        int idGroupeEtudiant = etudiantRecherche.getIdGroupe();

        //Chercher seances correspondantes au groupe
        Seances_Groupes_Manager seance_groupesManager = DAOFactory.getSeanceGroupeManager();
        ArrayList<Integer> listeIdSeances = seance_groupesManager.chercherSeances(idGroupeEtudiant);

        SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
        ArrayList<Seance> listesSeances = new ArrayList<>();
        for (Integer listeIdSeance : listeIdSeances) {
            Seance seanceCourante = seanceDAO.chercher(listeIdSeance);
            boolean seanceAppartientALaSemaine = seanceCourante.getSemaine() == semaine;
            if (seanceAppartientALaSemaine) {
                listesSeances.add(seanceCourante);
            }
        }
        return listesSeances;
    }


    public static ArrayList<Seance> rechercherSeancesEnseignant(int idUtilisateur, int semaine) {
        EnseignantDAO enseignantDAO = (EnseignantDAO) DAOFactory.getEnseignantDAO();
        ArrayList<Enseignant> enseignantListe= enseignantDAO.chercher("ID_UTILISATEUR",idUtilisateur);
        if(enseignantListe.isEmpty())
        {
            return null;
        }
        Enseignant enseignantRecherche=enseignantListe.get(0);

        // chercher la séance de l'enseignant
        int idEnseignant=enseignantRecherche.getIdUtilisateur();

        //Chercher seances correspondantes a l'enseignant
        Seances_Enseignants_Manager seanceEnseignantManager = DAOFactory.getSeanceEnseignantManager();
        ArrayList<Integer> listeIdSeances= seanceEnseignantManager.chercherSeances(idEnseignant);

        SeanceDAO seanceDAO=(SeanceDAO) DAOFactory.getSeanceDAO();
        int taille=listeIdSeances.size();
        ArrayList<Seance> listesSeances= new ArrayList<>();
        for (Integer listeIdSeance : listeIdSeances) {
            Seance seanceCourante = seanceDAO.chercher(listeIdSeance);
            boolean seanceAppartientASemaine = seanceCourante.getSemaine() == semaine;
            if (seanceAppartientASemaine) {
                listesSeances.add(seanceCourante);
            }
        }
        return  listesSeances;
    }

    public static ArrayList<Seance> rechercheSeancesPromotion(String nomPromotion,int semaine)
    {
        PromotionDAO promotionDAO = (PromotionDAO) DAOFactory.getPromotionDAO();
        ArrayList<Promotion> promotionListe= promotionDAO.chercher("NOM",nomPromotion);
        if(promotionListe.isEmpty())
        {
            return null;
        }
        Promotion promotionRecherche=promotionListe.get(0);

        // chercher l'id de la promotion
        int idPromotion=promotionRecherche.getId();
        GroupeDAO groupeDAO=(GroupeDAO) DAOFactory.getGroupeDAO();
        SeanceDAO seanceDAO=(SeanceDAO) DAOFactory.getSeanceDAO();
        ArrayList<Seance> listeSeances= new ArrayList<>();
        ArrayList<Integer> listeSeancesId= new ArrayList<>();
        Seances_Groupes_Manager seances_groupes_manager= DAOFactory.getSeanceGroupeManager();
        ArrayList<Groupe> listeGroupePromotion=groupeDAO.chercher("ID_PROMOTION",idPromotion);

        // on ajoute tout les id de seances dans une liste englobante (doublons inclus)
        for (Groupe listeGroupe : listeGroupePromotion) {
            ArrayList<Integer> listesIdSeancesGroupe = seances_groupes_manager.chercherSeances(listeGroupe.getId());
            listeSeancesId.addAll(listesIdSeancesGroupe);
        }
        
        //on ajoute chaque seance corrpondante dans la liste de seance tout en suppriman les prochaines occuratione la meme seance
        for(Integer idSeance: listeSeancesId)
        {
            final int idCourant=idSeance;
            Seance seanceCourante= seanceDAO.chercher(idSeance);
            listeSeancesId.removeIf(id-> id.equals(idCourant));
            listeSeances.add(seanceCourante);
        }
        return listeSeances;

    }

    public static ArrayList<Seance> rechercheSeancesGroupe(String nomGroupe,int semaine)
    {
        GroupeDAO groupeDAO = (GroupeDAO) DAOFactory.getGroupeDAO();
        ArrayList<Groupe> groupesListe= groupeDAO.chercher("NOM",nomGroupe);
        if(groupesListe.isEmpty())
        {
            return null;
        }
        Groupe groupeRecherche=groupesListe.get(0);

        // chercher la séance du groupe
        int idGroupe=groupeRecherche.getId();

        //Chercher seances correspondantes a l'enseignant
        Seances_Groupes_Manager seanceGroupeManager = DAOFactory.getSeanceGroupeManager();
        ArrayList<Integer> listeIdSeances= seanceGroupeManager.chercherSeances(idGroupe);

        SeanceDAO seanceDAO=(SeanceDAO) DAOFactory.getSeanceDAO();
        ArrayList<Seance> listesSeances= new ArrayList<>();
        for (Integer listeIdSeance : listeIdSeances) {
            Seance seanceCourante = seanceDAO.chercher(listeIdSeance);
            boolean seanceAppartientASemaine = seanceCourante.getSemaine() == semaine;
            if (seanceAppartientASemaine) {
                listesSeances.add(seanceCourante);
            }
        }
        return  listesSeances;
    }

    public static ArrayList<Seance> rechercheSeancesSalle(String nomSalle,int semaine)
    {
        SalleDAO salleDAO = (SalleDAO) DAOFactory.getGroupeDAO();
        ArrayList<Salle> sallesListe= salleDAO.chercher("NOM",nomSalle);
        if(sallesListe.isEmpty())
        {
            return null;
        }
        Salle salleRecherche=sallesListe.get(0);

        // chercher l'ID de la salle
        int idSalle=salleRecherche.getId();

        //Chercher seances d'une salle
        Seances_Salles_Manager seanceSalleManager = DAOFactory.getSeanceSalleManager();
        ArrayList<Integer> listeIdSeances= seanceSalleManager.chercherSeances(idSalle);

        SeanceDAO seanceDAO=(SeanceDAO) DAOFactory.getSeanceDAO();
        ArrayList<Seance> listesSeances= new ArrayList<>();
        for (Integer listeIdSeance : listeIdSeances) {
            Seance seanceCourante = seanceDAO.chercher(listeIdSeance);
            boolean seanceAppartientASemaine = seanceCourante.getSemaine() == semaine;
            if (seanceAppartientASemaine) {
                listesSeances.add(seanceCourante);
            }
        }
        return  listesSeances;
    }

}