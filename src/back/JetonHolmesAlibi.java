package back;
import javax.swing.*;

import front.FenetreJeu;
import front.Images;


public class JetonHolmesAlibi extends JetonAction {
    public JetonHolmesAlibi() {
        this.nameCote1 = "Holmes";
        this.nameCote2 = "Alibi";
    }

    // Action du Jeton Holmes
    public void actionJetonCote1(Plateau plateau, FenetreJeu fenetreJeu) {
        int pos = plateau.detective[0].getPosition();

        int pos1 = fenetreJeu.nextDetectivePos(1,pos);
        int pos2 = fenetreJeu.nextDetectivePos(2,pos);

        fenetreJeu.holmesEmplacement1.setBounds(FenetreJeu.getPositionInPixel(plateau, 1,pos1).get(0), FenetreJeu.getPositionInPixel(plateau,1,pos1).get(1), 65, 65);
        fenetreJeu.holmesEmplacement2.setBounds(FenetreJeu.getPositionInPixel(plateau, 1,pos2).get(0), FenetreJeu.getPositionInPixel(plateau,1,pos2).get(1), 65, 65);

        ImageIcon sherlockImageGris = Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonHolmesGris.png")), 45, 45);

        fenetreJeu.holmesEmplacement1.setIcon(sherlockImageGris);
        fenetreJeu.holmesEmplacement2.setIcon(sherlockImageGris);

        fenetreJeu.holmesEmplacement1.setRolloverIcon(fenetreJeu.sherlockImage);
        fenetreJeu.holmesEmplacement2.setRolloverIcon(fenetreJeu.sherlockImage);

        FenetreJeu.cleanButton(fenetreJeu.holmesEmplacement1);
        FenetreJeu.cleanButton(fenetreJeu.holmesEmplacement2);

        fenetreJeu.holmesEmplacement1.setVisible(true);
        fenetreJeu.holmesEmplacement2.setVisible(true);
    }

    // Action du Jeton Alibi
    public void actionJetonCote2(Plateau plateau, FenetreJeu fenetreJeu) {
        fenetreJeu.isJetonUtilisable = true;
        String aQuiLeTour = "";

        // On détermine qui doit jouer en fonction du tour (pair ou impair) et en fonction du nombre de jetons présents sur le plateau à ce moment-là
        aQuiLeTour = ((plateau.getRound() % 2 == 1) && (plateau.jetonsSurPlateau == 0 || plateau.jetonsSurPlateau == 3))
                || ((plateau.getRound() % 2 == 0) && (plateau.jetonsSurPlateau == 1 || plateau.jetonsSurPlateau == 2)) ? "Detective" : "MrJack";

        if (aQuiLeTour.equals("Detective")) {
            plateau.MrJackIdentity.paquetCarteJack.add(plateau.paquetCartesAlibi.get(0));
            plateau.MrJackIdentity.setSablierEarned(plateau.MrJackIdentity.getSablierEarned() + plateau.paquetCartesAlibi.get(0).getNumberOfBonusSablier());
            plateau.paquetCartesAlibi.remove(0);
            fenetreJeu.cartesAlibiDisplay();
        }

        else {
            int i2 = 0;
            int j2 = 0;

            for (int i = 0 ; i < 3 ; i++) {
                for (int j = 0 ; j < 3 ; j++) {
                    if (plateau.district[i][j].getPersonnage() == plateau.paquetCartesAlibi.get(0)) {
                        i2 = i;
                        j2 = j;
                    }
                }
            }
            if (plateau.district[i2][j2].getCoteDistrict() == 0) {
                plateau.district[i2][j2].setCoteDistrict(1);
                if (plateau.district[i2][j2].getPersonnage().getName().equals("Gris")) {
                    plateau.district[i2][j2].setCoteDistrict(-1);
                    plateau.district[i2][j2].setOrientation(-1);
                }
                plateau.setNumberOfVisiblesDistricts(plateau.getNumberOfVisiblesDistricts() - 1);
            }
            fenetreJeu.distritsDisplay();
            plateau.paquetCartesAlibi.remove(0);
        }
        fenetreJeu.nextAction();
    }
}
