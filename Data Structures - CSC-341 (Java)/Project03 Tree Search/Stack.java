public class Stack<T> implements QueueInterface<T> {
    //instance variables
    private int count;
    Node root;

    /**
     * default constructor
     */
    public Stack(){
        root = new Node(null);
        count = 0;
    }

    /**
     * checks to see if the stack is empty
     */
    public boolean isEmpty() {
        if(count==0){
            return true;
        }
        return false;
    }

    /**
     * adds a new value to the stack
     */
    public void push(T value) {
        //always add/maintain the head
        Node n = new Node(value);
        n.next = root.next;
        root.next = n;
        //maintain count
        count++;
    }

    /**
     * removes the value after the dumby node
     */
    public T pop() {
        //case1: if empty
        if(count==0){
            return null;
        }
        //case2: is there is only 1 value
        else if(count==1){
            //removes the value after the dumby node and maintains count
            T temp = root.next.value;
            root.next = null;
            count--;
            return temp;
        }
        //case3: if theres more than 1 value
        //removes the value after the dumby node and maintains count
        T temp = root.next.value;
        root.next = root.next.next;
        count--;
        return temp;
        }

    /**
     * returns the first value in the stack
     */
    public T peek(){
        //returns the value after the dumby node
        return root.next.value;
    }

    /**
     * getter for the count
     */
    public int size(){
        return count;
    }

    /**
     * Override for the default toString method
     */
    public String toString(){
        String result = "";
        Node current = root.next;
        while(current!=null){
            result = result + " " + current.value.toString();
            current = current.next;
        }
        return result;
    }

    //Helper funtions below
    private class Node{
        T value;
        Node next;

        Node(T v){
            value = v;
        }
    }
}
