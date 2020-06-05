package Classes_Conteneurs;

public class Etudiant {
    private int numeroEtudiant=-1;
    private int idGroupe=-1;
    private int idUtilisateur=-1;

    public Etudiant(int numeroEtudiant, int idGroupe, int idUtilisateur) {
        this.numeroEtudiant = numeroEtudiant;
        this.idGroupe = idGroupe;
        this.idUtilisateur = idUtilisateur;
    }

    public Etudiant() {};

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
