import java.util.ArrayList;

public class Directory extends Component {
    ArrayList<Component> components = new ArrayList<Component>();

    public Directory (String nom) {
        super(nom);
    }

    public void add(Component c){
        components.add(c);
    }

    public void show(){
        int indent =1;
        System.out.println("+--" + getNom());
       
        for(Component c: components){
            for (int j = 0; j < indent; j++) {
                System.out.print("|  ");
            }
            c.show(indent+1);
                      
            }
    }
    public void show(int indent){
        System.out.println("+--" + getNom());
       
        for(Component c: components){
            for (int j = 0; j < indent; j++) {
                System.out.print("|  ");
            }
            c.show(indent+1);
                      
            }
    }
}