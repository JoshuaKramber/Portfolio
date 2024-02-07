## Project 2 Binary Search Trees

### Implementation due Wednesday, November 23 end-of-day
_Zip all files and submit via Moodle_

### Questions due Sunday, November 27 end-of-day
_Submit as a pdf in the Lab 4 Questions submit link on Moodle_


<hr>

In this project, you will be building on previous labs to create a complete ListOrdered class that is implemented with a Binary Search Tree (BST). This list class will make use of Comparators to find, add, and sort elements.

Start with your Lab 04 BST, which should have the following:

- `void add(T value)`
- `void print()`
- `T find(T value)`
- `T find(T value, Comparator<T> comp)`
- `T min()`
- `T max()`
- `T[] toArray()`

<hr>

**If you were not able to complete Lab 4 or something wasn't working correctly, please come to office hours and I will fix it or provide you with code.**

<hr>


## Requirements

#### IMPLEMENTATION: Minor additions and modifications to Lab 4

- Rename the class to `SortedList`.
- Add `Node parent` to the Node class.
- Add `int height` to the Node class.
- Add `public boolean isEmpty() { return count==0; }`
- Add `public int size() { return count;}`
- Add `public void addAll(T[] array)`. Add all elements of array to the List.

<hr>

#### IMPLEMENTATION: Additions and modifications of methods related to tree height and ordering.

- Add `public int height()` that returns the height of the tree. _This should be private, but then I could not inspect it for testing._

- Modify `public void add(T value)` to maintain the height. After the node is added to the tree, adjust its parent's height, as appropriate, which might require adjusting its parent, and so on. Also, if at any point it is determined that the left and right subtrees of any node have heights that differ by 10, then rebuild the tree to balance it.

- Add `public T[] toArray(Comparator<T> comp)`: Return an array in an order based on the specified comparator. Use your toArray() and reorder() methods.

- Add `private void balance()`: Put everything into an ordered array and then reinsert into the tree. Think about which element you want as the root to make the left and right subtrees of equal height (or at most difference of 1). Then think about which nodes that you want as the left and right child of the root. This method needs to be recursive. Adding nodes back into the tree should automatically reset the height of each node because the add function is maintaining it. **Note, set the root to null before adding the nodes back in.**

- Add `public void reorder(Comparator<T> comp)`: Replace the ordering value with this comparator. Reorder the tree. Make sure it is **balanced**. Place the collection into an array, use the sorting algorithm from Lab 5 to sort according to the comparator, then place back into the tree in a balanced fashion.

<hr>

#### IMPLEMENTATION: Additions and modifications of methods related to search.

- Add/Modify `private Node find(T value)`. Modify your find(T value) method from Lab 4 so that it is private and returns the Node, rather than the value. Return null if it is not in the tree.

- Add `public boolean contains(T value)`. Call your `find(T value)` method from above. If it returns null, return false, else return true.

- Add/Modify `public ArrayList<T> query(Function<T,Boolean> fn)`: Fill the ArrayList with all elements that are equal to the value that satisfy the query (i.e. return true from applying the specified Function). This is a modification of your `find(T value, Comparator<T> comp)` method from Lab 4.

<hr>

#### IMPLEMENTATION: Successor and Predecessor.

When removing nodes or rebalancing a tree, it can be useful to find the successor and predecessor of a given node. We are not using these methods in this implementation because we do not perform rotations or remove nodes, but it is a good exercise.

> Suggestion: draw a tree with various configurations and follow the below algorithms to make sure you understand how these algorithms find the successor and predecessor.

- Add `public T successor(T value)`.  _Normally, this would be private and probably return a Node, but then I couldn't test your code._ Use your find method that returns the node corresponding to the given value. Then use the below algorithm to find its successor in the tree. If it has no successor, return null.

  If right subtree is not NULL, then successor is the minimum of the right subtree.
  If right subtree is NULL, then successor is one of the ancestors (if it exists -- notice that if the node is the rightmost node, there is no successor). Travel up using the parent pointer until you see a node which is a left child of its parent. The successor is that child's parent.

- Add `public T predecessor(T value)`. _Normally, this would be private, but then I couldn't test it._ Use your find method that returns the node corresponding to the given value. Then use the below algorithm to find its predecesor in the tree. If it has no predecessor, return null.

  If left subtree is not NULL, then predecessor is maximum value of the left subtree.
  If left subtree is NULL, the predecessor is an ancestor (if it exists -- notice that if the node is the leftmost node, there is no predecessor). Travel up the tree using parent pointers until you see a node that is the right child of its parent. The predecessor is that child's parent.

<hr>

### Documentation

- Javadocs are professional and complete. They do not describe implementation -- only input and output.
- Comment the code. Not too much, not too little.
- Style compliant.

<hr>

### Testing

It is up to you to test your code and make sure it is meeting the requirements. I will not assess your tests. I _might_ release some basic tests. I will help you to define the lambda function for the query.

<hr>
<hr>

### QUESTIONS

Questions are due Sunday, November 27, which is after the implementation is due. Please submit these questions as a pdf. Use the link specifically for the questions.

The quality of your answers matter. Please take the time to provide thoughtful, well-written answers.

1. Discuss the necessity of balancing the binary search tree. Include in your discussion the worst-case runtime bounds for the various operations on the tree.

2. Discuss the inefficiency of rebalancing the tree in the manner that is being done in this implementation. Include in your discussion the number of times each node/value is visited in the process of rebalancing. How do you think this compares to maintaining a Red-Black tree or an AVL tree that rebalances through node rotations at each add?

3. Consider a problem in which a collection of objects is being maintained in an ADT List. Describe requirements of this problem that would lead you to think it would be better to manage this collection with a List implemented using a Binary Search Tree, rather than an unsorted array. Be sure to comment on all factors influencing this choice, such as the operations add, remove and find; the frequency of the various operations; and memory requirements. Note that there is not one answer to this question. Different circumstances warrant different choices, and this question is asking you to describe circumstances in which one choice is better than the other.

4. Again, consider a problem in which a collection of objects is being maintatined in an ADT List. Describe the requirements of this problem that would lead you to think it would be better to manage this collection with a List implemented using an unsorted linked list, rather than a binary search tree or an array. Be sure to comment on all factors influencing this choice.
