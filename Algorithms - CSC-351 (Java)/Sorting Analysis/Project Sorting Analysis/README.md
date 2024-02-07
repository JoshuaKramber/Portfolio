## Sorting Analysis Project

<hr>

You may and should copy the algorithms from the textbook! Just remember to convert to 0-based indexing. You may look for examples of the Function class and the Comparator class to understand how they fit into this project. You may look at any documentation of Java to help with syntax.

Please do not be shy about asking me questions. This is a lot of code and I am happy to walk you through any parts that you do not understand. I can typically debug code pretty fast - tooting my own horn :-) -- but really, I'm fast, just ask when you are stuck!

<hr>

**In this comparative algorithm project**, you will be implementing sorting algorithms then comparing their runtime efficiency. Each algorithm will be used to sort a collection of arrays of increasing size. The arrays will be either in increasing sorted order, decreasing sorted order, or random order to begin with. The efficiency of the algorithm will be assessed based on the actual time it takes to run the algorithm and counting the total number of iterations/comparisons executed. The final deliverable is a report that presents and discusses the results.

First, you will implement the algorithms _Insertion_, _Counting_, and _Merge_ Sort within this provided framework. Each sorting algorithm is a method in a class that implements **the interface `Sorter`**. This structure is in place to assist in the experimental phase of the project. A _Sorter_ uses generics and technically could be used to sort any type of object. However, for Counting, we will assume that only Integer types will be sorted. For testing purposes, all algorithms will sort objects of type `AlphaNumeric`.

As part of your implementation of an algorithm, count the number of comparisons or iterations that are executed. There is a `count` variable in each _Sorter_ that should be reset to 0 at the start of the algorithm and incremented for each iteration. 


### Understanding the Code

`AlphaNumeric` is a very simple class that has 2 member variables -- one of type String (alpha) and one of type Integer (numeric). This is the primary class that will be used in the experiments. The code creates Java basic arrays of type `AlphaNumeric` in various configurations to be sorted by the different algorithms.

The `AlphaNumeric.orderAlpha` and `AlphaNumeric.orderNumeric` are of type `Comparator<AlphaNumeric>` which implements the `compare(T,T)` method. In _Insertion Sort_ and _Merge Sort_, this Comparator is assigned to the variable _orderBy_. It is used in this way to test if `array[i] > array[i+1]`:

```Java
if (orderBy.compare(array[i], array[i+1]) > 0) {
```

`ArrayMaker` can make arrays that are in random, ascending, or descending order relative to the provided comparator (i.e. `orderNumeric` or `orderAlpha`).

The `Sorter` interface has 2 forms of `sort()`, as well as the `getCount` method. All sorting algorithms implement this interface.

`class Insertion`, `class Merge`, `class Counting`, `class Radix` contain the implementations of the sorting algorithm. The constructor for each of these classes has a parameter for the Comparator. After instantiating a Sorter, you can pass it an array to be sorted.

`class Main` is the primary driver of the application. It has the structure to create a collection of arrays that can be sorted by the various Sorters.

<hr>

**Instantiation and use of Insertion Sort** looks like this:

```Java
Insertion<AlphaNumeric> insertionAlpha = new Insertion<>(AlphaNumeric.orderAlpha);
insertionAlpha.sort(array);
```

or 

```Java
Insertion<AlphaNumeric> insertionNumeric = new Insertion<>(AlphaNumeric.orderNumeric);
insertionNumeric.sort(array);
```

> In Insertion Sort, increment the counter (i.e. `++count`) inside the outer loop and the inner loop. This is to count the comparison in the stopping condition of the inner for loop, as well as all comparisons in the inner loop. 
<hr>

**Instantiation and use of Merge Sort** looks like this:

```Java
Merge<AlphaNumeric> mergeAlpha = new Merge<>(AlphaNumeric.orderAlpha);
mergeAlpha.sort(array);
```

or 

```Java
Merge<AlphaNumeric> mergeNumeric = new Merge<>(AlphaNumeric.orderNumeric);
mergeNumeric.sort(array);
```

>In Merge sort, increment the count in the inner and outer loop of the nested for loop at the end of Merge. Only count these iterations. The copying will be managed by a different counter. 



**Instantiation and use of the Counting Sorter** is:

```Java
Counting<AlphaNumeric> countingNumber = new Counting<>(AlphaNumeric.numberGetter);
countingNumber.sort(array);
```

>In Counting Sort, increment the counter in each of the for loops. 

> Notice that the call to sort the array only passes the array, however Counting Sort does not sort in-place. It places the sorted elements into another array. The user has the option to place the sorted elements back into the original array or preserve the ordering of the original and place the sorted elements in a different array. This is managed from inside the Sorter. Note that this might involve the time to copy the array to another array.

**Instantiation and use of the Radix Sorter** is:

```Java
Radix<AlphaNumeric> radixNumber = new Radix<>(AlphaNumeric.numberGetter);
radixNumber.sort(array);
```

The `numberGetter` is of type `Function<AlphaNumeric,Integer>`. When it is applied to an AlphaNumeric object, it will call the getter `number()`. It looks like this ...

```
Integer valueOf = keyGetter.apply(element);
```

In the above example, if the element is an AlphaNumeric object with the values "aab" and 734 and the keyGetter is `AlphaNumeric.numberGetter`, then `_valueOf_ = 734` after this line is executed.

The _Counting Sort_ algorithm will also use this `Function<>` to extract the Integer portion of an AlphaNumeric object.



**This is an independent project. You are to implement your own code. You may not copy code off the web. You may not email your code to a classmate. You may not let other's see your code so that they can copy it. You may not use TutorMe without my consent - it is likely to be a waste of your time anyway.**


