import java.util.LinkedList;

public class SudokuTree {

	Node<Board> root = null;

	int nodeCount = 0;

	public SudokuTree(Board puzzle) {

		LinkedList<Node<Board>> queue = new LinkedList<>();
		root = new Node<>(puzzle);
		queue.add(root);
		while (queue.peekFirst() != null) {
			Node<Board> n = queue.remove();
			++nodeCount;

			int row = n.value().rowFirstEmpty();
			int col = n.value().colFirstEmpty();
			if (row == -1) {
				continue;
			}
			
			for (int i=1; i<=puzzle.size(); i++) {
				Node<Board> copy = new Node<>(new Board(n.value()));
				copy.value().board[row][col] = i;
				if (copy.value().isValid()) {
					n.addChild(copy);
					queue.add(copy);
				}
			}
		}
		System.out.println("nodeCount "+nodeCount);
	} // end Constructor SudokuTree

	public Node<Board> root() {
		return root;
	}

	public int nodeCount() {
		return nodeCount;
	}
} // end class SudokuTree
