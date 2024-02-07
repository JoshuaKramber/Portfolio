import java.util.function.Function;

/** InsertionCount sort - an in-place sorting algorithm */
public class InsertionCount<T> implements Sorter<T> {

  /** Extracts the key from an object in the array */
  Function<T,Integer> keyGetter = null;

  /** Counter of compare operations */
  long count = 0;

  /** Constructor for InsertionCount Sort to set comparator
   * @param getter to extract the Integer out of the element
   */
  public InsertionCount(Function<T,Integer> getter) {
    keyGetter = getter;
  }

/** Sorts specified array using Insertion Sort. Inplace sorter.
   * @param array Array to be sorted.
   */
  public void sort(T[] array) {
    
    // Reset count back to 0
    count = 0;

    //___________ COMPLETE INSERTIONCOUNT SORT BELOW __________________

    int i = 0;
    int n = array.length;
    while(i<n){
        int lessCount = 0;
        //iterate over the array
        for(int j = i+1;j<n-1;j++){
            count++;
            //pulls the int value out so we can compare it
            int tempj = keyGetter.apply(array[j]);
            int tempi = keyGetter.apply(array[i]);
            //counts how many elements are less than the current element
            if(tempj<tempi){
                lessCount++;
            }
        }
        //increments the iterator if theres no elements smaller than the current
        if (lessCount==0){
            i++;
        }
        else{
            //swaps the values
            T temp = array[i];
            array[i] = array[lessCount+i];
            array[lessCount+i]=temp;
            i++;
        }
    }

  } // end sort(T[])

  /**  Sorts specified array, placing results in outArray. Not in place sorter.
   * @param inArray Values to be sorted. inArray remains untouched in process.
   * @param outArray Contains sorted values of inArray upon completion.
   */
  public void sort(T[] inArray, T[] outArray) {

    for (int i=0; i<inArray.length; i++) {
      outArray[i] = inArray[i];
    }

    sort(outArray);
  } // end sort(T[],T[])

  public long getCount() {
    return count;
  }
}
