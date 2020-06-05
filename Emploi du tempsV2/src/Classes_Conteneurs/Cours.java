package Classes_Conteneurs;

public class Cours {
    private int idCours=-1;
    private String nomCours="";

    public Cours(int idCours, String nomCours) {
        this.idCours = idCours;
        this.nomCours = nomCours;
    }

    public Cours() {};

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }
}
