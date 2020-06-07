/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import Classes_Conteneurs.*;
import Classes_Conteneurs.DAO.*;
import Classes_Conteneurs.Etudiant;
import Recherche.RechercheGroupes;
import Recherche.RechercheSeances;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author azglr
 */
public class PageRP extends JFrame implements ActionListener {

    private final JLabel salle, matières, personnes, vide, nom, num, nada, r, n, v, w, o;
    private final JButton salles, selectCours, selectCategories, selectCategories2;
    private final JPanel panel1, panel2, panel3, nord;
    private  JComboBox cours, pers, td, promo;
    private final JCheckBox tout;
    private final JTextArea zoneTexte;
    private final JTextField saisieInfos, saisieSemaine, saisieSemaine2;
    private DefaultComboBoxModel defaultComboBoxModel,comboBoxGroupes;

    String tab[] = new String[]{"etudiant", "enseignant", "promotion"};

    public void ajoutElement() {
        //ajout des objets dans les panneaux
        panel1.add(salle);
        panel1.add(matières);
        panel1.add(vide);
        panel1.add(personnes);
        panel1.add(nom);
        panel1.add(num);
        panel1.add(nada);
        panel1.add(r);
        panel1.add(n);
        panel1.add(v);
        panel1.add(w);
        panel1.add(o);
        panel2.add(salles);
        panel2.add(cours);
        panel2.add(selectCours);
        panel2.add(pers);
        panel2.add(saisieInfos);
        panel2.add(saisieSemaine);
        panel2.add(selectCategories);
        panel3.add(zoneTexte);
        promo.setVisible(false);
        tout.setVisible(false);
        selectCategories2.setVisible(false);
        td.setVisible(false);
        saisieSemaine2.setVisible(false);
        r.setVisible(false);
        panel2.add(saisieSemaine2);
        panel2.add(promo);
        panel2.add(tout);
        panel2.add(td);
        panel2.add(selectCategories2);
        nord.add("North", panel1);
        nord.add("North", panel2);
    }


    public String[] getMatieres() {
        CoursDAO coursDAO = (CoursDAO) DAOFactory.getCoursDAO();
        ArrayList<Cours> allCours = coursDAO.chercherToutLesCours();
        String[] nomDesCours = new String[allCours.size()];
        int i = 0;
        for (Cours cours : allCours) {
            nomDesCours[i] = cours.getNomCours();
            i++;
        }
        return nomDesCours;
    }

    public PageRP() {

        //création par héritage de la fenêtre
        super("Page Référent Pédagogique");
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(true);

        //création bouton salles
        salles = new JButton("Les salles");
        selectCours = new JButton("Rechercher");
        selectCategories = new JButton("Rechercher");
        selectCategories2 = new JButton("Rechercher");

        //création des JComboBox
        cours = new JComboBox(this.getMatieres());
        pers = new JComboBox(tab);
        promo = new JComboBox();
        td = new JComboBox();

        //création d'un JCheckBox
        tout = new JCheckBox("TOUS");

        //création des JTextField
        saisieInfos = new JTextField();
        saisieSemaine = new JTextField();
        saisieSemaine2 = new JTextField();

        //création des textes
        zoneTexte = new JTextArea();

        //création des labels
        salle = new JLabel(" ", JLabel.CENTER);
        matières = new JLabel("Les cours", JLabel.CENTER);
        vide = new JLabel(" ", JLabel.CENTER);
        personnes = new JLabel("Catégories de recherches", JLabel.CENTER);
        nom = new JLabel("Nom : ", JLabel.CENTER);
        num = new JLabel("N° Semaine : ", JLabel.CENTER);
        nada = new JLabel(" ", JLabel.CENTER);
        v = new JLabel(" ", JLabel.CENTER);
        n = new JLabel(" ", JLabel.CENTER);
        r = new JLabel("N° Semaine : ", JLabel.CENTER);
        w = new JLabel(" ", JLabel.CENTER);
        o = new JLabel(" ", JLabel.CENTER);

        //création des panneaux
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 12));


        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 12,10,0));
        panel2.setVisible(true);

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 1));

        nord = new JPanel();
        nord.setLayout(new GridLayout(2, 1));

        this.ajoutElement();

        //ajout des listener
        salles.addActionListener(this);
        selectCours.addActionListener(this);
        selectCategories.addActionListener(this);
        pers.addActionListener(this);
        salles.addActionListener(this);
        selectCategories.addActionListener(this);
        promo.addActionListener(this);
        td.addActionListener(this);
        selectCategories2.addActionListener(this);

        //disposition géographique des panneaux
        add("North", nord);
        add("Center", panel3);

        // le prgm s'arrête lorsuqe l'on ferme la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer
            }

        });
    }

    public void AfficherSalles() {
        zoneTexte.setText(null);
        SalleDAO salleDAO = (SalleDAO) DAOFactory.getSalleDAO();
        ArrayList<Salle> salles = salleDAO.chercherToutesLesSalles();

        SiteDAO siteDAO = (SiteDAO) DAOFactory.getSiteDAO();
        for (Salle salle : salles) {
            String ligneSalle = "NOM: " +
                    salle.getNom() +
                    "  CAPACITE: " +
                    salle.getCapacite() +
                    "  SITE: " +
                    siteDAO.chercher(salle.getSite()).getNom();
            String salleLigne = ligneSalle + "\n\n";
            zoneTexte.append(salleLigne);
        }
    }

    private void AfficherMatières(String coursBox) {
        zoneTexte.setText(null);
        ArrayList<Seance> seances = RechercheSeances.rechercheSeancesCours(coursBox, 1);
        Type_CoursDAO typeCoursDAO = (Type_CoursDAO) DAOFactory.getTypeCours();
        for (Seance seance : seances) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DATE: ");
            stringBuilder.append(seance.getDate().toString());
            stringBuilder.append("  DEBUT: ");
            stringBuilder.append(seance.getHeureDebut().toString());
            stringBuilder.append("  FIN: ");
            stringBuilder.append(seance.getHeureFin().toString());
            stringBuilder.append("  ETAT: ");
            stringBuilder.append(seance.getEtat().getNom());
            stringBuilder.append("  Type: ");
            stringBuilder.append(typeCoursDAO.chercher(seance.getTypeCours()).getNom());
            String ligneSeance = stringBuilder.toString();
            String salleLigne = ligneSeance + "\n\n";
            zoneTexte.append(salleLigne);
        }
    }

//    public void AfficherInfos(String choixTable, String saisieInfos, String saisieSemaine) {
//
//    }

    /**
     * @param action
     */
    @Override
    public void actionPerformed(ActionEvent action) {
        Object source = action.getSource();

        if (source == salles) {
            //récupérer les lignes de la tables "salle"
            AfficherSalles();

        } else if (source == selectCours) {
            String coursBox = (String) cours.getSelectedItem();
            AfficherMatières(coursBox);

        } else if (source == selectCategories) {
            String persBox = (String) pers.getSelectedItem();
            String infos = saisieInfos.getText();
            String semaine=saisieSemaine.getText();
            String semaine2=saisieSemaine2.getText();
            if(persBox.equals("etudiant"))
            {
                UtilisateurDAO utilisateurDAO= (UtilisateurDAO) DAOFactory.getUtilisateur();
                Utilisateur utilisateur=utilisateurDAO.chercher("NOM",infos).get(0);
                EtudiantDAO etudiantDAO= (EtudiantDAO) DAOFactory.getEtudiantDAO();
                Etudiant etudiantCorrespondant=etudiantDAO.chercher("ID_UTILISATEUR",utilisateur.getId()).get(0);
                ArrayList<Seance> seancesCorrespondantes=RechercheSeances.rechercherSeancesEtudiant(etudiantCorrespondant.getNumeroEtudiant(),Integer.parseInt(semaine2));
                assert seancesCorrespondantes != null;
                this.afficherSeances(seancesCorrespondantes);

            }
            else if(persBox=="enseignant"){
                UtilisateurDAO utilisateurDAO= (UtilisateurDAO) DAOFactory.getUtilisateur();
                Utilisateur utilisateur=utilisateurDAO.chercher("NOM",infos).get(0);
                ArrayList<Seance> seancesCorrespondantes=RechercheSeances.rechercherSeancesEnseignant(utilisateur.getId(),Integer.parseInt(semaine2));
                assert seancesCorrespondantes != null;
                this.afficherSeances(seancesCorrespondantes);
            }


        } else if (source == pers) {
            String selection = (String) pers.getSelectedItem();
            if (selection.equals("promotion")) {
                saisieInfos.setVisible(false);
                saisieSemaine.setVisible(false);
                selectCategories.setVisible(false);
                nom.setVisible(false);
                num.setVisible(false);
                promo.setVisible(true);
                tout.setVisible(true);
                selectCategories2.setVisible(true);
                td.setVisible(true);
                r.setVisible(true);
                saisieSemaine2.setVisible(true);
                this.remplirChoixPromos();
                this.remplirChoixGroupes();
            } else {
                saisieInfos.setVisible(true);
                saisieSemaine.setVisible(true);
                selectCategories.setVisible(true);
                nom.setVisible(true);
                num.setVisible(true);
                promo.setVisible(false);
                tout.setVisible(false);
                selectCategories2.setVisible(false);
                td.setVisible(false);
                r.setVisible(false);
                saisieSemaine2.setVisible(false);
            }

        } else if (source == promo) {
            this.remplirChoixGroupes();
        } else if (source == selectCategories2) {
            ArrayList<Seance> seancesPromo = new ArrayList<>();
            if (tout.isSelected()) {
                // on cherche les seances de la promo entiere
                seancesPromo = RechercheSeances.rechercheSeancesPromotion((String) promo.getSelectedItem(), 1);
            } else {
                seancesPromo = RechercheSeances.rechercheSeancesGroupe((String) td.getSelectedItem(), 1);
            }
            assert seancesPromo != null;
            this.afficherSeances(seancesPromo);
        }
    }
    public void remplirChoixPromos(){
        if(defaultComboBoxModel!=null) {
            defaultComboBoxModel.removeAllElements();
        }
        PromotionDAO promotionDAO= (PromotionDAO) DAOFactory.getPromotionDAO();
        ArrayList<Promotion> promotions =promotionDAO.chercherToutesLesPromotions();
        ArrayList<String> nomPromos= new ArrayList<>();

        for(Promotion promo:promotions)
        {
            nomPromos.add(promo.getNom());
        }
        defaultComboBoxModel= new DefaultComboBoxModel(nomPromos.toArray());
        promo.setModel(defaultComboBoxModel);

    }
    public void remplirChoixGroupes(){
        String promoChoisie= (String) promo.getSelectedItem();
        if(comboBoxGroupes!=null) {
            comboBoxGroupes.removeAllElements();
        }
        PromotionDAO promotionDAO= (PromotionDAO) DAOFactory.getPromotionDAO();
        ArrayList<Promotion> promotion =promotionDAO.chercher("NOM",promoChoisie);
        Promotion promotionChoisie= promotion.get(0);
        ArrayList<Groupe> groupesPromo=RechercheGroupes.rechercherGroupes(promotionChoisie.getId());
        ArrayList<String> nomGroupes= new ArrayList<>();

        for(Groupe groupe:groupesPromo)
        {
            nomGroupes.add(groupe.getNom());
        }
        comboBoxGroupes= new DefaultComboBoxModel(nomGroupes.toArray());
        td.setModel(comboBoxGroupes);
    }
    public void afficherSeances(ArrayList<Seance> seances) {
        zoneTexte.setText(null);
        Type_CoursDAO typeCoursDAO = (Type_CoursDAO) DAOFactory.getTypeCours();
        for (Seance seance : seances) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DATE: ");
            stringBuilder.append(seance.getDate().toString());
            stringBuilder.append("  DEBUT: ");
            stringBuilder.append(seance.getHeureDebut().toString());
            stringBuilder.append("  FIN: ");
            stringBuilder.append(seance.getHeureFin().toString());
            stringBuilder.append("  ETAT: ");
            stringBuilder.append(seance.getEtat().getNom());
            stringBuilder.append("  Type: ");
            stringBuilder.append(typeCoursDAO.chercher(seance.getTypeCours()).getNom());
            String ligneSeance = stringBuilder.toString();
            String salleLigne = ligneSeance + "\n\n";
            zoneTexte.append(salleLigne);
        }
    }
}

