package Classes_Conteneurs;
public class Utilisateur {
    // ID de l'utilisateur dans la base de donnees
    private int id = -1;
    // Nom de l'utilisateur
    private String nom = "";
    // Prénom de l'utilisateur
    private String prenom = "";
    // E-mail de l'utilisateur
    private String mail = "";
    // Mot de passe de l'utilisateur
    private String motDePasse = "";
    // Droit de l'utilisateur
    private Droit droit= Droit.ETUDIANT;

    //Constructeur par défaut
    public Utilisateur(){};

    //Constructeur par parametres
    public Utilisateur(int id, String nom, String prenom,String mail,String motDePasse, Droit droit) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail=mail;
        this.motDePasse=motDePasse;
        this.droit=droit;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String mdp) {
        this.motDePasse = mdp;
    }

    public Droit getDroit() {
        return droit;
    }

    public void setMotDePasse(Droit droit) { this.droit=droit; }
}
