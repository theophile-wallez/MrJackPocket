package back;

import java.util.ArrayList;

public class MrJack {
    private int sablierEarned;
    private Personnage carteMrJack;
    public ArrayList<Personnage> paquetCarteJack;

    public MrJack(Personnage carteMrJack) {
        this.sablierEarned = 0;
        this.carteMrJack = carteMrJack;
        this.paquetCarteJack = new ArrayList<>();
    }


    public void setSablierEarned(int sablierEarned) {
        this.sablierEarned = sablierEarned;
    }

    public int getSablierEarned() {
        return sablierEarned;
    }

    public Personnage getCarteMrJack() {
        return carteMrJack;
    }
}
