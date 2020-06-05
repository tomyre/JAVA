package Classes_Conteneurs;

public enum TypeCours {
    AUCUN(0,"AUCUN"),
    TD(1,"TD"),
    TP(2,"TP"),
    EXAMEN(3,"Examen"),
    COURS(4,"Cours"),
    PROJET(5,"Projet");

    private int id = -1;
    private String nom = "";

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    //Constructeur
    TypeCours(int id,String nomCours) {
        this.id = id;
        this.nom=nomCours;
    }
    public static TypeCours getType(int id)
    {
        return switch (id) {
            case 1 -> TypeCours.TD;
            case 2 ->  TypeCours.TP;
            case 3 ->  TypeCours.EXAMEN;
            case 4 ->  TypeCours.COURS;
            case 5 ->  TypeCours.PROJET;
            default -> TypeCours.AUCUN;
        };
    }
    // Methode d'acces au nom du cours

}
