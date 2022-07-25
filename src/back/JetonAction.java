package back;

import front.FenetreJeu;

import java.util.Random;

public class JetonAction {
    public String nameCote1;
    public String nameCote2;
    public int coteJeton;

    public void lanceJeton() {
        Random random = new Random();
        this.coteJeton = random.nextInt(2) + 1;
    }

    public void changerCoteJeton() {
        this.coteJeton = (this.coteJeton == 1) ? 2 : 1;
    }

    public int getPileFace() {return coteJeton;}

    public String getNameCote1() {return nameCote1;}

    public String getNameCote2() {return nameCote2;}

    public void actionJetonCote1(Plateau plateau, FenetreJeu fenetreJeu) {}

    public void actionJetonCote2(Plateau plateau, FenetreJeu fenetreJeu) {}

}