package MAJDonnes;

import Classes_Conteneurs.Groupe;
import Classes_Conteneurs.Salle;
import Classes_Conteneurs.Seance;

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
    public static boolean sallePasSaturee(Salle salle, Groupe nouveauGroupe){

    }
}
