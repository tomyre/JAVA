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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author azglr
 */
public class PageRP extends JFrame implements ActionListener {
    
    private final JLabel salle,matières,personnes;
    private final JButton salles;
    private final JPanel panel1, panel2, panel3, nord;
    private final JComboBox cours, pers;
    private final JTextArea fenetreLignes;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    private Statement stmt;
    private Connexion conn;
    
    public PageRP() throws SQLException, ClassNotFoundException{
        
	//création par héritage de la fenêtre
        super("Page Référent Pédagogique");
        
        conn = new Connexion("bddjava","root","");
        
        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(true);
        
        //création bouton salles
        salles = new JButton("Les salles");
        
        //création des JComboBox
        cours = new JComboBox();
        pers = new JComboBox();
        
        //création des textes
        fenetreLignes = new JTextArea();
        
        //création des labels
        salle = new JLabel(" ",JLabel.CENTER);
        matières = new JLabel("Les cours", JLabel.CENTER);
        personnes = new JLabel("Les types de personnes", JLabel.CENTER);
        
        //création des panneaux
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,3));
        
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,3));
        
        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,3));
        
        nord = new JPanel();
        nord.setLayout(new GridLayout(3,1));
        
        //ajout des objets dans les panneaux 
        panel1.add(salle);
        panel1.add(matières);
        panel1.add(personnes);
        
        panel2.add(salles);
        panel2.add(cours);
        panel2.add(pers);
        
        panel3.add(fenetreLignes);
        
        nord.add("North", panel1);
        nord.add("North", panel2);
        nord.add("North", panel3);
        
        //ajout des listener
        salles.addActionListener(this);
        
        //disposition géographique des panneaux
        add("North", nord);
           
        // le prgm s'arrête lorsuqe l'on ferme la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer
            }
    });
    }
    
     public void afficherLignes() {
         ArrayList<String> liste;
        try {
            
            // effacer les résultats
            fenetreLignes.removeAll();
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT * FROM salle";
            
            liste = conn.remplirChampsRequete(requeteSelectionnee);
            
            // afficher les lignes de la requete selectionnee a partir de la liste
            fenetreLignes.setText("");
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
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
                    
                    //récupérer les lignes de la tables "salle"
                    afficherLignes();
        }
}