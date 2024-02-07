import java.util.function.Function;

/** Counting Sort assumes key of type Integer */
public class Counting<T> implements Sorter<T> {

  /** Extracts the key from an object in the array */
  Function<T,Integer> keyGetter = null;

  /** Max Value in the array to be sorted */
  Integer maxValue = null;

  /** Counter of loop iterations */
  long count = 0;

  /** Default empty constructor. */
  public Counting() {}

  /** Constructor for Counting
   * 
   * @param order Comparator to establish ordering of array elements.
   */
  public Counting(Function<T,Integer> getter) {
    keyGetter = getter;
  }

    /** Constructor for Counting with known max value
   * 
   * @param order Comparator to establish ordering of array elements.
   */
  public Counting(Function<T,Integer> getter, Integer maximum) {
    keyGetter = getter;
    this.maxValue = maximum;
  }

  /** Sorts specified array using Counting Sort. Inplace version of the sorter.
   * 
   * @param array Array to be sorted.
   */
  public void sort(T[] array) {

    // Counting is not an in-place sorting algorithm.
    // Therefore, need to sort, then copy the new sorted array back into the original

    @SuppressWarnings("unchecked")
    T[] sorted = (T[]) new Object[array.length];

    sort(array,sorted);
    for (int i=0; i<array.length; i++) {
      array[i] = sorted[i];
    }
  } // end sort(T[])

  /**  Sorts specified array, placing results in outArray. Not inplace sorter.
   * @param inArray Values to be sorted. inArray remains untouched in process.
   * @param outArray Contains sorted values of inArray upon completion.
   */
  public void sort(T[] inArray, T[] outArray) {
    
    if (maxValue==null) {
      maxValue = findMax(inArray);
    }

    // Reset count back to 0
    count = 0;

    //creates a new array with the length of the max value of the inArray
    Integer[] C = new Integer[maxValue+1];

    //sets the new array's value to 0
    for(int i = 0;i<C.length;i++){
      count++;
      C[i] = 0;
    }

    //Iterates through A and conts the amount of times that a value appears
    for(int j = 0;j<inArray.length;j++){
      count++;
      C[keyGetter.apply(inArray[j])] = C[keyGetter.apply(inArray[j])]+1;
    }

    //sums the previous values
    for(int i = 1;i<maxValue+1;i++){
      count++;
      C[i] = C[i] + C[i-1];
    }

    //places the elements into the outArray by using indexes and values
    for(int j = inArray.length-1;j>=0;j--){
      count++;
      outArray[C[keyGetter.apply(inArray[j])]-1] = inArray[j];
      C[keyGetter.apply(inArray[j])] = C[keyGetter.apply(inArray[j])] - 1;
    }


    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^    

  } // end sort(T[], T[])

  private Integer findMax(T[] array) {
    Integer max = keyGetter.apply(array[0]);
    for (T element : array) {
      Integer valueOf = keyGetter.apply(element);
      if (valueOf > max) {
        max = valueOf;
      }
    }
    return max;
  } // end findMax()

  public void setKeyGetter(Function<T,Integer> getter) {
    keyGetter = getter;
  }

  public long getCount() {
    return count;
  }  
} // end class Countin
