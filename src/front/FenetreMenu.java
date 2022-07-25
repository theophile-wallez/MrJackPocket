package front;

import back.Plateau;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

import static front.FenetreJeu.cleanButton;
import static front.FenetreJeu.placeFont;

public class FenetreMenu extends JFrame implements ActionListener {
    private final JLayeredPane panel = new JLayeredPane();
    private final JPanel fondBleu = new JPanel();
    private final JButton jouerButton;
    private final JButton exitButton;
    private JButton confirmerButton;
    private JTextField joueur1;
    private JTextField joueur2;
    private boolean isPlayclicked;


    public FenetreMenu() {
        setBounds(0, 0, 500, 500);
        setTitle("MrJackPocket");
        setSize(500, 500); //Taille de la fenêtre
        setLocationRelativeTo(null); //Fenêtre centrée
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.isPlayclicked = false;

        fondBleu.setBackground(FenetreJeu.mainColor);
        fondBleu.setBounds(0, 0, 500, 500);

        jouerButton = new JButton();
        exitButton = new JButton("Quitter le jeu");
        placeButton();
        add(panel);
        add(jouerButton);
        add(exitButton);
        add(fondBleu);
    }

    public void placeButton(){
        ImageIcon fond = new ImageIcon(getClass().getResource("/assets/images/autres/boutonToDo.png"));
        ImageIcon fondRoll = new ImageIcon(getClass().getResource("/assets/images/autres/boutonToDoVert.png"));

        jouerButton.setIcon(fond);
        jouerButton.setRolloverIcon(fondRoll);
        jouerButton.setText("LANCER UNE PARTIE");

        cleanButton(jouerButton);
        jouerButton.setFont(placeFont(35f));
        jouerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        jouerButton.setHorizontalAlignment(SwingConstants.CENTER);
        jouerButton.setForeground(new Color(108,92,231));

        exitButton.setText("QUITTER LE JEU");
        cleanButton(exitButton);
        exitButton.setIcon(fond);
        exitButton.setRolloverIcon(fondRoll);
        exitButton.setFont(placeFont(35f));
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);

        exitButton.setForeground(new Color(108,92,231));

        exitButton.setBounds(0, 250, 500, 100);


        if (!isPlayclicked){
            jouerButton.setBounds(0, 150, 500, 100);
        }
        else {
            jouerButton.setBounds(0, 100, 500, 100);
            jouerButton.setText("ENTREZ VOS NOMS");
            jouerButton.setRolloverIcon(fond);
            exitButton.setVisible(false);
        }
        jouerButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    public void getPlayerNameButtons(){
        confirmerButton = new JButton();
        confirmerButton.setBounds(250-50/2, 350, 50, 50);
        confirmerButton.addActionListener(this);
        confirmerButton.setIcon(new ImageIcon(FenetreMenu.class.getResource("/assets/images/autres/confirmer.png")));
        confirmerButton.setRolloverIcon(new ImageIcon(getClass().getResource("/assets/images/autres/confirmerVert.png")));
        getLayeredPane().add(confirmerButton);
        cleanButton(confirmerButton);

        joueur1 = new JTextField();
        joueur1.setBackground(new Color(28,24,60));
        joueur1.setForeground(new Color(108,92,231));
        joueur1.setCaretColor(new Color(108,92,231));
        joueur1.setHorizontalAlignment(SwingConstants.CENTER);
        joueur1.setVisible(true);
        String defaultName1 = "NOM DU PREMIER JOUEUR";
        joueur1.setText(defaultName1);
        joueur1.setFont(placeFont(15f));
        joueur1.setBounds(500/2-110, 200, 210, 50);
        joueur1.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(joueur1.getText().equals(defaultName1))
                    joueur1.setText("");
            }

            public void focusLost(FocusEvent e) {
                if(joueur1.getText().equals("")){
                    joueur1.setText(defaultName1);
                }
            }
        });
        getLayeredPane().add(joueur1);


        String defaultName2 = "NOM DU DEUXIEME JOUEUR";
        joueur2 = new JTextField();
        joueur2.setVisible(true);
        joueur2.setBackground(new Color(28,24,60));
        joueur2.setForeground(new Color(108,92,231));
        joueur2.setCaretColor(new Color(108,92,231));
        joueur2.setHorizontalAlignment(SwingConstants.CENTER);
        joueur2.setText(defaultName2);
        joueur2.setFont(placeFont(15f));
        joueur2.setBounds(500/2-110, 275, 210, 50);
        joueur2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(joueur2.getText().equals(defaultName2))
                    joueur2.setText("");
            }

            public void focusLost(FocusEvent e) {
                if(joueur2.getText().equals("")){
                    joueur2.setText(defaultName2);
                }
            }
        });
        getLayeredPane().add(joueur2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jouerButton) {
            isPlayclicked = true;
            placeButton();
            getPlayerNameButtons();

        } else if (e.getSource() == confirmerButton) {

            if(joueur1.getText().equals("NOM DU PREMIER JOUEUR"))
                joueur1.setText("Le joueur 1");
            if(joueur2.getText().equals("NOM DU DEUXIEME JOUEUR"))
                joueur2.setText("Le joueur 2");

            this.dispose();
            FenetreJeu fenetreJeu = new FenetreJeu(joueur1.getText(), joueur2.getText(), new Plateau());
            fenetreJeu.setVisible(true);
        }
        else if (e.getSource() == exitButton){
            this.dispose();
        }
    }
}