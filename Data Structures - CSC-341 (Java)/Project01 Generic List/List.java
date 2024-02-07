/**
 * class List is a collection of unsorted T objects.
 * The records in the list should all have unique IDs.
 */
public class List <T extends Comparable<T>>{

    //creates a genenric array
    protected T[] items;
    

    /** Default for maximum number of items that can be stored */
    protected static final int DEFAULT_CAPACITY = 500;

    /** Maximum number of items that can be stored */
    private int capacity;

    /** The number of items stored in the array. */
    private int count = 0;

    // ============== 2 Overloaded Constructors ============== //

    /**
     * Constructor with capacity as parameter
     * 
     * @param size The capacity of the list (i.e. max number of elements)
     */
    public List(int size) {
        capacity = size;
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[size];
        items = temp;
    }

    /**
     * Default Constructor
     */
    public List() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Special Copy Constructor
     * creates a blank list using the defult constructer then add everything from tocopy to the new list
     * @param toCopy list that we are copying
     */
    public List(List<T> toCopy){
        //creates a new list with the capacity of the old list
        capacity = toCopy.capacity;
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[capacity];
        items = temp;
        //adds all of the elements from the old list to the new list
        for(int i = 0;i<toCopy.count;i++){
            this.add(toCopy.items[i]);
        }
    }

    // ************ SETTERS, GETTERS, print, helper ******************* //

    public int length() {
        return count;
    }

    public int capacity() {
        return capacity;
    }

    /**
     * Checks to see if this is full
     * @return boolean true or false if it is full or not
     */
    public boolean isFull() {
        //Compares capacity and count to determine if it is full
        if (this.capacity==count){
            return true;
        }
        return false;
    }

    /**
     * Checks if this is empty
     * @return boolean true or false if it is empty or not
     */
    public boolean isEmpty() {
        //Compares the count to zero to determine if the array is empty
        if (count==0){
            return true;
        }
        return false;
    }

    /**
     * 
     * @param index the specific slot that we are checking
     * @return boolean true or false if the spot is in this
     */
    private Boolean isValid(int index) {
        //Verifies the index is valid
        if((index<count)&&(index>=0)){
            return true;
        }
        return false;
    }

    /** Prints numbered list of items. */
    @Override
    public String toString() {
        String printedList = "";
        for (int i = 0; i < count; i++) {
            printedList += (i + 1) + ". " + items[i].toString() + "\n";
        }
        return printedList;
    } // end toString()

    // ******************* ADD ******************* //

    /**
     * Adds items to the end of the list
     * @param item what we are trying to add into the list
     */
    public void add(T item) {

        //Checks to see if the array is full
        if(this.isFull()==false){
            //Sets T in the first available open spot in the array, maintains count
            items[count]=item;
            count++;
        }
        else{
            //If array is full, increases capacity then adds the element
            this.increaseCapacity(10);
            items[count]=item; 
            count++;
        }
        
        

    } // end add(T)

    /**
     * Adds items to the list at a specific index
     * @param item specific item that we are adding
     * @param index specific spot where we are adding
     */
    public void add(T item, int index) {
        //Checks if there is a valid index
        if ((isValid(index)==true)||(index==count)){
            count++;
            //Iterates through the array backwards
            for(int i =count-1;index<i;i--){
                //Moves everything over 1 spot
                items[i]=items[i-1];
            }
            //puts our new T in the array
            items[index]=item;
        }
    } // end add(T, int)

    /**
     * Tries to add all of the new items to the passed array
     * @param items array of items that we are adding from
     * @return boolean true or false if we can add anything to this
     */
    public boolean addAll(T[] items){
        //Compares the open spaces to the number of items that need to be added
        if(this.capacity>=items.length){
            for(int i = 0;i<items.length;i++){
                this.add(items[i]);
            }
            return true;
        }
        return false;
	}

    /**
     * Increases the capacity by size
     * @param size amount that we are increasing capacity by
     */
    public void increaseCapacity(int size){
        //Gets new value of capacity
        this.capacity+=size;
        //Creates a new list with the capacity
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[capacity];
        //Copies everything over to the new list
        for(int i = 0;i<this.count;i++){
            temp[i] = items[i];
        }
        items = temp;
    }

    /**
     * Sets the specified index to the specified T 
     * @param T a specific item that we are setting in the array
     * @param index where we want to put the specific item
     */
    public void set(T T, int index) {
        //Replaces the old T with the new T
        if((isValid(index)==true)){
            items[index]=T;
        }
        
    } // end set(T, int)

    // ******************* SEARCH ******************* //

    /**
     * Returns the item in the list
     * @param index where we are trying to look in the array
     * @return null or the item at the index
     */
    public T peek(int index) {
        //Checks if the index is valid and then returns the item at that index
        if(isValid(index)==true){
            return items[index];
        }
        return null;
    } // end peek(int)

    /**
     * Returns the item if its in the list
     * @param item what we are looking for in the list
     * @return -1 if not in the list or the index of the item
     */
    public int recordNo(T item) {
        //Iterates over the list and returns the item if its in the list
        for(int i=0;i<count;i++){
            if(item.compareTo(items[i])==0){
                return i;
            }
        }
        return -1;
    } // end recordNo(T)

    // ******************* CONVERT ******************* //
    
    /**
     * Copies the old array into a new array
     * @param
     * @return a copy of the old array
     */
    public T[] toArray() {
        //Creates a new generic array
        @SuppressWarnings("unchecked")
        T[] newT = (T[]) new Comparable[count];
        //Iterates over the array and copies the old values into the new array
        for(int i =0;i<count;i++){
            newT[i]=items[i];
        }
        if(newT.length==0){
            return null;
        }
        else{
            return newT;
        }
    } // end toArray()

    /**
     * Copies as many values from the old array into a new array that will fit
     * @param array the array that we are copying from
     * @return the number of items we copied
     */
    public int toArray(T[] array){
        //creates counter variable
        int numElementsAdded = 0;
        int currentCount = this.count;
        //iterates through the count
        for(int i = 0;i<currentCount;i++){
            //added the T from the old array to this array and adds to the counter
            this.add(items[i]);
            numElementsAdded++;
        }
        return numElementsAdded;
    }//end toArray(T[] array)

    /**
     * removes a T by using the index of the T
     * @param index what slot in the array that we are trying to remove
     * @return null or the removed item
     */
    public T remove(int index){
        if(isValid(index)==false){
            return null;
        }
        //checks to see if the index is valid
        if((isValid(index)==true)||(index==count)){
            //Creates a temp variable for the item we are removing
            T item1 = items[index];
            items[index]=null;
            //Shifts all values down to fill the gap
            for(int i = index;i<count-1;i++){
                items[i] = items[i+1];
            }
            //Removes the gap and returns the temp variable
            count--;
            return item1;
        }
        return null;
    }//ends remove(int index)

    /**
     * removes a specific T from the array
     * @param f the specific item that we are trying to remove
     * @return boolean true or false if it was removed or not
     */
    Boolean remove(T f){
        int index;
        //Checks if the item is in the array
        if(recordNo(f)==-1){
            return false;
        }
        else{
            index = recordNo(f);
        }
        //Removes the item in the array
        this.remove(index);
        return true;
    }//ends remove(T f)

    /**
     * Removes all items in the list
     */
    public void clear(){
        this.count = 0;
    }//ends clear()

    /**
     * Finds the largest value in the list and returns it
     * @return null or the largest value
     */
    public T max(){
        if(isEmpty()){
            return null;
        }
        //Creates a max variable
        T max = items[0];
        //Iterates over the array comparing max to all values
        for(int i = 0;i<count;i++){
            if(items[i].compareTo(max)>0){
                //Sets max to the new max
                max = items[i];
            }
        }
        return max;
    }//end max()


    /**
     * Finds the smallest value in thr list and returns it
     * @return null or the smallest value
     */
    public T min(){
        if(isEmpty()){
            return null;
        }
        //Creates a min variable
        T min = items[0];
        //Iterates over the array comparing min to all values
        for(int i = 0;i<count;i++){
            if(items[i].compareTo(min)<0){
                //sets new min
                min = items[i];
            }
        }
        return min;
    }//end min() 

    

} // end class List
