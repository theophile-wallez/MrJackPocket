package front;

import back.District;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MrJackActionListener {
    private class DistrictRotateAL implements ActionListener{
        private int i;
        private int j;
        private FenetreJeu fenetreJeu;

        public DistrictRotateAL(int i,int j,FenetreJeu fenetreJeu) {
            this.i = i;
            this.j = j;
            this.fenetreJeu  = fenetreJeu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(fenetreJeu.canRotate){
                fenetreJeu.confirmerButton.setVisible(true);
                if (fenetreJeu.etat == 3*i+j || fenetreJeu.etat == -1) {
                    fenetreJeu.plateau.district[i][j].changeOrientation();fenetreJeu.etat = 3*i+j;}
                fenetreJeu.distritsDisplay();
            }
        }
    }

    public ActionListener createDistrictRotateAL(int i, int j, FenetreJeu fenetreJeu){
        return new DistrictRotateAL(i, j, fenetreJeu);
    }


    private class DistrictEchangeAL implements ActionListener{
        private int i;
        private int j;

        District stockageDistrict;
        private FenetreJeu fenetreJeu;

        public DistrictEchangeAL(int i,int j,FenetreJeu fenetreJeu) {
            this.i = i;
            this.j = j;
            this.fenetreJeu  = fenetreJeu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (fenetreJeu.firstEchange) {
                fenetreJeu.iSelect = i ; fenetreJeu.jSelect = j ;
                fenetreJeu.firstEchange = false;
                fenetreJeu.secondEchange = true;
            }
            else if(fenetreJeu.secondEchange){
                if (fenetreJeu.iSelect != i || fenetreJeu.jSelect != j) {
                    stockageDistrict = fenetreJeu.plateau.district[fenetreJeu.iSelect][fenetreJeu.jSelect];
                    fenetreJeu.plateau.district[fenetreJeu.iSelect][fenetreJeu.jSelect] = fenetreJeu.plateau.district[i][j];
                    fenetreJeu.plateau.district[i][j] = stockageDistrict;

                    fenetreJeu.distritsDisplay();
                    fenetreJeu.secondEchange = false;
                    fenetreJeu.isJetonUtilisable = true;
                    fenetreJeu.nextAction();
                }
            }
        }
    }

    public ActionListener createDistrictEchangeAL(int i, int j, FenetreJeu fenetreJeu){
        return new DistrictEchangeAL(i, j, fenetreJeu);
    }

    private class DetectiveMoveAL implements ActionListener{
        private int index;
        private FenetreJeu fenetreJeu;
        private int plusCmb;

        public DetectiveMoveAL(int index,int plusCmb,FenetreJeu fenetreJeu) {
            this.index = index;
            this.fenetreJeu  = fenetreJeu;
            this.plusCmb = plusCmb;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            fenetreJeu.tobyEmplacement1.setVisible(false);
            fenetreJeu.tobyEmplacement2.setVisible(false);
            fenetreJeu.holmesEmplacement1.setVisible(false);
            fenetreJeu.holmesEmplacement2.setVisible(false);
            fenetreJeu.watsonEmplacement1.setVisible(false);
            fenetreJeu.watsonEmplacement2.setVisible(false);

            fenetreJeu.plateau.detective[index].setPosition(fenetreJeu.nextDetectivePos(plusCmb,fenetreJeu.plateau.detective[index].getPosition()));
            System.out.println(index);
            fenetreJeu.detectivesDisplay();
            fenetreJeu.isJetonUtilisable = true;
            fenetreJeu.nextAction();
        }
    }
    public ActionListener createDetectiveMoveAL(int index,int plusCmb, FenetreJeu fenetreJeu){
        return new DetectiveMoveAL(index,plusCmb, fenetreJeu);
    }
}
