package Classes_Conteneurs;

public class Promotion {

    //ID de la promotion
    private int id = -1;
    //Nom de la promotion
    private String nom="";

    //Constructeur par defaut
    public Promotion() {};

    //Constructeur par paramètres
    public Promotion(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
