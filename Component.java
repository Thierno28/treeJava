abstract class Component {
    public abstract void show(int indent);

    public void add(Component c) {};

    public Component (String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    private String nom;

}