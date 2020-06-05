package Classes_Conteneurs;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Seance {
    // TODO Chnager Date et Heure
    private int id=-1;
    private int semaine=1;
    private Date date;
    private Time heureDebut;
    private Time heureFin;
    private Etat_Seance etat=Etat_Seance.VALIDEE;
    private int idCours;
    private TypeCours typeCours=TypeCours.COURS;
    private Set<Enseignant> listeEnseignants = new HashSet<Enseignant>();
    private Set<Groupe> listeGroupes = new HashSet<Groupe>();
    private Set<Salle> listeSalles = new HashSet<Salle>();
    public Seance(){};

    public Seance(int id, int semaine, Date date, Time heureDebut, Time heureFin, Etat_Seance etat, int cours, TypeCours typeCours, Set<Enseignant> listeEnseignants) {
        this.id = id;
        this.semaine = semaine;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.etat = etat;
        this.idCours = cours;
        this.typeCours = typeCours;
        this.listeEnseignants = listeEnseignants;
    }

    public Seance(int id, int semaine, Date date, Time heureDebut, Time heureFin, Etat_Seance etat, int idCours, TypeCours typeCours) {
        this.id = id;
        this.semaine = semaine;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.etat = etat;
        this.idCours = idCours;
        this.typeCours = typeCours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemaine() {
        return semaine;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public Etat_Seance getEtat() {
        return etat;
    }

    public void setEtat(Etat_Seance etat) {
        this.etat = etat;
    }

    public int getCours() {
        return idCours;
    }

    public void setCours(int cours) {
        this.idCours = cours;
    }

    public TypeCours getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(TypeCours typeCours) {
        this.typeCours = typeCours;
    }

    public Set<Enseignant> getListeEnseignants() {
        return listeEnseignants;
    }

    public void setListeEnseignants(Set<Enseignant> listeEnseignants) {
        this.listeEnseignants = listeEnseignants;
    }

    //Ajoute une matière à un professeur
    public void ajouterEnseignant(Enseignant enseignant){
        this.listeEnseignants.add(enseignant);
    }

    //Retire une matière à un professeur
    public void retirerEnseignant(Enseignant enseignant){
        this.listeEnseignants.remove(enseignant);
    }

    public Set<Groupe> getListeGroupes() {
        return listeGroupes;
    }

    public void setListeGroupes(Set<Groupe> listeSeance) {
        this.listeGroupes = listeSeance;
    }
    public void ajouterGroupe(Groupe groupe){
        this.listeGroupes.add(groupe);
    }

    public void retirerGroupe(Groupe groupe){
        this.listeGroupes.remove(groupe);
    }

    public Set<Salle> getListeSalles() {
        return listeSalles;
    }

    public void setListeSalles(Set<Salle> listeSalles) {
        this.listeSalles = listeSalles;
    }

    public void ajouterSalle(Salle salle){
        this.listeSalles.add(salle);
    }

    public void retirerSalle(Salle salle){
        this.listeSalles.remove(salle);
    }
}
