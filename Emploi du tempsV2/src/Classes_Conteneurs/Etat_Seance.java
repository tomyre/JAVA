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
        return switch (droit) {
            case 1 ->Etat_Seance.EN_COURS;
            case 2 -> Etat_Seance.VALIDEE;
            case 3 -> Etat_Seance.ANNULEE;
            default -> Etat_Seance.INCONNU;
        };
    }
}
