public class Queue<T> implements QueueInterface<T> {

    //instance variables
    @SuppressWarnings("unchecked")
    T[] queue = (T[]) new Object[1000000];
    int capacity = queue.length-1;
    int size = 0;
    int front = 0;
    int end = 0;

    /**
     * Queue constructor
     */
    public Queue(){
        
    }

    /**
     * adds the value at the end of queue
     */
    public void push(T value){
        //checks if we need to resize the queue
        if(size==capacity-1){
            //calls the resize method
            this.resize();
        }
        //checks if the next spot is a dumby node
        if(end+1!=front){
            //if the next spot is a dumby node then start at beginning of the array
            if(end==capacity-1){
                queue[end] = value;
                end = 0;
            }
            //otherwise continue adding in array order
            else{
                queue[end] = value;
                end++;
            }
            //maintain size
            size++;
        }
    }

    /**
     * removes the value at the front of the queue
     */
    public T pop(){
        //creates a temp of the front value and sets that spot to null
        T value = queue[front];
        queue[front] = null;
        //wraps back around if needed
        if(front==capacity){
            front = 0;
        }
        else{
            //maintain front
            front++;
        }
        //maintain size
        size--;
        return value;
    }

    /**
     * returns the value at the front of the queue
     */
    public T peek(){
        return queue[front];
    }

    /**
     * checks if the queue is empty
     */
    public boolean isEmpty(){
        if(front==end){
            return true;
        }
        return false;
    }

    /**
     * getter for size
     */
    public int size(){
        return size;
    }

    /**
     * Overide of the original toString method
     */
    public String toString(){
        String result = "";
        for (int i=0; i<size; i++) {
            result = result + queue[i] + " ";
        }
        return result;
    }

    /**
     * creates a new copy of the queue that is doubled in capacity
     */
    private void resize(){
        //gets a num = queue capacity*2
        int newCapacity = capacity*2;
        //creates new queue
        @SuppressWarnings("unchecked")
        T[] newQueue = (T[]) new Object[newCapacity+1];
        int current = front;
        //adds all of the values into the new queue
        for(int i = 0;i<size;i++){
            newQueue[i]=queue[current];
            current++;
            current = current%queue.length;
        }
        //maintains pointers
        capacity = newCapacity;
        front = 0;
        end = size;
        //sets the name to the old name
        queue = newQueue;
    }
}
