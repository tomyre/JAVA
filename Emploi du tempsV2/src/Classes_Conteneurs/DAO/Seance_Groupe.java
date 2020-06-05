package Classes_Conteneurs.DAO;

public class Seance_Groupe {
    private int idGroupe=-1;
    private int idSeance =-1;

    public Seance_Groupe() {
    }

    public Seance_Groupe(int idGroupe, int idSeance) {
        this.idGroupe = idGroupe;
        this.idSeance = idSeance;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }
}
