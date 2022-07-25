package front;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FenetreFin extends JFrame implements ActionListener {
    private final JLayeredPane panel = new JLayeredPane();
    private final JPanel fondBleu = new JPanel();
    public JLabel LMot;
    public JButton home;


    public FenetreFin() {
        setBounds(0, 0, 500, 500);
        setTitle("MrJackPocket");
        setSize(500, 500); //Taille de la fenêtre
        setLocationRelativeTo(null); //Fenêtre centrée
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fondBleu.setBackground(FenetreJeu.mainColor);
        fondBleu.setBounds(0, 0, 500, 500);


        LMot = new JLabel("Partie terminée");
        LMot.setFont(FenetreJeu.placeFont(35f));
        LMot.setHorizontalAlignment(SwingConstants.CENTER);
        LMot.setForeground(Color.white);
        LMot.setBounds(100, 100, 300, 50);

        home = new JButton();
        FenetreJeu.cleanButton(home);
        home.setIcon(new ImageIcon(getClass().getResource("/assets/images/autres/Home.png")));
        home.setBounds(200, 250, 80, 80);
        home.addActionListener((ActionListener) this);


        add(home);
        add(LMot);
        add(fondBleu);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            this.dispose();
            FenetreMenu fenetreMenu = new FenetreMenu();
            fenetreMenu.setVisible(true);
        }
    }
}