package Classes_Conteneurs;

public enum Droit {
    AUCUN(0),
    ADMIN(1),
    REFERENT_PEDAGOGIQUE(2),
    ENSEIGNANT(3),
    ETUDIANT(4);

    private int indiceDroit = 0;

    //Constructeur
    Droit(int droit) {
        this.indiceDroit = droit;
    }
    // Methode d'acces a l'indice du droit
    public int getDroit() {
        return this.indiceDroit;
    }
    public static Droit getDroit(int droit){
        return switch (droit) {
            case 1 -> Droit.ADMIN;
            case 2 -> Droit.REFERENT_PEDAGOGIQUE;
            case 3 -> Droit.ENSEIGNANT;
            case 4 -> Droit.ETUDIANT;
            default -> Droit.AUCUN;
        };
    }
}
