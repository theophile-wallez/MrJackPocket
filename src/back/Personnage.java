package back;

public class Personnage {
    private String name;
    private int numberOfBonusSablier;
    public boolean isHidden;

    public Personnage(String name, int numberOfBonusSablier) {
        this.name = name;
        this.numberOfBonusSablier = numberOfBonusSablier;
        this.isHidden = true;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfBonusSablier() {
        return numberOfBonusSablier;
    }

}
