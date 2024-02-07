import java.util.Comparator;

/** Merge Sort divide-and-conquer recursive algorithm */
public class Merge<T> implements Sorter<T> {

  /** Establishes ordering of type T */
  private Comparator<T> orderBy;

  /** Counter of compare operations */
  int count = 0;


  /** Constructor for Merge Sort to set comparator
   * 
   * @param order Comparator to establish ordering of array elements.
   */
  public Merge(Comparator<T> order) {
    orderBy = order;
  }

  /** Sorts specified array using Merge Sort
   * 
   * @param array Array to be sorted.
   */
  public void sort(T[] array) {
    
    // Reset count back to 0
    count = 0;

    //creates a index for the first and last element in the array
    int p = 0;
    int r = array.length-1;
    MS(array,p,r);
    

    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

  } // end sort(T[])

  /**
   * Recursivly calls itself to split the array into smaller arrays
   * @param A generic array
   * @param p index at the beginning of the array
   * @param r index of the last element of the array
   */
  public void MS(T[] A, int p, int r){
    //if the beginning is less than the end
    if (p<r){
        //finds the mid-point of the array
        int q = (int)Math.floor(p+r)/2;
        //calls itself for the left side
        MS(A,p,q);
        //calls itself for the right side
        MS(A,q+1,r);
        //calls the function to put the split arrays back together
        Mergesort(A,p,q,r);
    }
  }

  /**
   * Puts elements from the split arrays into a full ordered array
   * @param A Array to be sorted
   * @param p index of the first element
   * @param q index of the middle element
   * @param r index of the last element
   */
  public void Mergesort(T[] A, int p, int q, int r){
    //finds the end points of our new arrays
    int n1 = q - p + 1;
    int n2 = r - q;
    @SuppressWarnings("unchecked")
    T L[] = (T[]) new Object[n1+1];
    
    @SuppressWarnings("unchecked")
    T R[] = (T[]) new Object[n2+1];

    //puts the values from the right and left into their specific arrays
    for(int i = 0; i<n1; i++){
        L[i] = A[p + i];
    }
    for(int j = 0; j<n2; j++){
        R[j] = A[q + j + 1];
    }

    //sets the end values of left and right to infinity
    L[n1] = null;
    R[n2] = null;
    int i = 0;
    int j = 0;

    //puts all of the elements back into the array in sorted order
    for(int k = p; k<=r; k++){
      //maintains the count
      count++;
        //adds from the right array if null
        if(L[i]==null){
            A[k] = R[j];
            j++;
        }
        else if(R[j]==null){
            //adds from the left array if null
            A[k] = L[i];
            i++;
        }
        else{//adds the min if neither are null
          if(orderBy.compare(L[i],R[j])<=0){
            A[k] = L[i];
            i++;
          }
          else{
            A[k] = R[j];
            j++;
          }
        }
    }

  }

  /**  Sorts specified array, placing results in outArray. Not inplace sorter.
   * @param inArray Values to be sorted. inArray remains untouched in process.
   * @param outArray Contains sorted values of inArray upon completion.
   */
  public void sort(T[] inArray, T[] outArray) {

    // Merge sort is designed to modify the original array.
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

  public int getCount() {
    return count;
  }
} // end class Merge
