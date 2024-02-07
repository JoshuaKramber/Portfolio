import java.util.*;
import java.util.function.Function;

import javax.print.attribute.standard.RequestingUserName;

public class SortedList<T extends Comparable<T>> {

    /** Count of elements in the List */
    int count = 0;

    /** Comparator for ordering tree. Uses compareTo of T by default */
    Comparator<T> ordering = new Comparator<T>() {
        @Override
        public int compare(T object1, T object2) {
            return object1.compareTo(object2);
        }
    };

    Node root;
    Node current;
    
    public SortedList() {
        root = null;
    }

    public SortedList(Comparator<T> c) {
        ordering = c;
    }

    /**
     * Prints the values in order using recursion
     */
    public void print() {
        //if the root is null then it returns nothing
        if(root==null){
            return;
        }
        else{
            //sets current equal to root then iterates over the tree and prints the values
            current = root;
            myPrint(current);
        }
    }
  
    /**
     * adds the item to the orrdered tree
     * @param item
     */
    public void add(T item) {
        //creates a new node with the item as the value
        Node newItem = new Node(item);
        //if the root is null then the item becomes the head of the tree, increases the count
        if (root == null) {
            root = newItem;
            count++;
            root.height = 0;
         }
         else {
            //iterates over the sorted tree
            current = root;
            while (current != null) {
                //if the value of the node is less than our new value then it goes to the left child
               if (ordering.compare(current.value,newItem.value)>0) {
                //if the left child is null then it sets the left child to our new node, maintains count
                  if (current.left == null) {
                     current.left = newItem;
                     //sets parent
                     current.left.parent = current;
                     newItem.height = 0;
                     //increases the height of each parent node in the tree
                     while(current.parent != null){
                        current.height++;
                        current = current.parent;
                     }
                     current = null;
                     count++;
                  }
                  //continues going left
                  else {
                     current = current.left;
                  }
               }
               //if the right child is null then it sets our node to be the left child, maintains count
               else {
                  if (current.right == null) {
                    //sets the new node and the parent
                     current.right = newItem;
                     current.right.parent = current;
                     newItem.height = 0;
                     //increases the height of each parent
                     while(current.parent != null){
                        current.height++;
                        current = current.parent;
                     }
                     current = null;
                     count++;
                  }
                  else {
                    //contines going right
                     current = current.right;
                  }
               }
            }
            if(Math.abs(root.right.height-root.left.height)>10){
                this.balance();
            }
         }
    }
    
    /**
     * finds the value in the tree
     * @param value
     * @return null or the value
     */
    private Node find(T value) {
        Node current = root;
        //while the node is not found, continue iteration
        while (current != null) {
            //if the value is found then return the value
            if (current.value.equals(value)) {
                return current;
            }
        //compares the values and then goes right or left depending on the comparison
        else if (ordering.compare(current.value, value)>0) {
            //goes left
            current = current.left;
        }
        else {
            //goes right
            current = current.right;
        }
        }
        return null;
    }

    /**
     * Finds the value in the tree otherwise returns null
     * @param value
     * @return true or false if the value is in the tree
     */
    public boolean contains(T value){
        //uses the find method, if its null then false
        if(this.find(value)==null){
            return false;
        }
        return true;
    }

    /**
     * Finds the next child of the value
     * @param value
     * @return value
     */
    public T successor(T value){
        //checks if the right child is null
        if(this.find(value).right!=null){
            //finds the min value of the subtree
            Node current = this.find(value).right;
            while(current.left!=null){
                current = current.left;
            }
            return current.value;
        }
        else{
            //travels up the tree until it can determine that the node is a left child
            current = this.find(value);
            while(!current.parent.left.equals(current)){
                current = current.parent;
            }
            return current.parent.value;
        }
    }

    /**
     * Finds the next parent of the value
     * @param value
     * @return value of the predecessor
     */
    public T predecessor(T value){
        //checks if the left child is null
        if(this.find(value).left!=null){
            //finds the max of the right subtree
            Node current = this.find(value).left;
            while(current.right!=null){
                current = current.right;
            }
            return current.value;
        }
        else{
            //climbs the tree until it finds that the current node is also a right child
            current = this.find(value);
            while(!current.parent.right.equals(current)){
                current = current.parent;
            }
            return current.parent.value;
        }
    }

    /**
     * Iterates over the tree and adds all values that match into an arraylist
     * @param fn
     * @param obj
     * @return arrayList containing everything that fits the search
     */
    public ArrayList<T> query(Function<T,Boolean> fn, Object obj){
        //creates a generic arraylist
        ArrayList<T> aList = new ArrayList<T>();
        //uses a helper method to search the tree
        queryHelper(root,aList,fn,obj);
        return aList;

    }

    //query helper method
    private void queryHelper(Node node, ArrayList<T> aList, Function<T,Boolean> fn, Object obj){
        if (node==null){
            return;
        }
        queryHelper(node.left, aList,fn,obj);
        if(fn.apply(node.value).equals(obj)){
            aList.add(node.value);
        }
        queryHelper(node.right, aList,fn,obj);
        return;
    }
    
    /**
     * iterates over the tree using recursion to try to find the value
     * @param value
     * @param comp
     * @return null or the value if its found
     */
    public T find(T value, Comparator<T> comp) {
        Node current = root;
        //recursivly searches
        return inOrderTraversal(current, comp, value);
    }
    
    /**
     * iterates over the tree and finds the lowest value
     * @return min value in the tree or null
     */
    public T min() {
        current = root;
        //checks if the left child is null and iterates to the left
        while(current.left!=null){
            current = current.left;
        }
        //returns the value
        return current.value;
    }

    /**
     * iterates over the tree and finds the max value
     * @return the max value or null
     */
    public T max() {
        current = root;
        //checks if the right child is null and iterates to the right
        while(current.right!=null){
            current = current.right;
        }
        //returns the largest value
        return current.value;
    }

    /**
     * copies all of the values of the tree and sorts them into an array
     * @return a sorted array based on the values of the tree
     */
    T[] toArray() {
        int x = 0;
        //creates a generic array based on the size of the tree
        @SuppressWarnings("unchecked")
        T[] genArray = (T[]) new Comparable[count];
        //recursivly iterates and adds all of the tree values to an array
        toArrayHelper(root, genArray, x);
        //returns the sorted array
        return genArray;
    }

    /**
     * reorders the tree then puts it into a sorted array
     * @param comp
     * @return a generic sorted array
     */
    public T[] toArray(Comparator<T> comp){
        //creates a new generic array
        @SuppressWarnings("unchecked")
        T[] genArray = (T[]) new Comparable[count];
        //reorders the tree based on the new comparator
        this.reorder(comp);
        //calls the regular toArray method which copies the tree into an array
        genArray = this.toArray();
        return genArray;

    }

    /**
     * Sorts the array based on the new comparator
     * @param comp
     */
    public void reorder(Comparator<T> comp){
        T[] genArray = this.toArray();
        //sorts the array using selection sort
        for(int i = 0;i<genArray.length;i++){
            for(int i2 = i;i2<genArray.length;i2++){
                if(comp.compare(genArray[i],genArray[i2])<0){
                    T temp = genArray[i];
                    genArray[i] = genArray[i2];
                    genArray[i2] = temp;
                }
            }
        }
    }

    private void balance(){
        //middle of the array = new root, then use recursion to sweep out
        T[] genArray = this.toArray();  
        root = balancer(genArray,0,genArray.length);
        return;
    }

    //I'm gonna be honest, I couldn't fully figure this out so I attempted it as best as I could
    private Node balancer(T[] array,int start, int end){
        if(end<start){
            return null;
        }
        int middle = (start + end)/2;
        Node n = new Node(array[middle]);
        @SuppressWarnings("unchecked")
        T[] genArray = (T[]) new Comparable[middle];
        for(int i = 0;i<genArray.length;i++){
            genArray[i] = array[i];
        }
        //call recursion
        n.left = balancer(genArray,0,middle-1);
        if(n.left!=null){
            n.left.parent = n;
            n.height++;
        }
        //do right side instead of left
        middle = (start + end)/2;
        n = new Node(array[middle]);
        @SuppressWarnings("unchecked")
        T[] genArray2 = (T[]) new Comparable[middle];
        for(int i = 0;i<genArray.length;i++){
            genArray2[i] = array[i+middle+1];
        }
        //call recursion
        n.right = balancer(genArray,middle+1,count);
        if(n.right!=null){
            n.right.parent = n;
            n.height++;
        }

    }

    /**
     * Checks if the list is empty
     * @return boolean based on count
     */
    public boolean isEmpty(){
        if(count==0){
            return true;
        }
        return false;
    }
    
    /**
     * gets the count of the list
     * @return count
     */
    public int size(){
        return count;
    }

    /**
     * adds all of the elements of the array into a list
     * @param array
     */
    public void addAll(T[] array){
        //iterates over the array
        for(int i = 0;i<array.length;i++){
            //uses the add funtion to add the element to the list
            this.add(array[i]);
        }
    }

    /**
     * returns the height of the tree
     * @return height of the tree
     */
    public int height(){
        //checks if the list is empty
        if(isEmpty()){
            return 0;
        }

        //sets an iterating node
        Node current = root;
        int treeHeight = 0;
        //recursivly finds the height
        treeHeight = myHeight(current);
        return treeHeight;
    }

    //Helper funtions below
    private class Node{
        T value;
        Node left;
        Node right;
        Node parent;
        int height;

        Node(T v){
            value = v;
        }
    }

    private int myHeight(Node node){
        if(node==null){
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;

        if(node.left!=null){
           leftHeight = myHeight(node.left);
        }
        else if(node.right!=null){
            rightHeight = myHeight(node.right);
        }
        return Math.max(leftHeight,rightHeight) + 1;
    }

    private void myPrint(Node node){
        if (node == null){
        return;   
        }                  

        myPrint(node.left);
        System.out.println(node.value + " ");                     
        myPrint(node.right);
    }

    private T inOrderTraversal(Node node, Comparator<T> comp, T value){
        if (node==null){
            return null;
        }
        T x = inOrderTraversal(node.left, comp, value);
        if(x!=null){
            return x;
        }
        if (comp.compare(node.value, value)==0){
            return node.value;
        }
        x = inOrderTraversal(node.right, comp, value);
        if (x!=null){
            return x;
        }
        return null;
    }

    private int toArrayHelper(Node node, T[] genArray, int x){
        if (node==null){
            return x;
        }
        x = toArrayHelper(node.left, genArray, x);
        genArray[x] = node.value;
        x++;
        x = toArrayHelper(node.right, genArray, x);
        return x;
    }

  } // end class List
