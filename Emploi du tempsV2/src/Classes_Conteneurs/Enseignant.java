package Classes_Conteneurs;

public class Enseignant {

    //ID de l'utilisateur
    private int idUtilisateur=-1;
    //ID du cours
    private int idCours = -1;

    //Constructeur par défaut
    public Enseignant() {};

    //Constructeur par paramètres
    public Enseignant(int idUtilisateur, int idCours) {
        this.idUtilisateur = idUtilisateur;
        this.idCours = idCours;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }
}

