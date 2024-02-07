public interface QueueInterface<T> {

    /**
     * adds the value as a node into either the stack or the queue
     * @param value
     */
    public void push(T value);

    /**
     * removes a node from the stack/queue
     * @return value of removed node
     */
    public T pop();

    /**
     * looks at the next value in the stack/queue
     * @return the next value in stack/queue
     */
    public T peek();

    /**
     * checks if the stack/queue is empty 
     * @return boolean 
     */
    public boolean isEmpty();

    /**
     * gets the number of elements in the stack/queue
     * @return num elements
     */
    public int size();
}
