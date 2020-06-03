/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import controller.Connexion;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
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
    private Connection conn;
    
    /**
     * Constructeur avec 3 paramètres : nom, login et password de la BDD locale
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        // chargement driver "com.mysql.jdbc.Driver"
       Class.forName("com.mysql.jdbc.Driver");   
       
       String urlDatabase = "jdbc:mysql://localhost/" + nameDatabase;
       
       //création d'une connexion JDBC à la base 
       conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
       
       // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
        //System.out.println("connexion reussi");
        }
    
    public PageRP() throws SQLException{
        
	//création par héritage de la fenêtre
        super("Page Référent Pédagogique");
        
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
        nord.setLayout(new GridLayout(2,1));
        
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
        
        //ajout des listener
        addActionListener(this);
        
        //disposition géographique des panneaux
        add("North", nord);
           
        // le prgm s'arrête lorsuqe l'on ferme la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
    });
    }
    
    public void afficherLignes() {
        try {
            ArrayList<String> liste;

            // effacer les résultats
            fenetreLignes.removeAll();

            // recupérér les résultats de la table selectionnee
            liste = remplirChampsTable();

            // afficher les champs de la table selectionnee 
            fenetreLignes.setText("");
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }
            
        } catch (SQLException e) {
        }
    }
    
    public ArrayList remplirChampsTable() throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from salle");

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" à la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }

    /**
     *
     * @param action
     */
    @Override
        public void actionPerformed(ActionEvent action) {
               
                try {
                    Connexion nouvelleConnexion= new Connexion("bddjava","root","");
                    
                } catch (SQLException throwables) {
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Pageconnexion.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
}