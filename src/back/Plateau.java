package back;

import java.util.*;

public class Plateau {
    public District[][] district = new District[3][3]; //Plateau 3*3
    public Detective[] detective = new Detective[3]; //3 détectives
    public ArrayList<JetonAction> listeJetons = new ArrayList<>();
    public ArrayList<Personnage> paquetCartesAlibi = new ArrayList<>();
    public int jetonsSurPlateau;
    public MrJack MrJackIdentity;
    private int numberOfVisiblesDistricts;
    private int round;

    public Plateau() {

        this.round = 0;
        this.numberOfVisiblesDistricts = 9;
        this.jetonsSurPlateau = 4;

        addAlibi();
        addJetons();
        addDistricts();
        addDetective();

        // La méthode shuffle permet de mélanger les cartes Alibi
        Collections.shuffle(paquetCartesAlibi);
        // MrJack prend l'identité d'un des personnages alibi, aléatoirement
        MrJackIdentity = new MrJack(paquetCartesAlibi.get(0));
        // On retire la carte de MrJack du paquet Alibi
        paquetCartesAlibi.remove(0);
    }

    private void addJetons() {
        // On ajoute les 4 jetons à la liste jetons
        listeJetons.add(new JetonHolmesAlibi());
        listeJetons.add(new JetonRotationEchange());
        listeJetons.add(new JetonRotationJoker());
        listeJetons.add(new JetonWatsonToby());

        // On lance les jetons au hasard ??
        for (int i = 0 ; i < 4 ; i++) {
            listeJetons.get(i).lanceJeton();
        }
    }

    private void addAlibi() {
        // Cartes alibi avec 0 sablier
        paquetCartesAlibi.add(new Personnage("Noir", 0));
        paquetCartesAlibi.add(new Personnage("Bleu", 0));
        // Cartes alibi avec 1 sablier
        paquetCartesAlibi.add(new Personnage("Orange", 1));
        paquetCartesAlibi.add(new Personnage("Violet", 1));
        paquetCartesAlibi.add(new Personnage("Vert", 1));
        paquetCartesAlibi.add(new Personnage("Jaune", 1));
        paquetCartesAlibi.add(new Personnage("Blanc", 1));
        paquetCartesAlibi.add(new Personnage("Gris", 1));
        // Cartes alibi avec 2 sabliers
        paquetCartesAlibi.add(new Personnage("Rose", 2));
        Collections.shuffle(paquetCartesAlibi);

    }

    private void addDistricts() {
        Random random = new Random();
        // Placement aléatoire des districts avec comme condition initiale que les détectives soient face à un mur
        district[0][0] = new District(3, paquetCartesAlibi.get(0));
        district[0][1] = new District(random.nextInt(4), paquetCartesAlibi.get(1));
        district[0][2] = new District(1, paquetCartesAlibi.get(2));
        district[1][0] = new District(random.nextInt(4), paquetCartesAlibi.get(3));
        district[1][1] = new District(random.nextInt(4), paquetCartesAlibi.get(4));
        district[1][2] = new District(random.nextInt(4), paquetCartesAlibi.get(5));
        district[2][0] = new District(random.nextInt(4), paquetCartesAlibi.get(6));
        district[2][1] = new District(2, paquetCartesAlibi.get(7));
        district[2][2] = new District(random.nextInt(4), paquetCartesAlibi.get(8));
    }

    private void addDetective() {
        detective[0] = new Detective("Holmes", 11); // Position 11 = en haut à gauche
        detective[1] = new Detective("Watson", 3);  // Position 3 = en haut à droite
        detective[2] = new Detective("Toby", 7);    // Position 7 = en bas au milieu
    }

    public void setNumberOfVisiblesDistricts(int nbCase0) {
        this.numberOfVisiblesDistricts = nbCase0;
    }

    public int getNumberOfVisiblesDistricts() {
        return numberOfVisiblesDistricts;
    }

    public void nextRound() {
        this.round++;
    }

    public int getRound() {
        return round;
    }
}