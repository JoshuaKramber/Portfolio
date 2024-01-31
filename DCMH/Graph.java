import java.util.LinkedList;
import java.util.Queue;


public class Graph{

    //initializing helpful variables
    public Vertex root;
    public Vertex exitVertex;
    public int time;

    /**
     * Pre-made graph of veticies that holds int[] values in each node
     */
    public Graph(){

        //key for int[] = {dog,cat,mouse,human}
        //old house = 0, new house = 1
        int[] rootArr = new int[]{0,0,0,0};
        root = new Vertex(rootArr);

        //first set of children
        int[] aArr = new int[]{0,0,0,1};//dead
        int[] bArr = new int[]{0,1,0,1};
        int[] cArr = new int[]{0,0,1,1}; //dead
        int[] dArr = new int[]{1,0,0,1}; //dead

        Vertex a = new Vertex(aArr,root);//dead
        Vertex b = new Vertex(bArr,root);
        Vertex c = new Vertex(cArr,root);//dead
        Vertex d = new Vertex(dArr,root);//dead

        root.addChild(a);//dead
        root.addChild(b);
        root.addChild(c);//dead
        root.addChild(d);//dead

        //second set of children
        int[] c2Arr = new int[]{0,1,0,0};

        Vertex c2 = new Vertex(c2Arr,b);
        b.addChild(c2);

        //third set of children
        int[] c3Arr = new int[]{1,1,0,1};
        int[] d3Arr = new int[]{0,1,1,1};

        Vertex c3 = new Vertex(c3Arr,c2);
        Vertex d3 = new Vertex(d3Arr,c2);

        c2.addChild(c3);
        c2.addChild(d3);

        //fourth set
        int[] a4Arr = new int[]{1,1,0,0};//dead
        int[] b4Arr = new int[]{1,0,0,0};

        int[] c4Arr = new int[]{0,1,1,0};//dead
        int[] d4Arr = new int[]{0,0,1,0};

        Vertex a4 = new Vertex(a4Arr);//dead
        Vertex b4 = new Vertex(b4Arr);
        Vertex c4 = new Vertex(c4Arr);//dead
        Vertex d4 = new Vertex(d4Arr);

        c3.addChild(a4);
        c3.addChild(b4);

        d3.addChild(c4);
        d3.addChild(d4);

        //fith set
        int[] a5Arr = new int[]{1,0,0,1};//dead
        int[] b5Arr = new int[]{1,0,1,1};
        int[] c5Arr = new int[]{0,0,1,1};//dead

        Vertex a5 = new Vertex(a5Arr);//dead
        Vertex b5 = new Vertex(b5Arr);
        Vertex c5 = new Vertex(c5Arr);//dead

        b4.addChild(a5);//dead
        b4.addChild(b5);

        d4.addChild(c5);//dead
        d4.addChild(b5);

        //6th set
        int[] a6Arr = new int[]{1,0,1,0};

        Vertex a6 = new Vertex(a6Arr);
        b5.addChild(a6);

        //7th set
        int[] a7Arr = new int[]{1,1,1,1};

        Vertex a7 = new Vertex(a7Arr);
        a6.addChild(a7);
        exitVertex = a7;

    }

    /**
     * returns the root of the graph
     * @return root
     */
    public Vertex returnRoot(){
        return root;
    }

    /**
     * Prints all of the Vertex children's values from their value int[]
     * @param v Vertex
     */
    public void printChildValues(Vertex v){
        //prints the values of the children's vertices
        for(int i = 0;i<v.children.size();i++){
            int[] childArr = v.children.get(i).values;
            System.out.println(childArr[0] + " " + childArr[1] + " " +childArr[2] + " " +childArr[3]);
        }
    }

    /**
     * Prints the values of the vertex from their value int[]
     * @param v Vertex
     */
    public void printValues(Vertex v){
        //prints the values of the vertex
        for(int i = 0;i<v.values.length;i++){
            System.out.print(v.values[i] + " ");
        }
        System.out.println();
    }

    /**
     * Performs BFS search on the graph to find the solution (Vertex a7, [1,1,1,1])
     * @param G graph
     * @param s Vertex
     */
    public void BFS(Graph G, Vertex s){
        //creates a queue and adds the root
        Queue<Vertex> Q = new LinkedList<>();
        Q.add(s);
        s.visited=true;
        //while the queue is not empty, iterate through all of the nodes 
        while(Q.size()!=0){
            //add the child nodes to the queue
            for(Vertex c : Q.peek().children){
                Q.add(c);
                c.visited=true;
            }
            //checks if we found the solution
            if(Q.peek()==exitVertex){
                //prints the path of choices to the solution, then exits the while loop
                System.out.println("Exit Found!");
                choices(Q.peek());
                break;
            }
            //removes the head of the queue
            Q.remove();
            
        }
    }

    /**
     * follows the vertex's parent edges up to the root and prints those verticies/values
     * @param v Vertex
     */
    public void choices(Vertex v){
        System.out.println("Printing path in reverse!");
        //follows from the vertex up to the root while printing the solution
        while(v.parent!=null){
            printValues(v);
            v=v.parent;
        }
        //prints root (final value)
        printValues(v);
    }

    /**
     * Performs DFS on the graph to find the solution (Vertex A7, int[1,1,1,1])
     * @param G graph
     */
    public void DFS(Graph G){
        //Iterating over the child nodes
        for(Vertex v : G.root.children){
            //making sure we havent visited the vertex yet
            if(v.visited==false){
                //calls helper method
                DFS_Visit(G, v);
            }
        }
    }

    /**
     * Helper method for DFS
     * @param g graph
     * @param v vertex
     */
    public void DFS_Visit(Graph g, Vertex v){
        //mark that we visit the vertex
        v.visited=true;
        //check if the vertex is the solution
        if(v==exitVertex){
            //prints out the choices in reverse order
            System.out.println("Exit Found!");
            choices(v);
        }
        else{
            //iterates over the child verticies
            for(Vertex u:v.children){
                //makes sure it hasn't been visited yet, then recalls the helper method
                if(u.visited==false){
                    DFS_Visit(g, u);
                }
            }   
        }
    }
}
