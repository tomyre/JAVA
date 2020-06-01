/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import controller.*;
/**
 *
 * @author azglr
 */

public class Pageconnexion extends JFrame implements ActionListener {
    
     private final JLabel label;
     private final Connexion controlleurConnexions;
    
    public Pageconnexion() {
        
        //crétion de la fenêtre 
        super("Connexion");
        this.controlleurConnexions= null;
        this.label = new JLabel("");
        
        addActionListener((ActionListener) this);
 
        //le programme s'arrête dès que l'on ferme la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setVisible(true);
        setContentPane(buildContentPane());
        
    }
        
    private JPanel buildContentPane() {
        JPanel panel = new JPanel();
        JPanel contentPane = (JPanel) this.getContentPane();
        
        /*
        //création des champs de saisie "email" et "mdp"
        JTextField email = new JTextField("email");
        contentPane.add(email);
        panel.add(email);
        
        JTextField password = new JTextField("password");
        contentPane.add(password);
        panel.add(password);
        */
        
        //création des champs de saisie "email" et "mdp"
        JLabel mail=new JLabel("email");
        JTextField email = new JTextField("");
        contentPane.add(email);
        panel.add(mail);
        panel.add(email);
        
        JLabel mdp=new JLabel("password");
        JTextField password = new JTextField("");
        contentPane.add(password);
        panel.add(mdp);
        panel.add(password);

        //bouton connexion
        JButton connexion = new JButton("Connexion");
        connexion.addActionListener(this);
        panel.add(connexion);
        panel.add(label);
        
        //récupération des données des JTextField par l'action du bouton connexion
        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                String emailSaisi = email.getText();
                String mdpSaisi = password.getText();
                try {
                    Connexion nouvelleConnexion= new Connexion("bddjava",emailSaisi,mdpSaisi);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        });
        
        return panel;
        }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
  