package Classes_Conteneurs.DAO;

import controller.Connexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOFactory {
    protected static Connection conn = null;

    static {
        try {
            conn = Connexion.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DAO getCoursDAO(){
        return new CoursDAO(conn);
    }
    public static DAO getEnseignantDAO(){
        return new EnseignantDAO(conn);
    }
    public static DAO getEtudiantDAO(){
        return new EtudiantDAO(conn);
    }
    public static DAO getGroupeDAO(){
        return new GroupeDAO(conn);
    }
    public static DAO getPromotionDAO(){
        return new PromotionDAO(conn);
    }
    public static DAO getSalleDAO(){
        return new SalleDAO(conn);
    }
    public static DAO getSeanceDAO(){
        return new SeanceDAO(conn);
    }
    public static DAO getSiteDAO(){
        return new SiteDAO(conn);
    }
    public static DAO getTypeCours(){
        return new Type_CoursDAO(conn);
    }
    public static DAO getUtilisateur(){
        return new UtilisateurDAO(conn);
    }
}
