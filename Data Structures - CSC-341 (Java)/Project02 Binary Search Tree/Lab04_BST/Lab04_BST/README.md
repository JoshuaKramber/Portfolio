## Lab 04 Binary Search Trees

DUE Thursday, October 27th end-of-day

In this lab, you will again create the Abstract Data Type List, except this time you will use a Binary Search Tree for implementation. The List will make use of generic types that implement the interface Comparable.

Learning Outcomes:
- Understand the structure of a binary tree.
- Understand the structure of a binary search tree.
- Know how to implement a binary search tree.
- Know how to implement a class that uses the Comparator interface.
- Use iteration to traverse a binary search tree.
- Use recursion to traverse a binary search tree.


<hr>

### General Requirements

1. Implementation
- `int compareTo(Track other)` in class Track. Use the song title as the basis of comparison.
- `boolean equals(Object other)` in class Track.
- `public static class byTitle` in file Order.
- `public static class byPopularity` in file Order. Use the song title as a tie-breaker.

2. Implementation for class List
- `void add(T value)`
- `void print()` : **Use recursion** to print values in order
- `T find(T value)` : Use List ordering to find first match. Note that it will only match on the basis of the ordering. For example, if it is ordered by Artist, then the first element that matches the Artist in value will be returned.
- `T find(T value, Comparator<T> comp)` : use the passed Comparator to find the first element that matches the value. As stated above, this will find the first match (based on the List ordering) that matches on the basis of `comp`.
- `T min()` : based on order of tree
- `T max()` : based on order of tree
- `T[] toArray()` : place elements in the tree into the array IN SORTED ORDER. You cannot use any Java functions to do this for you. You must declare the array, and individually place each element into the array.

3. Javadocs: complete and polished for `class List`.
4. Code is commented in added methods of `class Track`
5. Code is commented in all methods of `class List`.
6. Code is style compliant.
7. Questions answered in report.txt.

<hr>

### Answer Questions

1. What is the worst-case scenario of `find(T value)` and of `find(T value, Comparator<T> comp)`? Briefly describe the situations that produces these worst-case scenarios. Would you characterize each as linear, logarithmic, or constant with respect to the size of the collection? 

2. What is best-case and worst-case scenarios for `add(T value)` for a BST? Briefly describe the situations that produces these best- and worst-case scenarios. Would you characterize each as linear, logarithmic, or constant with respect to the size of the collection? 

3. What are the differences in space requirements for a Binary Search Tree implementation of a List versus an array implementation?

4. Imagine you are frequently adding new customers to your dataset. On occasion, you have to print a report of all customers in alphabetical order. What would be the best choice for implementing the List of customers? Briefly justify.

### Resources 

- https://www.kaggle.com/datasets/leonardopena/top50spotify2019
- https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
- https://www.geeksforgeeks.org/comparator-interface-java/
- https://www.baeldung.com/java-comparator-comparable
- https://www.geeksforgeeks.org/comparator-interface-java/
- zyBooks reading assignment #07










