///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import java.sql.*;
//import javax.swing.JOptionPane;
//
///**
// *
// * @author tom
// */
//public class Operation {
//
//    private final Connection conn;
//    private final Statement stmt;
//    private ResultSet rset = null;
//    private ResultSetMetaData rsetMeta;
//    private String requete;
//    private int res;
//
//
//    public void OperationMofidication(String table,String colonne,String ID, String modif){
//
//        requete = "UPDATE "+table+" SET "+colonne+" = '"+modif+"' WHERE ID = '"+ID+"'";
//
//        try{
//            res =stmt.executeUpdate(requete);
//            if(res==1)
//            {
//                JOptionPane.showMessageDialog(null, "Informations modifié", "Info", JOptionPane.INFORMATION_MESSAGE);
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null, "Erreur  dans les saisies veuillez vous assurer que vous ne vous etes pas trompé", "ERREUR", JOptionPane.ERROR_MESSAGE);
//            }
//        }catch (SQLException e){
//        JOptionPane.showMessageDialog(null, "Erreur  dans les saisies veuillez vous assurer que vous ne vous etes pas trompé", "ERREUR", JOptionPane.ERROR_MESSAGE);
//        }
//   }
//
// public void OperationInsertionCours(String table,String NomCours){
//        requete = "INSERT INTO "+table+"(NOM) VALUES ('"+NomCours+"')";
//
//        try{
//            res=stmt.executeUpdate(requete);
//            if(res==1)
//            {
//                JOptionPane.showMessageDialog(null, "Le cours à éte ajouté", "Info", JOptionPane.INFORMATION_MESSAGE);
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null, "Erreur 2 dans les saisies veuillez vous assurer que vous ne vous etes pas trompé", "ERREUR", JOptionPane.ERROR_MESSAGE);
//            }
//        }catch (SQLException e){
//        JOptionPane.showMessageDialog(null, "Erreur 1 dans les saisies veuillez vous assurer que vous ne vous etes pas trompé", "ERREUR", JOptionPane.ERROR_MESSAGE);
//        }
//   }
//}