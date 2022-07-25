package back;

import front.FenetreJeu;

public class JetonRotationEchange extends JetonAction
{

    public JetonRotationEchange() {
        this.nameCote1 = "Rotation";
        this.nameCote2 = "Echange";
    }

    public void actionJetonCote1(Plateau plateau, FenetreJeu fenetreJeu) {
        fenetreJeu.canRotate = true;

    }

    public void actionJetonCote2(Plateau plateau, FenetreJeu fenetreJeu) {
        fenetreJeu.firstEchange = true;
    }
}
