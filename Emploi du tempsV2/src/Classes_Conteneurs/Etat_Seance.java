package Classes_Conteneurs;

public enum Etat_Seance {
    INCONNU(0,"INCONNU"),
    EN_COURS(1,"EN COURS"),
    VALIDEE(2,"VALIDEE"),
    ANNULEE(3,"ANNULEE");

    private int indiceEtat = 0;
    private String etat="";
    //Constructeur
    Etat_Seance(int etat,String nom) {
        this.indiceEtat = etat;
        this.etat=nom;
    }
    // Methode d'acces a l'indice du droit
    public int getIndiceEtat() {
        return this.indiceEtat;
    }
    public String getNom(){
        return etat;
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
