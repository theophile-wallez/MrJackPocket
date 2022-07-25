package back;

public class Detective {
    public int position;
    public String name;

    public Detective(String nom, int position) {
        this.position = position;
        this.name = nom;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}
