package MAJDonnes;

import Classes_Conteneurs.Cours;
import Classes_Conteneurs.DAO.*;
import Classes_Conteneurs.Seance;
import Classes_Conteneurs.TypeCours;

import java.util.ArrayList;

public class MAJCours {
        public static boolean AssigneModificationTypeCours (int ID,String Nom) {
            Type_CoursDAO types_coursDAO = (Type_CoursDAO) DAOFactory.getTypeCours();
            TypeCours seancePretendante = types_coursDAO.chercher(ID);
            if (seancePretendante == null) {
                return false;
            } else {

                return false;
            }
        }
        public static boolean AssigneModificationNomCours(int ID, String Nom){
            CoursDAO coursDAO = (CoursDAO) DAOFactory.getCoursDAO();
            Cours seancePretendante = coursDAO.chercher(ID);
            if (seancePretendante == null) {
                return false;
            }else
            {

            }
            return false;
        }
}
