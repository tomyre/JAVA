/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import Classes_Conteneurs.*;
import Classes_Conteneurs.DAO.*;
import Classes_Conteneurs.Etudiant;
import MAJDonnes.MAJCours;
import MAJDonnes.MAJSeancesEnseignants;
import Recherche.RechercheGroupes;
import Recherche.RechercheSeances;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 * @author azglr
 */
public class Admin extends JFrame implements ActionListener {

    private final JLabel salle, matières, personnes, vide, nom, num, nada, v, r, n, w, o, a, b, c, d, f, g, i, j, k, l;
    private final JButton Afficher5,Afficher3, Afficher4, Afficher2, MAJ, salles, selectCours, selectCategories, selectCategories2, Continuer, Ajouter, Supprimer, Affecter, Modifier, Afficher;
    private final JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10, panel11, panel12, nord;
    private final JComboBox cours, pers, td, promo, choixmodif, Lieu, Type, Semaine, Table;
    private final JCheckBox tout;
    private final JTextArea zoneTexte;
    private final JTextField nouveauNom, ID4, ID5, ID6, saisieInfos, saisieSemaine, saisieSemaine2, saisieInfosModif, saisieInfosSup, saisieInfosInsert, NOM, NOM2, Capacite, Numero, Date, IDchoix, HeureDebut, HeureFin;
    private DefaultComboBoxModel defaultComboBoxModel, comboBoxGroupes;

    Object tab[] = new Object[]{"etudiant", "enseignant", "promotion"};
    Object tab2[] = new Object[]{"Modifier", "Ajouter", "Affecter", "Supprimer"};
    Object tab3[] = new Object[]{"1", "2", "3", "4", "5"};
    Object tab4[] = new Object[]{"TD", "TP", "EXAMEN", "COURS", "PROJET"};
    Object tab5[] = new Object[]{"cours", "site", "enseignant", "groupe"};

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

    public Admin() throws SQLException, ClassNotFoundException {

        //création par héritage de la fenêtre
        super("Page Admin");
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
        Continuer = new JButton("Continuer");
        Ajouter = new JButton("Ajouter");
        Supprimer = new JButton("Suppression");
        Modifier = new JButton("Modification");
        Affecter = new JButton("Affecter");
        Afficher = new JButton("Afficher");
        MAJ = new JButton("MAJ");
        Afficher2 = new JButton("Afficher");
        Afficher3 = new JButton("Afficher Seance");
        Afficher4 = new JButton("Afficher enseignant");
        Afficher5 = new JButton(("Affcher groupe"));


        //creation des CheckBox
        tout = new JCheckBox("TOUS");

        //création des JComboBox
        cours = new JComboBox(this.getMatieres());
        pers = new JComboBox(tab);
        choixmodif = new JComboBox(tab2);
        Lieu = new JComboBox();
        Semaine = new JComboBox(tab3);
        Type = new JComboBox(tab4);
        Table = new JComboBox(tab5);
        promo = new JComboBox();
        td = new JComboBox();


        //création des JTextField
        saisieInfos = new JTextField();
        saisieSemaine = new JTextField();
        saisieSemaine2 = new JTextField();
        saisieInfosModif = new JTextField();
        saisieInfosSup = new JTextField();
        saisieInfosInsert = new JTextField();
        NOM = new JTextField();
        NOM2 = new JTextField();
        Numero = new JTextField();
        Capacite = new JTextField();
        Date = new JTextField();
        IDchoix = new JTextField();
        HeureDebut = new JTextField();
        HeureFin = new JTextField();
        nouveauNom = new JTextField();
        ID4 = new JTextField();
        ID5 = new JTextField();
        ID6 = new JTextField();


        //création des textes
        zoneTexte = new JTextArea();

        //création des labels
        salle = new JLabel(" ", JLabel.CENTER);
        matières = new JLabel("Les cours ", JLabel.CENTER);
        vide = new JLabel(" ", JLabel.CENTER);
        personnes = new JLabel("Catégories ", JLabel.CENTER);
        nom = new JLabel("Nom : ", JLabel.CENTER);
        num = new JLabel("N° Semaine : ", JLabel.CENTER);
        nada = new JLabel(" ", JLabel.CENTER);
        v = new JLabel(" ", JLabel.CENTER);
        n = new JLabel(" ", JLabel.CENTER);
        r = new JLabel("N° Semaine : ", JLabel.CENTER);
        w = new JLabel(" ", JLabel.CENTER);
        o = new JLabel(" ", JLabel.CENTER);
        a = new JLabel("Nom courant : ", JLabel.CENTER);
        b = new JLabel("Nouveau nom : ", JLabel.CENTER);
        i = new JLabel(" ", JLabel.CENTER);
        l = new JLabel(" ", JLabel.CENTER);
        c = new JLabel("Nom : ", JLabel.CENTER);
        d = new JLabel("ID : ", JLabel.CENTER);
        j = new JLabel(" ", JLabel.CENTER);
        f = new JLabel("ID enseignant", JLabel.CENTER);
        g = new JLabel("ID seance", JLabel.CENTER);
        k = new JLabel("ID groupe ", JLabel.CENTER);

        //création des panneaux
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 12));

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 12, 10, 0));
        panel2.setVisible(true);

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 3));

        panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1, 4));

        panel5 = new JPanel();
        panel5.setLayout(new GridLayout(1, 4));

        panel6 = new JPanel();
        panel6.setLayout(new GridLayout(1, 5));

        panel7 = new JPanel();
        panel7.setLayout(new GridLayout(1, 5));

        panel8 = new JPanel();
        panel8.setLayout(new GridLayout(1, 1));

        panel9 = new JPanel();
        panel9.setLayout(new GridLayout(1, 4));

        panel10 = new JPanel();
        panel10.setLayout(new GridLayout(1, 3));

        panel11 = new JPanel();
        panel11.setLayout(new GridLayout(1, 3));

        panel12 = new JPanel();
        panel12.setLayout(new GridLayout(1, 3));


        nord = new JPanel();
        nord.setLayout(new GridLayout(11, 1));

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

        panel3.add(choixmodif);
        panel3.add(Table);
        panel3.add(Continuer);

        panel4.add(NOM);
        panel4.add(nouveauNom);
        panel4.add(Afficher);
        panel4.add(MAJ);
        panel4.setVisible(false);

        panel5.add(NOM2);
        panel5.add(Modifier);
        panel5.add(Afficher2);
        panel5.setVisible(false);

        panel6.add(ID4);
        panel6.add(ID5);
        panel6.add(Afficher3);
        panel6.add(Afficher4);
        panel6.add(Affecter);
        panel6.setVisible(false);


        panel7.add(ID6);
        panel7.add(Afficher5);
        panel7.add(Supprimer);
        panel7.setVisible(false);

        panel8.add(zoneTexte);

        panel9.add(a);
        panel9.add(b);
        panel9.add(i);
        panel9.add(l);
        panel9.setVisible(false);

        panel10.add(c);
        panel10.add(j);
        panel10.setVisible(false);

        panel11.add(f);
        panel11.add(g);
        panel11.setVisible(false);

        panel12.add(k);
        panel12.setVisible(false);

        //panel12.add();

        nord.add("North", panel1);
        nord.add("North", panel2);
        nord.add("North", panel3);
        nord.add("North", panel9);
        nord.add("North", panel4);
        nord.add("North", panel10);
        nord.add("North", panel5);
        nord.add("North", panel11);
        nord.add("North", panel6);
        nord.add("North", panel12);
        nord.add("North", panel7);


        //ajout des listener
        salles.addActionListener(this);
        selectCours.addActionListener(this);
        selectCategories.addActionListener(this);
        pers.addActionListener(this);
        promo.addActionListener(this);
        td.addActionListener(this);
        selectCategories2.addActionListener(this);
        choixmodif.addActionListener(this);
        Lieu.addActionListener(this);
        Ajouter.addActionListener(this);
        Supprimer.addActionListener(this);
        Afficher.addActionListener(this);
        Continuer.addActionListener(this);
        Modifier.addActionListener(this);
        MAJ.addActionListener(this);
        Afficher2.addActionListener(this);
        Afficher3.addActionListener(this);
        Afficher4.addActionListener(this);
        Affecter.addActionListener(this);
        Afficher5.addActionListener(this);

        //disposition géographique des panneaux
        add("North", nord);
        add("Center", panel8);


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

    public void AfficherMatières(String coursBox) {
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
            String semaine = saisieSemaine.getText();
            if (persBox.equals("etudiant")) {
                UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getUtilisateur();
                Utilisateur utilisateur = utilisateurDAO.chercher("NOM", infos).get(0);
                EtudiantDAO etudiantDAO = (EtudiantDAO) DAOFactory.getEtudiantDAO();
                Etudiant etudiantCorrespondant = etudiantDAO.chercher("ID_UTILISATEUR", utilisateur.getId()).get(0);
                ArrayList<Seance> seancesCorrespondantes = RechercheSeances.rechercherSeancesEtudiant(etudiantCorrespondant.getNumeroEtudiant(), Integer.parseInt(semaine));
                assert seancesCorrespondantes != null;
                this.afficherSeances(seancesCorrespondantes);

            } else if (persBox == "enseignant") {
                UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getUtilisateur();
                Utilisateur utilisateur = utilisateurDAO.chercher("NOM", infos).get(0);
                ArrayList<Seance> seancesCorrespondantes = RechercheSeances.rechercherSeancesEnseignant(utilisateur.getId(), Integer.parseInt(semaine));
                assert seancesCorrespondantes != null;
                this.afficherSeances(seancesCorrespondantes);
            }

        } else if (source == pers) {
            String selection = (String) pers.getSelectedItem();
            if (selection.equals("promotion")) {
                saisieInfos.setVisible(false);
                selectCategories.setVisible(false);
                nom.setVisible(false);
                num.setVisible(false);
                saisieSemaine.setVisible(false);
                saisieSemaine2.setVisible(true);
                promo.setVisible(true);
                tout.setVisible(true);
                selectCategories2.setVisible(true);
                td.setVisible(true);
                r.setVisible(true);
                this.remplirChoixPromos();
                this.remplirChoixGroupes();
            } else {
                saisieInfos.setVisible(true);
                selectCategories.setVisible(true);
                saisieSemaine.setVisible(true);
                nom.setVisible(true);
                num.setVisible(true);
                saisieSemaine2.setVisible(false);
                promo.setVisible(false);
                tout.setVisible(false);
                selectCategories2.setVisible(false);
                td.setVisible(false);
                r.setVisible(false);
            }

        } else if (source == promo) {
            this.remplirChoixGroupes();

        } else if (source == selectCategories2) {
            String semaine2 = saisieSemaine2.getText();
            ArrayList<Seance> seancesPromo = new ArrayList<>();
            if (tout.isSelected()) {
                // on cherche les seances de la promo entiere
                seancesPromo = RechercheSeances.rechercheSeancesPromotion((String) promo.getSelectedItem(), 1);
            } else {
                seancesPromo = RechercheSeances.rechercheSeancesGroupe((String) td.getSelectedItem(), 1);
            }
            assert seancesPromo != null;
            this.afficherSeances(seancesPromo);

        } else if (source == Continuer) {
            String selection = (String) choixmodif.getSelectedItem();
            String choixTables = (String) Table.getSelectedItem();

            if ((selection.equals("Modifier")) && (choixTables.equals("cours"))) {
                panel9.setVisible(true);
                panel4.setVisible(true);
                panel10.setVisible(false);
                panel5.setVisible(false);
                panel11.setVisible(false);
                panel6.setVisible(false);
                panel12.setVisible(false);
                panel7.setVisible(false);

            } else if ((selection.equals("Ajouter")) && (choixTables.equals("site"))) {
                panel9.setVisible(false);
                panel4.setVisible(false);
                panel10.setVisible(true);
                panel5.setVisible(true);
                panel11.setVisible(false);
                panel6.setVisible(false);
                panel12.setVisible(false);
                panel7.setVisible(false);

            } else if ((selection.equals("Affecter")) && (choixTables.equals("enseignant"))) {
                panel9.setVisible(false);
                panel4.setVisible(false);
                panel10.setVisible(false);
                panel5.setVisible(false);
                panel11.setVisible(true);
                panel6.setVisible(true);
                panel12.setVisible(false);
                panel7.setVisible(false);

            } else if ((selection.equals("Supprimer")) && (choixTables.equals("groupe"))) {
                panel9.setVisible(false);
                panel4.setVisible(false);
                panel10.setVisible(false);
                panel5.setVisible(false);
                panel11.setVisible(false);
                panel6.setVisible(false);
                panel12.setVisible(true);
                panel7.setVisible(true);
            }

        } else if (source == Afficher) {
            SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
            ArrayList<Seance> seances = seanceDAO.chercherToutesLesSeances();
            this.afficherSeances(seances);

        } else if (source == MAJ) {
            String nomCourant = NOM.getText();
            String nouveauNomSaisi = nouveauNom.getText();
            CoursDAO coursDAO = (CoursDAO) DAOFactory.getCoursDAO();
            Cours coursAModifie = coursDAO.chercher(nomCourant);
            int idCours = coursAModifie.getIdCours();
            SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
            Seance seance = seanceDAO.chercher("ID_COURS", idCours).get(0);
            MAJCours.modificationNomCours(seance.getId(), nouveauNomSaisi);

        } else if (source == Modifier) {
            String nomSite = NOM2.getText();
            Site nouveauSite = new Site(nomSite);
            SiteDAO siteDAO = (SiteDAO) DAOFactory.getSiteDAO();
            siteDAO.creer(nouveauSite);

        } else if (source == Affecter) {
            String idEnseignant = ID4.getText();
            String idSeance = ID5.getText();
            System.out.println(idEnseignant+"   "+idSeance);
            MAJSeancesEnseignants.assigneEnseignantSeance(Integer.parseInt(idEnseignant), Integer.parseInt(idSeance));

            //Appeler fonction;
        } else if (source == Supprimer) {
            String idGroupe=ID6.getText();
            GroupeDAO groupeDAO = (GroupeDAO) DAOFactory.getGroupeDAO();
            Groupe groupeASupprimer=new Groupe(Integer.parseInt(idGroupe));
            groupeDAO.supprimer(groupeASupprimer);

        } else if (source == Afficher3) {
            SeanceDAO seanceDAO = (SeanceDAO) DAOFactory.getSeanceDAO();
            this.afficherSeances(seanceDAO.chercherToutesLesSeances());
        } else if (source == Afficher4) {
            EnseignantDAO enseignantDAO= (EnseignantDAO) DAOFactory.getEnseignantDAO();
            this.afficherEnseignants(enseignantDAO.chercherToutLesEnseignants());
        }
        else if(source==Afficher5)
        {
            GroupeDAO groupeDAO=(GroupeDAO) DAOFactory.getGroupeDAO();
            this.afficherGroupes(groupeDAO.chercherToutLesGroupes());
        }

    }

    public void remplirChoixPromos() {
        if (defaultComboBoxModel != null) {
            defaultComboBoxModel.removeAllElements();
        }
        PromotionDAO promotionDAO = (PromotionDAO) DAOFactory.getPromotionDAO();
        ArrayList<Promotion> promotions = promotionDAO.chercherToutesLesPromotions();
        ArrayList<String> nomPromos = new ArrayList<>();

        for (Promotion promo : promotions) {
            nomPromos.add(promo.getNom());
        }
        defaultComboBoxModel = new DefaultComboBoxModel(nomPromos.toArray());
        promo.setModel(defaultComboBoxModel);

    }

    public void remplirChoixGroupes() {
        String promoChoisie = (String) promo.getSelectedItem();
        if (comboBoxGroupes != null) {
            comboBoxGroupes.removeAllElements();
        }
        PromotionDAO promotionDAO = (PromotionDAO) DAOFactory.getPromotionDAO();
        ArrayList<Promotion> promotion = promotionDAO.chercher("NOM", promoChoisie);
        Promotion promotionChoisie = promotion.get(0);
        ArrayList<Groupe> groupesPromo = RechercheGroupes.rechercherGroupes(promotionChoisie.getId());
        ArrayList<String> nomGroupes = new ArrayList<>();

        for (Groupe groupe : groupesPromo) {
            nomGroupes.add(groupe.getNom());
        }
        comboBoxGroupes = new DefaultComboBoxModel(nomGroupes.toArray());
        td.setModel(comboBoxGroupes);
    }

    public void afficherSeances(ArrayList<Seance> seances) {
        zoneTexte.setText(null);
        Type_CoursDAO typeCoursDAO = (Type_CoursDAO) DAOFactory.getTypeCours();
        CoursDAO coursDAO = (CoursDAO) DAOFactory.getCoursDAO();
        for (Seance seance : seances) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ID_SEANCE: ");
            stringBuilder.append(seance.getId());
            stringBuilder.append("      DATE: ");
            stringBuilder.append(seance.getDate().toString());
            stringBuilder.append("      DEBUT: ");
            stringBuilder.append(seance.getHeureDebut().toString());
            stringBuilder.append("      FIN: ");
            stringBuilder.append(seance.getHeureFin().toString());
            stringBuilder.append("      ETAT: ");
            stringBuilder.append(seance.getEtat().getNom());
            stringBuilder.append("      Type: ");
            stringBuilder.append(typeCoursDAO.chercher(seance.getTypeCours()).getNom());
            stringBuilder.append("      ID_TYPE: ");
            stringBuilder.append(typeCoursDAO.chercher(seance.getTypeCours()).getId());
            stringBuilder.append("      ID_COURS: ");
            stringBuilder.append(seance.getCours());
            stringBuilder.append("      NOM_COURS: ");
            stringBuilder.append(coursDAO.chercher(seance.getCours()).getNomCours());
            String ligneSeance = stringBuilder.toString();
            String salleLigne = ligneSeance + "\n\n";
            zoneTexte.append(salleLigne);
        }
    }

    public void afficherEnseignants(ArrayList<Enseignant> enseignants) {
        zoneTexte.setText(null);
        for (Enseignant enseignant : enseignants) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ID: ");
            stringBuilder.append(enseignant.getIdUtilisateur());
            stringBuilder.append("      NOM: ");
            UtilisateurDAO utilisateurDAO= (UtilisateurDAO) DAOFactory.getUtilisateur();
            Utilisateur utilisateurCorrespondant=utilisateurDAO.chercher(enseignant.getIdUtilisateur());
            stringBuilder.append(utilisateurCorrespondant.getNom());
            stringBuilder.append("      PRENOM: ");
            stringBuilder.append(utilisateurCorrespondant.getPrenom());
            String ligneEnseign = stringBuilder.toString();
            String ligneEnseignant = ligneEnseign + "\n\n";
            zoneTexte.append(ligneEnseignant);
        }
    }
    public void afficherGroupes(ArrayList<Groupe> groupes) {
        zoneTexte.setText(null);
        for (Groupe groupe : groupes) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ID: ");
            stringBuilder.append(groupe.getId());
            stringBuilder.append("      NOM: ");
            stringBuilder.append(groupe.getNom());
            PromotionDAO promotionDAO= (PromotionDAO) DAOFactory.getPromotionDAO();
            Promotion promotionCorrespondante=promotionDAO.chercher(groupe.getIdPromotion());
            stringBuilder.append("      PROMOTION: ");
            stringBuilder.append(promotionCorrespondante.getNom());
            String ligneGroupe = stringBuilder.toString();
            String ligneGroupeEntiere = ligneGroupe + "\n\n";
            zoneTexte.append(ligneGroupeEntiere);
        }
    }
    private Object getId(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

