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
    
    //initialisation 
    CardLayout cl = new CardLayout();
    JPanel content = new JPanel();
    
    //liste des conteneurs
    String[] listContent = {"edt", "recap"};
    int indice = 0;
    
    public Etudiant() {
        
        //création de la fenetre
        super("Page etudiant");
        
        //le programme s'arrête dès que l'on ferme la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        
        
        //coloration d background des conteneurs
        JPanel card1 = new JPanel();
        card1.setBackground(Color.pink);
        JPanel card2 = new JPanel();
        card2.setBackground(Color.lightGray);
        
        JPanel boutonPane = new JPanel();
        
        //conteneur edt
        
        JButton edt = new JButton("Emploi du temps");
        //action du bouton edt
        edt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                cl.next(content); //passsage au porchain conteneur
            }
        });
        
        //conteneur recap
        
        JButton recap = new JButton("Récapitulatif des cours");
        //action du bouton recap
        recap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(++indice >1)
                    indice = 0;
                
                cl.show(content, listContent[indice]); //
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
    }
    
}
