/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import Classes_Conteneurs.DAO.DAOFactory;
import Classes_Conteneurs.DAO.UtilisateurDAO;
import Classes_Conteneurs.Utilisateur;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author azglr
 */

public class Pageconnexion extends JFrame implements ActionListener, ItemListener {
    public static int droitUtilisateurConnecte = -1;
    public static String nomUtilisateurConnecte ="";
    private final JLabel email, password, vide;
    private final JTextField mail;
    private final JPasswordField mdp;
    private final JButton connexion;
    private final JPanel panel1, panel2, nord;

    public Pageconnexion() {

        // creation par heritage de la fenetre
        super("EDT ECE");

        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(true);

        // creation des boutons
        connexion = new JButton("Connexion");

        // creation des textes
        mail = new JTextField();
        mdp = new JPasswordField();

        // creation des labels
        email = new JLabel("email", JLabel.CENTER);
        password = new JLabel("mdp", JLabel.CENTER);
        vide = new JLabel(" ", JLabel.CENTER);


        // creation des panneaux
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 3));

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));

        nord = new JPanel();
        nord.setLayout(new GridLayout(2, 1));


        // ajout des objets graphqiues dans les panneaux

        panel1.add(email);
        panel1.add(password);
        panel1.add(vide);
        panel2.add(mail);
        panel2.add(mdp);
        panel2.add(connexion);

        nord.add("North", panel1);
        nord.add("North", panel2);

        // ajout des listeners
        connexion.addActionListener(this);


        // disposition geographique des panneaux
        add("North", nord);


        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
        Action doNothing = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String emailSaisi = mail.getText();
                String mdpSaisi = String.valueOf(mdp.getPassword());
                UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getUtilisateur();
                Utilisateur utilisateurMailCorrespondant = utilisateurDAO.chercher(emailSaisi);
                if (utilisateurMailCorrespondant == null) {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir une adresse mail existante !", "Utilisateur Introuvable", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean utilisateurIdentifie = utilisateurMailCorrespondant.getMotDePasse().equals(mdpSaisi);
                if (utilisateurIdentifie) {
                    System.out.println(utilisateurMailCorrespondant.getNom()+"  "+ utilisateurMailCorrespondant.getPrenom());
                    int OptentionDroit = utilisateurMailCorrespondant.getDroit().getDroit();
                    Pageconnexion.droitUtilisateurConnecte =OptentionDroit;
                    Pageconnexion.nomUtilisateurConnecte=utilisateurMailCorrespondant.getNom();
                    if (OptentionDroit >0) {
                        switch (OptentionDroit) {
                            case 1:
                                Admin A = new Admin();
                                break;
                            case 2:
                                PageRP ref = new PageRP();
                                break;
                            case 3:
                            case 4:
                                PageClassique pageClassique = new PageClassique();
                                break;
                        }
                        //System.out.print("On affiche la fenetre de droit: "+OptentionDroit);
                    }
                }
                else {
                    // pas bon mdp
                    JOptionPane.showMessageDialog(null, "Veuillez saisir le mot de passe correct", "Mot de passe Incorrect", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        mdp.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                "doNothing");
        mdp.getActionMap().put("doNothing",
                doNothing);
    }

    /**
     * @param action
     */
    @Override
    public void actionPerformed(ActionEvent action) {
        this.connexionUtilisateur();
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void connexionUtilisateur()
    {
        String emailSaisi = mail.getText();
        String mdpSaisi = String.valueOf(mdp.getPassword());
        System.out.println(mdpSaisi);
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getUtilisateur();
        Utilisateur utilisateurMailCorrespondant = utilisateurDAO.chercher(emailSaisi);
        if (utilisateurMailCorrespondant == null) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir une adresse mail existante !", "Utilisateur Introuvable", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean utilisateurIdentifie = utilisateurMailCorrespondant.getMotDePasse().equals(mdpSaisi);
        if (utilisateurIdentifie) {
            int OptentionDroit = utilisateurMailCorrespondant.getDroit().getDroit();
            if (OptentionDroit >0) {
                switch (OptentionDroit) {
                    case 1:
                        //admin A = new Admin();
                    case 2:
                        PageRP ref = new PageRP();
                    case 3:
                        PageClassique prof = new PageClassique();
                    case 4:
                        PageClassique etudiant = new PageClassique();
                }
                //System.out.print("On affiche la fenetre de droit: "+OptentionDroit);
            }
        }
        else {
            // pas bon mdp
            JOptionPane.showMessageDialog(null, "Veuillez saisir le mot de passe correct", "Mot de passe Incorrect", JOptionPane.ERROR_MESSAGE);

        }
    }
}

    
       
   
  