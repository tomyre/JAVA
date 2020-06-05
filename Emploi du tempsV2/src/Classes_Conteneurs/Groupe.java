package Classes_Conteneurs;

import java.util.HashSet;
import java.util.Set;

public class Groupe {
    private int id=-1;
    private String nom="";
    private int idPromotion=-1;

    public Groupe(int id, String nom, int idPromotion) {
        this.id = id;
        this.nom = nom;
        this.idPromotion = idPromotion;
    }

    public Groupe() {};
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

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

}
