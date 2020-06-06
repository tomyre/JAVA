package Classes_Conteneurs;

public enum Etat_Seance {
    INCONNU(0),
    EN_COURS(1),
    VALIDEE(2),
    ANNULEE(3);

    private int indiceEtat = 0;

    //Constructeur
    Etat_Seance(int etat) {
        this.indiceEtat = etat;
    }
    // Methode d'acces a l'indice du droit
    public int getIndiceEtat() {
        return this.indiceEtat;
    }
    public static Etat_Seance getEtat(int droit){
        switch (droit)
        {
            case 1:
                return Etat_Seance.EN_COURS;
            case 2:
                return Etat_Seance.VALIDEE;
            case 3:
                return Etat_Seance.ANNULEE;
            default:
                return Etat_Seance.INCONNU;
        }
    }
}
