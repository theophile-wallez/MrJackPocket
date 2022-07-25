package back;

import front.*;

import javax.swing.*;

public class JetonRotationJoker extends JetonAction
{
    public JetonRotationJoker() {
        this.nameCote1 = "Rotation";
        this.nameCote2 = "Joker";
    }

    public void actionJetonCote1(Plateau plateau, FenetreJeu fenetreJeu) {
        fenetreJeu.canRotate = true;
    }

    public void actionJetonCote2(Plateau plateau, FenetreJeu fenetreJeu) {
        JButton button;
        for (int i = 0; i < 3; i++) {
            int pos = plateau.detective[i].getPosition();
            switch (i){
                case 0: button = fenetreJeu.holmesEmplacement1;break;
                case 1: button = fenetreJeu.watsonEmplacement1;break;
                case 2: button = fenetreJeu.tobyEmplacement1;break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }

            int pos1 = pos+1;
            if (pos1 == 12)
                pos1 = 0;

            button.setBounds(FenetreJeu.getPositionInPixel(plateau, 1,pos1).get(0), FenetreJeu.getPositionInPixel(plateau,1,pos1).get(1), 65, 65);

        }

        ImageIcon watsonImageGris = Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonWatsonGris.png")), 45, 45);
        ImageIcon tobyImageGris = Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonChienGris.png")), 45, 45);
        ImageIcon sherlockImageGris = Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonHolmesGris.png")), 45, 45);


        fenetreJeu.holmesEmplacement1.setIcon(sherlockImageGris);
        fenetreJeu.watsonEmplacement1.setIcon(watsonImageGris);
        fenetreJeu.tobyEmplacement1.setIcon(tobyImageGris);

        fenetreJeu.holmesEmplacement1.setRolloverIcon(fenetreJeu.sherlockImage);
        fenetreJeu.watsonEmplacement1.setRolloverIcon(fenetreJeu.watsonImage);
        fenetreJeu.tobyEmplacement1.setRolloverIcon(fenetreJeu.tobyImage);

        FenetreJeu.cleanButton(fenetreJeu.holmesEmplacement1);
        FenetreJeu.cleanButton(fenetreJeu.watsonEmplacement1);
        FenetreJeu.cleanButton(fenetreJeu.tobyEmplacement1);

        fenetreJeu.holmesEmplacement1.setVisible(true);
        fenetreJeu.watsonEmplacement1.setVisible(true);
        fenetreJeu.tobyEmplacement1.setVisible(true);
    }
}
