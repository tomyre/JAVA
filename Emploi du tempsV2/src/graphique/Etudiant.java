/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author azglr
 */
public class Etudiant extends JFrame {

//    private final JTextArea fenetreEdt;

    //initialisation
    CardLayout cl = new CardLayout();
    JPanel content = new JPanel();
    
    //liste des conteneurs
    String[] listContent = {"edt", "recap"};
    int indice = 0;
    
    public Etudiant() {
        
        //création de la fenetre
        super("Page etudiant");

        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(true);

        //création d'un JTextArea
//        fenetreEdt = new JTextArea();

        //coloration d background des conteneurs
        JPanel card1 = new JPanel();
        card1.setBackground(Color.pink);
        JPanel card2 = new JPanel();
        card2.setBackground(Color.lightGray);
        
        JPanel boutonPane = new JPanel();
        
        //ccréation des boutons
        JButton edt = new JButton("Emploi du temps");
        JButton recap = new JButton("Récapitulatif des cours");

        //ajout des listener

        //action du bouton edt
        edt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                cl.next(content); //passage au porchain conteneur
   }
        });

        //action du bouton recap
        recap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(++indice >1)
                    indice = 0;
                cl.show(content, listContent[indice]);
            }
        });
        
        boutonPane.add(edt);
        boutonPane.add(recap);
        
        content.setLayout(cl); //définition du layout
        
        content.add(card1, listContent[0]);
        content.add(card2, listContent[1]);
    
        this.getContentPane().add(boutonPane, BorderLayout.NORTH);
        this.getContentPane().add(content, BorderLayout.CENTER);
        setVisible(true);

        // le prgm s'arrête lorsuqe l'on ferme la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer
            }
        });
    }
    
}
