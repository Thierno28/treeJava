public class Files extends Component{
    
    public Files (String nom) {
        super(nom);
    }
    
    public void show(int indent) {
        System.out.println("+--" + getNom());
    }
}