import java.util.Comparator;

/** Insertion sort - an in-place sorting algorithm */
public class Insertion<T> implements Sorter<T> {

  /** Establishes ordering of type T */
  private Comparator<T> orderBy;

  /** Counter of compare operations */
  long count = 0;


  /** Constructor for Insertion Sort to set comparator
   * 
   * @param order Comparator to establish ordering of array elements.
   */
  public Insertion(Comparator<T> order) {
    orderBy = order;
  }

  /** Sorts specified array using Insertion Sort. Inplace sorter.
   * 
   * @param array Array to be sorted.
   */
  public void sort(T[] array) {
    
    // Reset count back to 0
    count = 0;
  
    //iterates over the array
    for(int j = 1;j<array.length;j++){
      //key is used to compare everything before it in the array
      T key = array[j];
      int i = j-1;
      //sweeps back down the array comparing and swapping to order the array
      while(i>-1 && orderBy.compare(array[i],key)>0){
        array[i+1] = array[i];
        i--;
        count++;
      }
      count++;
      //sets an updated key to move us further in the array
      array[i+1] = key;
    }

    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

  } // end sort(T[])

  /**  Sorts specified array, placing results in outArray. Not inplace sorter.
   * @param inArray Values to be sorted. inArray remains untouched in process.
   * @param outArray Contains sorted values of inArray upon completion.
   */
  public void sort(T[] inArray, T[] outArray) {

    // Insertion sort is designed to modify the original array.
    // To preserve original array values, copy the inArray to the outArray. 
    // Then sort inplace on the outArray, leaving inArray untouched.
    for (int i=0; i<inArray.length; i++) {
      outArray[i] = inArray[i];
    }

    sort(outArray);
  } // end sort(T[],T[])

  public void setComparator(Comparator<T> order) {
    orderBy = order;
  }

  public long getCount() {
    return count;
  }
} // end class Insertion
