/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import controller.Connexion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author azglr
 */
public class Admin extends JFrame implements ActionListener {
    
    private final JLabel salle,matières, personnes, vide, rien, nada, v, r, n, modifier, supprimer, inserer;
    private final JButton salles, selectCours, selectCategories, selectModif, selectSup, selectInsert;
    private final JPanel panel1, panel2, panel3, panel4, panel5, nord;
    private final JComboBox cours, pers;
    private final JTextArea fenetreLignes;
    private final JTextField saisieInfos, saisieInfosModif, saisieInfosSup, saisieInfosInsert;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    private Statement stmt;
    private final Connexion conn;
    
    Object tab[] = new Object[] {"etudiant", "enseignant", "promotion", "groupe"};
    
    public Admin() throws SQLException, ClassNotFoundException{
        
	//création par héritage de la fenêtre
        super("Page Admin");
        
        conn = new Connexion("bddjava","root","");
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(true);
        
        //création bouton salles
        salles = new JButton("Les salles");
        selectCours = new JButton("Rechercher");
        selectCategories = new JButton("Rechercher");
        selectModif = new JButton("Modifier");
        selectSup = new JButton("Supprimer");
        selectInsert = new JButton("Inserer");
        
        //création des JComboBox
        cours = new JComboBox(conn.AfficherCours());
        pers = new JComboBox(tab);
        
        //création des JTextField
        saisieInfos = new JTextField();
        saisieInfosModif = new JTextField();
        saisieInfosSup = new JTextField();
        saisieInfosInsert = new JTextField();
        
        //création des textes
        fenetreLignes = new JTextArea();
        
        //création des labels
        salle = new JLabel(" ",JLabel.CENTER);
        matières = new JLabel("Les cours", JLabel.CENTER);
        vide = new JLabel(" ", JLabel.CENTER);
        personnes = new JLabel("Catégories de recherches", JLabel.CENTER);
        rien = new JLabel(" ", JLabel.CENTER);
        nada = new JLabel(" ", JLabel.CENTER);
        modifier = new JLabel("Modification", JLabel.CENTER);
        v = new JLabel(" ", JLabel.CENTER);
        supprimer = new JLabel("Suppression", JLabel.CENTER);
        r = new JLabel(" ", JLabel.CENTER);
        inserer = new JLabel("Insertion", JLabel.CENTER);
        n = new JLabel(" ", JLabel.CENTER);
        
        //création des panneaux
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,5));
        
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,5));
        
        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,6));
        
        panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1,6));
        
        panel5 = new JPanel();
        panel5.setLayout(new GridLayout(1,1));
        
        nord = new JPanel();
        nord.setLayout(new GridLayout(4,1));
        
        //ajout des objets dans les panneaux 
        panel1.add(salle);
        panel1.add(matières);
        panel1.add(vide);
        panel1.add(personnes);
        panel1.add(rien);
        panel1.add(nada);
        
        panel2.add(salles);
        panel2.add(cours);
        panel2.add(selectCours);
        panel2.add(pers);
        panel2.add(saisieInfos);
        panel2.add(selectCategories);
        
        panel3.add(modifier);
        panel3.add(v);
        panel3.add(supprimer);
        panel3.add(r);
        panel3.add(inserer);
        panel3.add(n);
        
        panel4.add(saisieInfosModif);
        panel4.add(selectModif);
        panel4.add(saisieInfosSup);
        panel4.add(selectSup);
        panel4.add(saisieInfosInsert);
        panel4.add(selectInsert);
        
        panel5.add(fenetreLignes);
        
        nord.add("North", panel1);
        nord.add("North", panel2);
        nord.add("North", panel3);
        nord.add("North", panel4);
       
        //ajout des listener
        salles.addActionListener(this);
        selectCours.addActionListener(this);
        selectCategories.addActionListener(this);
        selectModif.addActionListener(this);
        selectSup.addActionListener(this);
        selectInsert.addActionListener(this);
        
        //disposition géographique des panneaux
        add("North", nord);
        add("Center", panel5);
           
        // le prgm s'arrête lorsuqe l'on ferme la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer
            }
            
    });
    }
    
    public void AfficherSalles() {
         ArrayList<String> liste;
        try {
            
            // effacer les résultats
            fenetreLignes.removeAll();
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT * FROM salle";
            
            liste = conn.RemplirChampsRequete(requeteSelectionnee);
            
            // afficher les lignes de la requete selectionnee a partir de la liste
            fenetreLignes.setText("");
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }
            
        } catch (SQLException e) {
            System.out.println("problème!!!");
        }
    }
     
    public void AfficherMatières(String cours) {
         ArrayList<String> liste;
        try {
            
            // effacer les résultats
            fenetreLignes.removeAll();
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT ID FROM cours WHERE NOM = '" + cours + "'";
            
            liste = conn.RemplirChampsRequeteCours(requeteSelectionnee);
            
            // afficher les lignes de la requete selectionnee a partir de la liste
            fenetreLignes.setText("");
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }
            
        } catch (SQLException e) {
            System.out.println("problème!!!");
        }
    }
     
    public void AfficherInfos(String choixTable, String saisieInfo) {
        ArrayList<String> liste;
        try {
            
            // effacer les résultats
            fenetreLignes.removeAll();
            
            if (null != choixTable) switch (choixTable) {
                 
                case "etudiant":
                    String requeteSelectionnee1 = "SELECT ID FROM utilisateur WHERE NOM = '" + saisieInfo + "'";
                    liste = conn.RemplirChampsRequeteInfos1(requeteSelectionnee1);
                    
                    // afficher les lignes de la requete selectionnee a partir de la liste
                    fenetreLignes.setText("");
                    
                    for (String liste1 : liste) {
                        fenetreLignes.append(liste1);
                    }
                    break;
                
                /*
                case "enseignant":
                    String requeteSelectionnee2 = "SELECT ID FROM utilisateur WHERE NOM = '" + saisieInfo + "'";
                    liste = conn.RemplirChampsRequeteInfos2(requeteSelectionnee2);
                    // afficher les lignes de la requete selectionnee a partir de la liste
                    fenetreLignes.setText("");
                    for (String liste1 : liste) {
                        fenetreLignes.append(liste1);
                    }
                    break;
                */
                    
                case "promotion":
                    String requeteSelectionnee3 = "SELECT ID FROM promotion WHERE NOM = '" + saisieInfo + "'";
                    liste = conn.RemplirChampsRequeteInfos3(requeteSelectionnee3);
                    
                    // afficher les lignes de la requete selectionnee a partir de la liste
                    fenetreLignes.setText("");
                    
                    for (String liste1 : liste) {
                        fenetreLignes.append(liste1);
                    }
                    break;
                     
                case "groupe":
                    String requeteSelectionnee4 = "SELECT ID FROM groupe WHERE NOM = '" + saisieInfo + "'";
                    liste = conn.RemplirChampsRequeteInfos4(requeteSelectionnee4);
                    
                    // afficher les lignes de la requete selectionnee a partir de la liste
                    fenetreLignes.setText("");
                    
                    for (String liste1 : liste) {
                        fenetreLignes.append(liste1);
                    }
                    break;
                     
                default:
                     break;
            }
            
        } catch (SQLException e) {
            System.out.println("problème!!!");
        }
    }
     
    /**
     *
     * @param action
     */
    @Override
        public void actionPerformed(ActionEvent action) {
            Object source = action.getSource();
            
            if (source == salles) {             
                //récupérer les lignes de la tables "salle"
                AfficherSalles();
                
            } else if (source == selectCours) {
                String coursBox =  (String) cours.getSelectedItem();
                AfficherMatières(coursBox);
                
            } else if (source == selectCategories) {
                String persBox =  (String) pers.getSelectedItem();
                String infos = saisieInfos.getText();
                AfficherInfos(persBox, infos);
                
            } else if (source == selectModif) {
                //String modif = saisieModif.getText();
                //conn.OperationMofidication(table, colonne, ID, modif);
                
            } else if (source == selectSup) {
                
                
            } else if (source == selectInsert) {
                
            }
        }

    private Object getId(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

