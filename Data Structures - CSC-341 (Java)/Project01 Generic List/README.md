### PROJECT 1 : The LIST Abstract Data Type
#### Due Tuesday, October 11 end-of-day
#### Submit a single zipped file to Moodle
#### Complete the Google form to report status and answer questions: <a href="https://forms.gle/nGLb8jed8LL6cqK47" target="_blank">https://forms.gle/nGLb8jed8LL6cqK47</a>

In this first project, you will complete a generic List class that is implemented using
Java's basic array data structure. The List class will be able to manage a collection
of any type. It will also implement the Comparable interface, so that an ordering can
be established within the collection.

Following a style guide is an important part of coding on group projects. It makes it easier
for everyone to read and understand the code. There are also some stylistic choices that
can improve code readability. There are some style requirements described below.

Documentation is an important part of coding. You are expected to complete the Javadocs
for every member variable and method within the List class. Additionally, there should be
comments using the double slash within the code to explain THE PURPOSE of the following lines
of code, not redundantly describe the code.

Understanding the implementation and its advantages/disadvantages is also an important
part of coding. You are expected to answer the questions as part of your submission.

Learning Outcomes:

- Understand and implement a class making use of a generic type.
- Understand and implement the Comparable interface.
- Understand and implement the primary operations for a _List_ implemented with an array.
- Understand the practice of _Test-Driven Development_.
- Practiced in code documentation using Javadocs.
- Practiced in code testing to verify correctness.

<hr>

### General Requirements

- Implement the methods for the List class as described below. At a minimum, all methods
must have a stub so that the tests will compile.
- Comply with the listed style guides.
- Document all code with Javadocs.
- Comment your code (rule of thumb: ~1 comment to every 3-4 lines).
- Answer the discussion questions.
- Complete the Google form: https://forms.gle/nGLb8jed8LL6cqK47

##### New Methods
- `increaseCapacity()`
- `clear()`
- `min()`
- `max()`


### Getting Started

Pull the repo to get this file and all of the tests. Copy your List.java from the lab to this folder. You will need to modify everything to use a generic type.

<hr>

### List -- an ADT

The Abstract Data Type (ADT) **_List_** refers to a collection of elements, which is maintained with primary operators that add, remove, find, and order elements.

- The list is not stored in a sorted order.
- If no positional information is provided, the `add()` operator will add to the END of the list.
- If positional information is provided, the `add()` operator will add at the INDEX indicated, shifting elements to make room.
- Any `remove()` operations need to shift elements to not leave a gap in the stored collection.
- Maintain a dynamic array, so that if the user adds and the array is full,
increase the capacity of the array.

<hr>

#### STYLE REQUIREMENTS

One common style guide is Google's <a href="https://google.github.io/styleguide/javaguide.html" target="_blank">https://google.github.io/styleguide/javaguide.html</a>. Here are
some rules for you to follow for this project.

- variable names use lower case (camel case for multiple words). Example: `studentName`
- method names use lower case (camel case for multiple words). Example: `helperFunction`
- class names use upper case with camel case (although try to avoid camel).
- constants are all capital

- curly brace opening is on the same line as start of the block. Example: `if (x==y) {`
- curly brace closing is on its own line or part of the else. Example: `} else {`

- place error checking code at the top of the method and return immediately without using an else.

- use `@Override` whenever overriding a method.

<hr>

#### LIST IMPLEMENTATION

##### Characteristics

- Remove all references to Face (e.g. the name of the array can no longer be `faces`). Instead use something like `items` or `elements` or `collection`.
- List can store any object type. You must use `<T extends Comparable<T>>` as part of the class defintion.
- List stores objects that implement `Comparable`.

The 2 characteristics above require special coding to create generic arrays. It looks like this:

```Java
T[] items;

@SuppressWarnings("unchecked")
T[] temp = (T[]) new Comparable[size];
items = temp;
```
##### Helper Functions

- `Boolean isFull()` determines whether or not there is capacity for more items.
- `Boolean isEmpty()` determines whether or not there are any items in the List.
- `Boolean isValid(int index)` determines if an item is stored at that index.`
- `String toString()` overrides Object method.
- `void increaseCapacity(int size)` increases the capacity of the List by amount `size`. A new array
will need to be created and all elements copied over.

##### Constructors

- `List()` default constructor with `this(DEFAULT_CAPACITY)` in the body.
- `List(int size)` creates a new array with `size` capacity.
- `List(List toCopy)` creates a copy of the  "this" object (sometimes referred to as a copy constructor). Copy all elements from `toCopy` into `this`. Be sure the count and capacity are also copied.

##### Find Methods

- `int recordNo(T item)` returns index of the first element that equals item (use `equals` method) or return -1 if not in List.
- `T peek(int index)` returns array element at that index or null if index is not valid.

##### Add and Set Methods

- `void add(T item)` adds that item to the end. **Resizes the array if it is full -- add 10 to the current capacity**.
- `void add(T item, int index)` adds that item at the stated index or ignores the request if the index is not valid. Items must be shifted to make room. **Note: if the index==count, the element can be added, but only if it the array is not full. Do not resize the array for this add.**
- `Boolean addAll(T[] items)` adds all elements in the array passed in the method BUT only if they can ALL fit. Return true if they were added, else false.
- `void set(T item, int index)` changes the element at index to the passed item or ignores the request
if the index is not valid. This will overwrite the element.

##### Remove Methods

- `Boolean remove(T item)` Removes the first occurence that is equal to face (use the `equals` method). Returns true if it was successfully removed, otherwise false.
- `T remove(int index)` Removes the element at that index (if the index is valid). Returns the element that was removed or null if nothing was removed.
- `void clear()` removes all elements from the list. Note: do not iterate over the array to do this - create a new array and set the count to 0.

##### Conversion Methods

- `T[] toArray()` returns a new array that is the size of the number of elements in the List. All elements are copied into the array in the order they appear in the List.

- `int toArray(T[] array)` copies as many elements in the "this" array that will fit into the passed array. Returns the number of elements added to `array`.

##### Ordering Methods

- `T min()` returns the minimum element in the List or null if it is empty. Use `compareTo`.
- `T max()` returns the maximum element in the List or null if it is empty. Use `compareTo`.

<hr>

#### TESTS for min and max Methods

In a file named `TestOrder.java`, create tests for both the min and max methods. You can use
`List<Integer>` as the list type for testing. Be sure to test the following ...

- returned element is at index 0
- returned element is at index _count-1_
- returned element is at an index somewhere between 0 and _count-1_
- returned element is negative
- returned element is 0
- returned element is null

<hr>

#### QUESTIONS

> ANSWER THESE QUESTIONS IN THE GOOGLE FORM: <a href="https://forms.gle/nGLb8jed8LL6cqK47" target="_blank">https://forms.gle/nGLb8jed8LL6cqK47</a>

1. Why is it important that we can use generic types to create class? In other words, what would you have to do if that did not exist?

2. It was suggested that as you are writing code, if you find yourself cutting and pasting from another method that you might instead call the function or create a helper function. Why is it important to not duplicate code (other than you have to type less)?

3. Why was it necessary for the List class to extend the Comparable class (well, technically it is an interface)?

4. Why should all array iterations use `count` as the stopping condition, rather than `items.length`?


#### Resources

> LIST YOUR RESOURCES IN THE GOOGLE FORM.

1. List students and tutors with whom you discussed this project.

2. Identify any online resources that you took advantage of (not including zyBooks).
