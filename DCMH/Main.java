
public class Main{
    public static void main(String[]args){
        //make graphs
        Graph graph = new Graph();
        Graph graph2 = new Graph();

        //make & run bfs & dfs with a semi pretty output
        System.out.println("Now running BFS");
        graph.BFS(graph,graph.returnRoot());
        System.out.println();
        System.out.println("Now running DFS");
        graph2.DFS(graph2);

    }
}