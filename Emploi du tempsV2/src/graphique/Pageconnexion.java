/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import controller.Connexion;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author azglr
 */

public class Pageconnexion extends JFrame implements ActionListener,ItemListener {
    
    private final JLabel email,password,vide;
    private final JTextField mail,mdp;
    private final JButton connexion;
    private final JPanel panel1, panel2, nord;
    private String emailSaisi,mdpSaisi;

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
        mdp = new JTextField();

        // creation des labels
        email = new JLabel("email", JLabel.CENTER);
        password =new JLabel("mdp",JLabel.CENTER);
        vide =new JLabel(" ",JLabel.CENTER);


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
  }

    /**
     *
     * @param action
     */
    @Override
        public void actionPerformed(ActionEvent action) {
                emailSaisi = email.getText();
                mdpSaisi = password.getText();
                try {
                    Connexion nouvelleConnexion= new Connexion("bddjava","root","");
                    nouvelleConnexion.RecupererDonnees(emailSaisi,mdpSaisi);
                    
                } catch (SQLException throwables) {
                } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pageconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }


            }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        }

    
       
   
  