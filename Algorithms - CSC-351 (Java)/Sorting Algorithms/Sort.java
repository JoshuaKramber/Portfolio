public class Sort {
    /**
     * Counting is based off of the Counting Sort psudocode
     * @param A is the int array that we are sorting
     */
    public static void counting(int[] A){
        int k = A[0];

        //iterates through A and sets k to the max value of A
        for (int i = 0;i<A.length;i++){
            if(k<A[i]){
                k = A[i];
            }
        }
        
        int[] C = new int[k+1];

        //Iterates through A and counts the amount of times a value appears
        for(int j = 0; j<A.length;j++){
            C[A[j]] = C[A[j]] + 1;
        }

        //C[i] is the sum of previous values
        for(int i = 1; i<k+1; i++){
            C[i] = C[i] + C[i-1];
        }

        int[] B = new int[A.length];

        //Actually sorts based on the indexs and values
        for(int j = A.length-1; j>=0; j--){
            B[C[A[j]]-1] = A[j];
            C[A[j]] = C[A[j]] - 1;
        }

        //transfers the new sorted array to the old array
        for(int i = 0;i<A.length;i++){
            A[i] = B[i];
        }

    }

    /**
     * Sorts via divide and conquer. This finds initial values then starts the recursion
     * @param A is an int array that we want to sort
     */
    public static void mergesort(int[] A){
        int p = 0;
        int r = A.length-1;
        Sort.MS(A,p,r);
    }

    /**
     * This is the recursive funtion which splits the arrays
     * @param A is the array that we want to sort
     * @param p equals the beginning value of the array
     * @param r equals the end value of the array
     */
    public static void  MS(int[] A, int p, int r){
        //if the beginning is less than the end
        if (p<r){
            //finds the mid-point of the array
            int q = (int)Math.floor(p+r)/2;
            //calls itself for the left side
            Sort.MS(A,p,q);
            //calls itself for the right side
            Sort.MS(A,q+1,r);
            //calls the function to put the split arrays back together
            Sort.Merge(A,p,q,r);
        }
    }

    public static void Merge(int[] A, int p, int q, int r){
        //finds the end points of our new arrays
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        //puts the values from the right and left into their specific arrays
        for(int i = 0; i<n1; i++){
            L[i] = A[p + i];
        }
        for(int j = 0; j<n2; j++){
            R[j] = A[q + j + 1];
        }

        //sets the end values of left and right to infinity
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        //puts all of the elements back into the array in sorted order
        for(int k = p; k<=r; k++){
            if(L[i] <= R[j]){
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
