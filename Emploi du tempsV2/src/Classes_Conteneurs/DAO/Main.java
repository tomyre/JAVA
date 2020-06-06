package Classes_Conteneurs.DAO;

import Classes_Conteneurs.*;
import MAJDonnes.MAJSeancesEnseignants;
import Recherche.RechercheSeances;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Seance> listeSeances=RechercheSeances.rechercherSeancesEtudiant(3,1);
        for(Seance seance:listeSeances)
        {
            System.out.println("DATE: "+seance.getDate());
            System.out.println("DEBUT: "+seance.getHeureDebut());
            System.out.println("FIN: "+seance.getHeureFin());
        }

    }

}
