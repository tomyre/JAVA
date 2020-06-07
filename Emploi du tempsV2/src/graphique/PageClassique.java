/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import Classes_Conteneurs.DAO.*;
import Classes_Conteneurs.Droit;
import Classes_Conteneurs.Etudiant;
import Classes_Conteneurs.Seance;
import Classes_Conteneurs.Utilisateur;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author azglr
 */
public class PageClassique extends JFrame implements ActionListener {

    private final JPanel panelBouton, content1, content2, contentall ;
    private final JTextArea zoneText1, zoneText2;
    private final JButton edt, recap;
    
    public PageClassique() {
        
        //création de la fenetre
        super("Page "+ Droit.getDroitNom(Pageconnexion.droitUtilisateurConnecte));



        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(true);

        //création d'un JTextArea
        zoneText1 = new JTextArea("zone 1");
        zoneText2 = new JTextArea("zone 2");
        
        //coloration des background des conteneurs
        panelBouton = new JPanel();
        panelBouton.setLayout(new GridLayout(1,2, 10, 0));
        content1 = new JPanel();
        content2 = new JPanel();
        contentall = new JPanel();
        contentall.setLayout(new GridLayout(2, 1));

        //ccréation des boutons
        edt = new JButton("Emploi du temps");
        recap = new JButton("Récapitulatif des cours");

        //ajout des elts aux panels
        panelBouton.add(edt);
        panelBouton.add(recap);
        content1.add(zoneText1);
        content1.setVisible(false);
        content2.add(zoneText2);
        content2.setVisible(false);
        contentall.add("Center",content1);
        contentall.add("Center",content2);

        //disposition géographique des panneaux
        add("North", panelBouton);
        add("Center",contentall);


        //ajout des listeners
        edt.addActionListener(this);
        recap.addActionListener(this);

        // le prgm s'arrête lorsuqe l'on ferme la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer
            }
        });
    }

    public void afficherSeances(ArrayList<Seance> seances) {
        zoneText1.setText(null);
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
            zoneText1.append(salleLigne);
        }
    }

    /**
     * @param action
     */
        public void actionPerformed(ActionEvent action) {
        Object source = action.getSource();

        if (source == recap) {
            content1.setVisible(false);
            content2.setVisible(true);

        } else if (source == edt) {
            content2.setVisible(false);
            content1.setVisible(true);
            if(Pageconnexion.droitUtilisateurConnecte ==3)
            {
                //prof
                UtilisateurDAO utilisateurDAO= (UtilisateurDAO) DAOFactory.getUtilisateur();
                Utilisateur utilisateurConnecte=utilisateurDAO.chercher("NOM",Pageconnexion.nomUtilisateurConnecte).get(0);
                Seances_Enseignants_Manager seances_enseignants_manager= new Seances_Enseignants_Manager();
               ArrayList<Integer> listeSeancesId= seances_enseignants_manager.chercherSeances(utilisateurConnecte.getId());
                SeanceDAO seanceDAO= (SeanceDAO) DAOFactory.getSeanceDAO();
                ArrayList<Seance> listeSeanceEnseigants= new ArrayList<>();
               for(Integer idSeance:listeSeancesId)
               {
                   Seance seanceCourante=seanceDAO.chercher(idSeance);
                   listeSeanceEnseigants.add(seanceCourante);

               }
               this.afficherSeances(listeSeanceEnseigants);
            }
            else if(Pageconnexion.droitUtilisateurConnecte ==4)
            {
                //eleve
                UtilisateurDAO utilisateurDAO= (UtilisateurDAO) DAOFactory.getUtilisateur();
                Utilisateur utilisateurConnecte=utilisateurDAO.chercher("NOM",Pageconnexion.nomUtilisateurConnecte).get(0);
                EtudiantDAO etudiantDAO= (EtudiantDAO) DAOFactory.getEtudiantDAO();
                Etudiant etudiantConnecte= etudiantDAO.chercher(utilisateurConnecte.getId());
                ArrayList<Integer> listeSeancesId= Seances_Groupes_Manager.chercherSeances(etudiantConnecte.getIdGroupe());
                SeanceDAO seanceDAO= (SeanceDAO) DAOFactory.getSeanceDAO();
                ArrayList<Seance> listeSeanceEleve= new ArrayList<>();
                for(Integer idSeance:listeSeancesId)
                {
                    Seance seanceCourante=seanceDAO.chercher(idSeance);
                    listeSeanceEleve.add(seanceCourante);

                }
                this.afficherSeances(listeSeanceEleve);
            }

        }
    }

}
