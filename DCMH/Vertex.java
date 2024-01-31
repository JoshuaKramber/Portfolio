import java.util.ArrayList;

public class Vertex{

    //initializing some basic vars for the class
    boolean visited;
    int[] values;
    ArrayList<Vertex> children;
    Vertex parent;

    /**
     * Basic Vertex constructor, mainly used for creating the root vertex
     * @param v int array of values
     */
    public Vertex(int[] v){
        values = v;
        visited = false;
        children = new ArrayList<Vertex>();
    }

    /**
     * Main vertex constructor
     * @param v int array of values
     * @param p is the parent vertex
     */
    public Vertex(int[] v, Vertex p){
        values = v;
        visited = false;
        parent = p;
        children = new ArrayList<Vertex>();
    }

    /**
     * used to add a edge (child) 
     * @param c vertex
     */
    public void addChild(Vertex c){
        this.children.add(c);
        c.parent = this;
    }
}
