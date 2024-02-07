## Project 3 Breadth First (BFS) and Depth First Search (DFS) of Trees

### Due Friday, December 9 end-of-day
_Zip all files and submit via Moodle_

<hr>

In this project, you will create the ADT Queue and the ADT Stack using Java's basic array data structure and a linked list, respectively. Both the Queue and Stack must implement the QueueInterface, which you will write.

The Queue should be implemented using a **Circular Buffer** (a.k.a. a **Ring Buffer**), which uses indices to mark the front and back of the queue that is stored in the array. You should also use a **dummy value** in this implementation, meaning an extra space that is a buffer between the front and the back of the queue.

> If the indices used for the front and back of the Queue are equal, then the Queue is empty. If the back index + 1 would be equal to the front index of the Queue, then the Queue is full.

The Stack should be implemented using a Linked List with a dummy node at the head of the list. For the implementation, both add and remove from the front of the list.

You will create a small test tree and output the results of a BFS and DFS to make sure you understand the structure of a tree, traversal of the tree using either BFS or DFS, and to test your code.

You will use your Queue and Stack to traverse a tree that represents a Sudoku puzzle (which has been provided). Each node can have 0 to 9 children, depending on the size of the puzzle and the constraints of the specific puzzle. Children are stored in an `ArrayList<Node<Board>>`. While traversing the tree to find the solution, you will track the number of nodes visited to compare a BFS to a DFS. The code is identical for the 2 different searches, except that a BFS uses a Queue and the DFS uses a Stack.

<hr>

### Implementation of QueueInterface

Your `public interface QueueInterface<T>` should have the following public methods.

- `public void push(T value)` : add a value to the queue.
- `public T pop()` : remove the next element of the queue.
- `public T peek()` : return the next element of the queue, but do not remove it.
- `public boolean isEmpty()` : true if the queue is empty.
- `public int size()` : getter for the number of elements in the queue

> Note that queue above is not capitalized. The term queue refers to either a first-in-first-out (FIFO) queue (i.e. a Queue) or a last-in-first-out (LIFO) queue (i.e. a Stack).

<hr>

### Implementation of class Queue

The method descriptions for `public class Queue<T> implements QueueInterface<T>` are:

- `public void push(T value)` : add a value to the end of the Queue.
- `public T pop()` : remove the first element of the Queue.
- `public T peek()` : return the first element of the Queue, but do not remove it.
- `public boolean isEmpty()` : true if Queue is empty.
- `public int size()` : getter for the number of elements in the Queue
- `public String toString()` : override Object toString

Your class Queue should have the following private members and methods. You may include more than these.
- `private int front` : index of the first element in the Queue
- `private int end` : index of the location to place the next added value
- `private int capacity` : size of the array - 1 (1 extra for "dummy")
- `private int size` : number of elements in the array
- `private void resize()` : double the size of the array if it is overflowing   

> Note that when pushing an element onto the Queue, if the Queue is full, call resize() to double its size.

<hr>

### Implementation of class Stack

The method descriptions for `public class Stack<T> implements QueueInterface<T>` are:

- `public void push(T value)` : add a value to the "top" of the Stack.
- `public T pop()` : remove the "top" of the Stack.
- `public T peek()` : return the "top" of the Stack, but do not remove it.
- `public boolean isEmpty()` : true if Stack is empty.
- `public int size()` : getter for the number of elements in the Stack
- `public String toString()` : override Object toString

Your class Queue<T> should have the following class member variable. You may include more.
- `private int head` : points to the dummy node at the front of the list.

> Note that there is no need for a tail in a stack. All operations are managed from the one end.

<hr>

### Implementation for Testing Tree Searches

Create a method `public static void testSearch()` in Main. In this method, create a small test tree with known structure and Integer values. Vary the number of children in the tree, ranging from 0 to 3. Perform a BFS and DFS on the tree, printing each node value as it is removed from the queue (this is explained in more detail for the Sudoku puzzle below). Additionally, record the maximum number of elements on the Queue (or Stack) and print that value. Use this to make sure that your Queue and Stack are working. This will also insure that you know how to traverse a tree. 

### Implementation for Solving a Sudoku Puzzle

In the class Main, perform a Breadth-First-Search over the SudokuTree puzzle to find the solution. Start by placing the root of the tree in a Queue. In a while loop (until a solution is found), pop an element from the Queue, check if the value in the Node is a complete solution (board.isComplete()), and **print the solution**. Keep track of the number of "visited" nodes, meaning count the total number of nodes pushed onto the queue. Also keep track of the maximum number of elements that are ever on the queue. **Print each of these statistics.**

Add exactly 1 line of code to the above to make it a Depth-First-Search. Instead of using a Queue, use a Stack. You can use one or the other by simply commenting out one of the 2 lines that declare a Queue and a Stack.


### Documentation

- Javadocs are professional and complete. They do not describe implementation -- only input and output.
- Comment the code. Not too much, not too little.
- Style compliant.

<hr>

### Testing

It is up to you to test your code and make sure it is meeting the requirements. I will not assess your tests. I _might_ release some basic tests.
<hr>
<hr>

### QUESTIONS

The quality of your answers matter. Please take the time to provide thoughtful, well-written answers.

1. Explain the benefits of using a dummy node and dummy value in the Linked List and in the Circular Buffer.

2. Explain the benefits of using a Circular Buffer to implement a Queue, as opposed to using an array in which the first element is always at index 0. Explain why using a Circular Buffer to implement a List is not a good idea.

3. Report on the number of nodes visited and the maximum size of the Queue/Stack in both the BFS and DFS, when solving the 9x9 Sudoku puzzle. Which one outperformed the other, meaning the numbers were significantly smaller? Briefly explain why those differences were observed.

4. Explain the benefits of using the interface for the Queue and Stack.
