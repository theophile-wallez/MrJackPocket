package front;

import back.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class FenetreJeu extends JFrame implements ActionListener {
    public JLayeredPane layeredPane = new JLayeredPane();
    public MrJackActionListener mrJackAL = new MrJackActionListener();
    public JPanel districtsPanel;
    public JPanel jetonPanel;

    public JButton[][] boutonDistrict = new JButton[3][3];
    public JButton[] BDetective = new JButton[3];
    public JButton[] Jeton = new JButton[4];

    public JButton carteID;
    public JButton[] carteAlibi = new JButton[4];

    public JLabel LSablier = new JLabel();
    public JLabel LSablierText = new JLabel();
    public JLabel LTeteMrJack;
    public JLabel LTour;

    public JLabel LRoundText;
    public JLabel LRoundFond;

    public JLabel LToDoText;
    public JLabel LToDoFond;


    public JButton holmesEmplacement1 = new JButton();
    public JButton holmesEmplacement2 = new JButton();
    public JButton watsonEmplacement1 = new JButton();
    public JButton watsonEmplacement2 = new JButton();
    public JButton tobyEmplacement1 = new JButton();
    public JButton tobyEmplacement2 = new JButton();

    public ArrayList<JButton> detectivePlus1 = new ArrayList<>();
    public ArrayList<JButton> detectivePlus2 = new ArrayList<>();

    public JButton confirmerButton;
    public JButton home;

    public JLabel LWhoIsSherlock;
    public JLabel LWhoIsMrJack;
    public JLabel LWhoIsSherlockIcon;
    public JLabel LWhoIsMrJackIcon;

    public JLabel boutonJeton;

    public ImageIcon carteJackImage;
    public ImageIcon sherlockImage;
    public ImageIcon watsonImage;
    public ImageIcon tobyImage;

    public static Color mainColor = new Color(19,16,40);

    Jeu jeu;
    Plateau plateau;
    String name;
    String nomJoueur1;
    String nomJoueur2;

    public boolean canRotate;

    public boolean firstEchange;
    public boolean secondEchange;

    int etat;
    int iSelect;
    int jSelect;

    public boolean isJetonUtilisable;
    public static int xPlat = 207;
    public static int yPlat = 230;

    public FenetreJeu(String nomJoueur1, String nomJoueur2, Plateau plateau) {
        this.nomJoueur1 = nomJoueur1;
        this.etat = -1;
        this.jeu = new Jeu();
        this.nomJoueur2 = nomJoueur2;
        this.plateau = plateau;

        this.canRotate = false;

        this.firstEchange = false;
        this.secondEchange = false;

        this.isJetonUtilisable = true;
        this.setTitle("MrJackPocket");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(840, 800);
        this.setLocationRelativeTo(null);

        this.setBackground(mainColor);
        getContentPane().setBackground(mainColor);

        layeredPane.setBounds(0, 0, 840, 800);

        int whIcon = 45;
        watsonImage = Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonWatson.png")), whIcon, whIcon);
        sherlockImage =Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonHolmes.png")), whIcon, whIcon);
        tobyImage = Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonChien.png")), whIcon, whIcon);

        init();


    }

    public static void cleanButton(JButton button){
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
    }

    public void init() {
        WhoIsWho();
        autreElements();
        distritsDisplay();
        cartesAlibiDisplay();
        detectivesDisplay();
        sablierDisplay();
        roundDisplay();
        toDoDisplay();
        jetonsDisplay();

        detectivePlus1.add(holmesEmplacement1);
        detectivePlus1.add(watsonEmplacement1);
        detectivePlus1.add(tobyEmplacement1);

        detectivePlus2.add(holmesEmplacement2);
        detectivePlus2.add(watsonEmplacement2);
        detectivePlus2.add(tobyEmplacement2);

        //A retirer

        for (int i = 0; i < detectivePlus1.size(); i++) {
            detectivePlus1.get(i).addActionListener(mrJackAL.createDetectiveMoveAL(i,1,this));
        }

        for (int i = 0; i < detectivePlus2.size(); i++) {
            detectivePlus2.get(i).addActionListener(mrJackAL.createDetectiveMoveAL(i,2,this));
        }

        confirmerButton.addActionListener(this);


        for (int i = 0; i < 4; i++) {
            Jeton[i].addActionListener(this);
        }

        for (int i = 0; i < 3; i++) {
            carteAlibi[i].addActionListener(this);
        }

        for (int i = 0; i < 3; i++) {
            BDetective[i].addActionListener(this);
        }


        layeredPane.add(home, Integer.valueOf(20));
        layeredPane.add(LTour, Integer.valueOf(-20));

        for (int i = 0; i < 4; i++) {
            layeredPane.add(carteAlibi[i]);
        }

        for (int i = 0; i < 3; i++) {
            layeredPane.add(BDetective[i], Integer.valueOf(i));
        }

        layeredPane.add(LRoundText);
        layeredPane.add(LRoundFond);
        layeredPane.add(LSablier);
        layeredPane.add(LSablierText);

        layeredPane.add(holmesEmplacement1);
        layeredPane.add(holmesEmplacement2);
        layeredPane.add(watsonEmplacement1);
        layeredPane.add(watsonEmplacement2);
        layeredPane.add(tobyEmplacement1);
        layeredPane.add(tobyEmplacement2);

        layeredPane.add(confirmerButton);
        layeredPane.add(carteID);

        layeredPane.add(LWhoIsSherlockIcon);
        layeredPane.add(LWhoIsSherlock);
        layeredPane.add(LWhoIsMrJack);
        layeredPane.add(LWhoIsMrJackIcon);

        layeredPane.add(LToDoText);
        layeredPane.add(LToDoFond);

        layeredPane.add(districtsPanel);
        layeredPane.add(jetonPanel);
        layeredPane.add(boutonJeton);


        layeredPane.setBackground(mainColor);
        this.add(layeredPane);
    }

    public void autreElements() {
        home = new JButton();
        home.setIcon(new ImageIcon(getClass().getResource("/assets/images/autres/Home.png")));
        home.setRolloverIcon(new ImageIcon(getClass().getResource("/assets/images/autres/HomeVert.png")));
        home.setBounds(790, 15, 32, 32);
        cleanButton(home);
        home.addActionListener(this);

        LTeteMrJack = new JLabel();
        LTeteMrJack.setIcon(new ImageIcon(getClass().getResource("/assets/images/autres/MrJack.png")));
        LTeteMrJack.setBounds(780, 705, 60, 75);

        LTour = new JLabel();

        // PANNEAU QUI CONTIENT LES DISTRICTS
        districtsPanel = new JPanel();
        districtsPanel.setLayout(new GridLayout(3, 3, 0, 0));
        districtsPanel.setBounds(xPlat, yPlat, 426, 426);

        jetonPanel = new JPanel();
        jetonPanel.setLayout(new GridLayout(4, 1, 0, 0));
        //jetonPanel.setBackground(new Color(29,32,37,0));
        jetonPanel.setOpaque(false);
        int wPanel = 65;
        int hPanel = 320;
        jetonPanel.setBounds(15, 420-hPanel/2, wPanel, hPanel);

        LRoundText = new JLabel();
        LRoundFond = new JLabel();
        LToDoText =  new JLabel();
        LToDoFond = new JLabel();


        confirmerButton = new JButton();
        confirmerButton.setBounds(730, yPlat+426/2-50/2, 50, 50);
        confirmerButton.setIcon(new ImageIcon(getClass().getResource("/assets/images/autres/confirmer.png")));
        confirmerButton.setRolloverIcon(new ImageIcon(getClass().getResource("/assets/images/autres/confirmerVert.png")));
        cleanButton(confirmerButton);
        confirmerButton.setVisible(false);

        for (int k = 0; k < 6; k++) {
            if (k < 4){
                carteAlibi[k] = new JButton();
                Jeton[k] = new JButton();
            }

        }

        // Cette boucle permet de rendre les districts cliquables
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boutonDistrict[i][j] = new JButton();
                boutonDistrict[i][j].addActionListener(mrJackAL.createDistrictRotateAL(i,j,this));
                boutonDistrict[i][j].addActionListener(mrJackAL.createDistrictEchangeAL(i,j,this));
            }
        }

        // Cette boucle permet de rendre les détectives cliquables
        for (int i = 0; i < 3; i++) {
            BDetective[i] = new JButton();
        }
    }

    public void distritsDisplay() {
        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                name = plateau.district[i][j].getPersonnage().getName();
                cleanButton(boutonDistrict[i][j]);
                if (plateau.district[i][j].getCoteDistrict() == 0) {

                    Icon districtIcon = new ImageIcon(getClass().getResource("/assets/images/districts/districtClean" + plateau.district[i][j].getOrientation() + ".png"));
                    ImageIcon persoImageIcon = new ImageIcon(getClass().getResource("/assets/images/personnages/"+name+".png"));

                    try {
                        boutonDistrict[i][j].setIcon(Images.imageCombine(districtIcon,persoImageIcon));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Icon districtImageIconRollOver = new ImageIcon(getClass().getResource("/assets/images/districts/districtCleanVert" + plateau.district[i][j].getOrientation() + ".png"));

                    // Permet de mettre en surbrillance l'image lors du survol de la souris
                    try {
                        boutonDistrict[i][j].setRolloverIcon(Images.imageCombine(districtImageIconRollOver,persoImageIcon));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (plateau.district[i][j].getCoteDistrict() == 1) {
                    boutonDistrict[i][j].setIcon(new ImageIcon(getClass().getResource("/assets/images/districts/districtClean" + plateau.district[i][j].getOrientation() + ".png")));
                    boutonDistrict[i][j].setRolloverIcon(new ImageIcon(getClass().getResource("/assets/images/districts/districtCleanVert" + plateau.district[i][j].getOrientation() + ".png")));
                }
                else if (plateau.district[i][j].getCoteDistrict() == -1) {
                    boutonDistrict[i][j].setIcon(new ImageIcon(getClass().getResource("/assets/images/districts/districtCleanR2.png")));
                    boutonDistrict[i][j].setRolloverIcon(new ImageIcon(getClass().getResource("/assets/images/districts/districtCleanVertR2.png")));
                }
                districtsPanel.add(boutonDistrict[i][j]);
            }
        }
    }

    public void jetonsDisplay() {
        int whIcon = 45;

        for (int i = 0 ; i < 4 ; i++) {
            cleanButton(Jeton[i]);
            if (plateau.listeJetons.get(i).getPileFace() == 1) {
                switch (plateau.listeJetons.get(i).getNameCote1()) {
                    case "Holmes" -> Jeton[i].setIcon(sherlockImage);
                    case "Rotation" -> Jeton[i].setIcon(Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonTourner.png")), whIcon, whIcon));
                    case "Watson" -> Jeton[i].setIcon(watsonImage);
                }
            }
            else if (plateau.listeJetons.get(i).getPileFace() == 2) {
                switch (plateau.listeJetons.get(i).getNameCote2()) {
                    case "Alibi" -> Jeton[i].setIcon(Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonCarte.png")), whIcon, whIcon));
                    case "Echange" -> Jeton[i].setIcon(Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonEchanger.png")), whIcon, whIcon));
                    case "Joker" -> Jeton[i].setIcon(Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/jetons/JetonJoker.png")), whIcon, whIcon));
                    case "Toby" -> Jeton[i].setIcon(tobyImage);
                }
            }
            jetonPanel.add(Jeton[i]);

            boutonJeton = new JLabel();
        }
        
        // Cette boucle permet d'afficher les 4 jetons à chaque début de tour
        for (int i = 0; i < 4; i++) {
            Jeton[i].setVisible(true);
        }
    }

    public void WhoIsWho(){
        Random rand = new Random();
        LWhoIsSherlockIcon = new JLabel();
        LWhoIsSherlock = new JLabel();
        LWhoIsMrJackIcon = new JLabel();
        LWhoIsMrJack = new JLabel();
        carteID = new JButton();
        carteJackImage = new ImageIcon(getClass().getResource("/assets/images/autres/Carte.png"));
        carteJackImage = Images.resizeImage(carteJackImage,52,100);
        carteID.setIcon(carteJackImage);
        carteID.setBounds(30, 650, 52, 100);
        carteID.addActionListener(this);

        int p = rand.nextInt(2);
        if (p==0){
            String sherlockText = nomJoueur1 + " est détective";
            sherlockText = sherlockText.toUpperCase();
            String mrJackText = nomJoueur2 + " est Mr. Jack";
            mrJackText = mrJackText.toUpperCase();
            LWhoIsSherlock.setText(sherlockText);
            LWhoIsMrJack.setText(mrJackText);
        }
        else{
            String sherlockText = nomJoueur2 + " est détective";
            sherlockText = sherlockText.toUpperCase();
            String mrJackText = nomJoueur1 + " est MrJack";
            mrJackText = mrJackText.toUpperCase();
            LWhoIsSherlock.setText(sherlockText);
            LWhoIsMrJack.setText(mrJackText);
        }

        LWhoIsSherlock.setBounds(15,35,300,20);
        LWhoIsSherlock.setForeground(new Color(108,92,231));

        LWhoIsMrJack.setBounds(15,65,300,20);
        LWhoIsMrJack.setForeground(new Color(108,92,231));

        LWhoIsSherlock.setFont(placeFont(15f));
        LWhoIsMrJack.setFont(placeFont(15f));
    }

    public static Font placeFont(float fl){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, FenetreJeu.class.getResource("/assets/fonts/Andromeda.otf").openStream());
            font = font.deriveFont(fl);
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
        }
        catch(IOException | FontFormatException ignored) {
        }
        return font;
    }

    public void roundDisplay() {
        int tour = plateau.getRound()+1;
        LRoundText.setText("ROUND "+tour);

        LRoundText.setForeground(new Color(108,92,231));
        LRoundText.setHorizontalAlignment(SwingConstants.CENTER);
        LRoundText.setHorizontalTextPosition(SwingConstants.CENTER);
        LRoundText.setVerticalTextPosition(SwingConstants.CENTER);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/assets/fonts/Andromeda.otf").openStream());
            font = font.deriveFont(36f);
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(font);
            LRoundText.setFont(font);
        }
        catch(IOException | FontFormatException ignored) {
        }
        LRoundText.setBounds(320, 10, 200, 100);

        LRoundFond.setIcon(new ImageIcon(getClass().getResource("/assets/images/autres/boutonScore.png")));
        LRoundFond.setBounds(266,20,308,83);
    }

    public void toDoDisplay(){
        String texte = "";
        String detectiveTurn = "C'est au tour du détective";
        String mrJackTurn = "C'est au tour de Mr. Jack";

        int round  = plateau.getRound() + 1;
        if (round % 2 != 0){
            texte = switch (plateau.jetonsSurPlateau) {
                case 4, 1 ->  detectiveTurn;
                case 3, 2 ->  mrJackTurn;
                default -> texte;
            };
        }
        else {
            texte = switch (plateau.jetonsSurPlateau) {
                case 4, 1 -> mrJackTurn;
                case 3, 2 -> detectiveTurn;
                default -> texte;
            };
        }

        LToDoText.setText(texte.toUpperCase());
        LToDoText.setBounds(206,110,428,47);
        LToDoText.setFont(placeFont(20f));
        LToDoText.setHorizontalAlignment(SwingConstants.CENTER);
        LToDoText.setVerticalTextPosition(SwingConstants.CENTER);
        LToDoText.setForeground(new Color(108,92,231));

        LToDoFond.setIcon(new ImageIcon(getClass().getResource("/assets/images/autres/boutonToDo.png")));
        LToDoFond.setBounds(206,110,428,47);
    }

    public void cartesAlibiDisplay() {
        for (int i = 0; i < plateau.MrJackIdentity.paquetCarteJack.size() ; i++) {
            if (i > 3)
                break;
            carteAlibi[i].setIcon(new ImageIcon(getClass().getResource("/assets/images/autres/CartePetit.png")));
            carteAlibi[i].setBounds(110 + (i * 40), 700, 30, 50);
            cleanButton(carteAlibi[i]);
        }
    }

    public void sablierDisplay() {
        ImageIcon sablierIcon = new ImageIcon(getClass().getResource("/assets/images/autres/sablier.png"));
        sablierIcon = Images.resizeImage(sablierIcon,100,96);

        LSablier.setIcon(sablierIcon);
        LSablier.setBounds(750,670,100,96);

        LSablierText.setText(""+plateau.MrJackIdentity.getSablierEarned());
        LSablierText.setForeground(new Color(66,57,139));

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/assets/fonts/Andromeda.otf").openStream());
            font = font.deriveFont(100f);
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
            LSablierText.setFont(font);
        }
        catch(IOException | FontFormatException ignored) {
        }
        LSablierText.setBounds(700,660,200,100);
    }

    public static ArrayList<Integer> getPositionInPixel(Plateau plateau, int i, Integer posi){
        int pos = posi == 100 ?  plateau.detective[i].getPosition() : posi;
        ArrayList<Integer> positionDetective = new ArrayList<>();
        switch (pos) {
            case 0  -> {positionDetective.add(xPlat + 71 - 32);  positionDetective.add(yPlat - 65);}
            case 1  -> {positionDetective.add(xPlat + 213 - 32); positionDetective.add(yPlat - 65);}
            case 2  -> {positionDetective.add(xPlat + 355 - 32); positionDetective.add(yPlat - 65);}
            case 3  -> {positionDetective.add(xPlat + 426 );     positionDetective.add(yPlat + 71 - 32);}
            case 4  -> {positionDetective.add(xPlat + 426 );     positionDetective.add(yPlat + 213 - 32);}
            case 5  -> {positionDetective.add(xPlat + 426 );     positionDetective.add(yPlat + 355 - 32);}
            case 6  -> {positionDetective.add(xPlat + 355 - 32); positionDetective.add(yPlat + 426 );}
            case 7  -> {positionDetective.add(xPlat + 213 - 32); positionDetective.add(yPlat + 426 );}
            case 8  -> {positionDetective.add(xPlat + 71 - 32);  positionDetective.add(yPlat + 426 );}
            case 9  -> {positionDetective.add(xPlat - 65);       positionDetective.add(yPlat + 355 - 32);}
            case 10 -> {positionDetective.add(xPlat - 65);       positionDetective.add(yPlat + 213 - 32);}
            case 11 -> {positionDetective.add(xPlat - 65);       positionDetective.add(yPlat + 71 - 32);}
        }
        return positionDetective;
    }

    public void detectivesDisplay() {
        for (int i = 0 ; i < 3 ; i++) {
            name = plateau.detective[i].getName();
            cleanButton(BDetective[i]);
            switch (name) {
                case "Holmes" -> {
                    BDetective[i].setIcon(sherlockImage);
                    BDetective[i].setBounds(getPositionInPixel(plateau, i, 100).get(0), getPositionInPixel(plateau, i, 100).get(1), 65, 65);
                }
                case "Watson" -> {
                    BDetective[i].setIcon(watsonImage);
                    BDetective[i].setBounds(getPositionInPixel(plateau, i, 100).get(0), getPositionInPixel(plateau, i, 100).get(1), 65, 65);
                }
                case "Toby" -> {
                    BDetective[i].setIcon(tobyImage);
                    BDetective[i].setBounds(getPositionInPixel(plateau, i, 100).get(0), getPositionInPixel(plateau, i, 100).get(1), 65, 65);
                }
            }
        }
    }

    public void cartesAlibiDisplay(ActionEvent event) {
        for (int k = 0; k < 4; k++) {
            if (event.getSource() == carteAlibi[k])
                jSelect = k;
        }
        if (plateau.MrJackIdentity.paquetCarteJack.get(jSelect).isHidden) {
            carteAlibi[jSelect].setIcon(new ImageIcon(getClass().getResource("/assets/images/personnages/P"+plateau.MrJackIdentity.paquetCarteJack.get(jSelect).getName()+"2.png")));
            plateau.MrJackIdentity.paquetCarteJack.get(jSelect).isHidden = false;
        }
        else {
            carteAlibi[jSelect].setIcon(new ImageIcon(getClass().getResource("/assets/images/autres/CartePetit.png")));
            plateau.MrJackIdentity.paquetCarteJack.get(jSelect).isHidden = true;
        }
    }

    public void nextAction() {
        // Si le nombre de jeton tombe à zero on change le tour et on rajoute 4 nouveau jetons
        toDoDisplay();
        if (plateau.jetonsSurPlateau == 0) {
            plateau.nextRound();
            plateau.jetonsSurPlateau = 4;
            roundDisplay();
            toDoDisplay();

            jeu.updateJeu(plateau);

            if (jeu.finDePartie(plateau)) { // Si la partie est finie
                this.dispose(); // on ferme la fenetre
                FenetreFin fenetreFin = new FenetreFin(); // On ouvre la fenetre de fin
                fenetreFin.setVisible(true);
            }

            sablierDisplay();
            distritsDisplay();

            if (plateau.getRound() % 2 == 0) {
                plateau.listeJetons.get(0).lanceJeton();
                plateau.listeJetons.get(1).lanceJeton();
                plateau.listeJetons.get(2).lanceJeton();
                plateau.listeJetons.get(3).lanceJeton();
            }
            else {
                plateau.listeJetons.get(0).changerCoteJeton();
                plateau.listeJetons.get(1).changerCoteJeton();
                plateau.listeJetons.get(2).changerCoteJeton();
                plateau.listeJetons.get(3).changerCoteJeton();
            }
            jetonsDisplay();
            
        }
    }

    public void nextJeton(int i) {
        isJetonUtilisable = false;
        plateau.jetonsSurPlateau--;
        this.etat = -1;
        Jeton[i].setVisible(false);

        if (plateau.listeJetons.get(i).getPileFace() == 1) {
            plateau.listeJetons.get(i).actionJetonCote1(plateau, this);
        }
        else if (plateau.listeJetons.get(i).getPileFace() == 2) {
            plateau.listeJetons.get(i).actionJetonCote2(plateau, this);
        }
    }


    public int nextDetectivePos(int plusCmb,int pos){
        if (plusCmb ==1){
            if (pos != 11)
                pos ++;
            else
                pos = 0;
        }

        else if (plusCmb == 2){
            if (pos != 10 && pos != 11)
                pos += 2;
            else if (pos == 10)
                pos = 0;
            else
                pos = 1;
        }
        return pos;
    }

    @Override
    public void actionPerformed( ActionEvent event) {
        if (event.getSource() == home) {
            this.dispose();
            FenetreMenu fenetreMenu = new FenetreMenu();
            fenetreMenu.setVisible(true);

            //Cas carte ID affiche image de l'identité de MrJack
        } else if (event.getSource() == carteID) {
            if (plateau.MrJackIdentity.getCarteMrJack().isHidden) {
                carteID.setIcon(Images.resizeImage(new ImageIcon(getClass().getResource("/assets/images/personnages/P" + plateau.MrJackIdentity.getCarteMrJack().getName() + ".png")),carteJackImage.getIconWidth(),carteJackImage.getIconHeight()));
                plateau.MrJackIdentity.getCarteMrJack().isHidden = false;
            }
            else {
                carteID.setIcon(carteJackImage);
                plateau.MrJackIdentity.getCarteMrJack().isHidden = true;
            }

        } else if (event.getSource() == carteAlibi[0] || event.getSource() == carteAlibi[1]
                || event.getSource() == carteAlibi[2] || event.getSource() == carteAlibi[3]) {
            cartesAlibiDisplay(event);
        }

        for (int k = 0; k < 4; k++)
            if (event.getSource() == Jeton[k] && isJetonUtilisable)
                nextJeton(k);

        else if (event.getSource() == confirmerButton) {
            canRotate = false;
            isJetonUtilisable = true;
            confirmerButton.setVisible(false);
            etat = -1;
            nextAction();
        }
    }
}