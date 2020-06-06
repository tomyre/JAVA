package Classes_Conteneurs.DAO;

import Classes_Conteneurs.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //TODO METTRE Cet algo dans une classe du package recherche
        // Trouver etudiant Numero-> Etudiant -> ID GROUPE(1)-> SEANCE(0->*)
        // Checher l'etudiant a partir de son numero
        EtudiantDAO etudiantDAO = (EtudiantDAO) DAOFactory.getEtudiantDAO();
        ArrayList<Etudiant> etudiantListe= etudiantDAO.chercher("NUMERO",1106);
        if(etudiantListe.isEmpty())
        {
           return;
        }
        Etudiant etudiant=etudiantListe.get(0);

        // chercher groupe de l'etudiant
        GroupeDAO groupeDAO = (GroupeDAO) DAOFactory.getGroupeDAO();
        Groupe groupeEtudiant= groupeDAO.chercher(etudiant.getIdGroupe());

        Seances_Groupes_Manager seance_groupesManager = DAOFactory.getSeanceGroupeManager();
        ArrayList<Integer> listeIdSeances= seance_groupesManager.chercherSeances(groupeEtudiant.getId());

        SeanceDAO seanceDAO=(SeanceDAO) DAOFactory.getSeanceDAO();
        int taille=listeIdSeances.size();
        for(int i=0;i<taille;i++)
        {
            Seance seanceCourante =seanceDAO.chercher(listeIdSeances.get(i));
            System.out.print("ID SEANCE= "+seanceCourante.getId());
            System.out.print("  DATE= "+seanceCourante.getDate());
            System.out.print("  DEBUT= "+seanceCourante.getHeureDebut());
            System.out.println("  FIN= "+seanceCourante.getHeureFin());
        }
    }

}
