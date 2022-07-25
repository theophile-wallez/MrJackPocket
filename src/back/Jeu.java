package back;

import java.util.*;
public class Jeu {

    public void updateJeu(Plateau plateau) {
        ArrayList<Integer> districtVisible = new ArrayList<>();
        ArrayList<Integer> districtInvisible = new ArrayList<>();

        // Cette boucle permet d'ajouter les districts visibles à la liste districtVisible
        // et les districts non visibles à la liste districtInvisible
        for (int numDistrict = 0 ; numDistrict < 9 ; numDistrict++) {   // On parcourt tous les districts de 0 à 8
            if (isDistrictVisible(plateau, numDistrict / 3, numDistrict % 3))   // On récupère l'indice du numéro de ligne et l'indice du numéro de colonne
                                                                                     // correspondant au district numéro numDistrict
                districtVisible.add(numDistrict);
            else
                districtInvisible.add(numDistrict);
        }


        // Si MrJack est visible, on retire tous les districts non visibles
        if (isMrJackVisible(plateau, districtVisible)) {
            for (Integer number : districtInvisible) {
                int ligne = number / 3;
                int colonne = number % 3;
                if (plateau.district[ligne][colonne].getCoteDistrict() == 0) {
                    plateau.district[ligne][colonne].setCoteDistrict(1);
                    plateau.setNumberOfVisiblesDistricts(plateau.getNumberOfVisiblesDistricts() - 1);
                }
                System.out.println("MrJack est visible");
            }
        }

        // Si MrJack n'est pas visible, on retire les districts visibles
        else {
                for (Integer number : districtVisible) {
                    int ligne = number / 3;
                    int colonne = number % 3;
                    if (plateau.district[ligne][colonne].getCoteDistrict() == 0) {
                        plateau.district[ligne][colonne].setCoteDistrict(1);
                        plateau.setNumberOfVisiblesDistricts(plateau.getNumberOfVisiblesDistricts() - 1);
                    }
                }
                // MrJack n'est pas visible, on lui ajoute donc un sablier
                plateau.MrJackIdentity.setSablierEarned(plateau.MrJackIdentity.getSablierEarned() + 1);
                System.out.println("MrJack n'est pas visible");
            }
    }


    public boolean isDistrictVisible(Plateau plateau, int i, int j) {
        // On note i pour la ligne et j pour la colonne
        // Si le district est visible par un des détectives, la méthode renvoie true
        if (i == 0 && j == 0) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 0 && (plateau.district[i][j].getOrientation() == 1
                                                                       || plateau.district[i][j].getOrientation() == 2
                                                                       || plateau.district[i][j].getOrientation() == 3)) {

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 11 && (plateau.district[i][j].getOrientation() == 0
                                                                             || plateau.district[i][j].getOrientation() == 1
                                                                             || plateau.district[i][j].getOrientation() == 2)){

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 3 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 1
                        || plateau.district[i][j].getOrientation() == 2) && plateau.district[0][1].getOrientation() != 1){

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 3 &&
                        plateau.district[i][j].getOrientation() != 1 &&
                        (plateau.district[0][1].getOrientation() != 1 && plateau.district[0][1].getOrientation() != 3) &&
                        plateau.district[0][2].getOrientation() != 1
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 8 &&
                        plateau.district[i][j].getOrientation() != 2 &&
                        (plateau.district[1][0].getOrientation() != 0 && plateau.district[0][1].getOrientation() != 2) &&
                        plateau.district[2][0].getOrientation() != 2
                ){
                    return true;
                }


            }
        }
        else if (i == 0 && j == 1) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 1 && (plateau.district[i][j].getOrientation() == 1
                        || plateau.district[i][j].getOrientation() == 2
                        || plateau.district[i][j].getOrientation() == 3)) {

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 3 &&
                        plateau.district[i][j].getOrientation() != 1 &&
                        (plateau.district[0][2].getOrientation() != 1 && plateau.district[0][2].getOrientation() != 3)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 7 &&
                        plateau.district[i][j].getOrientation() != 2 &&
                        (plateau.district[1][1].getOrientation() != 0 && plateau.district[1][1].getOrientation() != 2) &&
                        (plateau.district[2][1].getOrientation() != 0 && plateau.district[2][1].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 11 &&
                        plateau.district[i][j].getOrientation() != 3 &&
                        (plateau.district[0][0].getOrientation() != 1 && plateau.district[0][0].getOrientation() != 3)
                ){
                    return true;
                }


            }
        }
        else if (i == 0 && j == 2) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 2 && (plateau.district[i][j].getOrientation() == 1
                        || plateau.district[i][j].getOrientation() == 2
                        || plateau.district[i][j].getOrientation() == 3)) {

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 3 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 2
                        || plateau.district[i][j].getOrientation() == 3)){

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 6 &&
                        plateau.district[i][j].getOrientation() != 2 &&
                        (plateau.district[1][2].getOrientation() != 0 && plateau.district[1][2].getOrientation() != 2) &&
                        (plateau.district[2][2].getOrientation() != 0 && plateau.district[2][2].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 11 &&
                        plateau.district[i][j].getOrientation() != 3 &&
                        (plateau.district[0][1].getOrientation() != 1 && plateau.district[0][1].getOrientation() != 3) &&
                        (plateau.district[0][0].getOrientation() != 1 && plateau.district[0][0].getOrientation() != 3)
                ){
                    return true;
                }


            }
        }

        else if (i == 1 && j == 0) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 10 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 1
                        || plateau.district[i][j].getOrientation() == 2)) {

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 0 &&
                        plateau.district[i][j].getOrientation() !=  0 &&
                        (plateau.district[0][0].getOrientation() != 0 && plateau.district[0][0].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 4 &&
                        plateau.district[i][j].getOrientation() !=  1 &&
                        (plateau.district[1][1].getOrientation() != 1 && plateau.district[1][1].getOrientation() != 3) &&
                        (plateau.district[1][2].getOrientation() != 1 && plateau.district[1][2].getOrientation() != 3)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 8 &&
                        plateau.district[i][j].getOrientation() !=  2 &&
                        (plateau.district[0][0].getOrientation() != 1 && plateau.district[1][1].getOrientation() != 3) &&
                        (plateau.district[2][0].getOrientation() != 0 && plateau.district[2][0].getOrientation() != 2)
                ){
                    return true;
                }


            }
        }
        else if (i == 1 && j == 1) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 1 &&
                        plateau.district[i][j].getOrientation() != 0 &&
                        (plateau.district[0][1].getOrientation() != 0 && plateau.district[0][1].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 4 &&
                        plateau.district[i][j].getOrientation() != 1 &&
                        (plateau.district[1][2].getOrientation() != 1 && plateau.district[1][2].getOrientation() != 3)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 7 &&
                        plateau.district[i][j].getOrientation() != 2 &&
                        (plateau.district[2][1].getOrientation() != 0 && plateau.district[2][1].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 10 &&
                        plateau.district[i][j].getOrientation() != 3 &&
                        (plateau.district[1][0].getOrientation() != 1 && plateau.district[1][0].getOrientation() != 3)
                ){
                    return true;
                }


            }
        }
        else if (i == 1 && j == 2) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 4 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 2
                        || plateau.district[i][j].getOrientation() == 3)) {

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 2 &&
                        plateau.district[i][j].getOrientation() != 0 &&
                        (plateau.district[0][2].getOrientation() != 0 && plateau.district[0][2].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 6 &&
                        plateau.district[i][j].getOrientation() != 2 &&
                        (plateau.district[2][2].getOrientation() != 0 && plateau.district[2][2].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 10 &&
                        plateau.district[i][j].getOrientation() != 1 &&
                        (plateau.district[1][1].getOrientation() != 1 && plateau.district[1][1].getOrientation() != 3) &&
                        (plateau.district[1][0].getOrientation() != 1 && plateau.district[1][0].getOrientation() != 3)

                ){
                    return true;
                }


            }
        }

        else if (i == 2 && j == 0) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 8 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 1
                        || plateau.district[i][j].getOrientation() == 3)) {

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 9 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 1
                        || plateau.district[i][j].getOrientation() == 2)){

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 0 &&
                        plateau.district[i][j].getOrientation() != 0 &&
                        (plateau.district[1][0].getOrientation() != 0 && plateau.district[1][0].getOrientation() != 2) &&
                        (plateau.district[0][0].getOrientation() != 0 && plateau.district[0][0].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 5 &&
                        plateau.district[i][j].getOrientation() != 1 &&
                        (plateau.district[2][1].getOrientation() != 1 && plateau.district[2][1].getOrientation() != 3) &&
                        (plateau.district[2][2].getOrientation() != 1 && plateau.district[2][2].getOrientation() != 3)
                ){
                    return true;
                }


            }
        }
        else if (i == 2 && j == 1) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 7 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 1
                        || plateau.district[i][j].getOrientation() == 3)) {

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 1 &&
                        plateau.district[i][j].getOrientation() != 0 &&
                        (plateau.district[1][1].getOrientation() != 0 && plateau.district[1][1].getOrientation() != 2) &&
                        (plateau.district[0][1].getOrientation() != 0 && plateau.district[0][1].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 5 &&
                        plateau.district[i][j].getOrientation() != 1 &&
                        (plateau.district[2][2].getOrientation() != 1 && plateau.district[2][2].getOrientation() != 3)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 9 &&
                        plateau.district[i][j].getOrientation() != 3 &&
                        (plateau.district[2][0].getOrientation() != 1 && plateau.district[2][0].getOrientation() != 3)
                ){
                    return true;
                }


            }
        }
        else if (i == 2 && j == 2) {
            for (int numDetective = 0; numDetective < 3; numDetective++) {
                if (plateau.detective[numDetective].getPosition() == 5 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 2
                        || plateau.district[i][j].getOrientation() == 3)) {

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 6 && (plateau.district[i][j].getOrientation() == 0
                        || plateau.district[i][j].getOrientation() == 1
                        || plateau.district[i][j].getOrientation() == 3)){

                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 2 &&
                        plateau.district[i][j].getOrientation() != 0 &&
                        (plateau.district[1][2].getOrientation() != 0 && plateau.district[1][2].getOrientation() != 2) &&
                        (plateau.district[0][2].getOrientation() != 0 && plateau.district[0][2].getOrientation() != 2)
                ){
                    return true;
                }
                else if (plateau.detective[numDetective].getPosition() == 9 &&
                        plateau.district[i][j].getOrientation() != 3 &&
                        (plateau.district[2][1].getOrientation() != 1 && plateau.district[2][1].getOrientation() != 3) &&
                        (plateau.district[2][0].getOrientation() != 1 && plateau.district[2][0].getOrientation() != 3)
                ){
                    return true;
                }


            }
        }
        return false;
    }

    // Méthode qui renvoie true si le district de MrJack fait partie des districts visibles
    public boolean isMrJackVisible(Plateau plateau, ArrayList<Integer> quartierVisible) {
        for (Integer number : quartierVisible) {
            if (plateau.district[number / 3][number % 3].getPersonnage()
                    == plateau.MrJackIdentity.getCarteMrJack())
                return true;
        }
        return false;
    }


    // Conditions de fin de partie
    public boolean finDePartie(Plateau plateau) {
        boolean isTrue = false;
        System.out.println(plateau.getNumberOfVisiblesDistricts());
        if (plateau.getRound() == 8 && plateau.getNumberOfVisiblesDistricts() > 1) {
            isTrue = true;
        } else if (plateau.getNumberOfVisiblesDistricts() > 1 && plateau.MrJackIdentity.getSablierEarned() == 6) {
            isTrue = true;
        } else if (plateau.getNumberOfVisiblesDistricts() == 1 && plateau.MrJackIdentity.getSablierEarned() < 6) {
            isTrue = true;
        }
        return isTrue;
    }
}

