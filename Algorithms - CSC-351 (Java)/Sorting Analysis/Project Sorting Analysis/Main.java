public class Main {

    public static void main(String[]args){
        /** start time in nanoseconds */
        long startNS;
        long startNS2;
        long startNS3;

        /** end time in nanoseconds */
        long endNS;
        long endNS2;
        long endNS3;
        //run in the terminal javac *.java
        //then run java Main > merge_reversed.csv
        AlphaNumeric[] randArray;
        AlphaNumeric[] randArray2;
        AlphaNumeric[] randArray3;
        // Create a RANDOM array of 50000, which is basis of all subarrays
        randArray = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.SORTED,10000);
        randArray2 = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.SORTED,10000);
        randArray3 = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.SORTED,10000);
        // Sort arrays of increasing size from n = 1000, 2000, 3000, …, 50000
        /*for (int i=1000; i<=50000; i=i+1000) {
	        // Create a subarray of size [ i ] containing elements [0] to [i] of the original array
            AlphaNumeric[] smallerArray = new AlphaNumeric[i];
            for(int j = 0;j<smallerArray.length;j++){
                smallerArray[j] = randArray[j];
            }
	        
        }*/

        // Sort the array using different algorithms
        InsertionCount<AlphaNumeric> algorithm= new InsertionCount<>(AlphaNumeric.numberGetter);
        Insertion<AlphaNumeric> algorithm2= new Insertion<>(AlphaNumeric.orderNumeric);
        Counting<AlphaNumeric> algorithm3= new Counting<>(AlphaNumeric.numberGetter);

        //hybrid
        startNS=System.nanoTime();
        algorithm.sort(randArray);
        endNS=System.nanoTime();
	    // Print or save the count 
        // total time in milliseconds         
        long timeNS = (endNS - startNS) / 1000000;
        long operations = algorithm.getCount();

        //Insertion
        startNS2=System.nanoTime();
        algorithm2.sort(randArray2);
        endNS2=System.nanoTime();
	    // Print or save the count 
        // total time in milliseconds         
        long timeNS2 = (endNS2 - startNS2) / 1000000;
        long operations2 = algorithm2.getCount();

        //Counting
        startNS3=System.nanoTime();
        algorithm3.sort(randArray2);
        endNS3=System.nanoTime();
	    // Print or save the count 
        // total time in milliseconds         
        long timeNS3 = (endNS3 - startNS3) / 1000000;
        long operations3 = algorithm3.getCount();

	    System.out.println("InsertionCount: Time = " +timeNS + ", Operations = " + operations);
        System.out.println("Insertion: Time = " +timeNS2 + ", Operations = " + operations2);
        System.out.println("Counting: Time = " +timeNS3 + ", Operations = " + operations3);

    }

    public static void test(String[] args) {

        AlphaNumeric[] array;
        
        System.out.println("\n___________________ SORTING WITH INSERTION (alpha)");
        
        // Make an array in which the numbers are increasing, but alpha is in decreasing sorted order
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.SORTED,10);
        print(array);

		// Create the Sorter and sort the above array
        Insertion<AlphaNumeric> insertionAlpha = new Insertion<>(AlphaNumeric.orderAlpha);
        insertionAlpha.sort(array);
        System.out.println("Operations count "+insertionAlpha.getCount());
        print(array);

        System.out.println("\n___________________ SORTING WITH INSERTION (number)");
        
        // Use the array that was just sorted (but is now in reverse order relative to this comparator)
        print(array);

        Insertion<AlphaNumeric> insertionNumber = new Insertion<>(AlphaNumeric.orderNumeric);
        insertionNumber.sort(array);
        System.out.println("Operations count "+insertionNumber.getCount());
        print(array);

		System.out.println("\n___________________ SORTING WITH MERGE (alpha)");
        
        // Make an array in which the numbers are increasing, but alpha is in decreasing sorted order
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.SORTED,10);
        print(array);

		// Create the Sorter and sort the above array
        Merge<AlphaNumeric> mergeAlpha = new Merge<>(AlphaNumeric.orderAlpha);
        mergeAlpha.sort(array);
        System.out.println("Operations count "+mergeAlpha.getCount());
        print(array);

        System.out.println("\n___________________ SORTING WITH COUNTING");
        
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.REVERSED,10);
        print(array);

        Counting<AlphaNumeric> countingNumber = new Counting<>(AlphaNumeric.numberGetter);
        countingNumber.sort(array);
        System.out.println("Operations count "+countingNumber.getCount());
        print(array);
        
        System.out.println("\n___________________ SORTING WITH RADIX");
        
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.REVERSED,10);
        print(array);

        Radix<AlphaNumeric> radixNumber = new Radix<>(AlphaNumeric.numberGetter);
        radixNumber.sort(array);
        System.out.println("Operations count "+radixNumber.getCount());
        print(array);
    } // end main

    public static void print(Object[] array) {
        for (Object el : array) {
            System.out.print(el+" ");
        }
        System.out.println();
    }
}
