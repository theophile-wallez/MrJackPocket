package back;

public class District {

    // 0 mur Nord, 1 mur Est, 2 mur Sud, 3 mur Ouest
    private int orientation;
    private Personnage personnage;
    private int coteDistrict; // 0 district avec perso, 1 sans perso

    public District(int orientation, Personnage perso) {
        this.coteDistrict = 0;
        this.orientation = orientation;
        this.personnage = perso;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public void setCoteDistrict(int coteDistrict) {
        this.coteDistrict = coteDistrict;
    }

    public int getCoteDistrict() {
        return coteDistrict;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }

    public void changeOrientation() {
        if (this.orientation == 3)
            this.orientation = 0;
        else
            this.orientation++;
    }

}
