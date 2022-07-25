package back;

import front.FenetreJeu;
import front.Images;

import javax.swing.*;

public class JetonWatsonToby extends JetonAction {
    public JetonWatsonToby() {
        this.nameCote1 = "Watson";
        this.nameCote2 = "Toby";
    }


    public void actionJetonCote1(Plateau plateau, FenetreJeu fenetreJeu) {
        // Definition de la position en pixel de l'image en fonction de la position de la carte sur le plateau

        int pos = plateau.detective[1].getPosition();

        int pos1 = fenetreJeu.nextDetectivePos(1,pos);
        int pos2 = fenetreJeu.nextDetectivePos(2,pos);

        fenetreJeu.watsonEmplacement1.setBounds(FenetreJeu.getPositionInPixel(plateau, 1,pos1).get(0), FenetreJeu.getPositionInPixel(plateau,1,pos1).get(1), 65, 65);
        fenetreJeu.watsonEmplacement2.setBounds(FenetreJeu.getPositionInPixel(plateau, 1,pos2).get(0), FenetreJeu.getPositionInPixel(plateau,1,pos2).get(1), 65, 65);

        // Griser l'image
        ImageIcon watsonImageGris = Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonWatsonGris.png")), 45, 45);

        fenetreJeu.watsonEmplacement1.setIcon(watsonImageGris);
        fenetreJeu.watsonEmplacement2.setIcon(watsonImageGris);

        FenetreJeu.cleanButton(fenetreJeu.watsonEmplacement1);
        FenetreJeu.cleanButton(fenetreJeu.watsonEmplacement2);

        fenetreJeu.watsonEmplacement1.setRolloverIcon(fenetreJeu.watsonImage);
        fenetreJeu.watsonEmplacement2.setRolloverIcon(fenetreJeu.watsonImage);

        fenetreJeu.watsonEmplacement1.setVisible(true);
        fenetreJeu.watsonEmplacement2.setVisible(true);
    }

    public void actionJetonCote2(Plateau plateau, FenetreJeu fenetreJeu) {
        int pos = plateau.detective[2].getPosition();

        int pos1 = fenetreJeu.nextDetectivePos(1,pos);
        int pos2 = fenetreJeu.nextDetectivePos(2,pos);

        fenetreJeu.tobyEmplacement1.setBounds(FenetreJeu.getPositionInPixel(plateau, 1,pos1).get(0), FenetreJeu.getPositionInPixel(plateau,1,pos1).get(1), 65, 65);
        fenetreJeu.tobyEmplacement2.setBounds(FenetreJeu.getPositionInPixel(plateau, 1,pos2).get(0), FenetreJeu.getPositionInPixel(plateau,1,pos2).get(1), 65, 65);


        ImageIcon tobyImageGris = Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonChienGris.png")), 45, 45);

        fenetreJeu.tobyEmplacement1.setIcon(tobyImageGris);
        fenetreJeu.tobyEmplacement2.setIcon(tobyImageGris);

        fenetreJeu.tobyEmplacement1.setRolloverIcon(fenetreJeu.tobyImage);
        fenetreJeu.tobyEmplacement2.setRolloverIcon(fenetreJeu.tobyImage);

        FenetreJeu.cleanButton(fenetreJeu.tobyEmplacement2);
        FenetreJeu.cleanButton(fenetreJeu.tobyEmplacement1);

        fenetreJeu.tobyEmplacement1.setVisible(true);
        fenetreJeu.tobyEmplacement2.setVisible(true);
    }
}
