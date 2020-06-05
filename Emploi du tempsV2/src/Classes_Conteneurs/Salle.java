package Classes_Conteneurs;

public class Salle {
    //Id de la salle
    private int id = -1;
    // Capacite de la salle
    private int capacite = 0;
    //Nom de la salle
    private String nom="";
    //Site de la salle
    private int siteId= -1;

    //Constructeur par défaut
    public Salle(){};

    //Constructeur par paramètres
    public Salle(int id, int capacite, String nom, int siteId ) {

        this.id = id;
        this.capacite = capacite;
        this.nom = nom;
        this.siteId = siteId;
    }

    public int getId() {
        return id;
    }

     public void setId(int id){
        this.id=id;
     }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSite() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
}
