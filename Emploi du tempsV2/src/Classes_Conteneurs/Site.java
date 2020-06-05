package Classes_Conteneurs;

public enum Site {
    Inconnu(0, "Inconnu"),
    Eiffel1(1, "Eiffel1"),
    Eiffel2(2, "Eiffel2"),
    Eiffel3(3,"Eiffel3"),
    Eiffel4(4,"Eiffel4"),
    Eiffel5(5,"Eiffel5");

    private int id = -1;
    private String nom = "";

    //Constructeur
    Site(int id,String nomSite) {
        this.id = id;
        this.nom=nomSite;
    }
    // Methode d'acces au nom du cours

    public int getId() {
        return id;
    }

    public String getNom() {

        return nom;
    }
    public static Site getSite(int id)
    {
        return switch (id) {
            case 1 -> Site.Eiffel1;
            case 2 -> Site.Eiffel2;
            case 3 -> Site.Eiffel3;
            case 4 -> Site.Eiffel4;
            case 5 -> Site.Eiffel5;
            default -> Site.Inconnu;
        };
    }
}

