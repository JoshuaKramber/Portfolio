
public class Main {

    // Collection of puzzles for testing
    static Integer[][] easy4x4 = {
		{0,3,4,0},
		{4,0,0,2},
		{1,0,0,3},
		{0,2,1,0}
	};
	static Integer[][] easy6x6 = {
		{0,0,3,0,1,0},
		{5,6,0,3,2,0},
		{0,5,4,2,0,3},
		{2,0,6,4,5,0},
		{0,1,2,0,4,5},
		{0,4,0,1,0,0}
	};
	static Integer[][] medium6x6 = {
		{2,6,0,5,0,1},
		{0,0,1,0,0,2},
		{4,0,0,0,6,0},
		{0,3,0,0,0,4},
		{1,0,0,4,0,0},
		{3,0,4,0,2,6}
	};
	static Integer[][] easy9x9 = {
		{0,1,0,6,0,0,0,9,0},
		{0,0,0,2,0,0,3,8,0},
		{0,3,8,0,4,0,0,0,0},
		{0,0,0,0,0,3,0,2,6},
		{9,6,1,0,0,5,0,7,0},
		{0,8,0,0,0,4,0,0,5},
		{0,0,0,9,0,1,6,0,0},
		{0,0,5,0,0,0,2,3,0},
		{7,4,0,0,8,0,0,0,0}
	};

	public static void main(String[] args) {

		// Use one of the boards defined above (or create your own)
		Board puzzle = new Board(easy9x9);
		System.out.println(puzzle);

		// Build a search tree to brute force the solution
		SudokuTree tree = new SudokuTree(puzzle);
		Node<Board> root = tree.root();

		// Use BFS or DFS to find solution in tree
		//creates stacks/queue and adds the root and maintains node count
		Stack<Node<Board>> q = new Stack<Node<Board>>();
        q.push(root);
		int nodesVisited = 1;
		//while loop to find the completed puzzle board
        while((!q.peek().value().isComplete())&&(!q.isEmpty())){
			//sets a temp variable to the popped node
            Node<Board> test = q.pop();
			//removes nulls
            if(test!=null){
				//pushes all of the temp nodes children and maintains node count
                for(int i3 = 0;i3<test.children().size();i3++){
                    q.push(test.children().get(i3));
					nodesVisited++;
                }
            }
        }
		//prints out the number of nodes visited, the completed board, and if it is the completed board
		System.out.println("Num Nodes Visited = " + nodesVisited);
		System.out.println(q.peek().value().isComplete());
		System.out.println(q.peek().value());
		
	} // end main
} // end class Main
