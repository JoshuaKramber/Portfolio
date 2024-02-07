import java.util.ArrayList;

public class Node<T> {

	/** Value of any Object type*/
	private T value;

	/** Can have any number of children */
	private ArrayList<Node<T>> children = new ArrayList<>();

	/** Constructor
	  * @param v Initial value of Node.
	*/
	public Node(T v) {
		value = v;
	}

	/** Add a Node that is a child of "this"
	* @param n Node with <T> type value.
	*/
	public void addChild(Node<T> n) {
		children.add(n);
	}

	public String toString(){
		return value.toString();
	}

	/** Getter for children. */
	public ArrayList<Node<T>> children() {
		return children;
	}

	// Getter and setter for value
	public T value() {
		return value;
	}
	public void value(T v) {
		value = v;
	}
}
