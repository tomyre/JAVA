/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author azglr
 */
public class RP extends JFrame implements ActionListener {
    
    private final JLabel label = new JLabel("");
    
    //création de la liste des cours 
    private JComboBox cours;
    private JComboBox pers;
    
    public RP(){
	super("Page Référent Pédagogique");
        
        addActionListener(this);
           
        //le programme s'arrête dès que l'on ferme la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setVisible(true);
        setContentPane(buildContentPane());
    }    
    
    private JPanel buildContentPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));  
        
        //bouton d'accès aux salles
        JButton salles = new JButton("Les salles");
        salles.addActionListener(this);
        panel.add(salles);
        panel.add(label);
        
        //liste des cours
        Object[] matières = new Object[] {"POO JAVA", "Thermodynamique", "Web Dynamique"};
        cours = new JComboBox(matières);
        panel.add(cours);
        
        //liste des personnes
        Object[] personnes = new Object[] {"Professeurs", "Etudiants", "Promo", "Groupe"};
        pers = new JComboBox(personnes);
        panel.add(pers);
        
        return panel;
    }
    
    
    
    public JComboBox getCours() {
        return cours;
    }
    
    public JComboBox getPers() {
        return pers;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        label.setText("vous avez cliqué");
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
